package assignments.issuetracker.model;

import java.time.LocalDate;

/**
 * Item (WIT) class.
 */
public class Item {
	protected String description = "N/A";
	protected int priority = 1;
	private final LocalDate creationDate = LocalDate.now();
	protected int state = 0; // 0: "To Do", 1: "In Progress", 2: "Done"
	protected String[] states = {"To Do", "In Progress", "Done"};

	public boolean setDescription(String description) {
		if (description.trim().length() != 0) {
			this.description = description;
			return true;
		} else {
			return false;
		}
	}

	public String getDescription() {
		return this.description;
	}

	public boolean setPriority(int priority) {
		if (priority >= 1 && priority <= 10) {
			this.priority = priority;
			return true;
		} else {
			return false;
		}
	}

	public int getPriority() {
		return this.priority;
	}

	public LocalDate getCreationDate() {
		return this.creationDate;
	}

	public boolean setState(int state) {
		boolean result = false;
		if (state >= 0 && state < this.states.length) {
			if (!(this.state == 0 && state == 2) && !(this.state == 1 && state == 0) && !(this.state == 2 && state == 0)) {
				this.state = state;
				result = true;
			}
		}
		return result;
	}

	public String getState() {
		return this.states[this.state];
	}

	public int getStateCode() {
		return this.state;
	}
}
