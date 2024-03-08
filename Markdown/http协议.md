# Spring概述

Spring提供了若干个子项目，每个项目用于完成特定的功能。

利用Spring Boot可以快速构建应用程序、简化开发、提高效率。

创建Spring Boot基本步骤

1. 填写模块信息，并勾选web开发相关依赖。
2. 创建请求处理类，添加请求处理方法，并添加注解。
3. 运行启动类，在浏览器打开占用的端口（localhost:8080/hello）测试。

# HTTP协议

Hyper Text Transfer Protocol，超文本传输协议，规定了浏览器和服务器之间数据传输的规则。

HTTP特点：

1. 基于TCP协议：面向连接，安全

2. 基于请求-响应模型：一次请求对应一次响应

3. HTTP协议是无状态的协议：对于事务处理没有记忆能力，每次请求-响应都是独立的。

   缺点：多次请求之间不能共享数据。

   优点：速度快。

## 请求协议

### 请求数据格式

<img>https://github.com/wang-kaopu/JavaDemo/blob/main/Markdown/Pic/HTTP%E5%8D%8F%E8%AE%AE/http%E8%AF%B7%E6%B1%82%E6%A0%BC%E5%BC%8F.png?raw=true </img>

1. 请求行：

- 请求方式
- 资源路径
- 协议

2. 请求头

   第二行开始，格式是key：value

3. 请求体（POST请求特有）

   存放请求参数 

## 响应协议

## 响应解析