package tk.core.web.filters;

import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.CleanupFailureDataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.support.OpenSessionInViewFilter;

public class TenSinOpenSessionInViewFilter extends OpenSessionInViewFilter {

	public TenSinOpenSessionInViewFilter() {
	}

	protected Session getSession(SessionFactory sessionFactory)
			throws DataAccessResourceFailureException {
		Session session = SessionFactoryUtils.getSession(sessionFactory, true);
		session.setFlushMode(FlushMode.AUTO);
		return session;
	}

	protected void closeSession(Session session, SessionFactory sessionFactory)
			throws CleanupFailureDataAccessException {
		if (session != null && session.isOpen() && session.isConnected()) {
			try {
				session.flush();
			} catch (HibernateException e) {
				e.printStackTrace();
				SessionFactoryUtils.releaseSession(session, sessionFactory);
				throw new CleanupFailureDataAccessException(
						"Failed to flush session before close: "
								+ e.getMessage(), e);
			}
		}
		super.closeSession(session, sessionFactory);
	}
}