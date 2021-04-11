# JavaUtil
一个java工具类


#java调优日志

##一个内存泄露的例子


```shell
jmap -head pid
```


###打开jconsole可视化页面查看
```shell
jconsole pid
```

###每3秒打印区中内存使用状况，当E和O使用率明显增大的时候，说明内存泄漏
```shell
jstat -gcutil pid 3000
```


```
Heap Usage:
PS Young Generation
Eden Space:
   capacity = 131072000 (125.0MB)
   used     = 55639192 (53.061668395996094MB)
   free     = 75432808 (71.9383316040039MB)
   42.449334716796876% used
From Space:
   capacity = 11010048 (10.5MB)
   used     = 0 (0.0MB)
   free     = 11010048 (10.5MB)
   0.0% used
To Space:
   capacity = 13631488 (13.0MB)
   used     = 0 (0.0MB)
   free     = 13631488 (13.0MB)
   0.0% used
PS Old Generation
   capacity = 132120576 (126.0MB)
   used     = 16723992 (15.949241638183594MB)
   free     = 115396584 (110.0507583618164MB)
   12.658128284272694% used

16245 interned Strings occupying 1454744 bytes.

```

```shell
jmap -histo:live pid
```
```
 num     #instances         #bytes  class name
----------------------------------------------
   1:         39899        4014840  [C
   2:          3423        2245096  [B
   3:         14246        1253648  java.lang.reflect.Method
   4:         39671         952104  java.lang.String
   5:          8028         887744  java.lang.Class
   6:         20930         669760  java.util.concurrent.ConcurrentHashMap$Node
   7:          8039         412960  [Ljava.lang.Object;
   8:          4033         334592  [Ljava.util.HashMap$Node;
   9:          7637         305480  java.util.LinkedHashMap$Entry
  10:         13503         302608  [Ljava.lang.Class;
  11:          8452         270464  java.util.HashMap$Node
  12:          4190         234640  java.util.LinkedHashMap
  13:           133         198672  [Ljava.util.concurrent.ConcurrentHashMap$Node;
  14:         10630         170080  java.lang.Object
  15:          3697         167744  [I
  16:          1964         141408  java.lang.reflect.Field
  17:          1399         111920  java.lang.reflect.Constructor
  18:          3953          94872  org.springframework.core.MethodClassKey
  19:          1950          93600  java.util.HashMap
  20:          1358          81856  [Ljava.lang.reflect.Method;
  21:          1365          76440  java.lang.Class$ReflectionData
  22:          1326          74256  java.lang.invoke.MemberName
  23:          1739          69560  java.lang.ref.SoftReference
  24:          2517          60408  java.util.ArrayList
  25:          1431          56352  [Ljava.lang.String;
  26:          1181          47240  java.lang.invoke.MethodType
  27:          1177          47080  java.util.TreeMap$Entry
  28:          1396          44672  java.util.concurrent.locks.ReentrantLock$NonfairSync
  29:           896          43008  org.springframework.core.ResolvableType
  30:          1021          40840  java.lang.ref.Finalizer
```


###拿到heap文件
```shell
jmap -dump:live,format=b,file=heap.hprof 3514
```

