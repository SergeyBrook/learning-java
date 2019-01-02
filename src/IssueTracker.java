package assignments.issuetracker;

import assignments.issuetracker.model.ItemStore;

import java.time.LocalDate;

/**
 * IssueTracker class.
 */
public class IssueTracker {
	private ItemStore itemStore = new ItemStore();
	private IssueTrackerInputProvider inputProvider = new IssueTrackerInputProvider(itemStore);

	public static void main(String[] args) {
		IssueTracker issueTracker = new IssueTracker();
		issueTracker.mainMenu();
	}

	private void mainMenu() {
		int menuItem;

		String itemId;
		String subitemId;
		String sprintName;
		LocalDate dueDate;
		int state;
		int severity;

		fromWhile:
		while (true) {
			System.out.println("");
			System.out.println("================================");
			System.out.println("* Issue Tracking System");
			System.out.println("* By: Igor Evtushenko");
			System.out.println("================================");
			System.out.println("[1] Create new item.");
			System.out.println("[2] Set item state.");
			System.out.println("[3] Close all bugs.");
			System.out.println("[4] Close all tasks.");
			System.out.println("[5] Link user story to use case.");
			System.out.println("[6] Set new due date.");
			System.out.println("[7] Print use case.");
			System.out.println("[8] Set bug severity.");
			System.out.println("[9] Change sprint.");
			System.out.println("[10] Exit.");

			menuItem = inputProvider.getIntValue();
			switch (menuItem) {
				case 1:
					// 1. Create new item:
					itemId = createItemMenu();
					if (itemId.trim().length() > 0) {
						System.out.println("New item successfully created. New item id: " + itemId);
					} else {
						System.out.println("New item was not created (see error message above).");
					}
					break;
				case 2:
					// 2. Set item state:
					itemId = inputProvider.getIdValue();
					if (itemId.trim().length() > 0) {
						state = inputProvider.getIntValue("item state", "int: 0 = To Do, 1 = In Progress, 2 = Done");
						if (itemStore.setItemState(itemId, state)) {
							System.out.println("Ok.");
						} else {
							System.out.println("Invalid item state or transition!");
						}
					}
					break;
				case 3:
					// 3. Close all bugs:
					System.out.print("Closing all bugs...");
					itemStore.closeAllBugs();
					System.out.println(" done!");
					break;
				case 4:
					// 4. Close all tasks:
					System.out.print("Closing all tasks...");
					itemStore.closeAllTasks();
					System.out.println(" done!");
					break;
				case 5:
					// 5. Link user story to use case:
					itemId = inputProvider.getIdValue();
					if (itemId.trim().length() > 0) {
						subitemId = inputProvider.getIdValue();
						if (subitemId.trim().length() > 0) {
							if (itemStore.linkUserStoryToUseCase(itemId, subitemId)) {
								System.out.println("Ok.");
							} else {
								System.out.println("Not enough capacity to link additional user story or invalid user story or use case id!");
							}
						}
					}
					break;
				case 6:
					// 6. Set new due date for task:
					itemId = inputProvider.getIdValue();
					if (itemId.trim().length() > 0) {
						dueDate = inputProvider.getDateValue("due date", "valid format: yyyy-MM-dd");
						if (itemStore.changeTaskDueDate(itemId, dueDate)) {
							System.out.println("Ok.");
						} else {
							System.out.println("Invalid task id!");
						}
					}
					break;
				case 7:
					// 7. Print use case including linked user stories:
					itemId = inputProvider.getIdValue();
					if (itemId.trim().length() > 0) {
						itemStore.showUseCase(itemId);
					}
					break;
				case 8:
					// 8. Set bug severity:
					itemId = inputProvider.getIdValue();
					if (itemId.trim().length() > 0) {
						severity = inputProvider.getIntValue("bug severity", "int: 1 - 10");
						if (itemStore.changeBugSeverity(itemId, severity)) {
							System.out.println("Ok.");
						} else {
							System.out.println("Invalid sevrity level or bug id!");
						}
					}
					break;
				case 9:
					// 9. Change sprint name in user story:
					itemId = inputProvider.getIdValue();
					if (itemId.trim().length() > 0) {
						sprintName = inputProvider.getStringValue("sprint name", "");
						if (itemStore.changeUserStorySprintName(itemId, sprintName)) {
							System.out.println("Ok.");
						} else {
							System.out.println("Invalid user story id!");
						}
					}
					break;
				case 10:
					// 10. Exit:
					inputProvider.close();
					System.out.println("Exiting...");
					break fromWhile;
				default:
					System.out.println("Invalid menu item. Please choose 1 - 10.");
					break;
			}
		}
	}

	private String createItemMenu() {
		String itemType;
		String itemId = "";
		String subitemId;
		String description;
		String sprintName;
		LocalDate dueDate;
		int priority;
		int severity;

		itemType = inputProvider.getTypeValue();
		if (itemType.trim().length() > 0) {
			description = inputProvider.getStringValue("item description", "");
			if (description.trim().length() > 0) {
				priority = inputProvider.getIntValue("item priority", "int: 1 - 10");
				if (priority >= 1 && priority <= 10) {
					switch (itemType) {
						case "B":
							// Bug:
							severity = inputProvider.getIntValue("bug severity", "int: 1 - 10");
							if (severity >= 1 && severity <= 10) {
								itemId = itemStore.createNewBug(description, priority, severity);
							} else {
								System.out.println("Invalid sevrity level!");
							}
							break;
						case "T":
							// Task:
							dueDate = inputProvider.getDateValue("due date", "valid format: yyyy-MM-dd");
							itemId = itemStore.createNewTask(description, priority, dueDate);
							break;
						case "US":
							// User story:
							sprintName = inputProvider.getStringValue("sprint name", "");
							if (sprintName.trim().length() > 0) {
								itemId = itemStore.createNewUserStory(description, priority, sprintName);
							} else {
								System.out.println("Sprint name can't be an empty string!");
							}
							break;
						case "UC":
							// Use case:
							subitemId = inputProvider.getIdValue();
							if (subitemId.trim().length() > 0) {
								itemId = itemStore.createNewUseCase(description, priority, subitemId);
							}
							break;
						default:
							break;
					}
				} else {
					System.out.println("Invalid Priority level!");
				}
			} else {
				System.out.println("Item description can't be an empty string!");
			}
		}

		return itemId;
	}
}
