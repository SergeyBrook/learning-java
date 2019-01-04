package assignments.issuetracker;

import assignments.issuetracker.providers.ConsoleInputProvider;
import assignments.issuetracker.model.ItemStore;

/**
 * IssueTrackerInputProvider class
 */
public class IssueTrackerInputProvider extends ConsoleInputProvider {
	private ItemStore itemStore;

	public IssueTrackerInputProvider(ItemStore itemStore) {
		this.itemStore = itemStore;
	}

	public String getNotEmptyStringValue(String name, String message) {
		String result;
		while (true) {
			result = this.getStringValue(name, message);
			if (result.trim().length() != 0) {
				break;
			} else {
				System.out.println(name.substring(0, 1).toUpperCase() + name.substring(1) + " can't be an empty string, please try again.");
			}
		}
		return result;
	}

	public String getTypeValue() {
		String val = this.getNotEmptyStringValue("item type", "valid values: B = Bug, T = Task, US = User Story, UC = Use Case").toUpperCase();
		if (val.equals("B") || val.equals("T") || val.equals("US") || val.equals("UC")) {
			return val;
		} else {
			System.out.println("Invalid item type.");
			return "";
		}
	}

	public String getIdValue(String name) {
		String val = this.getNotEmptyStringValue(name + " id", "valid format: <type>-<index> e.g. AB-123").toUpperCase();
		if (this.itemStore.isValidItemId(val)) {
			return val;
		} else {
			System.out.println("Invalid item id format or item with such id does not exists.");
			return "";
		}
	}

	public int getIntRange(String name, String message, int minValue, int maxValue) {
		int result;
		while (true) {
			result = this.getIntValue(name, message);
			if (result >= minValue && result <= maxValue) {
				break;
			} else {
				System.out.println("Should be an integer number between " + minValue + " and " + maxValue + ", please try again.");
			}
		}
		return result;
	}

	public int getIntRange(String name, int minValue, int maxValue) {
		String message = "integer number between " + minValue + " and " + maxValue;
		return this.getIntRange(name, message, minValue, maxValue);
	}
}
