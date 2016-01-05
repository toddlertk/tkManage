
INSERT INTO sm_module_category(MODULE_CATEGORY_KEY,MODULE_CATEGORY_NAME,SORT,ICON,MENU_FLAG)
	VALUES('DEPART','部门配置',3,'fa-table','N');
COMMIT;

INSERT INTO sm_module(module_key,module_category_key,module_name,url,auth_key,sort)
	VALUES('DEPART_CONF','DEPART','配置','admin/depart/list/tk2y-kd92h.od','tk2y',1);
COMMIT;

INSERT INTO sm_function(function_key,module_key,FUNCTION_NAME,AUTH_KEY,sort)
	VALUES('NEW_DEPART_CONF','DEPART_CONF','新建','kd92h',0);
COMMIT;

INSERT INTO SM_DEPARTMENT VALUE('1000001' , '系统部');
INSERT INTO SM_DEPARTMENT VALUE('1000002' , '综合部');
INSERT INTO SM_DEPARTMENT VALUE('1000003' , '决策');
