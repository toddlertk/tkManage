package tk.function.award.service;

import java.util.Date;
import java.util.Random;

public class TestThread extends Thread {
	
	public void run(){
		int totalScore = 6000 , size = 1000;
		
		for(int i = 0 ; i < 2000 ; i++){
			int it = new Random().nextInt(totalScore * size);
			long lt = new Date().getTime() , lt1 = (lt  & it >> 2);
			int awardScore = (int)(lt1 % totalScore);
			size--;
			System.out.println(awardScore + " " + size);
			try {
				sleep(1001);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
