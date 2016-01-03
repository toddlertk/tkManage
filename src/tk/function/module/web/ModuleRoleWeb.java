package tk.function.module.web;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import tk.core.db.SQL;
import tk.core.db.template.HibernateTemplateExt;
import tk.core.web.BasePage;
import tk.core.web.RequestEntry;
import tk.core.web.ResponseResult;
import tk.entities.module.Function;
import tk.entities.module.Module;
import tk.entities.module.RoleFunction;
import tk.entities.org.Role;
import tk.function.module.service.ModuleService;
import tk.utils.StringUtils;

public class ModuleRoleWeb extends BasePage{

	public ResponseResult view(RequestEntry requestEntry){
		String act = requestEntry.getParameter("act");
		
		if(StringUtils.isNullOrEmpty(act)){
			//查看列表
			this.listRole(requestEntry);
		}else{
			//查看某个角色的权限列表
			this.viewPrivilege(requestEntry);
		}
		return null;
	}
	
	private ResponseResult viewPrivilege(RequestEntry requestEntry){
		String roleId = requestEntry.getParameter("roleId");
		Role role = HibernateTemplateExt.getInstance().get(Role.class, roleId);
		requestEntry.setAttribute("role", role);
		this.listCandidatePrivilege(requestEntry);
		requestEntry.setAttribute("grantedFuncMap", this.listGrantedPrivilege(roleId));
		return null;
	}
	
	public ResponseResult grant(RequestEntry requestEntry){
		String act = requestEntry.getParameter("act");
		String roleId = requestEntry.getParameter("roleId");
		Role role = HibernateTemplateExt.getInstance().get(Role.class, roleId);
		requestEntry.setAttribute("role", role);
		if(StringUtils.isNullOrEmpty(act)){
			//查看列表
			this.listRole(requestEntry);
		}else if(act.equals("grant")){
			//设置权限
			String[] funcs = requestEntry.getParameterValues("chkFuncs");
			ModuleService.getInstance().grant(roleId, funcs);
			this.viewPrivilege(requestEntry);
		}else if(act.equals("pre")){
			//打开设置权限页面
			this.listCandidatePrivilege(requestEntry);
			requestEntry.setAttribute("grantedFuncMap", this.listGrantedPrivilege(roleId));
		}
		return null;
	}

	private void listRole(RequestEntry requestEntry){
		SQL sql = new SQL("select o from Role o");
		List<?> roleList = HibernateTemplateExt.getInstance().find(sql);
		requestEntry.setAttribute("roleList", roleList);;
	}
	/**
	 * 已设置权限
	 * @param requestEntry
	 */
	private Map<String,String> listGrantedPrivilege(String roleId){
		SQL sql = SQL.begin().sql("select o from RoleFunction o where o.id.roleId=?",roleId).end();
		
		List<?> grantedFuncList = HibernateTemplateExt.getInstance().find(sql);
		Map<String,String> grantedfuncMap = new HashMap<String, String>();
		for(Iterator<?> it=grantedFuncList.iterator();it.hasNext();){
			RoleFunction rf = (RoleFunction)it.next();
			grantedfuncMap.put(rf.getId().getFunctionKey(), rf.getId().getFunctionKey());
		}
		return grantedfuncMap;
	}
	/**
	 * 查看所有候选权限
	 * @param requestEntry
	 */
	private void listCandidatePrivilege(RequestEntry requestEntry){
		// ModuleCategory
		SQL sql = new SQL("select o from ModuleCategory o");
		List<?> catList = HibernateTemplateExt.getInstance().find(sql);
		requestEntry.setAttribute("categoryList", catList);
		
		//Module
		SQL sqlModule = new SQL("select o from Module o");
		List<?> moduleObjsList = HibernateTemplateExt.getInstance().find(sqlModule);
		Map<String,List<Module>> moduleMap = new HashMap<String, List<Module>>();
		
		for(Iterator<?> it=moduleObjsList.iterator();it.hasNext();){
			Module module = (Module)it.next();
			
			List<Module> moduleList = moduleMap.get(module.getModuleCategoryKey());
			if(moduleList == null){
				moduleList = new ArrayList<Module>();
				moduleMap.put(module.getModuleCategoryKey(), moduleList);
			}
			moduleList.add(module);
		}
		requestEntry.setAttribute("moduleMap", moduleMap);
		//Function
		SQL sqlFunction = new SQL("select o from Function o");
		List<?> functionObjsList = HibernateTemplateExt.getInstance().find(sqlFunction);
		Map<String,List<Function>> functionMap = new HashMap<String, List<Function>>();
		
		for(Iterator<?> it=functionObjsList.iterator();it.hasNext();){
			Function function = (Function)it.next();
			
			List<Function> functionList = functionMap.get(function.getModuleKey());
			if(functionList == null){
				functionList = new ArrayList<Function>();
				functionMap.put(function.getModuleKey(), functionList);
			}
			functionList.add(function);
		}
		requestEntry.setAttribute("functionMap", functionMap);
		
	}

}
