package manager.matching;

import dao.schedule.FamilyScheduleViewDAO;
import dao.schedule.FamilyScheduleViewVO;
import dao.schedule.ScheduleViewDAO;
import dao.schedule.ScheduleViewVO;

public class MatchingManager {
	private ScheduleViewDAO scheduleViewDAO;
	private FamilyScheduleViewDAO familyScheduleViewDAO;
	
	public MatchingManager() {
		this.scheduleViewDAO = new ScheduleViewDAO();
		this.familyScheduleViewDAO = new FamilyScheduleViewDAO();
	}
	
	public String[][] cutTimeInSchedule(String [][]schedule){
		String [][]returnString = new String[schedule.length][schedule[0].length];
			
		for(int i=0; i<schedule.length; i++){
			for(int j=0; j<schedule[i].length; j++){
				if(j==2 || j==3){
					returnString[i][j] = schedule[i][j].substring(0, 8);
				}else{
					returnString[i][j] = schedule[i][j];
				}
			}
		}
		return returnString;
	}
	
	public String[][] checkIsSchedule(boolean []checkSchedule, String [][]schedule , String year, String month, int currentTotalDays){
		
		for(int cnt=0; cnt<schedule.length; cnt++){
			
			if(schedule[cnt][2].substring(3,5).equals(month) && schedule[cnt][3].substring(3,5).equals(month)){
				int sDate = Integer.parseInt(schedule[cnt][2].substring(6,8));
				int eDate = Integer.parseInt(schedule[cnt][3].substring(6,8));
				
				//System.out.println(""+sDate);
				//System.out.println(""+eDate);
				for(int days=sDate; days<=eDate; days++){
					checkSchedule[days-1] = true;
				}
				
			}else if(schedule[cnt][2].substring(3,5).equals(month) && (!schedule[cnt][3].substring(3,5).equals(month))){
				int sDate = Integer.parseInt(schedule[cnt][2].substring(6,8));
				int eDate = currentTotalDays;
				
				for(int days=sDate; days<=eDate; days++){
					checkSchedule[days-1] = true;
				}
			}else if( (!schedule[cnt][2].substring(3,5).equals(month)) && schedule[cnt][3].substring(3,5).equals(month) ){
				int sDate = 1;
				int eDate = Integer.parseInt(schedule[cnt][3].substring(6,8));
				for(int days=sDate; days<=eDate; days++){
					checkSchedule[days-1] = true;
				}
			}
		}
		
		
		return null;
	}
	
	public String[] requestScheduleMatching(String homeCode, String year, String month){
		System.out.println("Manager homeCode is : "+homeCode);
		System.out.println("Manager Year is : "+year);
		System.out.println("Manager month is : "+month);
		
		int month1[] = new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
		int month2[] = new int[]{31,29,31,30,31,30,31,31,30,31,30,31};
		
		boolean yearCheck = yearCheck(Integer.parseInt(year));
		
		int thisDate;
		if(yearCheck == true){
			thisDate = month1[Integer.parseInt(month)-1];
		}else{
			thisDate = month2[Integer.parseInt(month)-1];
		}
		
		String [][]tempIndividual = scheduleViewDAO.selectAllFamilyScheduleByDate(homeCode, year, month);
		String [][]tempFamily = familyScheduleViewDAO.selectFamilyScheduleByDate(homeCode, year, month);
		
		String [][]individual = cutTimeInSchedule(tempIndividual);
		String [][]family = cutTimeInSchedule(tempFamily);
		
		for(int i=0; i<individual.length; i++){
			
			for(int j=0; j<individual[i].length; j++){
				System.out.print(individual[i][j]+" // ");
			}
			System.out.println();
		}
		
		for(int i=0; i<family.length; i++){
			
			for(int j=0; j<family[i].length; j++){
				System.out.print(family[i][j]+" // ");
			}
			System.out.println();
		}
		
		boolean checkSchedule[] = new boolean[thisDate];
		
		//for(int cnt=0; cnt<checkSchedule.length; cnt++){
		//	System.out.println(checkSchedule[cnt]);
		//}
		
		checkIsSchedule(checkSchedule,individual,year,month,thisDate);
		
		for(int cnt=1; cnt<=31; cnt++){
			System.out.println(""+cnt+"ÀÏ : "+checkSchedule[cnt-1]);
		}
		
		String []result = new String[checkSchedule.length];
		
		for(int cnt=0; cnt<result.length; cnt++){
			if(checkSchedule[cnt] == true){
				result[cnt] = "true";
			}else{
				result[cnt] = "false";
			}
		}
		
		return result;
	}
	
	public boolean yearCheck(int year){
		if((year%4==0 && year%100 !=0) || year%400==0){
			return false;
		}else{
			return true;
		}
	}
	
	
	public static void main(String[] args) {
		MatchingManager manager = new MatchingManager();
		manager.requestScheduleMatching("h2", "15", "08");
		System.out.println("15-08-11 20:10".substring(3, 5));
		System.out.println("15-08-11 20:10".substring(6, 8));
	}
}
