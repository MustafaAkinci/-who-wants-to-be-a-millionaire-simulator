import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

public class Participant {

	public Participant() {
		// TODO Auto-generated constructor stub
	}
	
	static String[] participants;
	
	static void participantsToArray(String tf) {
		try {
			int lines=0;
			int cntr=0;
			BufferedReader bf = new BufferedReader(new FileReader(tf));
			while (bf.readLine() != null) lines++;
			bf.close();
			participants= new String[lines];
			
			BufferedReader bf2 = new BufferedReader(new FileReader(tf));
			String line = bf2.readLine();
			  while (line != null) {
		            //questions.add(line);
				  for( ; cntr<lines;cntr++) {
					  participants[cntr]=line;
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
	static String randParticipant() {
		Random rand = new Random(); //instance of random class
	      int upperbound = participants.length; 
	      int int_random = rand.nextInt(upperbound);
	      String[] holder= participants[int_random].split("#");
	      return holder[0];
	}
	
	public static void main(String[] args) {
		participantsToArray("participants.txt");
	}
	
	
}
