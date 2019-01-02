package assignments.issuetracker.model;

/**
 * UserStory class.
 */
public class UserStory extends Item {
	protected String sprintName = "N/A";

	public UserStory() {
		super();
	}

	public boolean setSprintName(String sprintName) {
		if (sprintName.trim().length() != 0) {
			this.sprintName = sprintName;
			return true;
		} else {
			return false;
		}
	}

	public String getSprintName() {
		return this.sprintName;
	}
}
