package calendar;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {

	/**
	 * Main driver method that takes in its arguments a set of file names or
	 * pathnames and puts all events in that file into one calendar. It then
	 * tries to find the free time of all events that are on the same day as the
	 * start date of the first event input into the calendar. and makes a new
	 * calendar based on that free time. It writes the .ics file of the free
	 * time events into "freeTime.ics"
	 * 
	 * @param args
	 *            the list of .ics files to write.
	 */
	public static void main(String[] args) {
		if (args.length < 1) {
			System.err
					.println("There are no file directories in the arguments.");
			System.exit(1);
		}
		BufferedReader read;
		Scanner scan;
		iCalendar calendar = new iCalendar(); // the calendar all events will be
												// compiled into
		Event event = null; // forms the events to add to the calendar
		String input = null; // each line of the file
		String[] split = null; // splits the .ics file lines between ':'
								// characters
		boolean error = false; // flag if something went wrong
		boolean finished = false; // flag for finding "END:VCALENDAR"
		for (int i = 0; i < args.length; i++) {
			if (input.endsWith(".ics")) {
				try {
					finished = false;
					error = false;
					read = new BufferedReader(new FileReader(new File(args[i])));
					scan = new Scanner(read);
					while (scan.hasNext() && !error && !finished) {
						input = scan.nextLine();
						split = input.split(":");
						// The following if-else chain covers supported value
						// types
						// and adds them to a new event or calendar until an
						// "END" statement
						// is found. For events, it will put that event into the
						// calendar.
						// for END:VCALENDAR, it stops reading from that file.
						if (split[0].equalsIgnoreCase("BEGIN")) {
							if (split[1].equalsIgnoreCase("VEVENT")) {
								event = new Event();
							} else if (split[1].equalsIgnoreCase("VCALENDAR")) {

							} else {
								System.out.println("Element " + split[1]
										+ " is not supported");
							}
						} else if (split[0].equalsIgnoreCase("VERSION")) {
							if (!split[1].equalsIgnoreCase("2.0")) {
								System.err.println("Version " + split[1]
										+ " is not supported");
								error = true;
							}
						} else if (split[0].equalsIgnoreCase("CALSCALE")) {
							if (!split[1].equalsIgnoreCase("GREGORIAN")) {
								System.err.println("Time format " + split[1]
										+ " is not supported");
								error = true;
							}
						} else if (split[0].equalsIgnoreCase("DTSTART")) {
							event.setDateTimeS(split[1]);
						} else if (split[0].equalsIgnoreCase("DTEND")) {
							event.setDateTimeE(split[1]);
						} else if (split[0].equalsIgnoreCase("CLASS")) {
							event.setClassType(split[1]);
						} else if (split[0].equalsIgnoreCase("CREATED")) {
							event.setTimeCreated(split[1]);
						} else if (split[0].equalsIgnoreCase("DESCRIPTION")) {
							event.setDescription(split[1]);
						} else if (split[0].equalsIgnoreCase("LAST-MODIFIED")) {
							event.setLastModified(split[1]);
						} else if (split[0].equalsIgnoreCase("LOCATION")) {
							event.setLocation(split[1]);
						} else if (split[0].equalsIgnoreCase("SEQIENCE")) {
							event.setSequence(Integer.parseInt(split[1]));
							event.resetUID();
						} else if (split[0].equalsIgnoreCase("STATUS")) {
							event.setStatus(split[1]);
						} else if (split[0].equalsIgnoreCase("SUMMARY")) {
							event.setSummary(split[1]);
						} else if (split[0].equalsIgnoreCase("PRIORITY")) {
							event.setPriority(Integer.parseInt(split[1]));
						} else if (split[0].equalsIgnoreCase("END")) {
							if (split[1].equalsIgnoreCase("VEVENT")) {
								calendar.addEvent(event);
								event = null;
							} else if (split[1].equalsIgnoreCase("VCALENDAR")) {
								finished = true;
							}
						}

					}
					if (error) {
						System.out
								.println("Did not create iCalendar due to errors listed.");
					} else if (finished) {

					} else {
						System.err
								.println("There was a problem with terminating the Calendar creation.");
					}
				} catch (FileNotFoundException e) {
					System.err.println("Could not find file " + args[i]);
				} catch (NullPointerException e) {
					System.err
							.println(".ics file's syntax is incorrect or corrupted");
				}
			} else {
				System.err.println(input + " is not an .ics file");
			}
		}
		String freeDay = calendar.getEvent(0).getDateTimeS().split("T")[0];
		ArrayList<Event> freeTime = iCalendar.findFreeTime(
				calendar.getEvents(), freeDay);
		iCalendar freeTimeCalendar = new iCalendar(freeTime);
		try {

			BufferedWriter write = new BufferedWriter(new FileWriter(new File(
					"freeTime.ics")));
			write.write(freeTimeCalendar.createics());
			write.close();
		} catch (IOException e) {
			System.err.println("Unable to write file");
		}
	}

	/**
	 * Asks the user for an event or events to enter into a calendar manually.
	 * Was once used for testing in check-in 1.
	 */
	public static void manualEntry() {
		Scanner scan = new Scanner(System.in);
		boolean quit = false;
		boolean valid = false;
		String input = "";
		String title;
		String description;
		String location;
		String startDate = "";
		String endDate = "";
		String classification = "";
		int year;
		int month;
		int day;
		int hour;
		int minute;
		int second;
		int priority = 0;

		System.out.println("Name this calendar:");
		input = scan.nextLine();
		iCalendar cal = new iCalendar(input);

		while (!quit) {
			quit = false;
			System.out.println("Would you like to enter an event? Y/N");
			input = scan.nextLine();
			if (input.equals("Y")) {
				System.out.println("Enter title of event:");
				title = scan.nextLine();
				System.out.println("Enter description of event:");
				description = scan.nextLine();
				System.out.println("Where will this event take place?");
				location = scan.nextLine();
				valid = false;

				System.out.println("When wll this event start?");
				while (!valid) {
					try {
						valid = true;
						System.out.print("Four-digit year: ");
						year = scan.nextInt();
						System.out.println("Number of month: ");
						month = scan.nextInt();
						System.out.println("Number of day: ");
						day = scan.nextInt();
						System.out.println("Hour (1 pm = hour 13): ");
						hour = scan.nextInt();
						System.out.println("Minute: ");
						minute = scan.nextInt();
						System.out.println("Second: ");
						second = scan.nextInt();
						if (!checkTime(year, month, day, hour, minute, second)) {
							System.out
									.println("Something is wrong with the date entered");
							valid = false;
						}
						startDate = Integer.toString(year);
						if (month < 10) {
							input = "0" + Integer.toString(month);
						} else {
							input = Integer.toString(month);
						}
						startDate += input;
						if (day < 10) {
							input = "0" + Integer.toString(day);
						} else {
							input = Integer.toString(day);
						}
						startDate += input;
						startDate += 'T';
						if (hour < 10) {
							input = "0" + Integer.toString(hour);
						} else {
							input = Integer.toString(hour);
						}
						startDate += input;
						if (minute < 10) {
							input = "0" + Integer.toString(minute);
						} else {
							input = Integer.toString(minute);
						}
						startDate += input;
						if (second < 10) {
							input = "0" + Integer.toString(second);
						} else {
							input = Integer.toString(second);
						}
						startDate += input;
					} catch (Exception e) {
						valid = false;
					}
				}
				valid = false;
				System.out.println("When wll this event end?");
				while (!valid) {
					try {
						valid = true;
						System.out.print("Four-digit year: ");
						year = scan.nextInt();
						System.out.println("Number of month: ");
						month = scan.nextInt();
						System.out.println("Number of day: ");
						day = scan.nextInt();
						System.out.println("Hour (1 pm = hour 13): ");
						hour = scan.nextInt();
						System.out.println("Minute: ");
						minute = scan.nextInt();
						System.out.println("Second: ");
						second = scan.nextInt();
						if (!checkTime(year, month, day, hour, minute, second)) {
							System.out
									.println("Something is wrong with the date entered");
							valid = false;
						}
						endDate = Integer.toString(year);
						if (month < 10) {
							input = "0" + Integer.toString(month);
						} else {
							input = Integer.toString(month);
						}
						endDate += input;
						if (day < 10) {
							input = "0" + Integer.toString(day);
						} else {
							input = Integer.toString(day);
						}
						endDate += input;
						endDate += 'T';
						if (hour < 10) {
							input = "0" + Integer.toString(hour);
						} else {
							input = Integer.toString(hour);
						}
						endDate += input;
						if (minute < 10) {
							input = "0" + Integer.toString(minute);
						} else {
							input = Integer.toString(minute);
						}
						endDate += input;
						if (second < 10) {
							input = "0" + Integer.toString(second);
						} else {
							input = Integer.toString(second);
						}
						endDate += input;
					} catch (Exception e) {
						valid = false;
					}
				}
				scan.nextLine();
				valid = false;
				while (!valid) {
					System.out
							.println("Is this event public, private, or confidential?");
					input = scan.nextLine();
					valid = true;
					if (input.equalsIgnoreCase("Private")
							|| input.equalsIgnoreCase("public")
							|| input.equalsIgnoreCase("confidential")) {
						classification = input;
					} else {
						valid = false;
						System.out.println("That is not a valid event type");
					}

				}
				valid = false;
				while (!valid) {
					System.out
							.println("1 as highest and 9 as lowest, what is the priority of this event?");
					System.out.println("Enter 0 if there is no priority");
					try {
						valid = true;
						priority = scan.nextInt();
						if (priority < 0 || priority > 9) {
							valid = false;
							System.out.println("Priority must be 0-9");
						}
					} catch (Exception e) {
						System.out.println("Invalid input");
						valid = false;
					}
				}
				Event e = new Event(description, title, location, startDate,
						endDate, classification, priority);
				cal.addEvent(e);
				scan.nextLine();

			}

			else {
				quit = true;
			}
		}

		input = cal.createics();
		System.out.println(input);
		cal.writeics(input, "test.ics");
		scan.close();

	}

	/**
	 * Helper method that checks if the time inputs are correct. Does not check
	 * for specific day limits for each month. Therefore a bug: Feb. 31 is a
	 * valid date, for example
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param minute
	 * @param second
	 * @return
	 */
	public static boolean checkTime(int year, int month, int day, int hour,
			int minute, int second) {
		if (year < 1900 || year > 2200) {
			return false;
		}
		if (month > 12 || month < 0) {
			return false;
		}
		if (day < 0 || day > 31) {
			return false;
		}
		if (hour < 0 || hour > 24) {
			return false;
		}
		if (minute < 0 || minute > 59) {
			return false;
		}
		if (second < 0 || second > 59) {
			return false;
		}
		return true;
	}
}