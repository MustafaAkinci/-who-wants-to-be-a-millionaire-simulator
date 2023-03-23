import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Question {

	public Question() {
		// TODO Auto-generated constructor stub
	}
	
	
	static String[] questions;
	static String[] stopWords;
	static String[] qWithoutStops;
	static String[] onlyQuestions; 
	static String[] wordCloud1;
	static String[] wordCloud2;
	static String[] level3Qs;
	static String[] level4Qs;
	static String[] level5Qs;
	
	
	static void  textToArray(String strVar) {
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

		}
		 catch(Exception e) {
		        e.getStackTrace();
		     }
}
	
	static void stopWordsToArray() {
		try {
			int lines=0;
			int cntr=0;
			BufferedReader bf = new BufferedReader(new FileReader("stop_words.txt"));
			while (bf.readLine() != null) lines++;
			bf.close();
			stopWords= new String[lines];
			
			BufferedReader bf2 = new BufferedReader(new FileReader("stop_words.txt"));
			String line = bf2.readLine();
			  while (line != null) {
		            //questions.add(line);
				  for( ; cntr<lines;cntr++) {
					  stopWords[cntr]=line;
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
	
	static void triming(String[] qArray, String[] sWords) {
		//String[] holder1= new String[8];
		
		for(int i=0; i<qArray.length; i++) {
			//holder holds all the question line 
			String[] holder1=qArray[i].split("#");
			onlyQuestions[i]=holder1[1];
		}
		
		String holder2="";
		for(int i=0; i<onlyQuestions.length-1; i++) {
			holder2=onlyQuestions[i];
			holder2 = holder2.replaceAll("[^a-zA-Z]", " ");
			onlyQuestions[i]=holder2;
		}
		
		int cntr=0;
		String holder="";
		String[] wordArray;
		for (String line : onlyQuestions) 
		{ 
		    holder=line;
		    wordArray=holder.toLowerCase().split(" ");
		    
		    StringBuilder builder = new StringBuilder();
		    for(String word : wordArray) {
		        if(!Arrays.asList(stopWords).contains(word)) {
		            builder.append(word);
		            builder.append(' ');
		        }
		    }
	
		    String result = builder.toString();
		    qWithoutStops[cntr]=result.trim();
		    cntr++;
		}
		


	}
	
	static void wordCloudMaker(String[] arr, String level) {
		String[] arrholder;
		for(int i=0; i<qWithoutStops.length;i++) {
			arrholder=questions[i].split("#");
			if(arrholder[7].equals(level)) {
				String[] holder;
				holder=qWithoutStops[i].split(" ");	
				if(holder[0]!=null) {
					arr[i]=holder[0];	
				}
			}
	}

	}

	static int diffCounterFunc(String diffLevel) {
		int diffCounter=0;
		for(int i=0; i<questions.length; i++) {
			String[] holder=questions[i].split("#");
			if(holder[7].equals(diffLevel)) {
				diffCounter++;
			}
		}
		return diffCounter;
	}
	
	
	
	static void levelArrayMaker(String[] arr,String level) {
		String[] arrholder;
		int j=0;
		String str;
		for(int i=0; i<questions.length;i++) {
			arrholder=questions[i].split("#");
			if(arrholder[7].equals(level)) {
					arr[j]=String.join("#", arrholder);;
					j++;
			}
	}

	}

	static void wordJobs(String fName) {

		textToArray(fName);
		stopWordsToArray();
		onlyQuestions= new String[questions.length];
		qWithoutStops= new String[questions.length];

		triming(questions, stopWords);
		wordCloud1= new String[qWithoutStops.length];
		wordCloudMaker(wordCloud1,"1");
		wordCloud2= new String[qWithoutStops.length];
		wordCloudMaker(wordCloud2,"2");
		
		 level3Qs= new String[diffCounterFunc("3")];
		 levelArrayMaker(level3Qs, "3");
		 level4Qs= new String[diffCounterFunc("4")];
		 levelArrayMaker(level4Qs, "4");
		 level5Qs= new String[diffCounterFunc("5")];
		 levelArrayMaker(level5Qs, "5");
	}
	
	public static void main(String[] args) {
		wordJobs("questions2.txt");
		for(String var: level5Qs) {
			System.out.println(var);
		}
	}

}
