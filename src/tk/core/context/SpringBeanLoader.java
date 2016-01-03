package tk.core.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

public class SpringBeanLoader {
	
	private static ApplicationContext applicationContext;
	
	public static void setApplicationContext(ApplicationContext applicationContext){
		SpringBeanLoader.applicationContext = applicationContext;
	}
	
	public static Object getBean(String beanName) throws BeansException{
		return applicationContext.getBean(beanName);
	}
}
