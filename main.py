# -*- coding: utf-8 -*-
"""
-------------------------------------------------
   File Name：     main
   Description :
   Author :       CoolCat
   date：          2019/1/15
-------------------------------------------------
   Change Activity:
                   2019/1/15:
-------------------------------------------------
"""
__author__ = 'CoolCat'

import time
import os
import subprocess
import re

import sys
reload(sys)
sys.setdefaultencoding('utf8')

try:
    import pymysql
except:
    os.system("pip install pymysql")

try:
    import configparser
except:
    os.system("pip install configparser")


prefix = ''
logPath = os.getcwd()
global log
logName = str(time.strftime('%Y_%m_%d')) + "_log.txt"
log = logPath + "/" + logName
log = log.replace("\\","/") #for windows not support to use \ in log file path


# set global general_log_file="C:\Users\Administrator\Desktop\Code\MySQLMonitor/2019_01_25_log.txt"

def logoMonitor(log):

    command = 'tail -f ' + log
    popen = subprocess.Popen(command, stdout=subprocess.PIPE, stderr=subprocess.PIPE, shell=True)
    try:
        while True:
            line = popen.stdout.readline().strip()
            encodeStr = bytes.decode(line)
            pattern = re.findall('Query\s*(.*)', encodeStr, re.S)
            if len(pattern) != 0:
                selectStr = pattern[0]
                if selectStr != "COMMIT":
                    joinTime = time.strftime("[%H:%M:%S]", time.localtime())
                    if prefix != "":
                        reg = re.findall(r'\b' + prefix + '\w*', encodeStr, re.S)
                        if len(reg) != 0:
                            table = '操作的表:' + reg[0]
                            joinTime += table
                    print(joinTime + selectStr)

    except KeyboardInterrupt:
        pass



def execSQL(db, sql):
    cursor = db.cursor()
    cursor.execute(sql)
    data = cursor.fetchone()
    return data
    print(time.strftime('[%H:%M:%S]:  ') + str(data ))

def getConfig():
    conf = configparser.ConfigParser()
    try:
        conf.read("config.ini")
        host = conf.get("dbconf", "host")
        port = conf.get("dbconf", "port")
        user = conf.get("dbconf", "user")
        password = conf.get("dbconf", "password")
        db_name = conf.get("dbconf", "db_name")
        charset = conf.get("dbconf", "charset")
        print(time.strftime('[%H:%M:%S]') + "配置解析成功...")
    except:
        print(time.strftime('[%H:%M:%S]') + "配置解析失败 请检查格式是否正确...")

    try:
        global db
        db = pymysql.connect(host, user, password, db_name, charset=charset)
        print(time.strftime('[%H:%M:%S]') + '数据库连接成功...')

    except:
        print(time.strftime('[%H:%M:%S]') + '数据库连接失败...')


if __name__ == '__main__':
    global db
    getConfig()
    data = execSQL(db,"SELECT VERSION()")
    print(time.strftime('[%H:%M:%S]') + "当前数据库版本为: %s " % data)
    time.sleep(1)
    data = execSQL(db, "show variables like '%general_log%';")[1]
    print(time.strftime('[%H:%M:%S]日志状态为:') + data)
    if data == "OFF":
        try:
            print(time.strftime('[%H:%M:%S]正在尝试开启日志模式...') )
            time.sleep(1)
            data = execSQL(db, "set global general_log_file='"+ log +"';")
            data = execSQL(db, "set global general_log=on;")
            data = execSQL(db, "show variables like '%general_log%';")[1]
            if data == "ON":
                print(time.strftime('[%H:%M:%S]日志模式已开启...:'))
                print(time.strftime('[%H:%M:%S]日志监听中...:'))
                logoMonitor(log)
        except:
            print(time.strftime('[%H:%M:%S]日志模式开启失败...:'))
            print(time.strftime('[%H:%M:%S]未知错误 请联系https://github.com/TheKingOfDuck/MySQLMonitor/issues反馈问题...:'))
            exit()
    else:
        print(time.strftime('[%H:%M:%S]日志监听中...:'))
    db.close()
    logoMonitor(log)
      

