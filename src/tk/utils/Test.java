/**
 * 
 */
package tk.utils;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.hibernate.Hibernate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tk.core.context.SpringBeanLoader;
import tk.core.db.template.HibernateTemplateExt;
import tk.entities.active.WxActive;
import tk.function.award.service.TestThread;
import tk.weixin.core.msg.AbstractMsgHandler;
import tk.weixin.core.msg.factory.MsgHandlerFactory;

/**
 * @author toddler
 *
 */
public class Test {

	@org.junit.Test
	public void test() {

		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:Spring_Context.xml");
		
		SpringBeanLoader.setApplicationContext(context);
		
		WxActive active = HibernateTemplateExt.getInstance().get(WxActive.class, 1);
		System.out.println(active.getBegTime().compareTo(new Timestamp(new Date().getTime())));
		System.out.println(active.getBegTime() + " : " + (new Timestamp(new Date().getTime())));
		
		
	}

}
