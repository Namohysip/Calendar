package calendar;


import java.io.*;
import java.util.Scanner;

public class Driver {

	/**
	 * Basic driver method that creates the required event to test output.
	 * Asks for user input. Once user is done, creates .ics file called 'test'
	 * @param args
	 */
	public static void main(String[] args) {
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
						if(month < 10){
							input = "0" + Integer.toString(month);
						}
						else{
							input = Integer.toString(month);
						}
						startDate += input;
						if(day < 10){
							input = "0" + Integer.toString(day);
						}
						else{
							input = Integer.toString(day);
						}
						startDate += input;
						startDate += 'T';
						if(hour < 10){
							input = "0" + Integer.toString(hour);
						}
						else{
							input = Integer.toString(hour);
						}
						startDate += input;
						if(minute < 10){
							input = "0" + Integer.toString(minute);
						}
						else{
							input = Integer.toString(minute);
						}
						startDate += input;
						if(second < 10){
							input = "0" + Integer.toString(second);
						}
						else{
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
						if(month < 10){
							input = "0" + Integer.toString(month);
						}
						else{
							input = Integer.toString(month);
						}
						endDate += input;
						if(day < 10){
							input = "0" + Integer.toString(day);
						}
						else{
							input = Integer.toString(day);
						}
						endDate += input;
						endDate += 'T';
						if(hour < 10){
							input = "0" + Integer.toString(hour);
						}
						else{
							input = Integer.toString(hour);
						}
						endDate += input;
						if(minute < 10){
							input = "0" + Integer.toString(minute);
						}
						else{
							input = Integer.toString(minute);
						}
						endDate += input;
						if(second < 10){
							input = "0" + Integer.toString(second);
						}
						else{
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
				while(!valid){
					System.out.println("1 as highest and 9 as lowest, what is the priority of this event?");
					System.out.println("Enter 0 if there is no priority");
					try{
						valid = true;
						priority = scan.nextInt();
						if(priority < 0 || priority > 9){
							valid = false;
							System.out.println("Priority must be 0-9");
						}
					}
					catch(Exception e){
						System.out.println("Invalid input");
						valid = false;
					}
				}
				Event e = new Event(description, title,
						location, startDate, endDate,
						classification, priority);
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

	}

	/**
	 * Helper method that checks if the time inputs are correct.
	 * Does not check for specific day limits for each month.
	 * Therefore a bug: Feb. 31 is a valid date, for example
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