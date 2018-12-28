import java.util.Scanner;

/**
 * App class.
 */
class App {
	public static void main(String[] args) {
		int mainMenuItem;
		Data data = new Data();
		
		/*data.createNewBug("Foo", 2, 3);
		data.createNewBug("Boo", 4, 5);
		data.showAllBugs();
		data.closeAllBugs();
		data.showAllBugs();*/
		
		Scanner input = new Scanner(System.in);

		while (true) {
			App.printMainMenu();
			mainMenuItem = input.nextInt();

			if (mainMenuItem == 1) {
				// Create new item.
			} else if (mainMenuItem == 2) {
				// Set item state.
			} else if (mainMenuItem == 3) {
				// Close all bugs.
				data.closeAllBugs();
			} else if (mainMenuItem == 4) {
				// Close all tasks.
				data.closeAllTasks();
			} else if (mainMenuItem == 5) {
				// Link user story to use case.
			} else if (mainMenuItem == 6) {
				// Set new due date for task.
			} else if (mainMenuItem == 7) {
				// Print use case.
			} else if (mainMenuItem == 8) {
				// Set bug severity.
			} else if (mainMenuItem == 9) {
				// Change sprint.
				// WTF?!
			} else if (mainMenuItem == 10) {
				// Exit.
				break;
			}
		}
	}

	public static void printMainMenu() {
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
	}
}
