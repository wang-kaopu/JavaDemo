# Ajax

异步的JavaScript和XML

作用：

- 数据交换：给服务器发送请求，并获取服务器的数据
- 异步交互：在不重新加载整个页面的情况下，与服务器交换并更新部分网页

Axios

对原生的Ajax进行了封装，简化书写，快速开发

```
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
```

```
<body>
    <input type="button" value = "获取数据get" onclick = "get()">
    <input type="button" value = "删除数据post" onclick = "post()">
</body>
<script>
    //发送get请求
    function get(){
        axios.get("https://yapi.smart-xwork.cn/mock/169327/emp/list").then (ret=>{
        console.log(ret.data);
    });

    //发送post请求
    function post(){}
    axios.post("https://yapi.smart-xwork.cn/mock/169327/emp/deleteById","id=1").then(result=>{
        console.log(ret.data);
    })
}
</script>
```

应用：
用在Vue对象里，作为Vue对象的数据模型的来源

比如说在Vue对象里，定义了一个emps数据模型，然后这个emps模型的数据哪来的呢？就可以在Vue对象的钩子函数里，用到axios对象来从服务器端get一系列数据，然后调用返回的这些数据的一个方法then，把this.emps赋值成ret.data，那么这个Vue对象的数据模型就是从服务器端获取到的了。接下来，就在html的body部分，为标签绑定v-指令，把这些数据渲染出来。