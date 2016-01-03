package tk.core.db.template;

import org.hibernate.HibernateException;
import org.hibernate.Session;

public interface HibernateRunable {
	public Object doInHibernate(Session session) throws HibernateException;
}
