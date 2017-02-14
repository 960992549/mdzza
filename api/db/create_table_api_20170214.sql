drop table if exists api_info;
create table if not exists api_info (
	id bigint not null auto_increment comment '编号',
	name varchar(20) not null comment '名称',
	module varchar(20) not null comment '模块',
	resource varchar(20) not null comment '资源',
	method varchar(20) not null comment '方法',
	invoke_method varchar(50) comment '调用方法',
	-- invoke_method varchar(50) not null comment '调用方法',
	description varchar(50) comment '说明',
	primary key (id)
);
alter table api_info comment 'api信息';

drop table if exists api_input;
create table if not exists api_input(
	id bigint not null auto_increment comment '编号',
	api_id bigint not null comment 'api编号',
	name varchar(50) not null comment '参数名',
	label varchar(50) not null comment '标签',
	description varchar(255) comment '说明',
	sort int comment '排序',
	primary key (id)
);
alter table api_input comment 'api入参';

drop table if exists api_input_validator;
create table if not exists api_input_validator(
	id bigint not null auto_increment comment '编号',
	input_id bigint not null comment '入参编号',
	rule text comment '规则',
	message varchar(50) comment '结果提示',
	sort int comment '排序',
	primary key (id)
);
alter table api_input_validator comment 'api入参校验规则';

drop table if exists api_output;
create table if not exists api_output(
	id bigint not null auto_increment comment '编号',
	api_id bigint not null comment 'api编号',
	name varchar(50) not null comment '参数名',
	label varchar(50) not null comment '标签',
	description varchar(255) comment '说明',
	sort int comment '排序',
	primary key (id)
);
alter table api_output comment 'api出参';

drop table if exists api_output_formater;
create table if not exists api_output_formater(
	id bigint not null auto_increment comment '编号',
	input_id bigint not null comment '出参编号',
	formater text comment '转换器',
	sort int comment '排序',
	primary key (id)
);
alter table api_output_formater comment 'api出参转换器';

drop table if exists action_info;
create table if not exists action_info(
	id bigint not null auto_increment comment '编号',
	name varchar(20) not null comment '名称',
	address varchar(50) comment '地址',
	description varchar(50) comment '说明',
	primary key (id)
);
alter table action_info comment '行为信息';

drop table if exists api_action;
create table if not exists api_action (
	id bigint not null auto_increment comment '编号',
	api_id bigint not null comment 'api编号',
	action_id bigint not null comment '行为编号',
	status varchar(10) not null comment '状态',
	primary key (id)
);
alter table api_action comment 'api行为配置';