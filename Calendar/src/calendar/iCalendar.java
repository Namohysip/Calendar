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
	private static int eventSequence;
	private String calendar;
	private String CN;
	private ArrayList<Event> allEvents = new ArrayList<Event>();
	
	/**
	 * Default constructor that sets
	 * the calendar's name to an empty string
	 */
	public iCalendar(){
		CN = "";
	}
	/**
	 * Constructor that gives the calendar a common name
	 * @param commonName
	 */
	public iCalendar(String commonName){
		CN = commonName;
	}
	
	/**
	 * Constructor that makes the common name an empty string
	 * and adds all events given into its own event list
	 * @param events
	 */
	public iCalendar(ArrayList<Event> events){
		CN = "";
		allEvents = new ArrayList<Event>(events);
	}
	
	/**
	 * Constructor that sets the common name with the String given
	 * and sets its starting list of events to the list given.
	 * @param events
	 * @param commonName
	 */
	public iCalendar(ArrayList<Event> events, String commonName){
		CN = commonName;
		allEvents = new ArrayList<Event>(events);
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
		calendar += "CALSCALE:GREGORIAN\nMETHOD:PUBLISH\n";
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
	
	public static void setEventSequence(int s){
		eventSequence = s;
	}
	
	public static int getEventSequence(){
		return eventSequence;
	}
	
	public static ArrayList<Event> findFreeTime(ArrayList<Event> busyTimes, String day){
		ArrayList<Event> freeTime = new ArrayList<Event>();
		//TODO: find free time between the given events.
		//NOTE: All of the given events must be on the same day.
		//The second parameter, day, describes this, but this can be changed
		//for alternate implementations. Currently, the 'day' should be in the format
		//YYYYMMDD ie, the part of the DateTime variable before the T.
		
		for(Event e: busyTimes){
			String start = e.getDateTimeS().split("T")[0]; //gets the start day, ie the left side of the T
			String end = e.getDateTimeE().split("T")[0]; //gets the end day, ie the left side of the T
			if(start.equalsIgnoreCase(end)){ //the event must start and end on the same day
				if(start.equalsIgnoreCase(day)){ //the event must be on the day specified
					//an event that gets this far is a valid day to use to find free time.
				}
			}
		}
		return freeTime;
	}
	
	public ArrayList<Event> getEvents(){
		return allEvents;
	}
	
	public Event getEvent(int index){
		if(index >= allEvents.size()){
			return null;
		}
		else{
			return allEvents.get(index);
		}
	}
}
