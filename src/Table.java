/**
 * 
 * @author Aashna Narang Intermediate class that allows people to place
 *         ingredients on the table and allows others to get ingredients
 */
public class Table {
	private static final int MAX_SANDWICHES = 20;
	private int count;
	private Food[] table;


	/**
	 * Table constructor to initialize variables
	 */
	public Table() {
		table = new Food[Food.values().length - 1];
		count = 0;
	}


	/**
	 * Check if the person requesting for ingredients can make a full sandwich given
	 * what is on the table. Wait if they cannot, else give the ingredients, clear
	 * table, and notify all.
	 * 
	 * @param ingredient The ingredient that you already have
	 * @return The remaining ingredients needed to make a full sandwich
	 */
	public synchronized Food[] getRemainingIngredients(Food ingredient) {
		while (isIngredientOnTableOrTableEmpty(ingredient)) {
			try {
				wait();
			} catch (InterruptedException e) {
				return null;
			}
		}
		Food[] ingredients = this.table.clone();
		count++;
		checkIsAtMaxSandwiches();
		clearTable();
		notifyAll();
		return ingredients;
	}


	/**
	 * Check if the table is full, if it is then wait. Else, restock table with new
	 * ingredients and notify all.
	 * 
	 * @param newIngredients Array of new ingredients to be put on the table
	 * @return True if ingredients were placed, null if an exception was thrown
	 */
	public synchronized Boolean putIngredients(Food[] newIngredients) {
		while (!isTableEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				return null;
			}
		}
		fillTable(newIngredients);
		notifyAll();
		return true;
	}


	/**
	 * Check if table is empty or if given ingredient is on the table. If given
	 * ingredient is on the table, then a full sandwich cannot be made
	 * 
	 * @param ingredient The ingredient the person already has
	 * @return True if table is empty or given ingredient is on the table. Else,
	 *         false
	 */
	private synchronized boolean isIngredientOnTableOrTableEmpty(Food ingredient) {
		for (int i = 0; i < table.length; i++) {
			if (table[i] == null) {
				return true;
			} else if (table[i].equals(ingredient)) {
				return true;
			}
		}
		return false;
	}


	/**
	 * Check if table has reached the max capacity of sandwiches it is allowed to
	 * make. Terminate the system if it has reached max capacity.
	 */
	private synchronized void checkIsAtMaxSandwiches() {
		if (count == MAX_SANDWICHES) {
			System.out.println("No more sandwiches to make");
			System.exit(0);
		}
	}


	/**
	 * Clear the table. Used once ingredients have been taken from the table.
	 */
	private synchronized void clearTable() {
		for (int i = 0; i < table.length; i++) {
			table[i] = null;
		}
	}


	/**
	 * Fill the table with the given ingredients
	 * 
	 * @param newIngredients New ingredients to place on table
	 */
	private synchronized void fillTable(Food[] newIngredients) {
		for (int i = 0; i < table.length; i++) {
			table[i] = newIngredients[i];
		}
	}


	/**
	 * Check if the table has no ingredients
	 * 
	 * @return True, if table is empty. Else false.
	 */
	private synchronized boolean isTableEmpty() {
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				return false;
			}
		}
		return true;
	}

}
