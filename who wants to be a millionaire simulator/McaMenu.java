import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.GroupLayout.Alignment;


public class McaMenu {
	static int linecounter = 0;
	static String[] questioncolumnarray;
	static String[][] arr; 
	static String[] categoryarray;
	static String[] difficultyArray;
	
	static void McaMenuWork(String fname) {
Scanner scanner = new Scanner(System.in);
			try {
				
				String questionfilename = fname;
				FileReader filereader = new FileReader(questionfilename);
				BufferedReader reader = new BufferedReader(filereader);
				
				String line = reader.readLine();
				
				
				while (line != null)
				{
					if(line.length() > 0)
					{
						linecounter++;         
					}
					
					line = reader.readLine();
				}
				
				reader.close();
				filereader.close();
				
				/////////////////////////////////////////////////////////////////

				filereader = new FileReader(questionfilename);
				reader = new BufferedReader(filereader);
				line = reader.readLine();
				
				 questioncolumnarray = new String[8];
				 arr = new String[linecounter][8]; 
				 categoryarray = new String[linecounter];
				 difficultyArray = new String[linecounter];
				
 				int categoryindex = 0;
 				int difficultyindex = 0;
				int counter = 0;
				
				while (line != null)
				{
					questioncolumnarray = line.split("#");
					
					for(int i = 0; i < 8; i++) 
					{
						arr[counter][i] = questioncolumnarray[i]; 
					} 
					counter++;
					
					categoryarray[categoryindex] = questioncolumnarray[0];
					categoryindex++;

					difficultyArray[difficultyindex] = questioncolumnarray[questioncolumnarray.length-2];
					difficultyindex++;
					
					line = reader.readLine();
				}
				////////////////////////////////////////////////////////
				
				System.out.println("Category                   The number of question");
				Counter(categoryarray);
				System.out.println("");
				System.out.println("Difficulty                   The number of question");
				Counter(difficultyArray);
				
				////////////////////////////////////////////////////////////////////

				reader.close();
				filereader.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		
   }
public static void Counter(String[] Array ) 
{
	int[] categorycounterarray = new int[Array.length];
	int counter = 0;
	for (int i = 0 ; i < Array.length ; i++)	{	
		for ( int j = 0 ; j < Array.length ; j++)
		{
			if (Array[i].equals(Array[j]))
			{
				counter++;
			}
		}
		categorycounterarray[i] = counter;
		counter = 0;
	}
	
	
	for (int i = 0 ; i < Array.length ; i++)
	{
		int flag = 0;

		for (int j = 0 ; j < i ; j++)
		{
			if (Array[i].equals(Array[j]))
			{
				flag = 1;
				break;
			}
			if (Array[i] == Array[j] && categorycounterarray[i] != 1)
			{
				flag = 1;
				break;
			}
		}
		if (flag == 0)
		{
			//System.out.println(Array[i] + "                               " + categorycounterarray[i]);
			System.out.printf( "%-15s %15s %n", Array[i], categorycounterarray[i]);
		}

	}
}
	

public static void main(String[] args) throws FileNotFoundException{
		McaMenuWork("questions2.txt");

}
}