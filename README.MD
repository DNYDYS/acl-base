## 单体项目脚手架 SpringBoot+Security+Jwt

### 2021-12-01

- 修改项目，完善readme文档，方便后续阅读
- 向git仓库中添加sql文件
- 向git仓库中添加postman配置信息
- 修改swagger配置，修复swagger打不开的错误
- TokenWebSecurityConfig 配置信息中放行swagger信息接口
- swagger文档地址：http://localhost:8080/doc.html
- 后端接口修复，添加对应的swagger注释信息
- 

------

### 项目技术栈

- SpringMVC
- Mybatis Plus
- Spring 5.0
- SpringBoot 2.4
- Mysql 5.7
- Redis 3.0
- Jwt

### 项目概述

项目采用前后端分离的形式开发，这里只是一个后端项目的基础模块， 试用与单体项目的快速开发，基本的权限系统都有，只需要关注具体的业务模块即可。



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


