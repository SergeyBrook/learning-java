/**
 * UseCase class.
 */
public class UseCase extends Item {
	protected int defaultStories = 10;
	protected int[] userStories;

	public UseCase() {
		super();
		this.userStories = new int[this.defaultStories];
	}

	public UseCase(int maxStories) {
		super();
		if (maxStories < 1 || maxStories > 10) {
			maxStories = this.defaultStories;
		}
		this.userStories = new int[maxStories];
	}

	public boolean addUserStory(int userStory) {
		// TODO: Implement.
		return false;
	}
}
