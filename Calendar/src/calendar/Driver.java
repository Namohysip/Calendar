package calendar;
import java.io.*;
public class Driver{

	/**
	 * Basic driver method that creates the required event to test output.
	 * TODO: Still needs user input via the console through a Scanner or similar class
	 * @param args
	 */
	public static void main(String[] args) {
		/*BufferedReader read;
		try{
			read = new BufferedReader(new FileReader(new File("test.ics")));
			
		}
		catch(FileNotFoundException e){
			System.out.println("Can't find file");
		}
		*/
		Event e = new Event("Studying for finals", "Studying for finals", "Hamilton Library", "20150508T180000", "20150508T220000", "PUBLIC", 1);
		iCalendar cal = new iCalendar("Test name");
		cal.addEvent(e);
		System.out.println(cal.createics());
	}

}
