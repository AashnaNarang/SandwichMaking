/**
 * 
 * @author Aashna Narang A Chef will have one of the ingredients needed to make
 *         a sandwich. A Chef will continuously request the ingredient they are
 *         missing and make a sandwich.
 */
public class Chef implements Runnable {
	private Food ingredient;
	private Table table;


	/**
	 * Create a Chef object
	 * 
	 * @param ingredient The ingredient that this Chef will have in stock
	 * @param table      Table object used to request remaining missing ingredients
	 */
	public Chef(Food ingredient, Table table) {
		this.ingredient = ingredient;
		this.table = table;
	}


	@Override
	/**
	 * Continuously request for the missing ingredients.
	 */
	public void run() {
		while (true) {
			System.out.println(Thread.currentThread().getName() + " has " + ingredient);
			Food[] remainingIngredients = table.getRemainingIngredients(ingredient);
			if (remainingIngredients == null) {
				continue;
			}
			if (remainingIngredients.length > 0) {
				System.out.println(Thread.currentThread().getName() + " got " + remainingIngredients[0] + " and "
						+ remainingIngredients[1]);
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
		}

	}
}
