import java.util.*;
import java.time.LocalDate;

/**
 * ItemStore class.
 */
public class ItemStore {
	private List<Bug> bugs = new ArrayList<Bug>();
	private List<Task> tasks = new ArrayList<Task>();
	private List<UserStory> userStories = new ArrayList<UserStory>();
	private List<UseCase> useCases = new ArrayList<UseCase>();

	public ItemStore() {}

	// Debug:
	public void showAllBugs() {
		int i = 0;
		for (Bug bug : this.bugs) {
			System.out.println("### Bug id: B-" + i + " ###");
			System.out.println("### Bug description: " + bug.getDescription());
			System.out.println("### Bug priority: " + bug.getPriority());
			System.out.println("### Bug date: " + bug.getCreationDate());
			System.out.println("### Bug state: " + bug.getState());
			System.out.println("### Bug severity: " + bug.getSeverity());
			System.out.println();
			i++;
		}
	}


	// 1(B). Create new bug:
	public String createNewBug(String description, int priority, int severity) {
		Bug bug = new Bug();

		bug.setDescription(description);
		bug.setPriority(priority);
		bug.setSeverity(severity);

		this.bugs.add(bug);

		return "B-" + (this.bugs.size() - 1);
	}

	// 1(T). Create new task:
	public String createNewTask(String description, int priority, LocalDate dueDate) {
		Task task = new Task();

		task.setDescription(description);
		task.setPriority(priority);
		task.setDueDate(dueDate);

		this.tasks.add(task);

		return "T-" + (this.tasks.size() - 1);
	}

	// 1(US). Create new user story:
	public String createNewUserStory(String description, int priority, String sprintName) {
		UserStory userStory = new UserStory();

		userStory.setDescription(description);
		userStory.setPriority(priority);
		userStory.setSprintName(sprintName);

		this.userStories.add(userStory);

		return "US-" + (this.userStories.size() - 1);
	}

	// 1(UC). Create new use case:
	public String createNewUseCase(String description, int priority, String userStoryId) {
		UseCase useCase = new UseCase();

		useCase.setDescription(description);
		useCase.setPriority(priority);

		if (this.isValidItemId(userStoryId, "US")) {
			// TODO: Handle invalid userStoryId.
			useCase.addUserStory(this.getItemIndexById(userStoryId));
		}

		this.useCases.add(useCase);

		return "UC-" + (this.useCases.size() - 1);
	}

	// 2. Set item state:
	public boolean setItemState(String itemId, int state) {
		boolean result = false;

		String itemType = this.getItemTypeById(itemId);
		int itemIndex = this.getItemIndexById(itemId);

		if (this.isValidItemId(itemId, itemType)) {
			switch (itemType) {
				case "B":
					result = this.bugs.get(itemIndex).setState(state);
					break;
				case "T":
					result = this.tasks.get(itemIndex).setState(state);
					break;
				case "US":
					result = this.userStories.get(itemIndex).setState(state);
					break;
				case "UC":
					result = this.useCases.get(itemIndex).setState(state);
					break;
				default:
					break;
			}
		}

		return result;
	}

	// 3. Close all bugs:
	public void closeAllBugs() {
		for (Bug bug : this.bugs) {
			if (bug.getStateCode() == 0) {
				bug.setState(1);
			}
			bug.setState(2);
		}
	}

	// 4. Close all tasks:
	public void closeAllTasks() {
		for (Task task : this.tasks) {
			if (task.getStateCode() == 0) {
				task.setState(1);
			}
			task.setState(2);
		}
	}

	// 5. Link user story to use case:
	public boolean linkUserStoryToUseCase(String useCaseId, String userStoryId) {
		boolean result = false;

		if (this.isValidItemId(useCaseId, "UC")) {
			if (this.isValidItemId(userStoryId, "US")) {
				result = this.useCases.get(this.getItemIndexById(useCaseId)).addUserStory(this.getItemIndexById(userStoryId));
			}
		}

		return result;
	}

	// 6. Set new due date for task:
	public boolean changeTaskDueDate(String itemId, LocalDate dueDate) {
		if (this.isValidItemId(itemId, "T")) {
			this.tasks.get(this.getItemIndexById(itemId)).setDueDate(dueDate);
			return true;
		} else {
			return false;
		}
	}

	// 7. Print use case and all linked user stories:
	public void showUseCase(String itemId) {
		if (this.isValidItemId(itemId, "UC")) {
			int useCaseIndex = this.getItemIndexById(itemId);
			System.out.println("# Use case id: " + itemId);
			System.out.println("# Use case description: " + this.useCases.get(useCaseIndex).getDescription());
			System.out.println("# Use case priority: " + this.useCases.get(useCaseIndex).getPriority());
			System.out.println("# Use case date: " + this.useCases.get(useCaseIndex).getCreationDate());
			System.out.println("# Use case state: " + this.useCases.get(useCaseIndex).getState());
			System.out.println();

			for (int i = 0; i < this.useCases.get(useCaseIndex).getUserStoriesCount(); i++) {
				int userStoryIndex = this.useCases.get(useCaseIndex).getUserStory(i);
				System.out.println("## User story id: US-" + userStoryIndex);
				System.out.println("## User story description: " + this.userStories.get(userStoryIndex).getDescription());
				System.out.println("## User story priority: " + this.userStories.get(userStoryIndex).getPriority());
				System.out.println("## User story date: " + this.userStories.get(userStoryIndex).getCreationDate());
				System.out.println("## User story state: " + this.userStories.get(userStoryIndex).getState());
				System.out.println("## User story sprint name: " + this.userStories.get(userStoryIndex).getSprintName());
				System.out.println();
			}

			System.out.println();
		} else {
			System.out.println("Invalid use case id.");
		}
	}

	// 8. Set bug severity:
	public boolean changeBugSeverity(String itemId, int severity) {
		if (this.isValidItemId(itemId, "B")) {
			return this.bugs.get(this.getItemIndexById(itemId)).setSeverity(severity);
		} else {
			return false;
		}
	}

	// 9. Change sprint name in user story:
	public boolean changeUserStorySprintName(String itemId, String sprintName) {
		if (this.isValidItemId(itemId, "US")) {
			this.userStories.get(this.getItemIndexById(itemId)).setSprintName(sprintName);
			return true;
		} else {
			return false;
		}
	}


	private boolean isValidItemId(String itemId, String requiredType) {
		boolean result = false;

		String itemType = this.getItemTypeById(itemId);
		int itemIndex = this.getItemIndexById(itemId);

		if (itemIndex >= 0 && itemType.equals(requiredType)) {
			switch (itemType) {
				case "B":
					result = (itemIndex < this.bugs.size()) ? true : false;
					break;
				case "T":
					result = (itemIndex < this.tasks.size()) ? true : false;
					break;
				case "US":
					result = (itemIndex < this.userStories.size()) ? true : false;
					break;
				case "UC":
					result = (itemIndex < this.useCases.size()) ? true : false;
					break;
				default:
					break;
			}
		}

		return result;
	}

	private String getItemTypeById(String itemId) {
		if (itemId.trim().length() != 0) {
			String[] idTokens = itemId.split("-");
			return idTokens[0];
		} else {
			return "";
		}
	}

	private int getItemIndexById(String itemId) {
		if (itemId.trim().length() != 0) {
			String[] idTokens = itemId.split("-");
			// TODO: Catch NumberFormatException.
			return Integer.parseInt(idTokens[1]);
		} else {
			return -1;
		}
	}
}
