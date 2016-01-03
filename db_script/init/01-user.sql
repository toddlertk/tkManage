
/*ROLE*/
INSERT INTO sm_role(role_id,role_name) 
	VALUES('R0000ADMIN','超级管理员');
COMMIT;

INSERT INTO sm_role_module(role_id,module_key)
	VALUES('R0000ADMIN','MODULEROLE');
COMMIT;

INSERT INTO sm_role_function(role_id,FUNCTION_KEY)
	VALUES('R0000ADMIN','MODULEVIEW');
INSERT INTO sm_role_function(role_id,FUNCTION_KEY)
	VALUES('R0000ADMIN','MODULEGRANT');
COMMIT;
INSERT INTO sm_role_module(role_id,module_key)
	VALUES('R0000ADMIN','HOME');
COMMIT;
INSERT INTO sm_role_function(role_id,FUNCTION_KEY)
	VALUES('R0000ADMIN','HOMEVIEW');
COMMIT;


/*User*/
INSERT INTO sm_user(`user_id`,`User_name`,`User_pws`,`User_status`)
	VALUES('admin','超级管理员','dd94709528bb1c83d08f3088d4403f4742891f4f',0);
COMMIT;


/** 用户 **/
INSERT INTO sm_module_category(MODULE_CATEGORY_KEY,MODULE_CATEGORY_NAME,SORT,ICON,MENU_FLAG)
	VALUES('USER','用户管理',3,'fa-table','Y');
COMMIT;

INSERT INTO sm_module(module_key,module_category_key,module_name,url,auth_key,sort)
	VALUES('USERLIST','USER','用户列表','admin/user/list/user-create.re?act=list','user',1);
COMMIT;

INSERT INTO sm_function(function_key,module_key,FUNCTION_NAME,AUTH_KEY,sort)
	VALUES('NEWUSER','USERLIST','新建用户','create',0);
COMMIT;
INSERT INTO sm_function(function_key,module_key,FUNCTION_NAME,AUTH_KEY,sort)
	VALUES('READUSER','USERLIST','查看','read',0);
COMMIT;
INSERT INTO sm_function(function_key,module_key,FUNCTION_NAME,AUTH_KEY,sort)
	VALUES('EDITUSER','USERLIST','修改','edit',0);
COMMIT;
INSERT INTO sm_function(function_key,module_key,FUNCTION_NAME,AUTH_KEY,sort)
	VALUES('DEL_USER','USERLIST','删除','delete',0);
COMMIT;


INSERT INTO `sm_role_user`(`ROLE_ID`,`USER_ID`)
VALUES ('R0000ADMIN','admin');

/*******角色管理************/

INSERT INTO sm_module(module_key,module_category_key,module_name,url,auth_key,sort)
	VALUES('ROLELIST','SYSTEM','角色管理','admin/role/list/role-list.re?act=list','role',1);
COMMIT;

INSERT INTO sm_function(function_key,module_key,FUNCTION_NAME,AUTH_KEY,sort)
	VALUES('ROLE_NEW','ROLELIST','新建角色','create',0);
COMMIT;
INSERT INTO sm_function(function_key,module_key,FUNCTION_NAME,AUTH_KEY,sort)
	VALUES('ROLE_LIST','ROLELIST','查看角色','list',0);
COMMIT;
INSERT INTO sm_function(function_key,module_key,FUNCTION_NAME,AUTH_KEY,sort)
	VALUES('ROLE_MANA','ROLELIST','删改角色','edit',0);
COMMIT;


INSERT INTO sm_module(module_key,module_category_key,module_name,url,auth_key,sort)
	VALUES('ROLEALLOT','SYSTEM','角色分配','admin/role/rulist/role-rulist.re','role',1);
COMMIT;

INSERT INTO sm_function(function_key,module_key,FUNCTION_NAME,AUTH_KEY,sort)
	VALUES('ROLE_N_ALLOT','ROLEALLOT','分配角色','allot',0);
COMMIT;
INSERT INTO sm_function(function_key,module_key,FUNCTION_NAME,AUTH_KEY,sort)
	VALUES('ROLE_R_ALLOT','ROLEALLOT','查看角色','rulist',0);
COMMIT;
