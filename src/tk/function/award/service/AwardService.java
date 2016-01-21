package tk.function.award.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import tk.core.db.SQL;
import tk.core.db.template.HibernateTemplateExt;
import tk.entities.active.WxUserScore;

public class AwardService {
	
	private static Object obj = new Object();
	private static AwardService _instance ;
	private static List<WxUserScore> listScore = new ArrayList<WxUserScore>();
	private static Integer totalScore ;
	
	public static AwardService getInstance(){
		if(_instance == null){
			synchronized (obj) {
				if(_instance == null)
					_instance = new AwardService();
			}
		}
		return _instance;
	}
	
	public AwardService(){
		SQL sql = SQL.begin().sql("from WxUserScore o where o.isValid='Y' order by o.scoreId desc ").end();
		listScore = (List<WxUserScore>) HibernateTemplateExt.getInstance().find(sql);
		totalScore = 0;
		for(WxUserScore score : listScore){
			totalScore += score.getScore();
		}		
	}
	
	public WxUserScore doAward(){
		SQL sql = SQL.begin().sql("from WxUserScore o where o.isValid='Y' order by o.scoreId desc ").end();
		listScore = (List<WxUserScore>) HibernateTemplateExt.getInstance().find(sql);
		totalScore = 0;
		for(WxUserScore score : listScore){
			totalScore += score.getScore();
		}	
		int it = new Random().nextInt(totalScore * listScore.size());
		long lt = new Date().getTime() , lt1 = (lt  & it >> 2);
		int awardScore = (int)(lt1 % totalScore);
		int tmpScore = 0;
		WxUserScore awardUserScore = null;
		for(WxUserScore score : listScore){
			tmpScore += score.getScore();
			if(tmpScore >= awardScore){
				awardUserScore = score;
				score.setIsValid("N");
				listScore.remove(awardUserScore); //下次不再中奖，取消中奖资格
				totalScore -= awardUserScore.getScore();
				HibernateTemplateExt.getInstance().update(score);
				break;
			}
		}
		return awardUserScore;
	}
	
	
	
	
}
