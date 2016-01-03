package tk.security.sha;

import java.security.MessageDigest;
import java.text.ParseException;

import tk.core.exception.ExceptionCreator;

public class SHAHelper {
	
	private static final String SHA = "SHA";
	
	public static String getSHACode(String originalString){
		try{
			if(originalString == null || originalString.length() == 0)
				throw ExceptionCreator.create("original String is null or empty,SHAHelper.getSHACode");;
			byte[] originalByte = originalString.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance(SHA);
			md.update(originalByte);
			
			originalByte = md.digest();
			
			String rtv = "";
			for(int i=0;i<originalByte.length;i++){
				int temp = originalByte[i] & 0xFF;
				String s = Integer.toHexString(temp);
				if(s.length() == 1)
					s += "0";
				rtv += s;
			}
			return rtv;
		}catch(Exception ex){
			throw ExceptionCreator.create(ex);
		}
	}

	public static void main(String[] args) throws ParseException{
		System.out.print(SHAHelper.getSHACode("adminadmin"));
	}
}
