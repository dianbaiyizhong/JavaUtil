# 路劲相关

## 获取maven工程下resources的绝对路劲

> 我们时常跑demo程序的时候，经常会将一些文件放到resources目录，而非D:xxx等这类路劲，这样不方便工程管理

```java
// 来自hutool工具包
ClassPathResource resource = new ClassPathResource("emp.csv");
resource.getAbsolutePath()
```

