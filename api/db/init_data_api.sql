delete from api_info where id=1;
insert into api_info (id,name,module,resource,method,invoke_method,description)
	values (1,'登录','sys','user','login','sysUserService.login','登录管理系统');

delete from api_input where api_id=1;
insert into api_input (id,api_id,name,label,data_type,sort) values (1,1,'username','用户名','java.lang.String',10);
insert into api_input (id,api_id,name,label,data_type,sort) values (2,1,'password','密码','java.lang.String',20);
-- insert into api_input (id,api_id,name,label,data_type,sort) values (3,1,'validateCode','验证码','java.lang.String',30);

delete from api_input_validator where input_id in (1,2);
insert into api_input_validator (input_id,rule,message,sort) values (1,'required','用户名不能为空',10);
insert into api_input_validator (input_id,rule,message,sort) values (1,'minLength=3','用户名长度需大于等于3位',20);
insert into api_input_validator (input_id,rule,message,sort) values (1,'maxLength=20','用户名长度需小于等于20位',30);
insert into api_input_validator (input_id,rule,message,sort) values (2,'required','密码不能为空',10);
insert into api_input_validator (input_id,rule,message,sort) values (2,'minLength=6','密码长度需大于等于6位',20);
insert into api_input_validator (input_id,rule,message,sort) values (2,'maxLength=50','密码长度需小于等于50位',30);

delete from api_output where api_id=1;
insert into api_output (id,api_id,name,label,data_type,sort) values (1,1,'message','说明','java.lang.String',10);
insert into api_output (id,api_id,name,label,data_type,sort) values (2,1,'token','token','java.lang.String',20);

delete from api_info where id=2;
insert into api_info (id,name,module,resource,method,invoke_method,description)
	value(2,'新增用户','sys','user','add','sysUserService.add','新增用户');

delete from api_input where api_id=2;
insert into api_input (id,api_id,name,label,data_type,sort) value (4,2,'token','token','java.lang.String',10);
insert into api_input (id,api_id,name,label,data_type,sort) value (5,2,'username','用户名','java.lang.String',20);
insert into api_input (id,api_id,name,label,data_type,sort) value (6,2,'password','密码','java.lang.String',30);
insert into api_input (id,api_id,name,label,data_type,sort) value (7,2,'name','姓名','java.lang.String',40);
insert into api_input (id,api_id,name,label,data_type,sort) value (8,2,'mobile','手机号','java.lang.String',50);

delete from api_input_validator where input_id=4;
insert into api_input_validator (input_id,rule,message,sort) values (4,'token','登录超时，请重新登录',10);

delete from api_input_validator where input_id=5;
insert into api_input_validator (input_id,rule,message,sort) values (5,'required','用户名不能为空',10);
insert into api_input_validator (input_id,rule,message,sort) values (5,'minLength=3','用户名长度需大于等于3位',20);
insert into api_input_validator (input_id,rule,message,sort) values (5,'maxLength=20','用户名长度需小于等于20位',30);

delete from api_input_validator where input_id=6;
insert into api_input_validator (input_id,rule,message,sort) values (6,'required','密码不能为空',10);
insert into api_input_validator (input_id,rule,message,sort) values (6,'minLength=6','密码长度需大于等于6位',20);
insert into api_input_validator (input_id,rule,message,sort) values (6,'maxLength=50','密码长度需小于等于50位',30);

delete from api_input_validator where input_id=7;
insert into api_input_validator (input_id,rule,message,sort) values (7,'minLength=2','姓名长度需大于等于2位',10);
insert into api_input_validator (input_id,rule,message,sort) values (7,'maxLength=10','姓名长度需小于等于10位',20);

delete from api_input_validator where input_id=8;
insert into api_input_validator (input_id,rule,message,sort) values (8,'length=11','手机号长度需等于11位',10);

delete from api_output where api_id=2;
insert into api_output (id,api_id,name,label,data_type,sort) values (3,2,'message','说明','java.lang.String',10);
insert into api_output (id,api_id,name,label,data_type,sort) values (4,2,'token','token','java.lang.String',20);

delete from api_info where id in (3,4,5,6,7);
insert into api_info (id,name,module,resource,method,invoke_method,description)
	values (3,'添加接口','api','apiInfo','add','apiInfoService.add','添加接口');
insert into api_info (id,name,module,resource,method,invoke_method,description)
	values (4,'配置入参','api','apiInput','config','apiInputService.config','配置接口入参');
insert into api_info (id,name,module,resource,method,invoke_method,description)
	values (5,'配置出参','api','apiOutput','config','apiOutputService.config','配置接口出参');
insert into api_info (id,name,module,resource,method,invoke_method,description)
	values (6,'配置入参校验规则','api','apiInputValidator','config','apiInputValidatorService.config','配置接口入参校验规则');
insert into api_info (id,name,module,resource,method,invoke_method,description)
	values (7,'配置出参转换器','api','apiOutputFormat','config','apiOutputFormatService.config','配置接口出参转换器');

delete from api_input where api_id=3;
insert into api_input (id,api_id,name,label,data_type,sort) value (9,3,'token','token','java.lang.String',10);
insert into api_input (id,api_id,name,label,data_type,sort) value (10,3,'name','名称','java.lang.String',20);
insert into api_input (id,api_id,name,label,data_type,sort) value (11,3,'module','模块','java.lang.String',30);
insert into api_input (id,api_id,name,label,data_type,sort) value (12,3,'resource','资源','java.lang.String',40);
insert into api_input (id,api_id,name,label,data_type,sort) value (13,3,'method','方法','java.lang.String',50);
insert into api_input (id,api_id,name,label,data_type,sort) value (14,3,'invoke_method','调用方法','java.lang.String',60);
insert into api_input (id,api_id,name,label,data_type,sort) value (15,3,'description','说明','java.lang.String',70);

delete from api_input where api_id=4;
insert into api_input (id,api_id,name,label,data_type,sort) value (16,4,'token','token','java.lang.String',10);
insert into api_input (id,api_id,name,label,data_type,sort) value (17,4,'apiId','接口编号','java.lang.Long',20);
insert into api_input (id,api_id,name,label,data_type,sort) value (18,4,'inputs','入参','java.lang.String',30);

-- user.user.register.input.inputs
--
