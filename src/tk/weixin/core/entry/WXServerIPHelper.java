package tk.weixin.core.entry;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;

public class WXServerIPHelper {
	/**
	 * �������ͣ� GET
	 */
	public final static String GET = "GET";
	/**
	 * �������ͣ� POST
	 */
	public final static String POST = "POST";

	/**
	 * ģ��Http Get����
	 * @param urlStr
	 *             ����·��
	 * @param paramMap
	 *             �������
	 * @return
	 * @throws Exception
	 */
	public static String get(String urlStr, Map<String, String> paramMap) throws Exception{
		urlStr = urlStr + "?" + getParamString(paramMap);
		HttpURLConnection conn = null;
		try{
			//����URL����
			URL url = new URL(urlStr);
			//��ȡURL����
			conn = (HttpURLConnection) url.openConnection();
			//����ͨ�õ���������
			setHttpUrlConnection(conn, GET);
			//����ʵ�ʵ�����
			conn.connect();
			//��ȡ��Ӧ������
			return readResponseContent(conn.getInputStream());
		}finally{
			if(null!=conn) conn.disconnect();
		}
	}
	
	public static String get(String urlStr, String token) throws Exception{
		urlStr = urlStr +token ;
		HttpURLConnection conn = null;
		try{
			//����URL����
			URL url = new URL(urlStr);
			//��ȡURL����
			conn = (HttpURLConnection) url.openConnection();
			//����ͨ�õ���������
			setHttpUrlConnection(conn, GET);
			//����ʵ�ʵ�����
			conn.connect();
			//��ȡ��Ӧ������
			String tmp = readResponseContent(conn.getInputStream());
			return tmp;
		}finally{
			if(null!=conn) conn.disconnect();
		}
	}
	/**
	 * ģ��Http Post����
	 * @param urlStr
	 *             ����·��
	 * @param paramMap
	 *             �������
	 * @return
	 * @throws Exception 
	 */
	public static String post(String urlStr, Map<String, String> paramMap) throws Exception{
		HttpURLConnection conn = null;
		PrintWriter writer = null;
		try{
			//����URL����
			URL url = new URL(urlStr);
			//��ȡ�������
			String param = getParamString(paramMap);
			//��ȡURL����
			conn = (HttpURLConnection) url.openConnection();
			//����ͨ����������
			setHttpUrlConnection(conn, POST);
			//����ʵ�ʵ�����
			conn.connect();
			//���������д�������ַ�����
			writer = new PrintWriter(conn.getOutputStream());
			writer.print(param);
			writer.flush();
			//��ȡ��Ӧ������
			return readResponseContent(conn.getInputStream());
		}finally{
			if(null!=conn) conn.disconnect();
			if(null!=writer) writer.close();
		}
	}

	/**
	 * ��ȡ��Ӧ�ֽ�������֮תΪ�ַ���
	 * @param in
	 *         Ҫ��ȡ���ֽ���
	 * @return
	 * @throws IOException
	 */
	private static String readResponseContent(InputStream in) throws IOException{
		Reader reader = null;
		StringBuilder content = new StringBuilder();
		try{
			reader = new InputStreamReader(in);
			char[] buffer = new char[1024];
			int head = 0;
			while( (head=reader.read(buffer))>0 ){
				content.append(new String(buffer, 0, head));
			}
			return content.toString();
		}finally{
			if(null!=in) in.close();
			if(null!=reader) reader.close();
		}
	}

	/**
	 * ����Http�������� 
	 * @param conn
	 *             http����
	 * @return
	 * @throws ProtocolException 
	 * @throws Exception 
	 */
	private static void setHttpUrlConnection(HttpURLConnection conn, String requestMethod) throws ProtocolException{
		conn.setRequestMethod(requestMethod);
		conn.setRequestProperty("accept", "*/*");
		conn.setRequestProperty("Accept-Language", "zh-CN");
		conn.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)");
		conn.setRequestProperty("Proxy-Connection", "Keep-Alive");
		if(null!=requestMethod && POST.equals(requestMethod)){
			conn.setDoOutput(true);
			conn.setDoInput(true);
		}
	}

	/**
	 * ������תΪ·���ַ���
	 * @param params
	 *             ����
	 * @return
	 */
	private static String getParamString(Map<String, String> paramMap){
		if(null==paramMap || paramMap.isEmpty()){
			return "";
		}
		StringBuilder builder = new StringBuilder();
		for(String key : paramMap.keySet() ){
			builder.append("&")
			.append(key).append("=").append(paramMap.get(key));
		}
		return builder.deleteCharAt(0).toString();
	}
}
