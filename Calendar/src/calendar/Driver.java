package calendar;
import java.io.*;
public class Driver{

	public static void main(String[] args) {
		/*BufferedReader read;
		try{
			read = new BufferedReader(new FileReader(new File("test.ics")));
			
		}
		catch(FileNotFoundException e){
			System.out.println("Can't find file");
		}
		*/
		Event e = new Event("Basic description", "Test", "UH Manoa", "20150310T091100", "20150401T120000", "CLASS:PRIVATE");
		iCalendar cal = new iCalendar("Test name");
		cal.addEvent(e);
	}

}
