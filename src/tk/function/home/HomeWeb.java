package tk.function.home;

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
import tk.entities.module.Module;
import tk.entities.module.ModuleCategory;
import tk.entities.org.User;
import tk.utils.StringUtils;

public class HomeWeb extends BasePage{

	public ResponseResult view(RequestEntry requestEntry) throws Exception{
		String act = requestEntry.getParameter("act");
		
		if(!StringUtils.isNullOrEmpty(act)){
			return this.info(requestEntry);
		}
		String userId = requestEntry.getUserId();
		User user = HibernateTemplateExt.getInstance().get(User.class, userId);
		requestEntry.setAttribute("user", user);

		SQL sql = SQL.begin().sql("select cat,m from ModuleCategory cat, Module m where cat.menuFlag=? and cat.moduleCategoryKey=m.moduleCategoryKey","Y")
				.sql(" and m.moduleKey in (select rm.id.moduleKey from RoleModule rm,RoleUser ru where rm.id.roleId=ru.id.roleId and ru.id.userId=?)",userId)
				.end();
		
		List<?> moduleObjsList = HibernateTemplateExt.getInstance().find(sql);
		
		Map<String,ModuleCategory> categoryMap = new HashMap<String, ModuleCategory>();
		Map<String,List<Module>> moduleMap = new HashMap<String, List<Module>>();
		
		for(Iterator<?> it=moduleObjsList.iterator();it.hasNext();){
			Object[] objs = (Object[])it.next();
			
			ModuleCategory cat = (ModuleCategory)objs[0];
			Module module = (Module)objs[1];
			
			if(!categoryMap.containsKey(cat.getModuleCategoryKey()))
				categoryMap.put(cat.getModuleCategoryKey(), cat);
			
			List<Module> moduleList = moduleMap.get(cat.getModuleCategoryKey());
			if(moduleList == null){
				moduleList = new ArrayList<Module>();
				moduleMap.put(cat.getModuleCategoryKey(), moduleList);
			}
			moduleList.add(module);
		}
		
		requestEntry.setAttribute("categoryMap", categoryMap);
		requestEntry.setAttribute("moduleMap", moduleMap);
		
		return null;
	}
	
	private ResponseResult info(RequestEntry requestEntry){
		User user = HibernateTemplateExt.getInstance().get(User.class,requestEntry.getUserId());
		requestEntry.setAttribute("user", user);

		return null;
	}
}
