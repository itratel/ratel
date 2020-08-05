 <p align="center">
   <img src="https://img.shields.io/badge/Avue-1.5.0-green.svg" alt="Build Status">
   <img src="https://img.shields.io/badge/Java-11-red.svg" alt="Build Status">
   <img src="https://img.shields.io/badge/Spring%20Cloud-Greenwich.SR3-blue.svg" alt="Coverage Status">
   <img src="https://img.shields.io/badge/Spring%20Boot-2.1.9.RELEASE-blue.svg" alt="Downloads">
 </p>  
 
   
#### 1.Ratel简介
**Ratel MicroService Architecture**  Ratel微服务开发框架，该框架主要采用Spring Cloud 和 Spring Could Alibaba的微服务组件实现，集成了服务注册，服务发现，服务监控，服务链路追踪，服务降级，限流等基本服务治理功能，另外继承包含了Oauth2认证中心，单点登录等重要功能，持续更新中，接下来介绍Ratel的主要功能；


#### 2.微服务治理基础组件

###### 2.1 服务注册和发现
服务注册和发现完全采用Spring cloud Alibaba的Nacos组件完成注册，想要深入可以到[官网](http://nacos.io)详细了解;

###### 2.2 服务配置中心
服务配置中同样是也是由Nacos组件实现;

###### 2.3 服务调用
服务调用是原Spring Cloud系统的声明式服务调用组件Feign实现;

###### 2.4 服务监控
服务监控还是采用Spring Boot Admin实现;

######  2.5 服务调用链路追踪
链路追踪采用开源社区的SkyWalking实现;

######  2.6 服务降级和限流
服务降级和限流采用Spring cloud Alibaba的Sentinel组件实现;


#### 3.模块说明
```lua
ratel
├── ratel-authorization -- 授权服务提供
     ├── ratel-authorization-oauth2-authserver oauth2认证授权中心
     └── ratel-authorization-oauth2-resource-server oauth2资源中心
└── ratel-common -- 系统公共模块 
     ├── ratel-common-core -- 公共工具类核心包
     └── ratel-common-security -- 安全工具类
├── ratel-registry -- 服务注册与发现
├── ratel-gateway -- gateway网关
└── ratel-upms -- 用户权限管理模块
     └── ratel-upms-api -- 用户权限管理系统公共api模块
     └── ratel-upms-core -- 用户权限管理系统业务处理模块
└── ratel-starter -- Ratel扩展实现的starter
     └── distributed-lock-ratel-boot-starter -- 分布式锁starter
     └── excel-ratel-boot-starter -- excel文件相关starter
     └── logger-ratel-boot-starter -- 日志相关组件starter
     └── multi-datasource-ratel-boot-starter -- 多数据源组件starter
└── ratel-system  -- 与系统相关模块 
     ├── ratel-monitor -- Spring Boot Admin监控
     ├── ratel-skywalking -- 链路调用监控
     └── ratel-codegen -- 化代码生成
```

