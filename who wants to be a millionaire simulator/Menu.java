import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import enigma.core.Enigma;
import enigma.event.TextMouseEvent;
import enigma.event.TextMouseListener;

public class Menu {
	
	public static enigma.console.Console cn = Enigma.getConsole("Who Wants to Be Millionaire",100,35); 
	public static KeyListener klis; 
	public static TextMouseListener tmlis; 
	
	static String menuChoice;
	static int px=0, py=0;
	
	   // ------ Standard variables for mouse and keyboard ------
	   public static  int mousepr;          // mouse pressed?
	   public static  int mousex, mousey;   // mouse text coords.
	   public static  int keypr;   // key pressed?
	   public static  int rkey;    // key   (for press/release)
	   // ----------------------------------------------------
	   
	   public static void writeWithCoordinate(String str, int x, int y) {
		   cn.getTextWindow().setCursorPosition(x,y);
		   cn.getTextWindow().output(str);
		   y++;
	   }
	   
	   public static void writeln(String str) {
		   py++;
		   cn.getTextWindow().setCursorPosition(px,py);
		   cn.getTextWindow().output(str);   
	   }
	   
	   public static void write(String str) {
		   cn.getTextWindow().output(str);   
	   }
	   
	   public static void clear() {
		   cn.getTextWindow().setCursorPosition(0,0);
		   for(int i=0; i<103; i++) {
			   for (int j=0; j<33; j++) {
				   cn.getTextWindow().setCursorPosition(i,j);
				   write(" ");
			   }
		   }
		   py=0;
		   px=0;
	   }
	   
	static void MenuDisplayer() {
		  cn.getTextWindow().pageDown();
	      tmlis=new TextMouseListener() {
	          public void mouseClicked(TextMouseEvent arg0) {}
	          public void mousePressed(TextMouseEvent arg0) {
	             if(mousepr==0) {
	                mousepr=1;
	                mousex=arg0.getX();
	                mousey=arg0.getY();
	             }
	          }
	          public void mouseReleased(TextMouseEvent arg0) {}
	       };
	       cn.getTextWindow().addTextMouseListener(tmlis);
	     
	       klis=new KeyListener() {
	          public void keyTyped(KeyEvent e) {}
	          public void keyPressed(KeyEvent e) {
	             if(keypr==0) {
	                keypr=1;
	                rkey=e.getKeyCode();
	             }
	          }
	          public void keyReleased(KeyEvent e) {}
	       };
	       cn.getTextWindow().addKeyListener(klis);
		//----------------------------------------
	       //MENU	       
	       writeln("***** Menu *****");
	       writeln("1.Load questions");
	       writeln("2.Load participants");
	       writeln("3.Start competition");
	       writeln("4.Show statistics");
	       writeln("5.Exit");
	       
	      while(true) {
	    	  py=cn.getTextWindow().getCursorY()+3;
	    	  writeln(">Enter your choice:");
	    	  menuChoice=cn.readLine(); 
	    	  boolean flag1=false,flag2=false,flag3=false,flag4=false;

	    	  if(menuChoice.equals("1") && flag1==false) {
	    		  writeln("Enter file name to load:  ");
	    		  String fileName=cn.readLine();
	    		  SpellChecker.spellCheckerFunc(fileName);
	    		  Question.wordJobs(fileName);
	    		  cn.getTextWindow().setCursorPosition(0,py);
	    		  McaMenu.McaMenuWork(fileName);
	    		  
	    		  py=cn.getTextWindow().getCursorY()+3;
	    		  cn.getTextWindow().setCursorPosition(0,py);
	    		  py++;
	    		  flag1=true;
	    	  }

	    	  else if(menuChoice.equals("2") && flag2==false) {
	    		  writeln("Enter file name to load:  ");
	    		  String participantsfileName=cn.readLine();
	    		  Participant.participantsToArray(participantsfileName);
	    		  writeln("The file is loaded.");
	    		  flag2=true;
	    	  }
	    	  else if(menuChoice.equals("3")) {
	    		  Competition.competitionFunc();
	    		  flag3=true;
	    		  timer();
	    	  }
	    	  else if(menuChoice.equals("4") && flag3==true) {
	    		  
	    		  flag4=true;
	    	  }
	    	  else if(menuChoice.equals("5")) {
	    		  
	    	  }
	    	  else {
	    		  writeln("Your choice is not executable. Chose something else.");
	    	  }
	      }
	      }
	      public static void timer() 
		   {
		   	Timer timer = new Timer();
		   	TimerTask task = new TimerTask() {
		   	    double i = 20;
		   	   
		   	    boolean flag3=true;
		   	    public void run(){
		   	     boolean flag2 = false;
				if (i == 0&&flag3) {
			        	System.out.println("Time's up!");
			        	
			        	cn.getTextWindow().setCursorPosition(55, 5);
		   	        	System.out.println("|Remaining Time: " + 0 );   	        	
						flag2 = false;
						boolean time_is_over = true;
						i=-1;
						
						}
		   	        boolean flagme = false;
					if (i >= 0) {
		   	           
		   	    		cn.getTextWindow().setCursorPosition(55, 3);
		   	    		System.out.println("+-----------------------------+");
		   	    		
		   	    	     
		   	    		cn.getTextWindow().setCursorPosition(55, 5);
		   	    		if(i<10) {
		   	    			
		   	    			System.out.println("|Remaining Time: " + i--);
		   	   	        	cn.getTextWindow().setCursorPosition(55, 6);
		   	    		}
		   	    		else {
		   	    			System.out.println("|Remaining Time: " + i-- );
		   	   	        	cn.getTextWindow().setCursorPosition(55, 6);
		   	    		}
		   	        	
		   	            
		   	            cn.getTextWindow().setCursorPosition(55, 8);
		   	            System.out.println("+-----------------------------+");
		   	            
		   	            if(!flag2) {
		   	            	i=0;
		   	            	
		   		   	        flag3=false;
		   	            }
		   	            if(!flagme) {
		   	            	i=0;
		   	             flag3=false;
		   	            }
		   	            
		   	        }
		   	       
		   	        if(i == 0&&!flag3) {
		   	        	flag2 = false;
		   	     	   i=-1;
		   	        }
		   	        if(i==0&&!flagme) {
		   	        	i=-1;
		   	        }
		   	    }
		   	
		   	};
				timer.scheduleAtFixedRate(task,0, 1000);
		   
		    
	}
	
}
