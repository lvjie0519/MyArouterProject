# 组件化实现方案
## activity 跳转
注解+注解处理器的方式实现， 参考阿里  ARouter

## api 调用
接口代理方式实现, 用到的技术：Google AutoService+ServiceLoader

### AutoService 组件化实现
原理：AutoService会自动在META-INF文件夹下生成Processor配置信息文件，该文件里就是实现该服务接口的具体实现类。
而当外部程序装配这个模块的时候， 就能通过该jar包META-INF/services/里的配置文件找到具体的实现类名，
并装载实例化，完成模块的注入。

## 参考网址
https://www.freesion.com/article/4635912848/
