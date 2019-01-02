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

	public String getTypeValue() {
		String val = this.getStringValue("item type", "valid values: B = Bug, T = Task, US = User Story, UC = Use Case").toUpperCase();
		if (val.equals("B") || val.equals("T") || val.equals("US") || val.equals("UC")) {
			return val;
		} else {
			System.out.println("Invalid item type.");
			return "";
		}
	}

	public String getIdValue() {
		String val = this.getStringValue("item id", "valid format: <type>-<index> e.g. UC-42").toUpperCase();
		if (this.itemStore.isValidItemId(val)) {
			return val;
		} else {
			System.out.println("Invalid item id format or item with such id does not exists.");
			return "";
		}
	}
}
