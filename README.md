[![GitHub license](https://img.shields.io/github/license/UyCode/project_librarium?color=blue&label=licence)](https://github.com/UyCode/project_librarium/blob/master/LICENSE)

---

![](https://gitee.com/UyCode/images-for-page/raw/master/page/librarium1.png)

---


这是一个简单的前后端分离项目，主要采用 Vue.js + SpringBoot 技术栈开发。

除了用作入门练习，我还希望该项目可以作为一些常见 Web 项目的脚手架，帮助大家简化搭建网站的流程。

后台效果：
![](https://gitee.com/UyCode/images-for-page/raw/master/page/librarium2.png)

# 技术栈

## 1.前端技术栈

1.Vue

2.Element-UI

3.axios   

## 2.后端技术栈

1.Spring Boot

2.Spring Data + JPA

3.MySQL

4.Shiro

## 3.部署方法

1.clone 项目到本地

`git clone git@github.com:UyCode/Project-Librarium.git`

2.数据库脚本放在 `database.sql`,在项目的根目录下，在MySQL中执行数据库脚本  

3.数据库配置在 `librarium` 项目的 `src\main\resources` 目录下的`application.properties` 文件中，mysql 版本为 8.0.15   

4.在IntelliJ IDEA中运行 `librarium` 项目，为了保证项目成功运行，可以右键点击 `pom.xml` 选择 maven -> reimport ，并重启项目

至此，服务端就启动成功了，同时，运行 `librarium-vue` 项目，访问 `http://localhost:8181` ，即可进入登录页面，默认账号是 `admin`，密码是 `123`

如果要做二次开发，请继续看第五、六步。

5.进入到 `librarium-vue` 目录中，在命令行依次输入如下命令：  

```
# 安装依赖
npm install

# 在 localhost:8181 启动项目
npm run dev

```

由于在 `librarium-vue` 项目中已经配置了端口转发，将数据转发到SpringBoot上，因此项目启动之后，在浏览器中输入 `http://localhost:8181` 就可以访问我们的前端项目了，所有的请求通过端口转发将数据传到 SpringBoot 中（注意此时不要关闭 SpringBoot 项目）。

6.最后可以用 `WebStorm` 等工具打开 `librarium-vue`项目，继续开发，开发完成后，当项目要上线时，依然进入到 `librarium-vue` 目录，然后执行如下命令：  

```
npm run build
```

该命令执行成功之后， `librarium-vue` 目录下生成一个 `dist` 文件夹，可以将该文件夹中的两个文件 `static` 和 `index.html` 拷贝到 `librarium` 项目中 `public/` 目录下，然后直接运行 `librarium` 项目，访问 `http://localhost:8088` ，实际上是把前端打包后作为静态文件，但不推荐使用这种方式。

(持续更新中)
