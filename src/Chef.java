public class Chef implements Runnable {
	private Food ingredient;
	private Table table;
	
	public Chef(Food ingredient, Table table) {
		this.ingredient = ingredient;
		this.table = table;
	}

	@Override
	public void run() {
		boolean done = false;
		while(!done) {
            System.out.println(Thread.currentThread().getName() + " has " + ingredient);
			Food[] remainingIngredients = table.getRemainingIngredients(ingredient);
			if(remainingIngredients == null) {
				continue;
			}
			if(remainingIngredients.length == 0) {
				done = true;
			} else {
	            System.out.println(Thread.currentThread().getName() + " got " + remainingIngredients[0] + " and " + remainingIngredients[1]);
			}
			try {
                Thread.sleep(500);
            } catch (InterruptedException e) {}
		}
		
	}
}
