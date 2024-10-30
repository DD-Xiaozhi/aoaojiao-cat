# Aoaojiao-o.o
嗷嗷叫平台基础框架。参考 Alibaba 的 COLA v5 架构，此架构单体服务和微服务皆可使用

## 技术组件
| 组件         | 依赖版本 |
|------------|------|
| SpringBoot | 3.2.5  |
| hutool-all | 5.8.32  |
| hutool-all | 5.8.32  |
| guava | 32.1.2-jre  |
| common-lang3 | 3.14.0  |


## 模块介绍
|        模块名称     |        模块功能        |
|---------------------|------------------------|
| aoaojiao-common     | 主要放置共用的类        |
| aoaojiao-compponent | 项目使用的到的组件，比如 oss组件、log组件...... |
| aoaojiao-dao        | 负责和数据库进行交互，这个也可以叫做repository 或者 mapper  |
| aoaojiao-domain     | 放置领域模型  |
| aoaojiao-service    | 放置不同模块的服务 |
| aoaojiao-starter    | 启动和测试，单体项目可放这个模块，微服务可将服务的启动和测试放置 service 模块 |

以上模块可以灵活变动，可以增加自己的模块或者减少本架构中的模块


## 自定义组件
| 组件名称          | 组件作用              |
|-------------------|------------------------|
| aoaojiao-comp-log | 日志组件，负责日志记录  |
| aoaojiao-comp-oss | 基于S3协议的OSS通用组件 |
| ..... 待定        | ...... |
