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
			WxUserScore score = AwardService.getInstance().doAward();
			requestEntry.setAttribute("score", score);
		}
		return null;
		
	}
}
