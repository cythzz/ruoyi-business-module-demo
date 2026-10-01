-- 客户回访业务表与 RuoYi 菜单权限。先导入项目自带 ry_*.sql，再执行本文件。
drop table if exists business_customer_follow_up;
create table business_customer_follow_up (
  follow_up_id       bigint(20)      not null auto_increment comment '回访主键',
  customer_name      varchar(50)     not null                comment '客户姓名',
  mobile             varchar(20)     default ''              comment '手机号',
  order_no           varchar(64)     not null                comment '订单号',
  status             char(1)         default '0'             comment '状态（0待联系 1跟进中 2已完成）',
  priority           char(1)         default '1'             comment '优先级（0低 1中 2高）',
  owner_name         varchar(50)     default ''              comment '负责人',
  next_contact_time  datetime                                comment '下次联系时间',
  create_by          varchar(64)     default ''              comment '创建者',
  create_time        datetime                                comment '创建时间',
  update_by          varchar(64)     default ''              comment '更新者',
  update_time        datetime                                comment '更新时间',
  remark             varchar(500)    default null            comment '备注',
  primary key (follow_up_id),
  unique key uk_follow_up_order (order_no),
  key idx_follow_up_status_time (status, next_contact_time)
) engine=innodb auto_increment=1 comment='客户回访记录';

insert into sys_menu values('2100', '客户运营', '0',    '5', 'business',  null,                       '', '', 1, 0, 'M', '0', '0', '',                         'peoples', 'admin', sysdate(), '', null, '客户运营目录');
insert into sys_menu values('2101', '客户回访', '2100', '1', 'follow-up', 'business/follow-up/index', '', '', 1, 0, 'C', '0', '0', 'business:followup:list',   'phone',   'admin', sysdate(), '', null, '客户回访菜单');
insert into sys_menu values('2102', '回访查询', '2101', '1', '',          '',                         '', '', 1, 0, 'F', '0', '0', 'business:followup:query',  '#',       'admin', sysdate(), '', null, '');
insert into sys_menu values('2103', '回访新增', '2101', '2', '',          '',                         '', '', 1, 0, 'F', '0', '0', 'business:followup:add',    '#',       'admin', sysdate(), '', null, '');
insert into sys_menu values('2104', '回访修改', '2101', '3', '',          '',                         '', '', 1, 0, 'F', '0', '0', 'business:followup:edit',   '#',       'admin', sysdate(), '', null, '');
insert into sys_menu values('2105', '回访删除', '2101', '4', '',          '',                         '', '', 1, 0, 'F', '0', '0', 'business:followup:remove', '#',       'admin', sysdate(), '', null, '');

insert into business_customer_follow_up
(customer_name, mobile, order_no, status, priority, owner_name, next_contact_time, create_by, create_time, remark)
values
('张女士', '13800000001', 'ORDER-20261001-001', '0', '2', '客服小陈', date_add(sysdate(), interval 1 day), 'admin', sysdate(), '直播间咨询尺码，次日回访'),
('李先生', '13800000002', 'ORDER-20261001-002', '1', '1', '客服小陈', date_add(sysdate(), interval 2 day), 'admin', sysdate(), '换货处理中');
