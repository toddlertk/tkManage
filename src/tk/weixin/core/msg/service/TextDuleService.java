package tk.weixin.core.msg.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import tk.core.db.template.HibernateTemplateExt;
import tk.entities.active.WxActive;
import tk.entities.msg.MsgText;
import tk.entities.org.DepartmentUser;
import tk.weixin.core.msg.MsgContrant;

public class TextDuleService {
	
	private static Object obj = new Object();
	private static TextDuleService _instance = null;
	
	private static List<DepartmentUser> listDepart = new ArrayList<DepartmentUser>();
	
	public static TextDuleService getInstance(){
		if(_instance == null){
			synchronized (obj) {
				if(_instance == null){
					_instance = new TextDuleService();
				}
			}
		}
		return  _instance;
	}
	
	public String duleContent(String content, String fromUserName, Object toUserName, MsgText text){
		
		String time = String.valueOf(new Date().getTime());  
		String textTpl = "<xml>"+  
				"<ToUserName><![CDATA[%1$s]]></ToUserName>"+  
				"<FromUserName><![CDATA[%2$s]]></FromUserName>"+  
				"<CreateTime>%3$s</CreateTime>"+  
				"<MsgType><![CDATA[%4$s]]></MsgType>"+  
				"<Content><![CDATA[%5$s]]></Content>"+  
				"<FuncFlag>0</FuncFlag>"+  
				"</xml>";         
		String resultStr = null;
		String contentStr = null;
		
		if(null!=content&&!content.equals("")){  
			if(content.equals("dj")){
				String url = "<a href=\"http://120.24.63.30/TKManage/ph/depart/tk2y-kd92h.od?openId=" + fromUserName + "\">";
				contentStr = "请" + url + "点击此处</a>进入微信与部门工号绑定，想要参与抽奖吗，想要神秘大奖吗？那就赶紧来动动手指"
						+ url + "进来绑定</a>吧~";  
			}else if(content.equals("cx")){
				String url = "<a href=\"http://120.24.63.30/TKManage/ph/depart/tk2y-kd92h.od?openId=" + fromUserName + "\">";
				contentStr = "请" + url + "点击此处</a>进入微信与部门工号绑定，想要参与抽奖吗，想要神秘大奖吗？那就赶紧来动动手指"
						+ url + "进来绑定</a>吧~";  
			}else{
				String []s = content.split("#");
				if(s.length == 2){
					try{
						WxActive active = HibernateTemplateExt.getInstance().get(WxActive.class, s[0]);
						
						
					}catch(Exception e){
						e.printStackTrace();
						contentStr = "年会君get不到哇~,请重新再来一次/:-";
					}
				}else{
					contentStr = "年会君get不到哇~";
				}
				 
			}
			resultStr = String.format(textTpl, fromUserName, toUserName, time, text.getMsgType(), contentStr);  
		}
		text.setContent(contentStr);
		text.setTableMsgId(UUID.randomUUID().toString().replace("-", ""));
		text.setContentType(MsgContrant.MSG_CONTENT_TYPE_SEND);
		HibernateTemplateExt.getInstance().save(text);
		
		return resultStr;
	}
}
