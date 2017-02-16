drop table if exists sys_user;
create table if not exists sys_user(
	id bigint not null auto_increment comment '编号',
	username varchar(30) not null comment '登录名',
	password varchar(50) not null comment '登录密码',
	status varchar(20) not null comment '状态',
	role_id bigint comment '角色编号',
	name varchar(20) comment '姓名',
	mobile varchar(20) comment '手机号',
	gender varchar(20) comment '性别',
	create_user_id bigint comment '创建人',
	create_datetime datetime comment '创建时间',
	primary key (id)
);
alter table sys_user comment '用户';

drop table if exists sys_role;
create table if not exists sys_role(
	id bigint not null auto_increment comment '编号',
	name_en varchar(20) not null comment '英文名称',
	name_cn varchar(20) not null comment '中文名称',
	description varchar(50) comment '描述',
	primary key (id)
);
alter table sys_role comment '角色';

drop table if exists sys_menu;
create table if not exists sys_menu(
	id bigint not null auto_increment comment '编号',
	name varchar(20) not null comment '名称',
	url varchar(255) comment '路径',
	parent_id bigint comment '父级编号',
	icon varchar(50) comment '图标',
	is_show varchar(20) comment '是否可见',
	sort int comment '排序',
	description varchar(255) comment '描述',
	primary key (id)
);
alter table sys_menu comment '菜单';

drop table if exists sys_role_menu;
create table if not exists sys_role_menu(
	id bigint not null auto_increment comment '编号',
	role_id bigint not null comment '角色编号',
	menu_id bigint not null comment '菜单编号',
	primary key (id)
);
alter table sys_role_menu comment '角色菜单';

drop table if exists sys_role_api;
create table if not exists sys_role_api(
	id bigint not null auto_increment comment '编号',
	role_id bigint not null comment '角色编号',
	api_id bigint not null comment '接口编号',
	primary key (id)
);
alter table sys_role_api comment '角色接口';

drop table if exists sys_dict;
create table if not exists sys_dict(
	id bigint not null auto_increment comment '编号',
	value varchar(20) not null comment '值',
	label varchar(20) not null comment '标签',
	type varchar(20) not null comment '类型',
	description varchar(30) comment '描述',
	sort int comment '排序',
	remarks varchar(100) comment '备注',
	primary key (id)
);
alter table sys_dict comment '字典';