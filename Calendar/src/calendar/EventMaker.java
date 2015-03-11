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
public class EventMaker {
	
	ArrayList<Event> allEvents = new ArrayList<Event>();
	
	public EventMaker(){
		
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
	public void createics(Event[] events){
		
	}
}
