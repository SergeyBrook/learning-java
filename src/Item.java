import java.util.*;
import java.text.*;

/**
 * Item (WIT) class.
 */
class Item {
	protected String description = "N/A";
	protected int priority = 1;		// 1 - 10
	protected String dateCreated = "N/A";
	protected int state = 0;		// 0: "To Do", 1: "In Progress", 2: "Done"
	protected String[] states = {"To Do", "In Progress", "Done"};

	public Item() {
		Date dt = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		this.dateCreated = ft.format(dt);
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getDateCreated() {
		return this.dateCreated;
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
}