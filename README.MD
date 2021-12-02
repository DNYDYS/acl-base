## 前后端分离后端模块脚手架 SpringBoot+Security+Jwt

<p align="left">
	<a target="_blank" href="https://www.apache.org/licenses/LICENSE-2.0.html">
		<img src="https://img.shields.io/:license-apache-blue.svg" ></img>
	</a>
	<a target="_blank" href="https://www.oracle.com/technetwork/java/javase/downloads/index.html">
		<img src="https://img.shields.io/badge/JDK-1.8+-green.svg" ></img>
	</a>
    <a target="_blank" href="https://www.oracle.com/technetwork/java/javase/downloads/index.html">
		<img src="https://img.shields.io/badge/SpringBoot-2.3.4-blue.svg" ></img>
	</a>
    <a target="_blank" href="https://www.oracle.com/technetwork/java/javase/downloads/index.html">
		<img src="https://img.shields.io/badge/Mysql-5.7-green.svg" ></img>
	</a>
    <a target="_blank" href="https://www.oracle.com/technetwork/java/javase/downloads/index.html">
		<img src="https://img.shields.io/badge/MybatisPlus-3.0-yellow.svg" ></img>
	</a>
	<a target="_blank" href="https://www.oracle.com/technetwork/java/javase/downloads/index.html">
		<img src="https://img.shields.io/badge/Redis-3.0-pink.svg" ></img>
	</a>
		<a target="_blank" href="https://www.oracle.com/technetwork/java/javase/downloads/index.html">
		<img src="https://img.shields.io/badge/Jwt-jjwt-red.svg" ></img>
	</a>
</p>

### 2021-12-02

- 重构项目名字，修改依赖版本
- 学习使用spring-boot-starter-data-redis，将验证码添加到redis中
- 新增获取验证码接口，用户获取验证码
- User实体新增验证码字段
- 用户登录接口做验证码验证
- 
- 

### 2021-12-01

- 修改项目，完善readme文档，方便后续阅读
- 向git仓库中添加sql文件
- 向git仓库中添加postman配置信息
- 修改swagger配置，修复swagger打不开的错误
- TokenWebSecurityConfig 配置信息中放行swagger信息接口
- swagger文档地址：http://localhost:8080/doc.html
- 后端接口修复，添加对应的swagger注释信息
- 修改mysql连接信息，不验证ssl
- 修改新增用户功能，修改密码加密方式

------

### 项目技术栈

- Java 1.8
- Mybatis Plus 3.0.5
- SpringBoot 2.3.4
- Mysql 5.7
- Redis 3.0
- Swagger 2.7.0
- Jwt Token

------

### 项目概述

项目采用前后端分离的形式开发，这里只是一个后端项目的基础模块， 试用于单体项目的快速开发，基本的权限系统接口都有，用户只需要关注具体的业务模块即可。
目前项目会持续维护中。。。

### 项目功能

- 首页功能
- 用户管理
- 角色管理
- 权限管理
- 日志管理（待开发）

------

### 登录接口：

请求地址：http://localhost:8080/admin/login

提交方式：post

数据格式：json

```
{
    "username":"admin",
    "password": "123456"
}
```

获取当前用户信息接口：

请求地址：http://localhost:8080/index/info

提交方式：get

请求头Header:  toke: 获取到的token值

其余接口可以通过api文档查看。
