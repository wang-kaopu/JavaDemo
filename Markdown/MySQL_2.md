# 一些指令

导入数据库

```
source {绝对路径};
```

查看数据库当前的编码方式

```
show variables like '%char%';
```

设置字符集编码

```
set character set utf8mb4;
```

MySQL服务端闪退：服务->MySQL80->自动



# 数据库操作

## 对于数据库

### 查询

查询所有数据库

```
show databases;
```

查询当前数据库

```
select database();
```

### 创建

```
create database [if not exists] {数据库名} [default charset {字符集名}] [collate {排序规则}]；
```

### 删除

```
drop database [if exist] {数据库名}；
```

### 使用

```
use {数据库名};
```



## 对于数据

### 查询数据

#### 投影查询

- **查询某表的全部数据**

```
select * from {表名}；
```

**表示所有列*

- **查询某表的某列**

```
select {列名 [别名]} from {表名[别名]}；
```

*可以为某一列或某表取一个别名简化指令*



#### 条件查询

```
select {列名} from {表名} where {条件};
```

*条件表达式可以用and，or，not修饰*

| 符号 | 意义     | 示例            |
| ---- | -------- | --------------- |
| =    | 相等     | name = ‘abc'    |
| >    | 大于     |                 |
| <    | 小于     |                 |
| <>   | 不等于   | name <> 'abc'   |
| <=   | 小于等于 |                 |
| >=   | 大于等于 |                 |
| LIKE | 相似     | name LIKE 'ab%' |

*%表示任意字符，例如'ab%'将匹配'ab', 'abc', 'abcd'.*



#### 排序

```
select {列名} from {表名} order by {表头} [ASC或DESC], {表头} [ASC或DESC]
```



#### 分页查询

```
select {列名} from {表名} limit {最多显示行数} offset {起始索引};
```

*最多显示行数->PageSize*

*起始索引->从这里开始偏移->*PageSize * (PageIndex - 1)

- 简化写法

```
select {列名} from {表名} limit {起始索引}, {最多显示行数};
```



#### 聚合查询

##### 函数聚合

```
select count(*) from students;
```

count(*)表示所有列的行数，等效于count(id)

count()是一个聚合函数，还有其他聚合函数

| 函数  | 说明                                 |
| ----- | ------------------------------------ |
| sum() | 某一列的合计值，该列必须为数值类型   |
| avg() | 某一列的平均值，该列必须为数值类型   |
| max() | 某一列的最大值，该列不必须为数值类型 |
| min() | 某一列的最小值，该列不必须为数值类型 |

##### 分组聚合

```
select class_id, count(*) num from students group by class_id;
```



#### 多表查询

##### 笛卡尔乘积

```
select * from {表1}，{表2};
```

如果两个表中有重复的列名，可以通过取别名简化

```
SELECT
    s.id sid,
    s.name,
    s.gender,
    s.score,
    c.id cid,
    c.name cname
FROM students s, classes c;

```



#### 连接查询

##### 内连接inner join

```
SELECT s.id, s.name, s.class_id, c.name class_name, s.gender, s.score
FROM students s
INNER JOIN classes c
ON s.class_id = c.id;
```

取交集



##### 外连接outer join

**左外连接left outer join**

```
SELECT s.id, s.name, s.class_id, c.name class_name, s.gender, s.score
FROM students s
INNER JOIN classes c
ON s.class_id = c.id;
```

取左表



**右外连接right join**

```
SELECT s.id, s.name, s.class_id, c.name class_name, s.gender, s.score
FROM students s
RIGHT OUTER JOIN classes c
ON s.class_id = c.id;
```

取右表



**左外连接union右外连接->full join**

```
SELECT s.id, s.name, s.class_id, c.name class_name, s.gender, s.score
FROM students s
INNER JOIN classes c
ON s.class_id = c.id
UNION
SELECT s.id, s.name, s.class_id, c.name class_name, s.gender, s.score
FROM students s
RIGHT OUTER JOIN classes c
ON s.class_id = c.id;
```

取并集



### 修改数据

#### insert

```
INSERT INTO <表名> (字段1, 字段2, ...) VALUES (值1, 值2, ...);
```

```
INSERT INTO students (class_id, name, gender, score) VALUES (2, '大牛', 'M', 80);
```



#### update

```
UPDATE <表名> SET 字段1=值1, 字段2=值2, ... WHERE ...;
```

```
UPDATE students SET name='大牛', score=66 WHERE id=1;
```

update语句可以没有where条件，此时整个表的记录都会被更新

因此在执行update前最好先select测试where条件是否筛选出了期望的记录集，然后再update更新



#### delete

```
DELETE FROM <表名> WHERE ...;
```

```
DELETE FROM students WHERE id=1;
```

注意到`DELETE`语句的`WHERE`条件也是用来筛选需要删除的行，因此和`UPDATE`类似，`DELETE`语句也可以一次删除多条记录

要特别小心的是，和`UPDATE`类似，不带`WHERE`条件的`DELETE`语句会删除整个表的数据。所以，在执行`DELETE`语句时也要非常小心，最好先用`SELECT`语句来测试`WHERE`条件是否筛选出了期望的记录集，然后再用`DELETE`删除。