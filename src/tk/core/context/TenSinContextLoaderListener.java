package tk.core.context;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class TenSinContextLoaderListener extends ContextLoaderListener{

	public void contextInitialized(ServletContextEvent event){
		super.contextInitialized(event);
		SpringBeanLoader.setApplicationContext(
				WebApplicationContextUtils.getWebApplicationContext(event.getServletContext())
		);
	}
}
