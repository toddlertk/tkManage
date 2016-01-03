package tk.core.reflect;

import java.lang.reflect.Method;

public class MethodInvocator {
	public static Object invokeMethod(Object targetObj,String methodName,Object args){
		try{
			Method method = getMethod(targetObj.getClass(),methodName,args);
			return method.invoke(targetObj, args);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	static Method getMethod(Class<? extends Object> targetClass,String methodName,Object args){
		try{
			/*if(args!=null && args.length>0)
				return targetClass.getMethod(methodName,args);
			else
				*/return targetClass.getMethod(methodName);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
