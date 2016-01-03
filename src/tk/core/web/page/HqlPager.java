package tk.core.web.page;

import java.util.List;

import tk.core.context.SpringBeanLoader;
import tk.core.db.SQL;
import tk.core.db.template.HibernateTemplateExt;
import tk.core.exception.ExceptionCreator;
import tk.core.web.RequestEntry;
import tk.utils.StringUtils;

public class HqlPager extends Pager{
	private HibernateTemplateExt hte = HibernateTemplateExt.getInstance();
	public HqlPager(RequestEntry requestEntry){
		super(requestEntry);	
	}
	public HqlPager(RequestEntry requestEntry , String tempateExt){
		super(requestEntry);	
		hte = (HibernateTemplateExt)SpringBeanLoader.getBean(tempateExt);
	}
	public int getCount() {
		if(count == 0){
			String hqlText = this.sqlObj.getText();
			if(StringUtils.isNullOrEmpty(hqlText))
				throw ExceptionCreator.create("SELECT_STATEMENT_ERROR");
			hqlText = hqlText.trim();
			if(hqlText.length() < 6)
				throw ExceptionCreator.create("SELECT_STATEMENT_ERROR");
			String startChars = hqlText.substring(0,6);
			if(!"SELECT".equals(startChars.toUpperCase())){
				String upperFrom = hqlText.toUpperCase();
				if(upperFrom.lastIndexOf("ORDER BY") > 0)
					hqlText = hqlText.substring(0,upperFrom.lastIndexOf("ORDER BY"));
				hqlText = "select count(*) " + hqlText;
			}else{
				String from = hqlText.substring(6);
				String upperFrom = from.toUpperCase();
				if(upperFrom.lastIndexOf("ORDER BY") > 0)
					from = from.substring(0,upperFrom.lastIndexOf("ORDER BY"));
				String fromSub = from.substring(upperFrom.indexOf("FROM"));
				hqlText = "select count(*) " + fromSub;
			}
			SQL coutSQL = new SQL(hqlText,this.sqlObj.getParams());
			List rs = hte.find(coutSQL);
			if(rs != null && rs.size() > 0)
				count = Integer.parseInt(rs.get(0).toString());
		}
		return count;
	}

	public List getPagerData() {
		if(this.dataList != null)
			return this.dataList;
		else{
			if(this.getCount() <this.pageSize)
				this.currentPage = 1;
			int startNo = (this.currentPage -1) * this.pageSize + 1;
			//int maxNo = this.currentPage * this.pageSize;
			int maxNo = this.pageSize;
			return hte.findPart(sqlObj, startNo, maxNo);
		}
	}

	public static void main(String[] args){
		String str = "123456789";
		System.out.println(str.substring(0,6));
		System.out.println(str.substring(str.indexOf("7")));
	}
}
