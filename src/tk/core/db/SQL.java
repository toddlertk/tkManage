package tk.core.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SQL implements Serializable{

	private static final long serialVersionUID = 6990993747520449067L;
	
	private String text = null;
	private List<Object> params = null;
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<Object> getParams() {
		return params;
	}

	public void setParams(List<Object> params) {
		this.params = params;
	}

	public SQL(){}
	
	public SQL(String text){
		this.text = text;
	}
	
	public SQL(String text, String key){
		this.text = text;
		this.params = new ArrayList<Object>();
		this.params.add(key);
	}
	
	public SQL(String text, List<Object> params){
		this.text = text;
		this.params = params;
	}
	
	public Object[] getParamsArray(){
		if(params == null)
			return new Object[0];
		return params.toArray();
	}
	
	public static SQLBuilder begin(){
		return new SQLBuilder();
	}
}
