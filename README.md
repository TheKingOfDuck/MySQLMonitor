[![PyPI](https://img.shields.io/badge/Python-All-blue.svg)]()
[![Build](https://img.shields.io/badge/Supported_OS-All-orange.svg)]()

# MySQLMonitor
MySQL实时监控工具（黑盒测试辅助工具）

# 更新：


2019-04-29：
  修复windows系统下监控不成功的问题（其实就是语序有问题 调整一下就OK） 感谢[@Aoyanm](https://github.com/Aoyanm)的反馈

2019-03-26：
  新增加对MySQL8.0.X（MacOS环境下）的支持 感谢[@ALEXI7](https://github.com/ALEXI7)的反馈

2019-03-23：
  修改了写错的单词 修复端口非3306无法连接的情况
  
  

2019-02-22：
  文章意外被ASRC公号选上
  所以顺便修复一下Python3下reload不存在这个问题

2019-01-25：
  新增对windows系统 Python2.7的支持
  修复编码问题
  
2019-01-15：
  发布第一版


# 使用：

### 环境：
在以下环境中运行通过

| OS | Python | MySQL | status |
| --- | --- | --- | --- |
| MacOS | 2.7/3.7 | 5.x-8.x | pass |
| Win7 | 2.7/3.7 | 5.x-8.x | pass |
| ubuntu18.04 | 3.x | 5.x | pass |

环境下运行通过 
理论上均可运行 如有问题或修改意见 请点击===>[问题反馈](https://github.com/TheKingOfDuck/MySQLMonitor/issues)

### 依赖：


> [pymysql](https://github.com/PyMySQL/PyMySQL)
> [configparser](https://docs.python.org/3/library/configparser.html)

可执行以下命令尝试安装

```
pip install pymysql
pip install configparser
```

### 运行：

```
git clone https://github.com/TheKingOfDuck/MySQLMonitor.git
cd MySQLMonitor
nano config.ini
python3 MySQLMonitor.py
```

使用前务必先修改config.ini里的配置为自己的数据库

# 功能

* 自动开启MySQL日志 实时显示程序执行过的语句

* 自动安装依赖

* 危险操作提醒（第一版尚未添加）

![screenshot](https://github.com/TheKingOfDuck/MySQLMonitor/blob/master/screenshot.png)

### 应用场景：

https://xz.aliyun.com/t/3875
        


