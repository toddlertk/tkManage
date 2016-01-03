package tk.core.db;

import java.util.ArrayList;
import java.util.List;

public class SQLBuilder {
	StringBuffer buf = new StringBuffer();
	List<Object> params = new ArrayList<Object>();
	
	public SQLBuilder sql(String text){
		buf.append(text);
		return this;
	}
	
	public SQLBuilder sql(String text, Object param){
		buf.append(text);
		params.add(param);
		return this;
	}
	
	public SQLBuilder sql(String text, Object param1, Object param2){
		buf.append(text);
		params.add(param1);
		params.add(param2);
		return this;
	}

	public SQLBuilder sql(String text, Object param1, Object param2, Object param3){
		buf.append(text);
		params.add(param1);
		params.add(param2);
		params.add(param3);
		return this;
	}
	
	public SQLBuilder in(Object[] ary){
		if(ary.length<=0) {
			ary=new Object[] {""};
		}
		sql(" in (");
		int i,n=ary.length;
		for(i=0;i<n;i++){
			sql("?",ary[i]);
			if(i != n-1)
				sql(",");
		}
		sql(")");
		return this;
	}
	public SQL end(){
		return new SQL(buf.toString(),params);
	}
	
}
