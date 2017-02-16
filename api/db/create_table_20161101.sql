drop table if exists user;
create table if not exists user(
	id bigint not null auto_increment comment '编号',
	username varchar(50) comment '用户名',
	password varchar(100) not null comment '密码',
	name varchar(50) comment '姓名',
	gender varchar(20) comment '性别',
	birthday date comment '出生日期',
	mobile varchar(20) comment '手机号',
	email varchar(100) comment '邮箱',
	nickname varchar(50) comment '昵称',
	status varchar(20) comment '状态',
	level int(11) comment '等级',
	experience int(11) comment '经验值',
	topic_number int(11) comment '发帖数',
	fans_number int(11) comment '粉丝数量',
	concern_number int(11) comment '关注数量',
	label_ids varchar(255) comment '标签',
	register_datetime datetime comment '注册时间',
	register_ip varchar(20) comment '注册ip',
	primary key (id)
);
alter table user comment '用户';

drop table if exists category;
create table if not exists category(
	id bigint not null auto_increment comment '编号',
	name varchar(20) not null comment '名称',
	sort int comment '排序',
	primary key (id)
);
alter table category comment '类别';

drop table if exists topic;
create table if not exists topic(
	id bigint not null auto_increment comment '编号',
	category_id bigint not null comment '类别',
	user_id bigint not null comment '发帖人',
	title varchar(255) not null comment '标题',
	description varchar(255) comment '描述',
	content text not null comment '内容',
	label_ids varchar(255) comment '标签',
	status varchar(50) comment '状态',
	create_datetime datetime not null comment '发帖时间',
	last_reply_datetime datetime comment '最后回复时间',
	view_times int(11) comment '浏览量',
	ding_times int(11) comment '顶人数',
	cai_times int(11) comment '踩人数',
	primary key (id)
);
alter table topic comment '帖子';

drop table if exists reply;
create table if not exists reply(
	id bigint not null auto_increment comment '编号',
	topic_id bigint not null comment '帖子编号',
	reply_id bigint comment '回复编号',
	content text not null comment '内容',
	create_datetime datetime not null comment '回复时间',
	ding_times int(11) comment '顶人数',
	cai_times int(11) comment '踩人数',
	primary key (id)
);
alter table reply comment '回复';

drop table if exists label;
create table if not exists label(
	id bigint not null auto_increment comment '编号',
	name varchar(20) not null comment '名称',
	primary key (id)
);
alter table label comment '标签';

drop table if exists topic_label;
create table if not exists topic_label(
	id bigint not null auto_increment comment '编号',
	topic_id bigint not null comment '帖子编号',
	label_id bigint not null comment '标签编号',
	primary key (id)
);
alter table topic_label comment '帖子标签';

drop table if exists concern;
create table if not exists concern(
	id bigint not null auto_increment comment '编号',
	user_id bigint not null comment '用户编号',
	concerned_user_id bigint not null comment '被关注用户编号',
	concern_datetime datetime not null comment '关注时间',
	primary key (id)
);
alter table concern comment '关注信息';

drop table if exists concern_history;
create table if not exists concern_history(
	id bigint not null auto_increment comment '编号',
	user_id bigint not null comment '用户编号',
	concerned_user_id bigint not null comment '被关注用户编号',
	action varchar(20) not null comment '动作',
	create_datetime datetime not null comment '创建时间',
	primary key (id)
);
alter table concern_history comment '关注历史';