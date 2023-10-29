# ipcounter-spring-boot-starter
统计IP信息starter

# 使用方法
1. 下载源码，使用Maven安装到本地仓库
2. 在需要使用的项目中引入依赖
```xml
    <dependency>
        <groupId>com.gallen</groupId>
        <artifactId>ipcounter-spring-boot-starter</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </dependency>
```
3. 在application.yml文件中进行配置
![微信截图_20231029160848](https://github.com/Gallenzzz/ipcounter-spring-boot-starter/assets/80974945/21a2e23f-1a65-488d-9467-1176630de297)

```properties
gallen:
  tools:
    ip:
      print: false
      cycle: 10
      cycle-reset: true
      store-in-redis: true
```
