INSERT INTO sm_module_category(MODULE_CATEGORY_KEY,MODULE_CATEGORY_NAME,SORT,ICON,MENU_FLAG)
	VALUES('SYSTEM','系统管理',1,'fa-cogs','Y');
COMMIT;

INSERT INTO sm_module(module_key,module_category_key,module_name,url,auth_key,sort)
	VALUES('MODULEROLE','SYSTEM','模块权限设置','admin/modulerole/list/moduleRole-view.re','moduleRole',1);
COMMIT;

INSERT INTO sm_function(function_key,module_key,FUNCTION_NAME,AUTH_KEY,sort)
	VALUES('MODULEVIEW','MODULEROLE','查看权限','view',0);
INSERT INTO sm_function(function_key,module_key,FUNCTION_NAME,AUTH_KEY,sort)
	VALUES('MODULEGRANT','MODULEROLE','设置权限','grant',0);
COMMIT;

/*其他权限设置*/
INSERT INTO sm_module_category(MODULE_CATEGORY_KEY,MODULE_CATEGORY_NAME,SORT,ICON,MENU_FLAG)
	VALUES('OTHERS','其他权限设置',1,'fa-th','N');
COMMIT;

/*后台登录权限*/

INSERT INTO sm_module(module_key,module_category_key,module_name,url,auth_key,sort)
	VALUES('HOME','OTHERS','默认首页','--','home',1);
COMMIT;
INSERT INTO sm_function(function_key,module_key,FUNCTION_NAME,AUTH_KEY,sort)
	VALUES('HOMEVIEW','HOME','首页访问','view',0);
COMMIT;


