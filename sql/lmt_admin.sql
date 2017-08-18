create table lmt_admin(
	id int(11) unsigned not null auto_increment primary key comment '主键自增id',
	username varchar(30) not null default '' comment '用户名',
	password char(32) character set latin1 not null default '' comment '密码',
	salt varchar(6) character set latin1 not null default '' comment '盐',
	email varchar(50) character set latin1 not null default '' comment '邮箱',
	phone_num varchar(18) character set latin1 not null default '' comment '手机号',
	realname varchar(10) not null default '' comment '真实姓名',
	status tinyint(4) not null default 1 comment '状态：0删除，1正常',
	unique key username (username) using btree
)engine=InnoDB default charset=UTF8;

create table lmt_role(
	id int(11) unsigned not null auto_increment primary key comment '主键自增id',
	name varchar(50) not null default '' comment '角色名',
	description varchar(1024) not null default '' comment '角色描述',
	status tinyint(4) not null default 1 comment '状态：0删除，1正常',
	unique key name (name) using btree
)engine=InnoDB default charset=UTF8;

create table lmt_resource(
	id int(11) unsigned not null auto_increment primary key comment '主键自增id',
	pid int(11) unsigned not null default 0 comment '上级资源id',
	name varchar(50) not null default '' comment '资源名',
	type varchar(10) character set latin1 not null default '' comment '资源类型',
	c_key varchar(128) character set latin1 not null default '' comment '资源id',
	url varchar(128) character set latin1 not null default '' comment '资源链接',
	return_type varchar(10) character set latin1 not null default '' comment '返回值类型',
	return_value varchar(1024) not null default '' comment '认证失败返回值',
	developer varchar(30) not null default '' comment '开发者',
	description varchar(1024) not null default '' comment '资源描述',
	status tinyint(4) not null default 1 comment '状态：0删除，1正常',
	unique key c_key (c_key) using btree,
	unique key url (url) using btree,
	unique key name (name) using btree,
	key pid (pid) using btree
)engine=InnoDB default charset=UTF8;

create table lmt_admin_role(
	id int(11) unsigned not null auto_increment primary key comment '主键自增id',
	admin_id int(11) unsigned not null default 0 comment '管理员id',
	role_id int(11) unsigned not null default 0 comment '角色id',
	status tinyint(4) not null default 1 comment '状态：0删除，1正常',
	unique key admin_id_role_id (admin_id,role_id) using btree,
	key role_id (role_id) using btree
)engine=InnoDB default charset=UTF8;

create table lmt_role_resource(
	id int(11) unsigned not null auto_increment primary key comment '主键自增id',
	role_id int(11) unsigned not null default 0 comment '角色id',
	resource_id int(11) unsigned not null default 0 comment '资源id',
	status tinyint(4) not null default 1 comment '状态：0删除，1正常',
	unique key role_id_resource_id (role_id,resource_id) using btree,
	key resource_id (resource_id) using btree
)engine=InnoDB default charset=UTF8;
