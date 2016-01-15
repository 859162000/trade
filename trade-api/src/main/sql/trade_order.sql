-- 2015.10.15，Jongly与李伟联调新增字段
alter table trade_order add column pass_city_id int(11) ;
alter table trade_order add column pass_city_name varchar(255) comment '途径城市名称';
