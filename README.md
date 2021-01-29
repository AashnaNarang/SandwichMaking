# SandwichMaking

## Sandwich Problem
"Consider a system with three chef threads and one agent thread. Each chef continuously makes a
sandwich and then eats it. But to make and eat a sandwich, the chef needs three ingredients: bread,
peanut butter, and jam. One of the chef threads has an infinite supply of bread, another has peanut
butter, and the third has jam. The agent has an infinite supply of all three ingredients. The agent
randomly selects two of the ingredients and places them on a table. The chef who has the remaining
ingredient then makes and eats a sandwich, signalling the agent on completion. The agent then puts
out another two of the three ingredients, and the cycle repeats."

Note: This project was built so that you can add a fourth ingredient and a fourth chef for ex. 

## Setup Instructions

1. Open Eclipse and click `File->Import`
2. Select `Import from Git (with smart import)`
3. Copy and Paste `https://github.com/AashnaNarang/SandwichMaking.git` into the Clone URI field
4. Select `Next`
5. Make sure the `master` branch is selected and click `Next`
6. Chose a directory to save the project into
7. Leave all of the default options. Hit `Next` and leave all the deafult options again
8. Click `Finish`
9. Open the `src` folder and open up SandwichProblem.java
10. Right click in the file editor and click `Run as -> Java Application`

## Classes
### SandwichProblem.java 
This class includes the main method that creates and starts all of the necessary threads to reenact the sandwich problem

### Food.java
This is an enumeration class to be able to refer to the three ingredients needed to make the sandwich. It currently includes BREAD, JAM, and PEANUT_BUTTER
This class needs to have at least 3 ingredients to reenact the sandwich problem. The classes have been built so that you can add more ingredients+corresponding chefs.

### Chef.java
A thread object that has 1 unique ingredient they have in stock. Thread continuously requests Table for the missing ingredients to make sandwiches.

### Agent.java
A thread object that continuously randomly picks ingredients and requests to fill the table. 

### Table.java
Object that controls getting and giving ingredients. 
1. If table if empty, an agent can place ingredients to fill the table. Otherwise it is forced to wait. 
2. If a table has ingredients, it checks if the requesting chef has the ingredient to make a full sandwich. If it does then ingredients are given to that chef and the table is cleared. Otherwise, the chef is forced to wait.

### Diagrams
UML class and sequence diagrams have been included
