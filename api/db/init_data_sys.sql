delete from sys_role where id=1;
insert into sys_role (id,name_en,name_cn) values (1,'superadmin','超级管理员');

delete from sys_user where username='superadmin';
insert into sys_user (username,password,status,role_id) values ('superadmin','superadmin','normal',1);

delete from sys_role_api where role_id=1;
insert into sys_role_api (role_id,api_id) values (1,1);
insert into sys_role_api (role_id,api_id) values (1,2);
insert into sys_role_api (role_id,api_id) values (1,3);

delete from sys_dict where type='data_type_java';
insert into sys_dict (value,label,type,sort) values ('java.lang.Integer','整型','data_type_java',10);
insert into sys_dict (value,label,type,sort) values ('java.lang.Long','长整型','data_type_java',20);
insert into sys_dict (value,label,type,sort) values ('java.lang.String','字符型','data_type_java',30);
insert into sys_dict (value,label,type,sort) values ('java.lang.Date','日期','data_type_java',40);

delete from sys_dict where type='sys_user_status';
insert into sys_dict (value,label,type,sort) values ('normal','正常','sys_user_status',10);
insert into sys_dict (value,label,type,sort) values ('disable','禁用','sys_user_status',20);