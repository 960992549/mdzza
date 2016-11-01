drop table if exists user;
create table if not exists user(
	id bigint(20) not null auto_increment comment '编号',
	username varchar(50) comment '用户名',
	password varchar(100) not null comment '密码',
	name varchar(50) comment '姓名',
	sale varchar(20) comment '性别',
	date_of_birth date comment '出生日期',
	mobile varchar(20) comment '手机号',
	email varchar(100) comment '邮箱',
	nickname varchar(50) comment '昵称',
	level int(11) null comment '等级',
	experience int(11) comment '经验值',
	topic_number int(11) comment '发帖数',
	fans_number int(11) comment '粉丝数量',
	concern_number int(11) comment '关注数量',
	label_ids varchar(255) comment '标签',
	primary key (id)
);
alter table user comment '用户';

drop table if exists topic;
create table if not exists topic(
	id bigint(20) not null auto_increment comment '编号',
	user_id bigint(20) not null comment '发帖人',
	name varchar(255) not null comment '名称',
	content text not null comment '内容',
	description varchar(255) comment '描述',
	category_id bigint(20) comment '类别',
	label_ids varchar(255) comment '标签',
	status varchar(50) comment '状态',
	create_datetime datetime not null comment '发帖时间',
	last_reply_datetime datetime comment '最后回复时间',
	view_times int(11) comment '浏览量',
	zan_times int(11) comment '点赞数',
	primary key (id)
);
alter table topic comment '帖子';

drop table if exists reply;
create table if not exists reply(
	id bigint(20) not null auto_increment comment '编号',
	topic_id bigint(20) not null comment '帖子编号',
	reply_id bigint(20) comment '回复编号',
	content text not null comment '内容',
	create_datetime datetime not null comment '回复时间',
	agree_times int(11) comment '赞成数量',
	disagree_times int(11) comment '不赞成数量',
	primary key (id)
);
alter table reply comment '回复';