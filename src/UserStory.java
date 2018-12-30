/**
 * UserStory class.
 */
public class UserStory extends Item {
	protected String sprintName = "N/A";

	public UserStory() {
		super();
	}

	public void setSprintName(String sprintName) {
		this.sprintName = sprintName;
	}

	public String getSprintName() {
		return this.sprintName;
	}
}
