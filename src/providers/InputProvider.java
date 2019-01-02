package assignments.issuetracker.providers;

import java.time.LocalDate;

public interface InputProvider {
	String getStringValue(String name, String message);

	String getStringValue(String message);

	int getIntValue(String name, String message);

	int getIntValue();

	LocalDate getDateValue(String name, String message);
}
