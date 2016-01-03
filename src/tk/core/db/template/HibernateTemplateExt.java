package tk.core.db.template;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import tk.core.context.SpringBeanLoader;
import tk.core.db.SQL;

public class HibernateTemplateExt extends HibernateTemplate{

	public HibernateTemplateExt(){}
	public HibernateTemplateExt(SessionFactory sessionFactory){
		super(sessionFactory);
	}
	public void setSessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	
	public static HibernateTemplateExt getInstance(){
		return (HibernateTemplateExt)SpringBeanLoader.getBean("hibernateTemplate");
	}

	public List<?> find(SQL sqlObj){
		return this.find(sqlObj.getText(), sqlObj.getParamsArray());
	}
	
	public int findSize(SQL sqlObj){
		int size = -1;
		List<?> list = this.find(sqlObj.getText(), sqlObj.getParamsArray());
		if(list != null){
			size = list.size();
		}
		return size;
	}
	
	public Object findFirst(SQL sqlObj){
		List<?> list =this.findPart(sqlObj, 1, 1);
		if(list == null ||list.size() == 0)
			return null;
		else
			return list.get(0);
	}
	
	public List<?> findPart(final SQL sqlObj,int startNo,int pageCount){
		final int firstResult = startNo - 1;
		final int maxResults = pageCount;
		return (List<?>)this.execute(new HibernateCallback<Object>() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = createQuery(session, sqlObj);
				if(firstResult > 0)
					query.setFirstResult(firstResult);
				if(maxResults > 0)
					query.setMaxResults(maxResults);
				return query.list();
			}
		});
		
	}
	
	public Object runInTransaction(final HibernateRunable callback) {

		PlatformTransactionManager transactionManager = (PlatformTransactionManager) SpringBeanLoader
				.getBean("transactionManager");
		TransactionTemplate transactionTemplate = new TransactionTemplate(
				transactionManager);
		return transactionTemplate.execute(new TransactionCallback<Object>() {
			public Object doInTransaction(TransactionStatus status) {
				Session session = getSession();
				Object ret = callback.doInHibernate(session);
				return ret;
			}
		});
	}
	
	private Query createQuery(Session session,SQL sqlObj){
		Query queryObject = session.createQuery(sqlObj.getText());
		prepareQuery(queryObject);
		Object[] values = sqlObj.getParamsArray();
		if (values != null) {
			for (int i=0; i < values.length; i++) {
				queryObject.setParameter(i, values[i]);
			}
		}
		return queryObject;
	}
}
