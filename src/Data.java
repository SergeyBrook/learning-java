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

	// 1 - Bug
	public String createNewBug(String description, int priority, int severity) {
		Bug bug = new Bug();

		bug.setDescription(description);
		bug.setPriority(priority);
		bug.setSeverity(severity);

		this.bugs.add(bug);

		return "B-" + (this.bugs.size() - 1);
	}

	// 1 - Task
	public String createNewTask(String description, int priority, String dueDate) {
		Task task = new Task();

		task.setDescription(description);
		task.setPriority(priority);
		task.setDueDate(dueDate);

		this.tasks.add(task);

		return "T-" + (this.tasks.size() - 1);
	}

	// 1 User story
	public String createNewUserStory(String description, int priority, String sprintName) {
		UserStory userStory = new UserStory();

		userStory.setDescription(description);
		userStory.setPriority(priority);
		userStory.setSprintName(sprintName);

		this.userStories.add(userStory);

		return "US-" + (this.userStories.size() - 1);
	}

	// 1 - Use case
	public String createNewUseCase(String description, int priority, int userStory) {
		UseCase useCase = new UseCase();

		useCase.setDescription(description);
		useCase.setPriority(priority);
		useCase.addUserStory(userStory);

		this.useCases.add(useCase);

		return "UC-" + (this.useCases.size() - 1);
	}

	// 2
	public boolean setItemState(String itemId, int state) {
		boolean result = false;

		// TODO: Check for valid input.
		String[] idTokens = itemId.split("-");
		int idx = Integer.parseInt(idTokens[1]);

		if (idx >= 0) {
			if (idTokens[0].equals("B")) {
				// Bug:
				if (idx < this.bugs.size()) {
					result = this.bugs.get(idx).setState(state);
				}
			} else if (idTokens[0].equals("T")) {
				// Task:
				if (idx < this.tasks.size()) {
					result = this.tasks.get(idx).setState(state);
				}
			} else if (idTokens[0].equals("US")) {
				// User story:
				if (idx < this.userStories.size()) {
					result = this.userStories.get(idx).setState(state);
				}
			} else if (idTokens[0].equals("UC")) {
				// Use case:
				if (idx < this.useCases.size()) {
					result = this.useCases.get(idx).setState(state);
				}
			}
		}

		return result;
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
