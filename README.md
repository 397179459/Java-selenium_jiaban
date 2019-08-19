# Java_selenium_jiaban
![](https://img.shields.io/badge/Fast-High%20Efficiency-success.svg)&emsp;![](https://img.shields.io/badge/author-%E7%8E%8B%20%E7%A3%8A-red.svg)&emsp;![](https://img.shields.io/github/commit-activity/y/397179459/Java-selenium_jiaban.svg)&emsp;![](https://img.shields.io/github/languages/top/397179459/Java-selenium_jiaban.svg?color=red)
### Change Log

- 19/8/19 加班的链接，**现在是用`Xpath`定位**，取代了之前是直接定位到文字的链接，因为之前的链接总是会变化。

* 19/7/26 重大突破，之前一直没找到指定日期的最优方法，**现在终于解决了如何直接指定日期**

  * 代码在`./src/jiaban/JiabanWork2.java`

    ```java
    //核心代码
    ...
    //一般都是提当月的加班，所以只对日期做了选择，比如这里和下面拼接的话就是提2019/7/23的加班
    String day = "23";   
    ...
    selenium.click("//td[@onclick='day_Click(2019,7," + day + ");']");
    ```

  * *待完善的地方：以后有时间再做模块化设计，就可以适用其他很多场景，如果有需求的话*

* 19/6/10 代码重构，定义了一个func代替循环体，以后的修改维护也更简单一点。删除了Jiaban_OP,因为日期问题不好解决，每个月的日期差都不固定。因为OA一直在变化，有些小的地方一直在变动，比如登录按钮的ID也规范(login)了，还有上次的字符(新)去掉了
* 19/5/10 新增选择 转当月调休 功能；**记得查看chrome版本和插件**；修改了进入加班的链接，以前开头是(新)，现在没有了
* 19/4/17 新增提指定日期的加班，代码在./src/jiaban/Jiaban_OP.java。

-----
![](https://github.com/397179459/Java-selenium_jiaban/blob/master/gif/jiaban.gif)
### 使用方法

1. 把该项目下载到本地

2. 把Chromedriver路径添加到环境变量(如果是Firefox同理) 
	
	* chrome驱动可以直接放到eclipse安装目录下，不用配置环境变量；
	
	* 或者在main函数里加一句,后面是你的文件位置:
	
	```java
	System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
	```
	
3. eclipse导入项目   

4. 源码在.\src\jiaban里，有`周内版`、 `周末版`、 `指定日期版`，用户要修改的地方是自己用户名和密码

5. 启动运行就OK了，如果报错的话，**一般都是chrome driver的版本问题**
-----
#### Tips：
* 其实这份代码，是为了解放你的双手，特别是加班人数特别多的时候，速度主要取决于你的网速和电脑；如果加班人数小于5人时，速度绝对给力
* 可能速度没有你手动那么熟练，但是程序运行后就不用管了，最小化就行了，再去干其他的事情
* 需要用到的包都在lib中，不过我用的是Chrome，如果你用Firefox的话要自己下载驱动和相应的包
* 目前有周内和周末模板，周末太复杂了，所以只有4个时间段，用户要修改的是OA的用户名和密码，还有各个时段的人数
* 我们IT做的功能目前不能输入工号，而且同名的人特别多，所以没办法直接定位到员工，只能手动添加
