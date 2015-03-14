/**
 * 
 */
package calendar;

import java.util.ArrayList;

/**
 * This class should be able to CREATE .ics files
 * @author Noah Higa
 *
 */
public class iCalendar {
	String calendar;
	String CN;
	ArrayList<Event> allEvents = new ArrayList<Event>();
	
	public iCalendar(String commonName){
		CN = commonName;
	}
	
	/**
	 * Adds event to the allEvents category
	 * @return
	 */
	public void addEvent(Event e){
		allEvents.add(e);
	}
	
	/**
	 * Creates an ics file based on the events that it is sent
	 * @param events
	 */
	public String createics(){
		calendar = "BEGIN:VCALENDAR\nVERSION:2.0\nPRODID:-//Rambutan\n";
		calendar += "CALSCALE:GEORGIAN\nMETHOD:PUBLISH\n";
		for(Event e: allEvents){
			calendar += "BEGIN:VEVENT\nDTSTART:";
			calendar += e.getDateTimeS();
			calendar += "\nDTEND:" + e.getDateTimeE();
			calendar += "\nDTSTAMP:" + e.getTimeStamp();
			calendar += "\nUID:"; //add in a Unique identification number later
			calendar += "\nCLASS:" + e.getClassType();
			calendar += "\nCREATED:" + e.getTimeCreated();
			calendar += "\nDESCRIPTION:" + e.getDescription();
			calendar += "\nLAST-MODIFIED:" + e.getLastModified();
			calendar += "\nLOCATION:" + e.getLocation();
			calendar += "\nSEQUENCE:" + Integer.toString(e.getSequence());
			calendar += "\nSTATUS:" + e.getStatus();
			calendar += "\nSUMMARY:" + e.getSummary();
			calendar += "\nPRIORITY:" + e.getPriority();
			calendar += "\nEND:VEVENT\n";
		}
		calendar +="END:VCALENDAR\n";
		return calendar;
	}
}
