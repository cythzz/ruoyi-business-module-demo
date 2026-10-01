# RuoYi 客户回访业务模块 Demo

这是一个基于 **RuoYi-Vue-fast** 的最小业务扩展示例，重点展示如何在现有 RuoYi 权限体系中新增“客户回访”模块，而不是再复制一套大型业务系统。

## 核心能力

- `business_customer_follow_up` 客户回访表与示例数据
- Controller、Service、Mapper、MyBatis XML 的完整 CRUD 链路
- `@PreAuthorize` 按钮级权限：查询、新增、修改、删除
- `@Log` 记录新增、修改、删除操作日志
- Redis 接口限流与防重复提交
- 手机号响应字段自动脱敏
- RuoYi 标准分页响应 `TableDataInfo`
- 新增记录自动补充默认状态、优先级与审计时间

## 扩展代码位置

```text
src/main/java/com/ruoyi/project/business/
├── controller/CustomerFollowUpController.java
├── domain/CustomerFollowUp.java
├── mapper/CustomerFollowUpMapper.java
└── service/
    ├── ICustomerFollowUpService.java
    └── impl/CustomerFollowUpServiceImpl.java
src/main/resources/mybatis/business/CustomerFollowUpMapper.xml
sql/customer_follow_up.sql
```

## 运行

本项目沿用上游 Java 8 与 Spring Boot 2.5.15，不强制更换本地 JDK。准备 MySQL 和 Redis 后：

1. 导入 `sql/ry_*.sql` 基础库。
2. 再执行 `sql/customer_follow_up.sql`。
3. 修改 `src/main/resources/application-druid.yml` 的数据库配置。
4. 执行 `mvn spring-boot:run`。

接口前缀为 `/business/follow-up`。为保持 Demo 轻量，本仓库只实现可复用的后端模块；菜单 SQL 中预留了前端组件路径，可按实际前端技术栈补充页面。

## 安全设计

- 权限必须由后端 `@PreAuthorize` 再校验，前端按钮隐藏不能防止越权调用。
- 列表接口按调用方限流，避免高频分页查询拖垮数据库。
- 新增、编辑、删除使用 `@RepeatSubmit`，请求体和 Token 在 Redis 中形成短期幂等判断。
- 手机号字段通过 Jackson 序列化器统一脱敏，避免各 Controller 重复处理。
- 增删改使用 `@Log` 形成操作审计记录，可追踪操作者、接口、参数和结果。

## 简历对应描述

基于 RuoYi 权限框架扩展客户回访业务模块，完成数据建模、分页 CRUD、按钮级权限控制、Redis 限流、防重复提交、敏感字段脱敏和操作审计，并通过业务 SQL 一键注册菜单及权限。

## 来源与许可

本项目在 `yangzongzhuan/RuoYi-Vue-fast` 的 MIT 许可代码基础上二次开发，保留原项目许可文件与版权声明。新增的客户回访模块用于学习和作品展示；二次使用时请继续遵守仓库中的 `LICENSE`。
