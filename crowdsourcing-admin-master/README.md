# 移动众包平台管理系统

> 基于Vue.js的管理系统

## 构建步骤

``` bash
# 安装依赖
npm install

# 启动开发服务器，默认8080端口，若被占用则会使用其他端口
npm run dev

# 构建
npm run build

```
## 目录说明

> 本项目使用Vue + Vuex + vue-router + axios为技术栈，使用了iview组件库

组件及页面位于：`src/components`
路由配置：`src/router`
vuex：`src/store` 其中子目录`modules`存储了4个子store，`types.js`配置了`mutation`的类型, `index.js`则是vuex的入口。
http请求：位于`src/api`, 其中每个文件对应了使用`axios`发起的请求

## 其它
1. 工程使用vue-cli构建。
2. 对于组件库，未使用按需加载。
3. 暂未使用token或者cookie(可和后端跟进)。
4. 如有任何问题，请讨论。



### 修改：2018.4.12

1. 增加了用户登录逻辑，传入{name, password} 返回 { name, token }， 增加了快速登录(同一台计算机未登出直接关闭下一次免登录， 但为了安全起见需要验证token的合法性);

2. 修改地址属性成功，把原先的{ name: '', location: '' } 修改为：{ location: '', longitude: '', latitude: '' }， 需要注意的是，`altitude`表示海拔，并不表示维度，所以改成了 `latitude`;

3. 增加了两个日期选择器组件，用于选定发布日期和截止日期(但是：没有做日期验证，这里后端去做吧)

4. 完成了任务显示详情的重新设计，现在点击详情将弹出一个模态框，除了显示基本信息外，还可以显示问题详情。


### 修改：2018.4.13

1. 新增注册功能，request 路径为 `site/regist`, POST 请求， body为 { userName: '', password: '' }, 返回result字段为：{ userName: '', token: '' } 用于标记是否登录成功的token

同理， 登录http请求路径为 `baseurl/login`, 同样的请求体和返回体

2. datapicker 组件的type已改

 