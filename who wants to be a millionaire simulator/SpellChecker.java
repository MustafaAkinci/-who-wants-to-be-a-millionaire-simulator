import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.io.FileWriter;
import java.io.BufferedWriter;


public class SpellChecker {

	static String[] dict;
	static String[] questions;
	static String[][] qMatrix;
	static String[][] qMatrixMark;
	static String[] fixedQuestions;
	static void  questionsToArray(String strVar) {
		try {
			int lines=0;
			int cntr=0;
			BufferedReader bf = new BufferedReader(new FileReader(strVar));
			while (bf.readLine() != null) lines++;
			bf.close();
			questions= new String[lines];
			
			BufferedReader bf2 = new BufferedReader(new FileReader(strVar));
			String line = bf2.readLine();
			  while (line != null) {
		            //questions.add(line);
				  for( ; cntr<lines;cntr++) {
					  questions[cntr]=line;
			          line = bf2.readLine();
			          continue;
				  }
		        }
			  bf2.close();
			  
			  for(int i=0; i<questions.length; i++) {
				  
				  String lineHolder= questions[i];
				  String holder2="";
				  for(int j=0; j<lineHolder.length(); j++) {
					  if(lineHolder.charAt(j)=='#') {
						  holder2+=" # ";
						  //continue;
					  }
					  if(lineHolder.charAt(j)!='#') {
						  holder2+=lineHolder.charAt(j);
					  }
				  }
				  questions[i]=holder2;
			  }
			  
			  
			  
			  
			}
		 catch(Exception e) {
		        e.getStackTrace();
		     }
		}
	
	
	public static void dictionaryToArray() {
		try {
			int lines=0;
			int cntr=0;
			BufferedReader bf = new BufferedReader(new FileReader("dictionary.txt"));
			while (bf.readLine() != null) lines++;
			bf.close();
			dict= new String[lines];
			
			BufferedReader bf2 = new BufferedReader(new FileReader("dictionary.txt"));
			String line = bf2.readLine();
			  while (line != null) {
		            //questions.add(line);
				  for( ; cntr<lines;cntr++) {
					  dict[cntr]=line;
			          line = bf2.readLine();
			          continue;
				  }
		        }
			  bf2.close();
		}
		 catch(Exception e) {
		        e.getStackTrace();
		     }
}

	public static void checkTheWord() {
		 qMatrix= new String[questions.length][100];
		 qMatrixMark= new String[questions.length][100];
		String[] holder;
		for(int i=0; i<questions.length;i++) {
			holder=questions[i].split(" ");
			for(int j=0; j<holder.length; j++) {
				qMatrix[i][j]=holder[j];
			}
		}
		
		
		String var;

		for(int i=0; i<questions.length;i++) {
			for(int j=0; j<100; j++) {
				if(qMatrix[i][j]!=null && !qMatrix[i][j].equals("#") && qMatrix[i][j].chars().allMatch(Character::isLetter) && !qMatrix[i][j].matches(".*[A-Z].*")) {
					
					var=qMatrix[i][j];
					var=var.toLowerCase();
					
					if(!Arrays.asList(dict).contains(var)) {
						qMatrixMark[i][j]="*";
					}
					else {
						qMatrixMark[i][j]="0";
					}
				}
				
			}
		}
		
	}
	
	
	public static int findTheMinimum(int a, int b, int c) {
		int minimum = a;
		if (b < minimum) {
			minimum = b;
		} 

		if (c < minimum) {
			minimum = c;
		} 

		return minimum;
	} 
	
	//finding Levensthein distance  
	public static int findDifference(String word1, String word2) {
		int firstLength, secondLength;
		char wh1, wh2; // word holder 1 and word holder 2
		int above, left, diagonal, cost;

		firstLength = word1.length();
		secondLength = word2.length();

		if (firstLength == 0) {
			return secondLength;
		} 

		if (secondLength == 0) {
			return firstLength;
		} 

		int[][] matrix = new int[firstLength + 1][secondLength + 1];

		
		for (int x = 0; x <= firstLength; x++) {
			matrix[x][0] = x;
		} 

		
		for (int x = 0; x <= secondLength; x++) {
			matrix[0][x] = x;
		} 


		for (int i = 1; i <= firstLength; i++) {
			wh1 = word1.charAt (i - 1);

			for (int j = 1; j <= secondLength; j++) {
				wh2 = word2.charAt (j - 1);


				above = matrix[i - 1][j] + 1;
				left = matrix[i][j - 1] + 1;


				if (wh1 == wh2) {
					diagonal = matrix[i - 1][j - 1];
				} else {

					diagonal = matrix[i - 1][j - 1] + 1;
				} 


				matrix[i][j] = findTheMinimum(above, left, diagonal);
			} 
		} 

		
		return matrix[firstLength][secondLength];
	} 
	
	
	

	public static void spellCheckerFunc(String qText) {
		questionsToArray(qText);
		dictionaryToArray();
		checkTheWord();
		int minA=0,minI=0,minJ=0;
		for(int i=0; i<questions.length;i++) {
			
			for(int j=0; j<100; j++) {
				if(qMatrixMark[i][j]==null) {
					continue;
				}
				if(qMatrixMark[i][j].equals("*")) {
					int minDistance=50;
					for(int a=0; a<dict.length; a++) {
						
						if(findDifference( dict[a] ,qMatrix[i][j]) <= minDistance) {
							minDistance=findDifference(qMatrix[i][j], dict[a]);
							minA=a;
							minI=i;
							minJ=j;
						}
					}
					qMatrix[minI][minJ]=dict[minA];
				}
			}
		}
		fixedQuestions=new String[questions.length];
		for(int i=0; i<questions.length; i++) {
			String line="";
			for(int j=0; j<100; j++) {
				if(qMatrix[i][j]!=null) {
					line=line+qMatrix[i][j]+" ";
					fixedQuestions[i]=line;
				}
			}
		}
		
		for(int e=0; e<fixedQuestions.length; e++) {
	        String lineHolder[];
	        String line="";
        	lineHolder=fixedQuestions[e].split("");
        	
        	for(int j=0; j<lineHolder.length; j++) {
        		
        		if(j+2==lineHolder.length) {
        			line=line+lineHolder[j];
        			fixedQuestions[e]=line;
        			break;
        		}
        		if(lineHolder[j+1].equals("#")) {
        			continue;
        		}
        		else if(lineHolder[j].equals("#")) {
        			line=line+lineHolder[j];
        			j++;
        		}
        		else {
        			line=line+lineHolder[j];
        		}
        	}
        	
        }

		
	    try {
	        // Creates a FileWriter
	        FileWriter file = new FileWriter(qText);

	        BufferedWriter output = new BufferedWriter(file);

	        
	        for(String line2: fixedQuestions) {
	        	output.write(line2 +"\n");
	        }
	         
	        output.close();
	      }
	    
	      catch (Exception e) {
	        e.getStackTrace();
	      }
	      
	}
	
	
	public static void main(String[] args) {
		//spellCheckerFunc("questions2.txt");
	}
	
}
