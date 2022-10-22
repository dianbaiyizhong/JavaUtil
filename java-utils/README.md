# Java工具类
## 空对象判断工具类

### EmptyUtils.isEmptyStrict
对象判空-最严格级别
```java
System.out.println(EmptyUtils.isEmptyStrict(null));  // 结果:true
System.out.println(EmptyUtils.isEmptyStrict("Null")); // 结果:true
System.out.println(EmptyUtils.isEmptyStrict("")); // 结果:true
```




## 日志辅助打印工具
### PrintUtils.getPrintStrByMap
获取一个List Map类型的表格字符串，方便控制台打印观赏
```java
     
System.out.println(PrintUtils.getPrintStrByMap(null)); 
```
```properties
+--------+--------+
|  sex   |  name  |
+--------+--------+
|   男    |   张三   |
|   女    |   李四   |
+--------+--------+
```
