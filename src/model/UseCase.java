package assignments.issuetracker.model;

/**
 * UseCase class.
 */
public class UseCase extends Item {
	protected int lastStoryIndex = -1;
	private final int[] userStories = new int[10];

	public UseCase() {
		super();
	}

	public boolean addUserStory(int itemIndex) {
		if (this.lastStoryIndex + 1 <= this.userStories.length - 1) {
			this.userStories[this.lastStoryIndex + 1] = itemIndex;
			this.lastStoryIndex++;
			return true;
		} else {
			return false;
		}
	}

	public int getUserStory(int userStoryIndex) {
		if (userStoryIndex >= 0 && userStoryIndex <= this.lastStoryIndex) {
			return this.userStories[userStoryIndex];
		} else {
			return -1;
		}
	}

	public int getUserStoriesCount() {
		return this.lastStoryIndex + 1;
	}

	public int getUserStoriesCapacity() {
		return this.userStories.length;
	}
}
