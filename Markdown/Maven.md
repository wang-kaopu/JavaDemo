# Maven

项目管理和构建工具，基于项目对象模型——POM（Project Object Model）的概念。

通过一小段信息来管理项目。

Maven的安装：https://github.com/wang-kaopu/JavaDemo/blob/main/Markdown/Pic/Maven/maven%E7%9A%84%E5%AE%89%E8%A3%85.png?raw=true

POM的交互过程如图：

https://github.com/wang-kaopu/JavaDemo/blob/main/Markdown/Pic/Maven/pom.png?raw=true

仓库：

用于存储资源，管理各种jar包。

本地仓库、中央仓库、远程仓库（私服）。

## Maven坐标

Maven中的坐标是**资源的唯一标识**，可以通过该坐标唯一定位资源位置。

使用坐标来定义或引入项目中需要的依赖。

### 主要组成

groupId：定义当前Maven项目隶属组织名称（通常是域名反写）。

artifactId：定义当前Maven项目名称（通常是模块名称，例如order-service, good-service）。

version：定义当前项目版本号。

## 依赖管理

### 依赖配置

依赖：指当前项目运行所需要的jar包，一个项目可以引入多个依赖。

配置：

```
1. 在pom.xml中编写<dependencies>标签
2. 在<dependencies>标签中编写<dependency>引入坐标
3. 定义坐标的groupId, artifactId, version
4. 刷新，引入最新加入的坐标
5. 依赖的坐标信息查询网站：https://mvnrepository.com/
```

### 依赖传递

Maven的依赖具有传递性

- 直接依赖：在当前项目中通过**依赖配置**建立的依赖关系。
- 间接依赖：**被依赖的资源**如果**依赖其他资源**，则当前项目简介依赖其他资源。

#### 排除依赖

在dependency标签中编写exclusions标签，再在其中编写exclusion标签，指定要排除的依赖的坐标（无需指定版本），就能主动断开依赖的资源。

```
    <dependencies>
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
            <version>1.2.10</version>
            <exclusions>
                <exclusion>
                    <groupId>junit</groupId>
                    <artifactId>junit</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
    </dependencies>
```

### 依赖范围

依赖的jar包默认情况下，使用范围是：

- 主程序（main文件夹）
- 测试程序（test文件夹）
- 是否参与打包运行（package指令范围内）

如果需要手动指定依赖范围，就要用到scope标签，标签内的值决定了该依赖的范围。

```
    <dependencies>
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
            <version>1.2.10</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
```

| scope值         | 主程序 | 测试程序 | 打包（运行） |
| --------------- | ------ | -------- | ------------ |
| compile（默认） | Y      | Y        | Y            |
| test            | -      | Y        | -            |
| provided        | Y      | Y        | -            |
| runtime         | -      | Y        | Y            |

### 生命周期

Maven中有三套相互独立的生命周期。

- clean：清理工作。
- default：核心工作，如：编译、测试、打包、安装、部署等。
- site：生成报告、发布站点等。

每套生命周期包含一些阶段，阶段是有顺序的，后面的阶段依赖于前面的阶段，也就是说在后面的阶段进行的同时，该阶段所在的这套生命周期的前面的阶段一定也是在进行的。

#### 重点关注的阶段

- clean
  - clean：移除上一次构建生成的文件。
- default
  - compile：编译项目源代码。
  - test：使用合适的单元测试框架（如junit）运行测试。
  - package：将编译后的文件打包，如jar、war等。
  - install：安装项目到本地仓库。

Maven的生命周期就是为了对所有的Maven项目构建过程进行抽象和统一。

#### 执行指定生命周期

- idea中右侧的maven工具栏
- 在项目工程文件夹处打开命令行，通过mvn指令操作
