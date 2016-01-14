/**
 * 
 */
package tk.utils;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import tk.core.context.SpringBeanLoader;
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

		/*
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:Spring_Context.xml");
		
		SpringBeanLoader.setApplicationContext(context);*/
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			System.out.println(sdf.parse("19700101").getTime());
			System.out.println(sdf.parse("20151119").getTime() - sdf.parse("19501230").getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		TestThread thread = new TestThread();
		thread.start();
	}

}
