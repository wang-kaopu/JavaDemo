网页的本质：前端代码

前端的代码是如何转换成用户眼中的网页的？

- 通过浏览器的**解析和渲染**
- 浏览器中对代码进行解析和渲染的部分称为**浏览器内核**

# Web标准

也称为网页标准

网页的三个组成部分：

- HTML：负责网页的结构（页面元素和内容）
- CSS：负责网页的表现（页面元素的外观、位置等页面样式，如：颜色、大小等）
- JavaScript：负责网页的行为（交互效果）

1. HTML－HyperText Markup Language：超文本标记语言

超文本：可以定义图片、音频、视频等内容

标记语言：由标签构成的语言

- HTML标签都是预定义好的。如<a>展示超链接，<img>展示图片。
- HTML代码直接在浏览器中运行，HTML**标签**由浏览器解析。

2. CSS－Cascading Style Sheet：层叠**样式**表

# HTML和CSS

1. HTML结构标签

```
<html>
	<head>
    	<meta charset="UTF-8" />
    	<title>title</title>
	</head>
	<body>
		
	</body>
</html>
```

2. 语法特点

- 标签不区分大小写
- 标签属性值单双引号都可以
- 语法松散



3. 引入css

#三种引入方式#

- 行内样式（x）
- 内嵌样式
- 外联样式

#颜色表示#

- 关键字：red、green、blue
- rgb表示法：rgb（255，0，0）
- 十六进制：#ff0000、#cccccc、#ccc

#颜色属性#

color：设置文本内容的颜色



4. <span>标签

- <span>是一个没有语义的布局标签
- 特点：一行可以显示多个（组合行内元素），宽度和高度默认由内容撑开



5. css选择器

- 元素选择器
- id选择器
- 类选择器
- 优先级：id选择器>类选择器>元素选择器



6. 音频、视频标签：<audio> <video>

7. 换行、段落标签：

   换行：<br>

   段落：<p>

8. 文本加粗标签

   <b> <strong>

9. css样式：

   line-height: 设置行高

   text-indent：设置缩进（单位：px）

   text-align：规定元素中的文本的对齐方式（left、right、center）

10. 空格占位符：

    ```
    &nbsp;
    ```

11. CSS盒子模型

    内容content

    内边距padding

    边框border

    外边距margin

12. CSS属性

    width：设置宽度

    height：设置高度

    border：设置边框的属性，如：1px，solid，#000

    padding：内边距

    margin：外边距

    如果只需要设置某一个方位的边框、内边距、外边距，可以在属性名后加上 -top或 -left或-right等

13. 表格标签

    ```
    <table>：定义表格
    <tr>：定义表格中的行，一个<tr>表示一行
    <th>：表示表头单元格，具有加粗居中效果
    <td>：表示普通单元格
    ```

    ```
        <table border="1px" cellspacing="0" width="600px">
            <tr>
                <th>序号</th>
                <th>品牌logo</th>
                <th>品牌图片</th>
                <th>企业名称</th>
            </tr>
            <tr>
                <td>1</td>
                <td> <img src="https://ts1.cn.mm.bing.net/th/id/R-C.f5bba551c5fde389168f0ce9e2201145?rik=XapyqJ%2b6fXYCcw&riu=http%3a%2f%2fwww.kuaipng.com%2fUploads%2fwater%2ftext%2f2017%2f06-07%2fgoods_water_6525_698_698_.png&ehk=%2fQlYmSlVMMarF6BUBbl11xoDiHtfK0PHpSE85FRcP0s%3d&risl=&pid=ImgRaw&r=0" width="100px"></td>
                <td>华为</td>
                <td>华为技术有限公司</td>
            </tr>
        </table>
    ```

    border：边框的粗度

    cellspacing：边框之间的距离

    14. 表单标签

        ```
            <form action="" method = "get">
                用户名：<input type="text" name="username">
                密码：<input type="password" name="password">
                <input type="submit" value="提交">
            </form>
        ```

        action：提交的目的url

        method：提交方式

        ​				get：表单数据拼接在url后面，?username=java，大小有限制

        ​				post：表单数据在请求体中携带，大小没有限制

        表单项必须有name属性才能提交

    15. 表单项标签

        ```
        <input>的type属性: text, password, radio, checkbox, file, date, datetime-local, time, number, hidden, button, submit
        
        <select>定义下拉列表
        <textarea cols=“30” rows=“10”>定义文本域
        ```

        ```
            <form action="" method = "get">
                用户名：<input type="text" name="username"><br>
                密码：<input type="password" name="password"><br>
                性别：<label><input type="radio" name="gender" value="1">男</label>
                        <label><input type="radio" name="gender" value="2">女</label><br>
                爱好：<label><input type="checkbox" name="hobby" value="java"></label>
                        <label><input type="checkbox" name="hobby" value="game"></label><br>
                <input type="submit" value="提交">
            </form>
        ```

        
