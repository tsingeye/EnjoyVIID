# EnjoyVIID
即将全开源 欢迎先mark~


#### 介绍
1400视图库平台



#### 软件架构
- 开发语言基于Java
- 存储基于minio
- 缓存基于redis

#### 功能


#### 安装教程

1. 首先使用git将项目克隆到本地，项目地址：https://github.com/tsingeye/EnjoyVIID.git
2. 使用本项目提供的enjoyviid.sql文件新建本地数据库。enjoyviid.sql文件位于/sql文件夹下。
3. 配置好本地maven及其仓库地址。
4. 使用maven打包命令打包部署，打包后的项目为enjoy-viid.jar
5. 使用java -Xms1g -Xmx1g -jar enjoy-viid.jar命令部署项目

#### 使用说明

1.  JDK版本1.8及以上
2.  MySql版本8.0及以上

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request



## 平台简介
* 后端采用SpringBoot、Mybatis-plus、Shiro。
* 使用了lombok
* 认证授权使用Shiro，本系统中由于不需要用户管理等功能，所有只做了登录认证，授权写死在代码里为admin。
* 已完成的有设备接入注册、设备接入保活、设备接入注销、以及存储功能。
* 其他业务需要在开发时自建表和设计表。

## 内置功能
```text
1. 设备接入注册：是用来给拍摄人脸及其他图片的设备进行接入注册。
2. 设备接入保活：给已经接入的设备进行保活操作。
3. 设备接入注销：已经接入注册的设备允许其自动注销。
4. 存储：将设备传输过来的图片存储到本系统的minio中。
```
## 服务端代码启动
***windows系统：java -Xms1g -Xmx1g -jar enjoy-viid.jar***

## 需对外接口
1. 登录、登出  
2. 获取设备列表  
3. 订阅设备消息  
4. 查询历史图片    
5. 系统统计展示  
