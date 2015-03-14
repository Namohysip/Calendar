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
	public String getDateTimeS() {
		return dateTimeS;
	}
	public void setDateTimeS(String dateTimeS) {
		this.dateTimeS = dateTimeS;
	}
	public String getDateTimeE() {
		return dateTimeE;
	}
	public void setDateTimeE(String dateTimeE) {
		this.dateTimeE = dateTimeE;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getClassType() {
		return classType;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}
	public String getTimeCreated() {
		return timeCreated;
	}
	public void setTimeCreated(String timeCreated) {
		this.timeCreated = timeCreated;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLastModified() {
		return lastModified;
	}
	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	private String location;
	private String status;
	private String summary;
	private int sequence;
	private int priority;
	/**
	 * 
	 * @param description
	 * @param summary
	 * @param location
	 * @param timeStart
	 * @param timeEnd
	 * @param classT
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
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
}
