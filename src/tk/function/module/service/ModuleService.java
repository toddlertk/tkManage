package tk.function.module.service;

import tk.core.db.SQL;
import tk.core.db.template.HibernateRunable;
import tk.core.db.template.HibernateTemplateExt;
import tk.entities.module.Function;
import tk.entities.module.RoleFunction;
import tk.entities.module.RoleFunctionId;
import tk.entities.module.RoleModule;
import tk.entities.module.RoleModuleId;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;


public class ModuleService {
	private static ModuleService _instance = null;
	private static Object obj = new Object();
	
	public static ModuleService getInstance(){
		if(_instance == null){
			synchronized (obj) {
				if(_instance == null){
					_instance = new ModuleService();
				}
			}
		}
		return _instance;
	}
	
	public void grant(final String roleId, final String[] funcs){
		if(funcs == null)
			return;
		SQL sqlFunction = SQL.begin().sql("select o from Function o where o.functionKey ")
				.in(funcs).end();
		final List<?> functionList = HibernateTemplateExt.getInstance().find(sqlFunction);
		final Map<String,String> moduleMap = new HashMap<String, String>();
		
		for(Iterator<?> it=functionList.iterator();it.hasNext();){
			Function function = (Function)it.next();

			moduleMap.put(function.getModuleKey(), function.getModuleKey());
		}
		//final Role role = HibernateTemplateExt.getInstance().get(Role.class, roleId);
		
		HibernateTemplateExt.getInstance().runInTransaction(new HibernateRunable() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				SQL sql = SQL.begin().sql("select o from RoleFunction o where o.id.roleId=?",roleId).end();
				List<?> funcsDel = HibernateTemplateExt.getInstance().find(sql);
				for(Iterator<?> it=funcsDel.iterator();it.hasNext();){
					HibernateTemplateExt.getInstance().delete(it.next());
				}
				
				SQL sqlModule = SQL.begin().sql("select o from RoleModule o where o.id.roleId=?",roleId).end();
				List<?> modulesDel = HibernateTemplateExt.getInstance().find(sqlModule);
				for(Iterator<?> it=modulesDel.iterator();it.hasNext();){
					HibernateTemplateExt.getInstance().delete(it.next());
				}
				HibernateTemplateExt.getInstance().flush();
				/*SQL sql = SQL.begin().sql("delete from sm_role_function where role_id=?",roleId).end();
				JdbcTemplateExt.getInstance().execute(sql);
				
				SQL sqlModule = SQL.begin().sql("delete from sm_role_module where role_id=?",roleId).end();
				JdbcTemplateExt.getInstance().execute(sqlModule);*/
				
				for(Iterator<?> it=functionList.iterator();it.hasNext();){
					Function function = (Function)it.next();
					RoleFunction rf = new RoleFunction();
					
					RoleFunctionId rfId = new RoleFunctionId();
					rfId.setFunctionKey(function.getFunctionKey());
					rfId.setRoleId(roleId);
					rf.setId(rfId);
					//rf.setFunction(function);
					//rf.setRole(role);
					HibernateTemplateExt.getInstance().save(rf);
				}
				HibernateTemplateExt.getInstance().flush();
				for(Iterator<String> it=moduleMap.keySet().iterator();it.hasNext();){
					RoleModule rm = new RoleModule();
					
					RoleModuleId rmId = new RoleModuleId();
					rmId.setModuleKey(it.next());
					rmId.setRoleId(roleId);
					rm.setId(rmId);
					
					HibernateTemplateExt.getInstance().save(rm);
				}
				HibernateTemplateExt.getInstance().flush();
				return null;
			}
		});
		System.out.println("===============");
	}
}
