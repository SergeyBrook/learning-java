package assignments.issuetracker.model;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

/**
 * ItemStore class.
 */
public class ItemStore {
	private List<Bug> bugs = new ArrayList<Bug>();
	private List<Task> tasks = new ArrayList<Task>();
	private List<UserStory> userStories = new ArrayList<UserStory>();
	private List<UseCase> useCases = new ArrayList<UseCase>();

	// 1(B). Create new bug:
	public String createNewBug(String description, int priority, int severity) {
		boolean isOk = true;

		Bug bug = new Bug();
		isOk &= bug.setDescription(description);
		isOk &= bug.setPriority(priority);
		isOk &= bug.setSeverity(severity);

		if (isOk) {
			this.bugs.add(bug);
			return "B-" + (this.bugs.size() - 1);
		} else {
			return "";
		}
	}

	// 1(T). Create new task:
	public String createNewTask(String description, int priority, LocalDate dueDate) {
		boolean isOk = true;

		Task task = new Task();
		isOk &= task.setDescription(description);
		isOk &= task.setPriority(priority);
		task.setDueDate(dueDate);

		if (isOk) {
			this.tasks.add(task);
			return "T-" + (this.tasks.size() - 1);
		} else {
			return "";
		}
	}

	// 1(US). Create new user story:
	public String createNewUserStory(String description, int priority, String sprintName) {
		boolean isOk = true;

		UserStory userStory = new UserStory();
		isOk &= userStory.setDescription(description);
		isOk &= userStory.setPriority(priority);
		isOk &= userStory.setSprintName(sprintName);

		if (isOk) {
			this.userStories.add(userStory);
			return "US-" + (this.userStories.size() - 1);
		} else {
			return "";
		}
	}

	// 1(UC). Create new use case:
	public String createNewUseCase(String description, int priority, String userStoryId) {
		boolean isOk = true;

		UseCase useCase = new UseCase();
		isOk &= useCase.setDescription(description);
		isOk &= useCase.setPriority(priority);

		if (this.isValidItemId(userStoryId, "US")) {
			isOk &= useCase.addUserStory(this.getItemIndexById(userStoryId));
		} else {
			isOk = false;
		}

		if (isOk) {
			this.useCases.add(useCase);
			return "UC-" + (this.useCases.size() - 1);
		} else {
			return "";
		}
	}

	// 2. Change item state:
	public boolean changeItemState(String itemId, int state) {
		boolean result = false;

		if (this.isValidItemId(itemId)) {
			String itemType = this.getItemTypeById(itemId);
			int itemIndex = this.getItemIndexById(itemId);

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

	// 6. Change task due date:
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
		String graph;
		int useCaseIndex;
		int userStoryIndex;

		if (this.isValidItemId(itemId, "UC")) {
			useCaseIndex = this.getItemIndexById(itemId);
			System.out.println("* Use case id: " + itemId);
			System.out.println("|  Use case description: " + this.useCases.get(useCaseIndex).getDescription());
			System.out.println("|  Use case priority: " + this.useCases.get(useCaseIndex).getPriority());
			System.out.println("|  Use case date: " + this.useCases.get(useCaseIndex).getCreationDate());
			System.out.println("|  Use case state: " + this.useCases.get(useCaseIndex).getState());

			for (int i = 0; i < this.useCases.get(useCaseIndex).getUserStoriesCount(); i++) {
				graph = (i == this.useCases.get(useCaseIndex).getUserStoriesCount() - 1) ? " " : "|";
				userStoryIndex = this.useCases.get(useCaseIndex).getUserStory(i);
				System.out.println("|");
				System.out.println("*- User story id: US-" + userStoryIndex);
				System.out.println(graph + "   User story description: " + this.userStories.get(userStoryIndex).getDescription());
				System.out.println(graph + "   User story priority: " + this.userStories.get(userStoryIndex).getPriority());
				System.out.println(graph + "   User story date: " + this.userStories.get(userStoryIndex).getCreationDate());
				System.out.println(graph + "   User story state: " + this.userStories.get(userStoryIndex).getState());
				System.out.println(graph + "   User story sprint name: " + this.userStories.get(userStoryIndex).getSprintName());
			}
		} else {
			System.out.println("Invalid use case id.");
		}
	}

	// 8. Change bug severity:
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
			return this.userStories.get(this.getItemIndexById(itemId)).setSprintName(sprintName);
		} else {
			return false;
		}
	}

	public boolean isValidItemId(String itemId) {
		boolean result = false;

		String itemType = this.getItemTypeById(itemId);
		int itemIndex = this.getItemIndexById(itemId);

		if (itemIndex >= 0) {
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

	public boolean isValidItemId(String itemId, String requiredType) {
		boolean result = false;

		if (requiredType.equalsIgnoreCase(this.getItemTypeById(itemId))) {
			result = this.isValidItemId(itemId);
		}

		return result;
	}

	private String getItemTypeById(String itemId) {
		String result = "";
		int tokenIndex = 0;

		if (itemId.trim().length() != 0) {
			String[] idTokens = itemId.split("-");

			if (idTokens.length > tokenIndex) {
				result = idTokens[tokenIndex].toUpperCase();
			}
		}

		return result;
	}

	private int getItemIndexById(String itemId) {
		int result = -1;
		int tokenIndex = 1;

		if (itemId.trim().length() != 0) {
			String[] idTokens = itemId.split("-");

			if (idTokens.length > tokenIndex) {
				try {
					result = Integer.parseInt(idTokens[tokenIndex]);
				} catch (NumberFormatException e) {
					// Do nothing.
				}
			}
		}

		return result;
	}
}
