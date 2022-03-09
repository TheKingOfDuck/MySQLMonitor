[![PyPI](https://img.shields.io/badge/JAVA-All-blue.svg)]()
[![Build](https://img.shields.io/badge/Supported_OS-All-orange.svg)]()

# 404StarLink 2.0 - Galaxy
![](https://github.com/knownsec/404StarLink-Project/raw/master/logo.png)

MySQLMonitor  是 404Team [星链计划2.0](https://github.com/knownsec/404StarLink2.0-Galaxy)中的一环，如果对MySQLMonitor有任何疑问又或是想要找小伙伴交流，可以参考星链计划的加群方式。

- [https://github.com/knownsec/404StarLink2.0-Galaxy#community](https://github.com/knownsec/404StarLink2.0-Galaxy#community)

# MySQLMonitor
MySQL实时监控工具（灰盒测试辅助工具）  这三年前的玩意儿了 总体上都不推荐使用这种方式挖洞 建议看看IAST

# 更新：

2020-10-18：
  使用Java重构,打包好的版本可点击[Releases](https://github.com/TheKingOfDuck/MySQLMonitor/releases/tag/1.0)下载

2019-04-29：
  修复windows系统下监控不成功的问题（其实就是语序有问题 调整一下就OK） 感谢[@Aoyanm](https://github.com/Aoyanm)的反馈

2019-03-26：
  新增加对MySQL8.0.X（MacOS环境下）的支持 感谢[@ALEXI7](https://github.com/ALEXI7)的反馈

2019-03-23：
  修改了写错的单词 修复端口非3306无法连接的情况


2019-01-25：
  新增对windows系统 Python2.7的支持
  修复编码问题
  
2019-01-15：
  开源第一版



# 使用：

```
java -jar MySQLMonitor.jar -h 127.0.0.1 -user CoolCat -pass mysqlmonitor
```

![](https://github.com/TheKingOfDuck/MySQLMonitor/blob/master/img/screenshot.png?raw=true)

### 环境：
在以下环境中运行通过

| OS | JAVA | MySQL | status |
| --- | --- | --- | --- |
| MacOS | 1.8 | 8.x | ok |


环境下运行通过 
理论上均可运行 如有问题或修改意见 请点击===>[问题反馈](https://github.com/TheKingOfDuck/MySQLMonitor/issues)


# 功能

* 自动开启MySQL日志 实时显示程序执行过的语句

* 自动安装依赖

### 应用场景：

https://xz.aliyun.com/t/3875
        


