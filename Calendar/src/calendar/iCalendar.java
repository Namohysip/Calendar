/**
 * 
 */
package calendar;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class should be able to CREATE .ics files
 * @author Noah Higa
 *
 */
public class iCalendar {
	private String calendar;
	private String CN;
	private ArrayList<Event> allEvents = new ArrayList<Event>();
	
	/**
	 * Constructor that gives the calendar a common name
	 * @param commonName
	 */
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
	 * Creates an ics file based on the events registered from the addEvent class
	 */
	public String createics(){
		calendar = "BEGIN:VCALENDAR\nVERSION:2.0\nPRODID:-//Rambutan\n";
		calendar += "CALSCALE:GEORGIAN\nMETHOD:PUBLISH\n";
		/*
		 * For each event, add in a VEVENT to the String representing it
		 */
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
	
	/**
	 * Writes a file based on the filename given and the .ics content.
	 * @param calendar
	 * @param filename
	 */
	public void writeics(String calendar, String filename){
		BufferedWriter write;
		try{
			write = new BufferedWriter(new FileWriter(new File(filename)));
			write.write(calendar);
			write.close();
		}
		catch(IOException e){
			System.err.println("Error writing file");
		}
	}
}
