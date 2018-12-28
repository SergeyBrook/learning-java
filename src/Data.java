import java.util.*;

/**
 * Data class.
 */
class Data {
	private List<Bug> bugs = new ArrayList<Bug>();
	private List<Task> tasks = new ArrayList<Task>();
	private List<UserStory> userStories = new ArrayList<UserStory>();
	private List<UseCase> useCases = new ArrayList<UseCase>();

	public Data() {}

	// Debug:
	public void showAllBugs() {
		int i = 0;
		for (Bug bug : this.bugs) {
			System.out.println("### Bug num: B-" + i + " ###");
			System.out.println("### Bug description: " + bug.getDescription());
			System.out.println("### Bug priority: " + bug.getPriority());
			System.out.println("### Bug date: " + bug.getDateCreated());
			System.out.println("### Bug state: " + bug.getState());
			System.out.println("### Bug severity: " + bug.getSeverity());
			System.out.println();
			i++;
		}
	}

	// 1.1
	public String createNewBug(String description, int priority, int severity) {
		Bug bug = new Bug();

		bug.setDescription(description);
		bug.setPriority(priority);
		bug.setSeverity(severity);

		this.bugs.add(bug);

		return "B-" + (this.bugs.size() - 1);
	}

	// 1.2
	public String createNewTask(String description, int priority, String dueDate) {
		return "T-0";
	}

	// 1.3
	public String createNewUserStory(String description, int priority, String sprintName) {
		return "US-0";
	}

	// 1.4
	public String createNewUseCase(String description, int priority, int userStory) {
		return "UC-0";
	}

	// 3
	public void closeAllBugs() {
		for (Bug bug : this.bugs) {
			if (bug.getStateCode() == 0) {
				bug.setState(1);
			}
			bug.setState(2);
		}
	}

	// 4
	public void closeAllTasks() {
		for (Task task : this.tasks) {
			if (task.getStateCode() == 0) {
				task.setState(1);
			}
			task.setState(2);
		}
	}
}