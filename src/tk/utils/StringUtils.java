package tk.utils;

public class StringUtils {
	public static boolean isNullOrEmpty(String str){
		if(str == null)
			return true;
		else if("".equals(str))
			return true;
		else
			return false;
	}
}
