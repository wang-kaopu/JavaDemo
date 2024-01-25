# JDBC

## 使用步骤

- 导入驱动jar包
- 注册驱动
- 获取数据库的连接对象
- 定义sql语句
- 获取执行sql语句的对象
- 执行sql并接收返回结果
- 处理结果
- 释放资源

## 注册驱动

```
Class.forName("com.mysql.cj.jdbc.Driver");
```

## JDBC连接

Connection代表一个JDBC连接，相当于Java程序到数据库的连接

打开一个Connection需要准备URL、用户名和口令

URL是由数据库厂商指定的格式，例如，MySQL的URL是：

```
jdbc:mysql://<hostname>:<port>/<db>?key1=value1&key2=value2
```

假设数据库运行在本机`localhost`，端口使用标准的`3306`，数据库名称是`learnjdbc`，那么URL如下：

```
jdbc:mysql://localhost:3306/learnjdbc?useSSL=false&characterEncoding=utf8
```

后面的两个参数表示不使用SSL加密，使用UTF-8作为字符编码（注意MySQL的UTF-8是`utf8`）。

要获取数据库连接，使用如下代码：

```
// JDBC连接的URL, 不同数据库有不同的格式:
String JDBC_URL = "jdbc:mysql://localhost:3306/test";
String JDBC_USER = "root";
String JDBC_PASSWORD = "password";
// 获取连接:
Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
// TODO: 访问数据库...
// 关闭连接:
conn.close();
```

核心代码是`DriverManager`提供的静态方法`getConnection()`。`DriverManager`会自动扫描classpath，找到所有的JDBC驱动，然后根据我们传入的URL自动挑选一个合适的驱动。

因为JDBC连接是一种昂贵的资源，所以使用后要及时释放。使用`try (resource)`来自动释放JDBC连接是一个好方法：

```
try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
    ...
}
```

## JDBC查询

第一步，通过`Connection`提供的`createStatement()`方法创建一个`Statement`对象，用于执行一个查询；

第二步，执行`Statement`对象提供的`executeQuery("SELECT * FROM students")`并传入SQL语句，执行查询并获得返回的结果集，使用`ResultSet`来引用这个结果集；

第三步，反复调用`ResultSet`的`next()`方法并读取每一行结果。

完整查询代码如下：

```
try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
    try (Statement stmt = conn.createStatement()) {
        try (ResultSet rs = stmt.executeQuery("SELECT id, grade, name, gender FROM students WHERE gender=1")) {
            while (rs.next()) {
                long id = rs.getLong(1); // 注意：索引从1开始
                long grade = rs.getLong(2);
                String name = rs.getString(3);
                int gender = rs.getInt(4);
            }
        }
    }
}
```

注意要点：

`Statement`和`ResultSet`都是需要关闭的资源，因此嵌套使用`try (resource)`确保及时关闭；

`rs.next()`用于判断是否有下一行记录，如果有，将自动把当前行移动到下一行（一开始获得`ResultSet`时当前行不是第一行）；

`ResultSet`获取列时，索引从`1`开始而不是`0`；

必须根据`SELECT`的列的对应位置来调用`getLong(1)`，`getString(2)`这些方法，否则对应位置的数据类型不对，将报错。

**但是这个手动拼字符串的方法可能有SQL注入的问题，所以不用Statement，改用PreparedStatement，用？占位符，然后再输入参数。**

实例如下：

```
        String JDBC_URL = "jdbc:mysql://localhost:3306/test";
        String JDBC_USER = "root";
        String JDBC_PASSWORD = "meiyoumima123";
        try(Connection conn = DriverManager.getConnection(
        JDBC_URL, JDBC_USER, JDBC_PASSWORD)){
            try(PreparedStatement ps = conn.prepareStatement(
                "select id, name from students where gender = ?")){
                ps.setObject(1,"M");
                try(ResultSet rs = ps.executeQuery()){
                    while(rs.next()){
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        System.out.println(id+"->"+name);
                    }
                }
            }
        }
```

结果集在ps.executeQuery（）的返回值里，是一个ResultSet对象。

遍历这个Set集合就可以获得结果集了。

## JDBC插入

也是和查询差不多的步骤，只是改一下sql语句，以及要获取以下executeUpdate的返回值，这个值是成功插入了的记录条数。

实例：

```
        try(Connection conn = DriverManager.getConnection(
                JDBC_URL, JDBC_USER, JDBC_PASSWORD)){
            try(PreparedStatement ps = conn.prepareStatement(
                    "insert into students (class_id, name, score, gender)",Statement.RETURN_GENERATED_KEYS)){
                ps.setObject(1,2);
                ps.setObject(2,"Faye");
                ps.setObject(3,89);
                ps.setObject(4,"F");


                int n = ps.executeUpdate();
```

## JDBC更新

实例：

```
try(Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)){
            try(PreparedStatement ps = connection.prepareStatement(
                    "update students set name = ? where id = 9;"
            )){
                ps.setObject(1,"小南");

                int n = ps.executeUpdate();
            }

        }
```

## JDBC删除

实例：

```
try(Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)){
            try(PreparedStatement ps = connection.prepareStatement(
                    "delete from students where id = 9;"
            )){
                //ps.setObject(1,"小南");

                int n = ps.executeUpdate();
                System.out.println(n);
            }
        }
```

## JDBC事务

```
Connection conn = openConnection();
try {
    // 关闭自动提交:
    conn.setAutoCommit(false);
    // 执行多条SQL语句:
    insert(); update(); delete();
    // 提交事务:
    conn.commit();
} catch (SQLException e) {
    // 回滚事务:
    conn.rollback();
} finally {
    conn.setAutoCommit(true);
    conn.close();
}
```

其中，开启事务的关键代码是`conn.setAutoCommit(false)`，表示关闭自动提交。提交事务的代码在执行完指定的若干条SQL语句后，调用`conn.commit()`。要注意事务不是总能成功，如果事务提交失败，会抛出SQL异常（也可能在执行SQL语句的时候就抛出了），此时我们必须捕获并调用`conn.rollback()`回滚事务。最后，在`finally`中通过`conn.setAutoCommit(true)`把`Connection`对象的状态恢复到初始值。

如果要设定事务的隔离级别，可以使用如下代码：

```
// 设定隔离级别为READ COMMITTED:
conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
```

如果没有调用上述方法，那么会使用数据库的默认隔离级别。MySQL的默认隔离级别是`REPEATABLE_READ`。

## JDBC Batch的批量处理

不使用循环语句，而使用Batch功能，批量处理重复度高的指令。

提高效率，提高可读性。

```
        ArrayList<student> students = new ArrayList<>();
        Collections.addAll(students,new student("Alice", 98, 2, "F"),
                new student("Bob", 97, 3, "M"));
        try(Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            try(PreparedStatement ps = connection.prepareStatement("insert into students (name, class_id, gender, score) values (?, ?, ?, ?)")){
                for(student s : students){
                    ps.setString(1, s.name);
                    ps.setInt(2, s.class_id);
                    ps.setString(3, s.gender);
                    ps.setInt(4, s.score);
                    ps.addBatch();
                }
                int[] executed = ps.executeBatch();
                for (int i : executed) {
                    System.out.println(i);
                }
            }
        }
```

把集合中的数据遍历取出，放到ps里准备，用addBatch变成一批，再通过sql塞进数据库。

