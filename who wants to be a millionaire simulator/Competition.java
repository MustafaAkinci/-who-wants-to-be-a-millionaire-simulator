import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;
public class Competition {

	static int questionNum=0;
	static int money=0;
	static boolean fifty_fifty=true;
	static boolean double_dip=true;
	static boolean double_dip_flag=false;
	static String[] holder;
	
	public static void infoBoxDisplayer(int x, int y) {
		y+=4;
		Menu.cn.getTextWindow().setCursorPosition(x+74,y-1);
		for(int i=0; i<21; i++) {
			Menu.write("-");
		}
		Menu.cn.getTextWindow().setCursorPosition(x+74,y+4);
		for(int i=0; i<21; i++) {
			Menu.write("-");
		}
		for(int i=1;i<5;i++) {
			Menu.cn.getTextWindow().setCursorPosition(x+74,y-1+i);
			Menu.write("|");
			Menu.cn.getTextWindow().setCursorPosition(x+74+20,y-1+i);
			Menu.write("|");
		}
		Menu.cn.getTextWindow().setCursorPosition(x+75,y);
		Menu.write("Money: $"+money);
		Menu.cn.getTextWindow().setCursorPosition(x+75,y+1);
		Menu.write("Remaining time:");
		Menu.cn.getTextWindow().setCursorPosition(x+75,y+2);
		if(fifty_fifty==true) {
			Menu.write("50%");
		}
		Menu.cn.getTextWindow().setCursorPosition(x+75,y+3);
		if(double_dip==true) {
			Menu.write("Double dip");
		}
	}
	
	
	public static int wordCloudDisplayer(String[] arr) {
		Menu.clear();
		Menu.cn.getTextWindow().pageDown();

		Menu.writeln("-------------------------------------------------------------------------------------------------");
		Menu.writeln("WORD CLOUD:");
		Menu.py+=2;
		Menu.cn.getTextWindow().setCursorPosition(0,Menu.py);
		for(int i=0; i<arr.length; i++) {
			if(arr[i]!=null) {
				Menu.write(arr[i]+"     ");
				Menu.px=Menu.px+4;
				if(i%4==0) {
					Menu.write("\n");
					Menu.py++;
				}
			}

		}
		Menu.writeln(" ");
		Menu.writeln(" ");
		Menu.writeln(" ");
		
		boolean flag=false;
		int num=0;
		
		while(true) {
			Menu.px=0;
			Menu.writeln(">Enter your selection:  ");
			String selection=Menu.cn.readLine();
			for(int j=0; j<arr.length; j++) {
				if(selection.equals(arr[j])) {
					flag=true;
					num=j;
					break;
				}
			}
			if(flag==true) {
				break;
			}
			Menu.writeln("Word cloud doesn't contain your answer. Try again");
		}
		
		
		return num;
	}
	

	
	public static boolean questionDisplayer(String[] arr,int qID) {	
		holder= arr[qID].split("#");
		int rightChoice=0;
		if(holder[6].equals("A")) {
			rightChoice=2;
		}
		else if(holder[6].equals("B")) {
			rightChoice=3;
		}
		else if(holder[6].equals("C")) {
			rightChoice=4;
		}
		else if(holder[6].equals("D")) {
			rightChoice=5;
		}
		
		while(true) {
		Menu.cn.getTextWindow().pageDown();
		Menu.py+=2;
		
		Menu.writeln(holder[1]);
		infoBoxDisplayer(Menu.px,Menu.py);
		Menu.py+=2;
		Menu.writeln("a)"+ holder[2]);
		Menu.writeln("b)"+ holder[3]);
		Menu.writeln("c)"+ holder[4]);
		Menu.writeln("d)"+ holder[5]);
		Menu.py+=2;
		Menu.writeln(">Enter your selection:  ");
		String choice=Menu.cn.readLine();
		
		//jokers----------------------------------------------------------------
		
		//%50
		if(choice.equals("%50") && fifty_fifty==true) {
		fifty_fifty=false;
		int c1, c2;
		while(true) {
			Random randChoice = new Random();
			 c1=randChoice.nextInt(4)+2;
			 c2=randChoice.nextInt(4)+2;
			if(c1==rightChoice || c2==rightChoice || c1==c2) {
				continue;
			}
			else break;
		}
		
			holder[c1]="";
			holder[c2]="";
			Menu.clear();
			continue;
		}
		else if(choice.equals("%50") && fifty_fifty==false) {
			Menu.clear();
			continue;
		}
		
		//double dip
		if(choice.toLowerCase().equals("double dip") && double_dip==true) {
			double_dip=false;
			double_dip_flag=true;
			Menu.clear();
			continue;
		}
		
		//-------------------------------------------------------------------------
		
		//choice
		if(choice.toUpperCase().equals(holder[6])) {
			Menu.writeln("True");
			try {
				Thread.sleep(2000);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Menu.clear();
			return true;
		}
		else {
			Menu.writeln("False");
			if(double_dip_flag==true) {
				double_dip_flag=false;
				int wrongChoice=0;
				if(choice.toUpperCase().equals("A")) {
					wrongChoice=2;
				}
				else if(choice.toUpperCase().equals("B")) {
					wrongChoice=3;
				}
				else if(choice.toUpperCase().equals("C")) {
					wrongChoice=4;
				}
				else if(choice.toUpperCase().equals("D")) {
					wrongChoice=5;
				}
				holder[wrongChoice]=" ";
				Menu.clear();
				continue;
			}
			try {
				Thread.sleep(2000);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Menu.clear();
			return false;
		}
		}
		
		
		
	}
	

	
	
	
	public static boolean competitionFunc() {
		boolean endOrContinue=true;
		Question.wordJobs("questions2.txt");

		// WORD CLOUD QUESTIONS
		endOrContinue = questionDisplayer(Question.questions,wordCloudDisplayer(Question.wordCloud1));
		if(endOrContinue==false) {
			return false;
		}
		else{
			questionNum++;
			money=20000;
		}
		endOrContinue =questionDisplayer(Question.questions,wordCloudDisplayer(Question.wordCloud2));
		if(endOrContinue==false) {
			money=0;
			return false;
		}
		else{
			questionNum++;
			money=100000;
		}
		Random rand = new Random();
		//Upper level questions
		endOrContinue=questionDisplayer(Question.level3Qs, rand.nextInt(Question.level3Qs.length));
		if(endOrContinue==false) {
			money=100000;
			return false;
		}
		else{
			questionNum++;
			money=250000;
		}
		
		endOrContinue=questionDisplayer(Question.level4Qs, rand.nextInt(Question.level4Qs.length));
		if(endOrContinue==false) {
			money=100000;
			return false;
		}
		else{
			questionNum++;
			money=500000;
		}
		
		endOrContinue=questionDisplayer(Question.level5Qs, rand.nextInt(Question.level5Qs.length));
		if(endOrContinue==false) {
			questionNum++;
			money=500000;
			try {
				Thread.sleep(2000);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else {
			money=1000000;
		}
			return true;
		
	}
	public static void main(String[] args) {
		competitionFunc();

	}
}
