# JavaScript

- 跨平台、面向对象的脚本语言，用来控制网页行为，使网页可交互。

# JavaScript引入方式

- 内部脚本：将JS代码定义在html页面中
  - JS代码必须位于<script></script>标签中。
  - 在html文档中可在任意位置放置任意数量的<script>。
  - 一般会把脚本置于<body>元素的底部，可以改善显示速度。

- 外部脚本：将JS代码定义在外部JS文件中，然后引入到html页面中

  - 外部JS文件中，只包含JS代码，不包括<script>标签。

  - <script>标签不能自闭合。

# JS基础语法

## 输出语句

```
window.alert("Hello JavaScript");//浏览器弹出警告框

document.write("Hello JavaScript");//写入HTML，在浏览器展示

console.log("Hello JavaScript");//写入浏览器控制台

```

## 变量

```
// 1. 全局变量var
{ 
    var x = 1;
}
x = "A";
alert(x);

// 2. 局部变量let
{
    let x = 1;
    x = "A";
}

// 3. 常量const
const pi = 3.14;
//p1 = 3.15;
```

## 数据类型

- 原始类型

```
number：数字
string：字符串
boolean：布尔
null：表示对象为空
undefined：当声明的变量未初始化时，该变量的默认值是undefined
```

```
使用typeof运算符可以获得数据类型
```

- 引用类型

## 运算符

```
==会进行类型转换，===不会进行类型转化
```

## 类型转换

- 字符串转为数字
  - 将字符串字面转为数字。如果字面值不是数字，则转为NaN(Not a number)
- 其他类型转为boolean
  - Number：0和NaN转为false，其他均转为true
  - String：空字符串为false，其他均转为true
  - Null和undefined：均转为false

## 函数

```
//定义方式1
function add(a, b, c, d){
    return a + b;
}

var sum = add(1, 2);
//JS中函数调用可以传递任意个数的参数
alert(sum);

//定义方式2
var add = function(a, b){
    return a + b;
}

var sum = add(1, 2， 3， 4);
alert(sum);
```

## Array对象：数组

```
//定义方式1
var arr1 = new Array(1, 2, 3, 4);
//定义方式2
var arr2 = [1, 2, 3, 4];
```

类似于java中的集合，可以存储不同类型的值，可以变长

```
//1. length属性
console.log(arr1.length);

//2. forEach方法，遍历数组，将数组中有值的元素作为参数传入函数
arr1.forEach(function(e){
    console.log(e);
})
//简化写法：箭头函数
arr1.forEach((e) => {
    console.log(e);
})

//3. push方法：添加元素到数组末尾
arr1.push(7, 8, 9);
console.log(arr1);

//4. splice方法：删除指定索引开始的指定数量的元素
arr1.splice(2, 3);
console.log(arr1);
```

## String对象：字符串

```
// 定义方式1
var str = new String("Hello String");
// 定义方式2
var str = "Hello String";
```

```
// 1. length属性
console.log(str.length);

// 2. charAt方法，返回在指定索引(首个非空字符起)的字符
console.log(str.charAt(8));

// 3. indexOf方法，检索字符串，返回索引(首个非空字符起)
console.log(str.indexOf("o"));

// 4. trim方法，去除字符串两边的空格
console.log(str.trim());

// 5. substring方法，截取起始索引到结束索引的字符串并返回，含头不含尾
console.log(str.substring(6, 12));
```

# 自定义对象

```
        //自定义对象
        var user = {
            name : "lisi",
            age : 23,
            //eat : function(){
            //    alert("用膳");
            //}
            eat () {
                alert("用膳");    
            }
        }
        alert(user.name);
        user.eat();
```

# JSON对象

```
 		//定义json格式字符串
        var userStr = '{"name":"zhangsan", "age":20, "numbers":[1, 2, 3 , 4]}';
        console.log(userStr.name);//输出undefine，字符串没有name这个属性
        
        //将字符串转为json对象
        var userObj = JSON.parse(userStr);
        console.log(userObj.name);//输出zhangsan

        //将json对象转为字符串
        var str = JSON.stringify(userObj);
        console.log(str);//输出{"name":"zhangsan","age":20,"numbers":[1,2,3,4]}
```

# BOM

Browser Object Model 浏览器对象模型， 允许JavaScript与浏览器对话，JavaScript将浏览器的各个组成部分封装为对象

组成：

- <u>window：浏览器窗口对象</u>
- navigator：浏览器对象
- screen：屏幕对象
- history：历史记录对象
- <u>location：地址栏对象</u>

## Window对象

属性：

history：对history对象的只读引用

location：用于窗口或框架的location对象

navigator：对navigator对象的只读引用

方法：

```
        //1. confirm方法
        window.confirm("确定吗？");

        //2. setInterval方法
        i = 0;
        setInterval(function(){
            i++;
            console.log("定时器启动"+i+"次");
        }, 2000)

        //3. setTimeout方法
        setTimeout(function(){
            alert("启动！");
        })
		
		//4. alert方法
```

## Location对象

属性：

- href：完整的URL

```
        alert(location.href);
        location.href = "https://cn.bing.com/?mkt=zh-CN";
```

# DOM

Document Object Model 文档对象模型

将标记语言的各个组成部分封装成对应的对象：

- Document：整个文档对象
- Element：元素对象
- Attribute：属性对象
- Text：文本对象
- Comment：注释对象

- Image：<img>
- Button：<input type = 'button'>

HTML种的Element对象可以通过Document对象获取，而Document对象是通过window对象获取的

Document对象中提供了以下获取Element对象的函数：

```
        //1. 根据id获取元素
        var time = document.getElementById("time");
        alert(time);
        //修改这个元素
        time.innerHTML = "2024年02月88日 25:47";
        
        //2. 根据标签名获取元素数组
        var audios = document.getElementsByTagName("audio");
        for (let index = 0; index < audios.length; index++) {
            alert(audios[index]);
        }
        //3. 根据name属性值获取元素数组
        var ps = document.getElementsByName("secondLine");
        for (let index = 0; index < ps.length; index++) {
            alert(ps[index]);
        }
        //4. 根据class属性值获取元素数组
        var fls = document.getElementsByClassName("firstName");
        for (let index = 0; index < fls.length; index++) {
            alert(fls[index]);
        }
```

# 事件监听

## 事件绑定

```
    //绑定方式1：通过HTML标签中的事件属性绑定
    <input type="button" id = "btn1" value = "事件绑定1" onclick = on()>
	//绑定方式2：通过DOM元素属性绑定
	<input type="button" id = "btn2" value = "事件绑定2">
    <script>
        function on(){
            alert("按钮1被点击");
        }

        document.getElementById("btn2").onclick = function(){
            alert("按钮2被点击");
        };
    </script>
```

## 常见事件

| 事件名        | 事件内容                 |
| ------------- | ------------------------ |
| onclick       | 鼠标单击事件             |
| onblur        | 元素失去焦点             |
| onfocus       | 元素获得焦点             |
| onload        | 某个页面或图像被完成加载 |
| onsubmit      | 当表单提交时触发该事件   |
| onkeydown1029 | 某个键盘的键被按下       |
| onmouseover   | 鼠标被移到某元素之上     |
| onmouseout    | 鼠标从某元素移开         |

