package tk.function.award.web;

import tk.core.web.BasePage;
import tk.core.web.RequestEntry;
import tk.core.web.ResponseResult;
import tk.entities.active.WxUserScore;
import tk.function.award.service.AwardService;
import tk.utils.StringUtils;

public class AwardWeb extends BasePage{

	public ResponseResult doAward(RequestEntry requestEntry){
		String act = requestEntry.getParameter("act");
		if(!StringUtils.isNullOrEmpty(act)){
			try{
				WxUserScore score = AwardService.getInstance().doAward();
				if(score == null)
					requestEntry.setAttribute("result", "");
				else{
					String s = score.getUserName() + "," + score.getUserId() + "," + score.getDepartmentName();
					requestEntry.setAttribute("result", s);
				}
			}catch(Exception e){
				requestEntry.setAttribute("result", "");
			}
		}
		return null;
		
	}
}
