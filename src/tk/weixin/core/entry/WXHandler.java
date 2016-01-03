package tk.weixin.core.entry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import tk.weixin.core.msg.AbstractMsgHandler;
import tk.weixin.core.msg.factory.MsgHandlerFactory;

public class WXHandler {
	final String TOKEN = "toddler";
	final HttpServletRequest final_request ;
	final HttpServletResponse final_response ;

	public WXHandler(HttpServletRequest request , HttpServletResponse response){

		final_request = request;
		final_response = response;
	}

	public void valid() {
		String echostr = final_request.getParameter("echostr");
		if(null==echostr||echostr.isEmpty()){  
			responseMsg();  
		}else{
			if (this.checkSignature()) {
				this.print(echostr);
			} else {
				this.print("error");
			}
		}
		String url = "https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=" + TOKEN;
	}
	
	public boolean checkSignature() {
		String signature = final_request.getParameter("signature");
		String timestamp = final_request.getParameter("timestamp");
		String nonce = final_request.getParameter("nonce");
		String token = TOKEN;
		String[] tmpArr = { token, timestamp, nonce };
		Arrays.sort(tmpArr);
		String tmpStr = this.ArrayToString(tmpArr);
		tmpStr = this.SHA1Encode(tmpStr);
		if (tmpStr.equalsIgnoreCase(signature)) {
			return true;
		} else {
			return false;
		}
	}

	public void print(String content) {
		try {
			final_response.getWriter().print(content);
			final_response.getWriter().flush();
			final_response.getWriter().close();
		} catch (Exception e) {

		}
	}

	public String ArrayToString(String[] arr) {
		StringBuffer bf = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			bf.append(arr[i]);
		}
		return bf.toString();
	}

	public String SHA1Encode(String sourceString) {
		String resultString = null;
		try {
			resultString = new String(sourceString);
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			resultString = byte2hexString(md.digest(resultString
					.getBytes()));
		} catch (Exception ex) {

		}
		return resultString;
	}

	public final String byte2hexString(byte[] bytes) {
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if (((int) bytes[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString((int) bytes[i] & 0xff, 16));
		}
		return buf.toString().toUpperCase();
	}

	public void responseMsg(){  
		String postStr=null;  
		try{  
			postStr=this.readStreamParameter(final_request.getInputStream());  
		}catch(Exception e){  
			e.printStackTrace();  
		}  
		System.out.println(postStr);  
		if (null!=postStr&&!postStr.isEmpty()){  
			Document document=null;  
			try{  
				document = DocumentHelper.parseText(postStr);  
			}catch(Exception e){  
				e.printStackTrace();  
			}
			if(null==document){  
				this.print("");  
				return;  
			}  
			Element root=document.getRootElement();  
			String MsgType = root.elementTextTrim("MsgType");
			System.out.println(MsgType);
			AbstractMsgHandler handler = MsgHandlerFactory.getInstance().getMsgHandler(MsgType);
			if(handler != null){
				this.print(handler.recv(root)); 
			}
		}else {  
			this.print(""); 

		}  
	}  

	public void sendContent(String fromUsername , String toUsername , String keyword){
		String time = String.valueOf(new Date().getTime());  
		if(null!=keyword&&!keyword.equals("")){  
			if(keyword.equals("1")){
				String textTpl = "<xml>"+  
						"<ToUserName><![CDATA[%1$s]]></ToUserName>"+  
						"<FromUserName><![CDATA[%2$s]]></FromUserName>"+  
						"<CreateTime>%3$s</CreateTime>"+  
						"<MsgType><![CDATA[%4$s]]></MsgType>"+  
						"<Content><![CDATA[%5$s]]></Content>"+  
						"<FuncFlag>0</FuncFlag>"+  
						"</xml>";
				String msgType = "text";  
				String contentStr = "Welcome to wechat world!";  
				String resultStr = textTpl.format(textTpl, fromUsername, toUsername, time, msgType, contentStr);  
				print(resultStr);  
			}else{
				String textTpl = "<xml>"+  
						"<ToUserName><![CDATA[%1$s]]></ToUserName>"+  
						"<FromUserName><![CDATA[%2$s]]></FromUserName>"+  
						"<CreateTime>%3$s</CreateTime>"+  
						"<MsgType><![CDATA[%4$s]]></MsgType>"+  
						"<ArticleCount>2</ArticleCount>"+  
						"<Articles><item>"+  
						"<Title><![CDATA[%5$s]]></Title>"+  
						"<Description><![CDATA[%6$s]]></Description>"+  
						"<PicUrl><![CDATA[%7$s]]></PicUrl>"+  
						"<Url><![CDATA[%8$s]]></Url>"+   
						"</item><item>"+   
						"<Title><![CDATA[%9$s]]></Title>"+  
						"<Description><![CDATA[%10$s]]></Description>"+ 
						"<PicUrl><![CDATA[%11$s]]></PicUrl>"+  
						"<Url><![CDATA[%12$s]]></Url>"+   
						"</item></Articles>"+    
						"</xml>";
				String msgType = "news"; 
			}

		}else{  
			this.print("");  
		} 
	}

	public String readStreamParameter(ServletInputStream in){ 
		StringBuilder buffer = new StringBuilder();  
		BufferedReader reader=null;  
		try{  
			reader = new BufferedReader(new InputStreamReader(in));  
			String line=null;  
			while((line = reader.readLine())!=null){  
				buffer.append(line);  
			}  
		}catch(Exception e){  
			e.printStackTrace();  
		}finally{  
			if(null!=reader){  
				try {  
					reader.close();  
				} catch (IOException e) {  
					e.printStackTrace();  
				}  
			}  
		}  
		return buffer.toString();  
	}  

}
