import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author Aashna Narang An Agent will randomly select new ingredients and
 *         request to place them on the table.
 */
public class Agent implements Runnable {
	private static final List<Food> INGREDIENTS_LIST = Arrays.asList(Food.values());
	private Table table;
	private Food[] newIngredients;


	/**
	 * Agent constructor to initialize variables
	 * 
	 * @param table The table the Agent will send requests to
	 */
	public Agent(Table table) {
		this.table = table;
		newIngredients = new Food[INGREDIENTS_LIST.size() - 1];
	}


	@Override
	/**
	 * Continuously request to place new ingredients on the table
	 */
	public void run() {
		while (true) {
			getNewTableIngredients();
			System.out.println(Thread.currentThread().getName() + " is about to restock");
			Boolean response = table.putIngredients(newIngredients);
			if (response == null) {
				continue;
			}
			if (response) {
				System.out.println(Thread.currentThread().getName() + " restocked the table");
			}
		}
	}


	/**
	 * Randomly select and save new ingredients from the possibilities
	 * 
	 * @return Array with randomly selected ingredients
	 */
	private Food[] getNewTableIngredients() {
		Collections.shuffle(INGREDIENTS_LIST);
		for (int i = 0; i < newIngredients.length; i++) {
			newIngredients[i] = INGREDIENTS_LIST.get(i);
		}
		return newIngredients;
	}
}
