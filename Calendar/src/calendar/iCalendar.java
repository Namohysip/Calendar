package calendar;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class should be able to CREATE .ics files
 * 
 * @author Noah Higa
 *
 */
public class iCalendar {
	private static int eventSequence;
	private String calendar;
	private String CN;
	private ArrayList<Event> allEvents = new ArrayList<Event>();

	/**
	 * Default constructor that sets the calendar's name to an empty string
	 */
	public iCalendar() {
		CN = "";
	}

	/**
	 * Constructor that gives the calendar a common name
	 * 
	 * @param commonName
	 */
	public iCalendar(String commonName) {
		CN = commonName;
	}

	/**
	 * Constructor that makes the common name an empty string and adds all
	 * events given into its own event list
	 * 
	 * @param events
	 */
	public iCalendar(ArrayList<Event> events) {
		CN = "";
		allEvents = new ArrayList<Event>(events);
	}

	/**
	 * Constructor that sets the common name with the String given and sets its
	 * starting list of events to the list given.
	 * 
	 * @param events
	 * @param commonName
	 */
	public iCalendar(ArrayList<Event> events, String commonName) {
		CN = commonName;
		allEvents = new ArrayList<Event>(events);
	}

	/**
	 * Adds event to the allEvents category
	 * 
	 * @return
	 */
	public void addEvent(Event e) {
		allEvents.add(e);
	}

	/**
	 * Creates an ics file based on the events registered from the addEvent
	 * class
	 */
	public String createics() {
		calendar = "BEGIN:VCALENDAR\nVERSION:2.0\nPRODID:-//Rambutan\n";
		calendar += "CALSCALE:GREGORIAN\nMETHOD:PUBLISH\n";
		/*
		 * For each event, add in a VEVENT to the String representing it
		 */
		for (Event e : allEvents) {
			calendar += "BEGIN:VEVENT\nDTSTART:";
			calendar += e.getDateTimeS();
			calendar += "\nDTEND:" + e.getDateTimeE();
			calendar += "\nDTSTAMP:" + e.getTimeStamp();
			calendar += "\nUID:"; // add in a Unique identification number later
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
		calendar += "END:VCALENDAR\n";
		return calendar;
	}

	/**
	 * Writes a file based on the filename for the calendar.
	 * 
	 * @param filename
	 */
	public void writeics(String filename) {
		createics();
		BufferedWriter write;
		try {
			write = new BufferedWriter(new FileWriter(new File(filename)));
			write.write(calendar);
			write.close();
		} catch (IOException e) {
			System.err.println("Error writing file");
		}
	}

	public static void setEventSequence(int s) {
		eventSequence = s;
	}

	public static int getEventSequence() {
		return eventSequence;
	}
//possible alternative to find free time if it isn't sorted?
	/*
	public static ArrayList<Event> findFreeTime(ArrayList<Event> busyTimes,
			String day) {
		ArrayList<Event> freeTime = new ArrayList<Event>();
		freeTime.add(new Event("free time", "free time", "Anywhere", day
				+ "T000001", day + "T0235959", "PUBLIC", 0));
		int busyStart;
		int busyEnd;
		int freeStart;
		int freeEnd;
		for (Event b : busyTimes) {
			if (b.getDateTimeS().split("T")[0].equals(day)
					&& b.getDateTimeE().split("T")[0].equals(day)) {
				// the event is on the specified day to find free time
				busyStart = Integer.parseInt(b.getDateTimeS().split("T")[1]);
				busyEnd = Integer.parseInt(b.getDateTimeE().split("T")[1]);
				for (int i = 0; i < freeTime.size(); i++) {
					freeStart = Integer.parseInt(freeTime.get(i).getDateTimeS()
							.split("T")[1]);
					freeEnd = Integer.parseInt(freeTime.get(i).getDateTimeE()
							.split("T")[1]);
					if (freeStart >= busyStart) {
						if (freeStart < busyEnd) {
							// freeTime starts during an event.
							if (freeEnd <= busyEnd) {
								// freeTime is within busy time. Remove
								freeTime.remove(i);
								i--;
							} else {// free time ends after event ends
								String newTime = day + "T"
										+ Integer.toString(busyEnd);
								freeTime.get(i).setDateTimeS(newTime);
								// free time now starts when that event ends
							}
						}
						// else{free time started after event ended. Good. }

					} else {// free time starts before busy time starts
						if (freeEnd > busyStart) {
							// free time ends after an event started
							if (freeEnd <= busyEnd) {
								// free time ended at or before that event ended
								String newTime = day + "T"
										+ Integer.toString(busyStart);
								freeTime.get(i).setDateTimeE(newTime);
								// free time now ends when that event starts
							} else {// the busy time is completely during free
									// time
								String newTime = day + "T"
										+ Integer.toString(busyStart);
								// free time ends when busy time starts
								freeTime.get(i).setDateTimeE(newTime);
								// free time starts when busy time ends
								freeTime.add(i + 1, new Event("free time",
										"free time", "Anywhere", day + "T"
												+ Integer.toString(busyEnd),
										day + "T" + Integer.toString(freeEnd),
										"PUBLIC", 0));
							}
						}
						// else{free time started and ended before busy time
						// started. Good}
					}
				}
			}
		}
		return freeTime;
	}
*/

	public static ArrayList<Event> findFreeTime(ArrayList<Event> busyTimes,
			String day) {
		ArrayList<Event> freeTime = new ArrayList<Event>();

		int eCount = busyTimes.size();
		for (int i = 0; (i + 1) < eCount; i++) {

			Event event1 = busyTimes.get(i);
			Event event2 = busyTimes.get(i + 1);

			String endEvent1 = event1.getDateTimeE().split("T")[1];
			String endEventString = event1.getDateTimeE();
			String startEvent2 = event2.getDateTimeS().split("T")[1];
			String startEventString = event2.getDateTimeS();

			int end = Integer.parseInt(endEvent1);
			int start = Integer.parseInt(startEvent2);
			System.out.println(end + "and" + start);
			int result = end - start;

			if (result > 0) {

				Event freeTimeEvent = new Event("free time", "free time",
						"Anywhere", endEventString, startEventString, "PUBLIC",
						0);
				freeTime.add(freeTimeEvent);
			}
		}
		return freeTime;
	}

	public ArrayList<Event> getEvents() {
		return allEvents;
	}

	public Event getEvent(int index) {
		if (index >= allEvents.size()) {
			return null;
		} else {
			return allEvents.get(index);
		}
	}
}
