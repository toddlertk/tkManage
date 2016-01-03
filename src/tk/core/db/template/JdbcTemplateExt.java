package tk.core.db.template;

import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import tk.core.context.SpringBeanLoader;
import tk.core.db.SQL;

public class JdbcTemplateExt extends JdbcTemplate{
	protected static Log logger = LogFactory.getLog(JdbcTemplateExt.class);
	public JdbcTemplateExt(){}
	public JdbcTemplateExt(DataSource dataSource){
		super.setDataSource(dataSource);
	}
	
	public static JdbcTemplateExt getInstance(){
		return (JdbcTemplateExt)SpringBeanLoader.getBean("jdbcTemplate");
	}
	public int findInt(SQL sqlObj){
		return this.queryForInt(sqlObj.getText(), sqlObj.getParamsArray());
	}
	
	public Object findScalar(SQL sqlObj){
		return this.query(sqlObj.getText(), sqlObj.getParamsArray(),JdbcRowMapper.SCALAR);
	}
	
	public int execute(SQL sql){
		return this.update(sql.getText(), sql.getParamsArray());
	}
	/*public List findPart(SQL sqlObj, int startNo, int pageCount){
		String sqlText = sqlObj.getText();
		sqlText = sqlText.trim();
		String selectKeyWords = sqlText.substring(0,6);
		String upperFrom = sqlText.toUpperCase();
		String order = "";
		if(upperFrom.lastIndexOf("ORDER BY") > 0)
			order = sqlText.substring(upperFrom.lastIndexOf("ORDER BY"));
		sqlText = sqlText.replaceFirst(selectKeyWords, "select rownumber() over("+order+") as rownumber_,");
		sqlText = "select * from ( " + sqlText + " ) as temp where rownumber_ between ?+1 and ?";
		List params = sqlObj.getParams();
		if(params == null)
			params = new ArrayList();
		params.add(new Integer(startNo-1));
		params.add(new Integer(pageCount));
		logger.info("JdbcTemplateExt:"+sqlText);
		return this.queryForList(sqlText, params.toArray());
	}*/
	public List find(SQL sqlObj){
		return this.queryForList(sqlObj.getText(), sqlObj.getParamsArray());
	}
	public void callProcedure(String procName,Object[] params){
		StringBuffer buf = new StringBuffer();
		buf.append("{call ").append(procName);
		if(params != null && params.length > 0){
			buf.append("(");
			int i,n=params.length;
			for(i=0;i<n;i++){
				buf.append("?");
				if(i != n-1)
					buf.append(",");
			}
			buf.append(")");
		}
		buf.append(" }");
		if(params != null){
			this.update(buf.toString(), params);
		}else{
			this.update(buf.toString());
		}
	}
}
