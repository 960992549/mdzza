drop table if exists api_info;
create table if not exists api_info (
	id bigint not null auto_increment comment '编号',
	name varchar(20) not null comment '名称',
	module varchar(20) not null comment '模块',
	resource varchar(20) not null comment '资源',
	method varchar(20) not null comment '方法',
	invoke_method varchar(50) not null comment '调用方法',
	description varchar(50) comment '说明',
	create_user_id bigint comment '创建人',
	create_datetime datetime comment '创建时间',
	last_change_user_id bigint comment '修改人',
	last_change_datetime datetime comment '修改时间',
	primary key (id)
);
alter table api_info comment '接口信息';

drop table if exists api_input;
create table if not exists api_input(
	id bigint not null auto_increment comment '编号',
	api_id bigint not null comment '接口编号',
	name varchar(50) not null comment '参数名',
	label varchar(50) not null comment '标签',
	data_type varchar(50) not null comment '数据类型',
	description varchar(255) comment '说明',
	sort int comment '排序',
	primary key (id)
);
alter table api_input comment '接口入参';

drop table if exists api_input_validator;
create table if not exists api_input_validator(
	id bigint not null auto_increment comment '编号',
	input_id bigint not null comment '入参编号',
	rule text comment '规则',
	message varchar(50) comment '提示信息',
	description varchar(255) comment '说明',
	sort int comment '排序',
	primary key (id)
);
alter table api_input_validator comment '接口入参校验规则';

drop table if exists api_output;
create table if not exists api_output(
	id bigint not null auto_increment comment '编号',
	api_id bigint not null comment '接口编号',
	name varchar(50) not null comment '参数名',
	label varchar(50) not null comment '标签',
	data_type varchar(50) not null comment '数据类型',
	description varchar(255) comment '说明',
	sort int comment '排序',
	primary key (id)
);
alter table api_output comment '接口出参';

drop table if exists api_output_format;
create table if not exists api_output_format(
	id bigint not null auto_increment comment '编号',
	output_id bigint not null comment '出参编号',
	format text comment '转换器',
	description varchar(255) comment '说明',
	sort int comment '排序',
	primary key (id)
);
alter table api_output_format comment '接口出参转换器';

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
	api_id bigint not null comment '接口编号',
	action_id bigint not null comment '行为编号',
	status varchar(10) not null comment '状态',
	primary key (id)
);
alter table api_action comment '接口行为配置';