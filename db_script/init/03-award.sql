
INSERT INTO sm_module_category(MODULE_CATEGORY_KEY,MODULE_CATEGORY_NAME,SORT,ICON,MENU_FLAG)
	VALUES('AWARD','抽奖',4,'fa-table','Y');
COMMIT;

INSERT INTO sm_module(module_key,module_category_key,module_name,url,auth_key,sort)
	VALUES('AWARD_CONF','AWARD','抽抽抽奖咯','admin/award/view/award-doAward.od','award',1);
COMMIT;

INSERT INTO sm_function(function_key,module_key,FUNCTION_NAME,AUTH_KEY,sort)
	VALUES('AWARD_CONF','AWARD_CONF','抽吧','doAward',0);
COMMIT;


INSERT INTO sm_module_category(MODULE_CATEGORY_KEY,MODULE_CATEGORY_NAME,SORT,ICON,MENU_FLAG)
	VALUES('ACITVE','节目配置',3,'fa-table','Y');
COMMIT;

INSERT INTO sm_module(module_key,module_category_key,module_name,url,auth_key,sort)
	VALUES('ACITVE_CONF','ACITVE','配置','admin/active/list/active-create.od?act=list','active',1);
COMMIT;

INSERT INTO sm_function(function_key,module_key,FUNCTION_NAME,AUTH_KEY,sort)
	VALUES('NEW_ACITVE_CONF','ACITVE_CONF','新建','create',0);
COMMIT;

INSERT INTO sm_module(module_key,module_category_key,module_name,url,auth_key,sort)
	VALUES('ACITVE_SCORE','ACITVE','查看分数','admin/active/listScore/active-read.od','active',1);
COMMIT;

INSERT INTO sm_function(function_key,module_key,FUNCTION_NAME,AUTH_KEY,sort)
	VALUES('NEW_ACITVE_SCORE','ACITVE_SCORE','查看','read',0);
COMMIT;


