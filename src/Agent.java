import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Agent implements Runnable {
	private static final List<Food> INGREDIENTS_LIST = Arrays.asList(Food.values());
	private Table table;
	private Food[] newIngredients;
	
	public Agent(Table table) {
		this.table = table;
		newIngredients = new Food[INGREDIENTS_LIST.size() - 1];
	}
	@Override
	public void run() {
		boolean done = false; 
		while (!done) {
			getNewTableIngredients();
	        System.out.println(Thread.currentThread().getName() + " is about to restock");
			Boolean response = table.putIngredients(newIngredients);
			if (response == null) {
				continue;
			}
			if (response) {
				System.out.println(Thread.currentThread().getName() + " restocked the table");
			} else {
				done = true;
			}
		}
		
	} 
	
	private Food[] getNewTableIngredients() {
		Collections.shuffle(INGREDIENTS_LIST);
		for (int i = 0; i < newIngredients.length; i++) {
			newIngredients[i] = INGREDIENTS_LIST.get(i);
		}
        return newIngredients;
	}
}
