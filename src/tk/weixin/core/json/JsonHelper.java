package tk.weixin.core.json;

import org.json.JSONArray;
import org.json.JSONObject;


public class JsonHelper {

	public String getStrByJson(String json , String name) throws Exception{
	     System.out.println("===========" + json.toString());
		JSONObject obj = new JSONObject(json.toString());  
		JSONArray obj2 = obj.names();
		for (int i = 0; i < obj2.length() ;  i++) {  
		      System.out.println("===========" + obj2.getString(i));
		      Object temp = obj.get(obj2.getString(i));  
		      if(obj2.getString(i).equals( name)){
		    	  return (String)temp;
		      }
		}
		return json;
	}

    public static void main(String[] args) {  
    	String s = "{\"access_token\":\"bgx0wZx83T1UpiB7nIm4Azk1TVo5EOl-xR68Q_I3QC1QaNF98WGZZ_mHe0k5fNdHBiXwkumtPN8bJGybRFMN6Hd9LHmVfFDugD_TnC2WTjgXYEcACADEK\",\"expires_in\":7200} ";
    	JsonHelper helper = new JsonHelper();
    	try {
			System.out.println(helper.getStrByJson(s, "access_token"));
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
    	
    }
	
	
}
