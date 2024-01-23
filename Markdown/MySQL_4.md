# 约束

SQL 约束用于规定表中的数据规则。

如果存在违反约束的数据行为，行为会被约束终止。

约束可以在创建表时规定（通过 CREATE TABLE 语句），或者在表创建之后规定（通过 ALTER TABLE 语句）。

- **NOT NULL** - 指示某列不能存储 NULL 值。
- **UNIQUE** - 保证某列的每行必须有唯一的值。
- **PRIMARY KEY** - NOT NULL 和 UNIQUE 的结合。确保某列（或两个列多个列的结合）有唯一标识，有助于更容易更快速地找到表中的一个特定的记录。
- **FOREIGN KEY** - 保证一个表中的数据匹配另一个表中的值的参照完整性。
- **CHECK** - 保证列中的值符合指定的条件。
- **DEFAULT** - 规定没有给列赋值时的默认值。



# ALTER 命令

用于修改数据库、表和索引等对象的结构。

**ALTER** 命令允许你添加、修改或删除数据库对象，并且可以用于更改表的列定义、添加约束、创建和删除索引等操作。

1.添加列

```
ALTER TABLE table_name
ADD COLUMN new_column_name datatype;
```

2.修改列的数据类型

 实例

```
ALTER TABLE TABLE_NAME
MODIFY COLUMN column_name new_datatype;
```

3. 修改列名

```
ALTER TABLE table_name
CHANGE COLUMN old_column_name new_column_name datatype;
```

4. 删除列

```
ALTER TABLE table_name
DROP COLUMN column_name;
```

5. 添加 PRIMARY KEY

```
ALTER TABLE table_name
ADD PRIMARY KEY (column_name);
```

6. 添加 FOREIGN KEY

```
ALTER TABLE child_table
ADD CONSTRAINT fk_name
FOREIGN KEY (column_name)
REFERENCES parent_table (column_name);
```

7. 修改表名

```
ALTER TABLE old_table_name
RENAME TO new_table_name;
```



# 索引

一种数据结构，用于加快数据库查询的速度和性能。

索引分单列索引和组合索引：

- 单列索引，即一个索引只包含单个列，一个表可以有多个单列索引。
- 组合索引，即一个索引包含多个列。

## 实质

实际上，索引也是一张表，该表保存了主键与索引字段，并指向实体表的记录。

## 缺点

- 索引需要占用额外的存储空间。
- 对表进行插入、更新和删除操作时，索引需要维护，可能会影响性能。
- 过多或不合理的索引可能会导致性能下降，因此需要谨慎选择和规划索引。

## 普通索引

### 创建索引

1.　**CREATE INDEX** ：

```
CREATE INDEX index_name
ON table_name (column1 [ASC|DESC], column2 [ASC|DESC], ...);
```

- `CREATE INDEX`: 用于创建普通索引的关键字。
- `index_name`: 指定要创建的索引的名称。索引名称在表中必须是唯一的。
- `table_name`: 指定要在哪个表上创建索引。
- `(column1, column2, ...)`: 指定要索引的表列名。你可以指定一个或多个列作为索引的组合。这些列的数据类型通常是数值、文本或日期。
- `ASC`和`DESC`（可选）: 用于指定索引的排序顺序。默认情况下，索引以升序（ASC）排序。

2.　ALTER　TABLE修改表结构

```
ALTER TABLE table_name
ADD INDEX index_name (column1 [ASC|DESC], column2 [ASC|DESC], ...);
```

3. 创建表时直接指定

```
CREATE TABLE table_name (
  column1 data_type,
  column2 data_type,
  ...,
  INDEX index_name (column1 [ASC|DESC], column2 [ASC|DESC], ...)
);
```

### 删除索引

1. DROP INDEX

```
DROP INDEX index_name ON table_name;
```

2. ALTER TABLE

```
ALTER TABLE table_name
DROP INDEX index_name;
```



## 唯一索引

唯一索引确保索引中的值是唯一的，不允许有重复值。

### 创建索引

1. **CREATE UNIQUE INDEX**

```
CREATE UNIQUE INDEX index_name
ON table_name (column1 [ASC|DESC], column2 [ASC|DESC], ...);
```

2. **ALTER TABLE** 

```
ALTER table mytable 
ADD CONSTRAINT unique_constraint_name UNIQUE (column1, column2, ...);
```

3. 创建表的时候直接指定

```
CREATE TABLE table_name (
  column1 data_type,
  column2 data_type,
  ...,
  CONSTRAINT index_name UNIQUE (column1 [ASC|DESC], column2 [ASC|DESC], ...)
);
```

## 使用 ALTER 命令添加和删除主键

主键作用于列上（可以一个列或多个列联合主键），添加主键索引时，你需要确保该主键默认不为空（NOT NULL）。

```
mysql> ALTER TABLE testalter_tbl MODIFY i INT NOT NULL;
mysql> ALTER TABLE testalter_tbl ADD PRIMARY KEY (i);
```

```
mysql> ALTER TABLE testalter_tbl DROP PRIMARY KEY;
```

## 显示索引信息

```
mysql> SHOW INDEX FROM table_name\G
```

- `\G`: 格式化输出信息。



# 临时表

临时表只在当前连接可见，当关闭连接时，MySQL 会自动删除表并释放所有空间。

## 创建临时表

```
CREATE TEMPORARY TABLE temp_table_name (
  column1 datatype,
  column2 datatype,
  ...
);
```

```
CREATE TEMPORARY TABLE temp_table_name AS
SELECT column1, column2, ...
FROM source_table
WHERE condition;
```

## 删除临时表

临时表在会话结束时会自动被销毁，但你也可以使用 DROP TABLE 明确删除它。

```
DROP TEMPORARY TABLE IF EXISTS temp_table_name;
```



# 复制表

- 使用 **SHOW CREATE TABLE** 命令获取创建数据表(**CREATE TABLE**) 语句，该语句包含了原数据表的结构，索引等。
- 复制以下命令显示的 SQL 语句，修改数据表名，并执行SQL语句，通过以上命令将完全地复制数据表结构。
- 然后就可以使用 **INSERT INTO ... SELECT** 语句来实现复制表的内容。



# 元数据

MySQL 元数据是关于数据库和其对象（如表、列、索引等）的信息。

元数据存储在系统表中，这些表位于 MySQL 数据库的 information_schema 数据库中，通过查询这些系统表，可以获取关于数据库结构、对象和其他相关信息的详细信息。

- **查询结果信息：** SELECT, UPDATE 或 DELETE语句影响的记录数。
- **数据库和数据表的信息：** 包含了数据库及数据表的结构信息。
- **MySQL 服务器信息：** 包含了数据库服务器的当前状态，版本号等。

## 常用元数据查询

查看表的结构：

```
DESC table_name;
```

查看表的索引：

```
SHOW INDEX FROM table_name;
```

查看列的信息：

```
SELECT COLUMN_NAME, DATA_TYPE, IS_NULLABLE, COLUMN_KEY
FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_SCHEMA = 'database_name'
AND TABLE_NAME = 'table_name';
```

查看外键信息：

```
SELECT
    TABLE_NAME,
    COLUMN_NAME,
    CONSTRAINT_NAME,
    REFERENCED_TABLE_NAME,
    REFERENCED_COLUMN_NAME
FROM
    INFORMATION_SCHEMA.KEY_COLUMN_USAGE
WHERE
    TABLE_SCHEMA = 'your_database_name'
    AND TABLE_NAME = 'your_table_name'
    AND REFERENCED_TABLE_NAME IS NOT NULL;
```



## information_schema 数据库

information_schema 是 MySQL 数据库中的一个系统数据库，它包含有关数据库服务器的元数据信息，这些信息以表的形式存储在 information_schema 数据库中。

1. SCHEMATA 表

存储有关数据库的信息，如数据库名、字符集、排序规则等。

```
SELECT * FROM information_schema.SCHEMATA;
```

2. TABLES 表

包含有关数据库中所有表的信息，如表名、数据库名、引擎、行数等。

```
SELECT * FROM information_schema.TABLES WHERE TABLE_SCHEMA = 'your_database_name';
```

3. COLUMNS 表

包含有关表中列的信息，如列名、数据类型、是否允许 NULL 等。

```
SELECT * FROM information_schema.COLUMNS WHERE TABLE_SCHEMA = 'your_database_name' AND TABLE_NAME = 'your_table_name';
```

4. STATISTICS 表

提供有关表索引的统计信息，如索引名、列名、唯一性等。

```
SELECT * FROM information_schema.STATISTICS WHERE TABLE_SCHEMA = 'your_database_name' AND TABLE_NAME = 'your_table_name';
```



# 序列

在 MySQL 中，序列是一种自增生成数字序列的对象，是一组整数 **1、2、3、...**，一张数据表只能有一个字段自增主键。

尽管 MySQL 本身并没有内建的序列类型，但可以使用 AUTO_INCREMENT 属性来模拟序列的行为，通常 **AUTO_INCREMENT** 属性用于指定表中某一列的自增性。

## 实例

```
CREATE TABLE example_table (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50)
);
```

获取表的当前自增值：

```
SHOW TABLE STATUS LIKE 'example_table';
```

在结果集中，Auto_increment 列的值即为当前表的自增值。

*用 AUTO_INCREMENT 属性的列只能是整数类型（通常是 INT 或 BIGINT）。此外，如果删除表中的某一行，其自增值不会被重新使用，而是会继续递增。如果希望手动设置自增值，可以使用 SET 语句，但这不是一种常规的做法，因为可能引起唯一性冲突。*



# 统计重复数据

## 实例

以下我们将统计表中 first_name 和 last_name的重复记录数：

```
mysql> SELECT COUNT(*) as repetitions, last_name, first_name
    -> FROM person_tbl
    -> GROUP BY last_name, first_name
    -> HAVING repetitions > 1;
```

以上查询语句将返回 person_tbl 表中重复的记录数。 一般情况下，查询重复的值，执行以下操作：

- 确定哪一列包含的值可能会重复。
- 在列选择列表使用COUNT(*)列出的那些列。
- 在GROUP BY子句中列出的列。
- HAVING子句设置重复数大于1。