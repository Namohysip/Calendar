package calendar;
import java.io.*;
public class Driver{

	public static void main(String[] args) {
		BufferedReader read;
		try{
			read = new BufferedReader(new FileReader(new File("test.ics")));
			
		}
		catch(FileNotFoundException e){
			System.out.println("Can't find file");
		}
		
	}

}
