# MySQLMonitor
MySQL实时监控工具（黑盒测试辅助工具）


# 使用：

### 环境：
测试环境为MacOS 10.14 
目前仅支持Python3 如有问题或修改意见 请点击===>[问题反馈](https://github.com/TheKingOfDuck/MySQLMonitor/issues)

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
git clone https://github.com/TheKingOfDuck/FileMonitor.git
cd FileMonitor
nano config.ini
python fileMonitor.py
```

使用前务必先修改config.ini里的配置为自己的数据库

# 功能

* 自动开启MySQL日志 实时显示程序执行过的语句

* 危险操作提醒（第一版尚未添加）

![screenshot](https://github.com/TheKingOfDuck/MySQLMonitor/blob/master/screenshot.png)

### 应用场景：

* https://xz.aliyun.com/t/3767
* https://xz.aliyun.com/t/3788
        



