package assignments.issuetracker.model;

/**
 * Bug class.
 */
public class Bug extends Item {
	protected int severity = 1; // 1 - 10

	public Bug() {
		super();
	}

	public boolean setSeverity(int severity) {
		if (severity >= 1 && severity <= 10) {
			this.severity = severity;
			return true;
		} else {
			return false;
		}
	}

	public int getSeverity() {
		return this.severity;
	}
}
