import java.util.TimerTask;
import enigma.core.Enigma;
import enigma.event.TextMouseEvent;
	import enigma.event.TextMouseListener;
	import java.awt.event.KeyEvent;
	import java.awt.event.KeyListener;
	import enigma.console.TextAttributes;
	import java.awt.Color;
public class Timer {
	

	
		  
	public static enigma.console.Console cn = Enigma.getConsole("Mouse and Keyboard");
	public static TextMouseListener tmlis; 
	   public static KeyListener klis; 
	   public static String choice;
	   static int timer = 20;                              // starts from 20
	   static long time1 = System.currentTimeMillis();     // it first gets milliseconds

	   // ------ Standard variables for mouse and keyboard ------
	   public static int mousepr;          // mouse pressed?
	   public static int mousex;   // mouse text coords.
	   public static int mousey;
	   public static int keypr;   // key pressed?
	   public static int rkey;    // key   (for press/release)
		   
		   
		   static void Game() throws Exception {   // --- Contructor
		                 
		      // ------ Standard code for mouse and keyboard ------ Do not change
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
		      // ----------------------------------------------------
		      

		      int px=5,py=5;
		      cn.getTextWindow().output(px,py,'P');
		      while(true) {
		    	  
		    	  long time2 = System.currentTimeMillis(); // time 2 will updates in every loop
					if (time1 >= time2 +1000)
					{
						
						time1 = time2;
						if(timer >= 10) {                    // if its pass 1 second the timer will printed
							cn.getTextWindow().setCursorPosition(88, 3);
							System.out.println(timer);
						}
						else if(timer < 10) {
							cn.getTextWindow().setCursorPosition(88, 3);
							System.out.println(" ");
							cn.getTextWindow().setCursorPosition(89, 3);
							System.out.println(timer);
						}
						
						timer--;
						Thread.sleep(1000);
					}
				time1 += 100000;
				
				if(Timer.timer == 0) { // if timer = 0 the game will be lost
					return;
				}
		         if(keypr==1) {    // if keyboard button pressed
		            if(rkey==KeyEvent.VK_LEFT) px--;   
		            if(rkey==KeyEvent.VK_RIGHT) px++;
		            if(rkey==KeyEvent.VK_UP) py--;
		            if(rkey==KeyEvent.VK_DOWN) py++;
		            
		            char rckey=(char)rkey;
		            //        left          right          up            down
		            if(rckey=='%' || rckey=='\'' || rckey=='&' || rckey=='(') cn.getTextWindow().output(px,py,'P'); // VK kullanmadan test teknigi
		            else {
		            	cn.getTextWindow().setCursorPosition(40, 6);
		            	cn.getTextWindow().output(rckey);
		            } 
		            
		            if(rkey==KeyEvent.VK_SPACE) {
		               String str;         
		               str=cn.readLine();     // keyboardlistener running and readline input by using enter 
		               cn.getTextWindow().setCursorPosition(5, 20);
		               cn.getTextWindow().output(str);
		               
		            }
		            choice=Character.toString(rckey);
		            keypr=0;    // last action  
		         }
		         Thread.sleep(20);
		      }
		   }
		

		
			}
		



