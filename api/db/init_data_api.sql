delete from api_info where id=1;
insert into api_info (id,name,module,resource,method,invoke_method,description)
	values (1,'登录','sys','user','login','sysUser.login','登录管理系统');

delete from api_input where api_id=1;
insert into api_input (id,api_id,name,label,sort) values (1,1,'username','用户名',10);
insert into api_input (id,api_id,name,label,sort) values (2,1,'password','密码',20);
-- insert into api_input (id,api_id,name,label,sort) values (3,1,'validateCode','验证码',30);

delete from api_input_validator where input_id in (1,2);
insert into api_input_validator (input_id,rule,message,sort) values (1,'is not null','用户名不能为空',10);
insert into api_input_validator (input_id,rule,message,sort) values (1,'length >= 3','用户名长度需大于等于3位',20);
insert into api_input_validator (input_id,rule,message,sort) values (1,'length <= 20','用户名长度需小于等于20位',30);
insert into api_input_validator (input_id,rule,message,sort) values (2,'is not null','密码不能为空',10);
insert into api_input_validator (input_id,rule,message,sort) values (2,'length >= 6','密码长度需大于等于6位',20);
insert into api_input_validator (input_id,rule,message,sort) values (2,'length <= 50','密码长度需小于等于50位',30);

delete from api_output where api_id=1;
insert into api_output (id,api_id,name,label,sort) values (1,1,'code','状态',10);
insert into api_output (id,api_id,name,label,sort) values (2,1,'token','令牌',20);
insert into api_output (id,api_id,name,label,sort) values (3,1,'message','说明',30);