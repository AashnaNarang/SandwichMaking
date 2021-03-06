
/**
 * 
 * @author Aashna Narang Class used to test the Sandwich Problem.
 */
public class SandwichProblem {
	/**
	 * Application's entry point. Create and start threads.
	 * 
	 * @param args An array of command-line arguments for the application
	 */
	public static void main(String[] args) {
		Food[] ingredients = Food.values();
		Thread chef1, chef2, chef3, agent;
		Table table = new Table();
		chef1 = new Thread(new Chef(ingredients[0], table), "Chef1");
		chef2 = new Thread(new Chef(ingredients[1], table), "Chef2");
		chef3 = new Thread(new Chef(ingredients[2], table), "Chef3");
		agent = new Thread(new Agent(table), "Agent1");
		chef1.start();
		chef2.start();
		chef3.start();
		agent.start();
	}
}
