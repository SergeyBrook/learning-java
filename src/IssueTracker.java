import java.util.Scanner;

/**
 * IssueTracker class.
 */
public class IssueTracker {
	private Scanner input = new Scanner(System.in);
	private ItemStore itemStore = new ItemStore();

	public static void main(String[] args) {
		IssueTracker issueTracker = new IssueTracker();
		issueTracker.mainMenu();
		//issueTracker.debug();
	}

	private void debug() {
		/*itemStore.createNewUserStory("User story one", 1, "Sprint one"); // US-0
		itemStore.createNewUserStory("User story two", 1, "Sprint two"); // US-1
		itemStore.createNewUserStory("User story three", 1, "Sprint three"); // US-2

		itemStore.createNewUseCase("Foo", 1, "US-0"); // UC-0
		itemStore.linkUserStoryToUseCase("UC-0", "US-1");
		itemStore.linkUserStoryToUseCase("UC-0", "US-2");

		itemStore.showUseCase("UC-0");*/


		/*itemStore.createNewBug("Foo", 2, 3);
		itemStore.createNewBug("Boo", 4, 5);
		itemStore.showAllBugs();*/

		//itemStore.closeAllBugs();

		/*itemStore.setItemState("B-0", 1);
		itemStore.setItemState("B-1", 2);
		itemStore.showAllBugs();

		itemStore.setItemState("B-0", 2);
		itemStore.setItemState("B-1", 1);
		itemStore.showAllBugs();

		itemStore.setItemState("B-0", 2);
		itemStore.setItemState("B-1", 2);
		itemStore.showAllBugs();*/

		/*System.out.println(itemStore.isValidItemId("B-0", "UC") ? "valid" : "not valid");
		System.out.println(itemStore.isValidItemId("B-0", "B") ? "valid" : "not valid");
		System.out.println(itemStore.isValidItemId("B-1", "B") ? "valid" : "not valid");
		System.out.println(itemStore.isValidItemId("B-2", "B") ? "valid" : "not valid");*/
	}


	private void mainMenu() {
		int menuItem;

		fromWhile:
		while (true) {
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
			System.out.print("> ");

			menuItem = input.nextInt();
			switch (menuItem) {
				case 1:
					// 1. Create new item.
					//String itemId = itemStore.createNewBug(description, priority, severity);
					//String itemId = itemStore.createNewTask(description, priority, dueDate);
					//String itemId = itemStore.createNewUserStory(description, priority, sprintName);
					//String itemId = itemStore.createNewUseCase(description, priority, userStory);
					break;
				case 2:
					// 2. Set item state.
					//boolean isOk = itemStore.setItemState(itemId, state);
					break;
				case 3:
					// 3. Close all bugs.
					System.out.println("Closing all bugs...");
					itemStore.closeAllBugs();
					break;
				case 4:
					// 4. Close all tasks.
					System.out.println("Closing all tasks...");
					itemStore.closeAllTasks();
					break;
				case 5:
					// 5. Link user story to use case.
					//boolean isOk = itemStore.linkUserStoryToUseCase(useCaseId, userStoryId);
					break;
				case 6:
					// 6. Set new due date for task.
					//boolean isOk = itemStore.changeTaskDueDate(itemId, dueDate);
					break;
				case 7:
					// 7. Print use case including linked user stories.
					//itemStore.showUseCase(itemId);
					break;
				case 8:
					// 8. Set bug severity.
					//boolean isOk = itemStore.changeBugSeverity(itemId, severity);
					break;
				case 9:
					// 9. Change sprint name in user story.
					//boolean isOk = itemStore.changeUserStorySprintName(itemId, sprintName);
					break;
				case 10:
					// 10. Exit.
					input.close();
					System.out.println("Exiting...");
					break fromWhile;
				default:
					break;
			}
		}
	}
}
