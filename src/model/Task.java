package assignments.issuetracker.model;

import java.time.LocalDate;

/**
 * Task class.
 */
public class Task extends Item {
	protected LocalDate dueDate;

	public Task() {
		super();
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public LocalDate getDueDate() {
		return this.dueDate;
	}
}
