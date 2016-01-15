package tk.weixin.core.msg.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import tk.core.db.SQL;
import tk.core.db.template.HibernateTemplateExt;
import tk.entities.active.WxActive;
import tk.entities.active.WxActiveScore;
import tk.entities.msg.MsgText;
import tk.entities.org.DepartmentUser;
import tk.weixin.core.msg.MsgContrant;

public class TextDuleService {
	
	private static Object obj = new Object();
	private static TextDuleService _instance = null;
	
	private static Map<String , DepartmentUser> mapDepart = new HashMap<String , DepartmentUser>();
	private static Map<Integer , WxActive> mapActive = new TreeMap<Integer , WxActive>();
	
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
	
	public TextDuleService(){
		List<DepartmentUser> listDepart = (List<DepartmentUser>)HibernateTemplateExt.getInstance().find(new SQL("from DepartmentUser"));
		for(DepartmentUser depart : listDepart){
			mapDepart.put(depart.getOpenId(), depart);
		}
		List<WxActive> listActive = (List<WxActive>)HibernateTemplateExt.getInstance().find(new SQL("from WxActive"));
		for(WxActive active : listActive){
			mapActive.put(active.getActiveIndex(), active);
		}
	}
	
	public void updateDepart(DepartmentUser depart){
		mapDepart.put(depart.getOpenId(), depart);
	}

	public void updateActive(WxActive active){
		mapActive.put(active.getActiveIndex(), active);
	}
	public String duleContent(String content, String fromUserName, String toUserName, MsgText text){
		
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
						+ url + "进来绑定</a>吧/玫瑰";  
			}else if(mapDepart.get(fromUserName) == null){
				String url = "<a href=\"http://120.24.63.30/TKManage/ph/depart/tk2y-kd92h.od?openId=" + fromUserName + "\">";
				contentStr = "微信君读不到您的信息，请" + url + "点击此处</a>进入微信与部门工号绑定/玫瑰"; 
			}else if(content.equals("cx")){
				contentStr = "";
				StringBuffer strbuf = new StringBuffer();
				SQL sql = SQL.begin().sql("from WxActiveScore a where a.openId=?" , fromUserName).end();
				List<?> list = HibernateTemplateExt.getInstance().find(sql);
				Map<Integer , Integer> mapScore = new HashMap<Integer , Integer>();
				if(list != null && list.size() > 0){
					for(Object obj : list){
						WxActiveScore score = (WxActiveScore)obj;
						mapScore.put(score.getActiveIndex(), score.getScore());
					}
				}
				for(Iterator<WxActive> it = mapActive.values().iterator() ; it.hasNext() ;){
					WxActive active = it.next();
					strbuf.append(active.getActiveIndex() + ":" + active.getActiveName());
					if(mapScore.containsKey(active.getActiveIndex())){
						strbuf.append(";投票:" + mapScore.get(active.getActiveIndex()));
					}
					strbuf.append("\n");
				}
				contentStr = strbuf.toString();
				
			}else{
				String []s = content.split("#");
				if(s.length == 2){
					try{
						int index = Integer.valueOf(s[0]) , iScore = Integer.valueOf(s[1]);
						WxActive active = mapActive.get(index);
						if(active == null){
							contentStr = "微信君get不到这个互动~,请检查一下活动ID/玫瑰";
						}else if(active.getBegTime().compareTo(new Timestamp(new Date().getTime())) > 0){
							contentStr = "这个节目暂未开演，请开演后再投票/玫瑰";
						}else if(active.getEndTime().compareTo(new Timestamp(new Date().getTime())) < 0){
							contentStr = "这个节目投票已经结束，不再接受投票/玫瑰";
						}else if(iScore < 1 || iScore > 10){
							contentStr = "微信君处理不了\""+ iScore + "\"，请输入1~10;/调皮";
						}else{
							WxActiveScore score = new WxActiveScore();
							SQL sql = SQL.begin().sql("from WxActiveScore a where a.activeIndex=? and a.openId=?" ,s[0] , fromUserName).end();
							List<?> list = HibernateTemplateExt.getInstance().find(sql);
							if(list != null && list.size() > 0){
								score = (WxActiveScore)list.get(0);
								score.setActiveIndex(active.getActiveIndex());
								score.setCreateTime(new Timestamp(new Date().getTime()));
								score.setDepartmentId(mapDepart.get(fromUserName).getDepartmentId());
								score.setOpenId(fromUserName);
								score.setScore(iScore);
								score.setScoreText(content);
								HibernateTemplateExt.getInstance().update(score);
							}else{
								score.setActiveIndex(active.getActiveIndex());
								score.setCreateTime(new Timestamp(new Date().getTime()));
								score.setDepartmentId(mapDepart.get(fromUserName).getDepartmentId());
								score.setOpenId(fromUserName);
								score.setScore(iScore);
								score.setScoreText(content);
								HibernateTemplateExt.getInstance().save(score);
							}
							contentStr = "投票成功！\n节目名称：" + active.getActiveName() + "\n投票分数：" + score.getScore();
						}
						
					}catch(Exception e){
						e.printStackTrace();
						contentStr = "微信君get不到哇~,请重新再来一次/呲牙";
					}
				}else{
					contentStr = "微信君get不到哇~";
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
