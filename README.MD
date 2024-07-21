## 项目说明
DDD规范演示。DDD四层架构落地。

## 项目结构
整体结构： 模拟服务开放平台。  
GenSIAPP为服务开放平台主体。MobileArea和MobileTag模拟后台核心业务。GenSIClient模拟待接入的第三方客户应用。  
后台核心业务通过GenSIAPP往互联网开放。第三方客户端按照GenSIAPP制定的标准访问后台核心业务。  

## 调试方式
### 1、本地调试方式 
1、GenSIApp中去掉nacos的pom依赖。  
2、GsRequestController中引入本地服务依赖@Resource(name = "LocalAsyncBusiService")  
3、依次启动GenSIAPP和GenSIClient。  
4、访问访问GenSIApp的应用配置页面：http://localhost:8888/interfaceManage/index.html#/manage/interface，访问密钥gensi。
在应用注册页面注册客户端信息。  
5、执行GenSIClient中test目录下的ClientTest，模拟向GenSIApp发起请求，需要按照注释修改参数。
客户端将对请求参数进行加密后访问到GenSIAPP。GenSIAPP验签成功后，返回响应信息。  
如果GenSIAPP本地有历史请求记录，就直接返回本地记录。如果没有，调用MobileArea或MobileTag中的服务，获取业务结果，并返回。  
6、以后接入新的后端服务时的扩展方式：后端应用可以以本地SPI的方式提供服务包。GenSIAPP不需要做任何改动。

### 2、微服务调试方式
1、本地启动nacos，作为微服务注册中心。GenSIAPP中添加nacos的pom依赖。  
2、MobileTag服务启动MobileTagApp。往nacos中注册服务信息。  
3、GsRequestController中引入本地服务依赖@Resource(name = "NacosAsyncBusiService")  
4、其他调试流程一样。但是GenSIClient只能调用MobileTag服务。  
5、以后接入新的后端服务时的扩展方式：后端应用只需要在nacos上注册服务即可。GenSIAPP不需要做任何改动。  

## 目的
DDD视角下，系统是由具体业务组成的，而并不是由MVC这样的技术层面组成的。
所以，只要业务没有变动，单体架构或者微服务架构都只是支撑手段。项目可以在业务开发的同时，随时在单体架构与微服务架构之间调整。对比MVC则可能需要做很大的重构。  
另外，这里着重演示DDD视角下，单体架构优先的思想。  
- Almost all the successful microservice stories have started with a monolith that got too big and was broken up
- Almost all the cases where I've heard of a system that was built as a microservice system from scratch, it has ended up in serious trouble.


