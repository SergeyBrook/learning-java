import java.util.*;

/**
 * App class.
 */
class App {
	private List<Bug> bugs = new ArrayList<Bug>();
	private List<Task> tasks = new ArrayList<Task>();
	private List<UserStory> userStories = new ArrayList<UserStory>();
	private List<UseCase> useCases = new ArrayList<UseCase>();

	public App() {}

	// Debug:
	public void showAllBugs() {
		int i = 0;
		for (Bug bug : this.bugs) {
			System.out.println("### Bug num: " + i + " ###");
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
	public int createNewBug(String description, int priority, int severity) {
		Bug bug = new Bug();

		bug.setDescription(description);
		bug.setPriority(priority);
		bug.setSeverity(severity);

		this.bugs.add(bug);

		return 0;
	}

	// 1.2
	public int createNewTask(String description, int priority, String dueDate) {
		return 0;
	}

	// 1.3
	public int createNewUserStory(String description, int priority, String sprintName) {
		return 0;
	}

	// 1.4
	public int createNewUseCase(String description, int priority, int userStory) {
		return 0;
	}

	// 3
	public void closeAllBugs() {}

	// 4
	public void closeAllTasks() {}
}
