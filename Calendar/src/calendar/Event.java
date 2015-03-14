/**
 * 
 */
package calendar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Noah Higa
 *
 */
public class Event {
	private String dateTimeS;
	private String dateTimeE;
	private String timeStamp;
	private String classType;
	private String timeCreated;
	private String description;
	private String lastModified;
	private String location;
	private String status;
	private String summary;
	private int sequence;
	private int priority;
	
	/**
	 * Constructor that creates an event based on the
	 * paramaters passed in, as follows:
	 * @param description a brief description of the event
	 * @param summary Essentially a title of the event
	 * @param location Where the event will take place
	 * @param timeStart When the event will start
	 * @param timeEnd When the event will end
	 * @param classT The type of class (Private, public)
	 * @param priority the priority of the event (from 0 to 9, 1 = highest 9 = lowest 0 = undefined)
	 */
	public Event(String description, String summary, String location, String timeStart, String timeEnd, String classT, int priority){
		dateTimeS = timeStart;
		dateTimeE = timeEnd;
		DateFormat df = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
		Date date = new Date();
		timeStamp = df.format(date.getTime());
		classType = classT;
		timeCreated = new String(timeStamp);
		lastModified = new String(timeStamp);
		this.description = description;
		this.location = location;
		sequence = 0;
		status = "CONFIRMED";
		this.summary = summary;
		this.priority = priority;
	}
	/**
	 * Gets the start date for the event
	 * @return
	 */
	public String getDateTimeS() {
		return dateTimeS;
	}
	
	/**
	 * Sets the start date/time for the event.
	 * @param dateTimeS
	 */
	public void setDateTimeS(String dateTimeS) {
		this.dateTimeS = dateTimeS;
	}
	
	/**
	 * Gets the end date/time for the event
	 * @return
	 */
	public String getDateTimeE() {
		return dateTimeE;
	}
	
	/**
	 * Sets the end date/time for the event
	 * @param dateTimeE
	 */
	public void setDateTimeE(String dateTimeE) {
		this.dateTimeE = dateTimeE;
	}
	
	/**
	 * Gets the timestamp of the event creation
	 * @return
	 */
	public String getTimeStamp() {
		return timeStamp;
	}
	
	/**
	 * Sets the timestamp of the event's creation
	 * @param timeStamp
	 */
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	/**
	 * Gets the classification of the event
	 * @return
	 */
	public String getClassType() {
		return classType;
	}
	
	/**
	 * Sets the classification of the event
	 * @param classType
	 */
	public void setClassType(String classType) {
		this.classType = classType;
	}
	
	/**
	 * Gets the date/time that the event was created
	 * @return
	 */
	public String getTimeCreated() {
		return timeCreated;
	}
	
	/**
	 * Sets the date/time that the event was created
	 * @param timeCreated
	 */
	public void setTimeCreated(String timeCreated) {
		this.timeCreated = timeCreated;
	}
	
	/**
	 * Gets the description of the event
	 * @return
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets the description of the event
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Gets the date/time of when the event was last modified
	 * @return
	 */
	public String getLastModified() {
		return lastModified;
	}
	
	/**
	 * Sets the date/time of when the event was last modified
	 * @param lastModified
	 */
	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}
	
	/**
	 * Gets the location of where the event will take place
	 * @return
	 */
	public String getLocation() {
		return location;
	}
	
	/**
	 * Sets the location of where the event will take place
	 * @param location
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	
	/**
	 * Gets the status of the event
	 * @return
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * Sets the status of the event
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * Gets the summary of the event
	 * @return
	 */
	public String getSummary() {
		return summary;
	}
	
	/**
	 * Sets the summary of the event
	 * @param summary
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	/**
	 * Gets the sequence number of the event (How many times it has been modified)
	 * @return
	 */
	public int getSequence() {
		return sequence;
	}
	
	/**
	 * Sets the sequence number of the event
	 * @param sequence
	 */
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	
	/**
	 * Gets the priority level of the event
	 * @return
	 */
	public int getPriority() {
		return priority;
	}
	
	/**
	 * Sets the priority level of the event
	 * @param priority
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}
}
