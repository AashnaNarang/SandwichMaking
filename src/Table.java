public class Table {
	private static final int MAX_SANDWICHES = 20;
	private int count;
	private Food[] table;

	public Table() {
		table = new Food[Food.values().length - 1];
		count = 0;
	}

	public synchronized Food[] getRemainingIngredients(Food ingredient) {
		// Check max sandwiches before and after while loop. If only check before, possibility of a race condition
		// allowing a user to create more than 21 sandwiches. If only check after, waste time + power until system.exit is called
		checkIsAtMaxSandwiches();
		while (isIngredientOnTableOrTableEmpty(ingredient)) {
			try {
				wait();
			} catch (InterruptedException e) {
                return null;
            }
		}
		checkIsAtMaxSandwiches();
		Food[] ingredients = this.table.clone();
		count++;
		clearTable();
		notifyAll();
		return ingredients;
	}
	
	public synchronized Boolean putIngredients(Food[] newIngredients) {
		// Check for max sandwiches to avoid wasting time and cpu power of putting items on table
		// if they cannot be used
		checkIsAtMaxSandwiches();
		while(!isTableEmpty()) {
			System.out.println("in put ingredients, table is full");
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

	private synchronized boolean isIngredientOnTableOrTableEmpty(Food ingredient) {
		for (int i = 0; i < table.length; i++) {
			if (table[i] == null) {
				return true;
			}
			else if (table[i].equals(ingredient)) {
				return true;
			}
		}
		return false;
	}
	
	private synchronized void checkIsAtMaxSandwiches() {
		if (count == MAX_SANDWICHES) {
            System.out.println("No more sandwiches to make");
            System.exit(0);			
		} 
	}
	
	
	private synchronized void clearTable() {
		for (int i = 0; i < table.length; i++) {
			table[i] = null;
		}
	}
	
	private synchronized void fillTable(Food[] newIngredients) {
		for (int i = 0; i < table.length; i++) {
			table[i] = newIngredients[i];
		}
	}
	
	private synchronized boolean isTableEmpty() {
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				return false;
			}
		}
		return true;
	}

}
