package assignments.issuetracker.providers;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * ConsoleInputProvider class
 */
public class ConsoleInputProvider implements InputProvider {
	private Scanner input = new Scanner(System.in);

	@Override
	public String getStringValue(String name, String message) {
		if (message.trim().length() != 0) {
			System.out.println("Please enter " + name + " (" + message + "):");
		} else if (name.trim().length() != 0) {
			System.out.println("Please enter " + name + ":");
		}
		System.out.print("> ");

		return this.input.nextLine();
	}

	@Override
	public String getStringValue(String message) {
		System.out.println(message);
		System.out.print("> ");

		return this.input.nextLine();
	}

	@Override
	public int getIntValue(String name, String message) {
		int result;
		while (true) {
			try {
				String val = getStringValue(name, message);
				result = Integer.parseInt(val);
				break;
			} catch (NumberFormatException e) {
				System.out.println("Should be an integer number, please try again");
			}
		}
		return result;
	}

	@Override
	public int getIntValue() {
		return getIntValue("", "");
	}

	@Override
	public LocalDate getDateValue(String name, String message) {
		LocalDate result = null;
		while (true) {
			try {
				String val = getStringValue(name, message);
				result = LocalDate.parse(val);
				break;
			} catch (DateTimeParseException e) {
				System.out.println("Should be a date. e.g. 2018-12-31, please try again");
			}
		}
		return result;
	}

	public void close() {
		this.input.close();
	}
}
