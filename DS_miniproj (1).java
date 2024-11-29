/*
 SMART RECIPE MANAGER
 
 502- ADVIKA KHORGADE
 512- SRUSHTI CHIKHALE
 515- ARPITA DEODIKAR
 522- JAGRUTI DISLE
 */
 




package DS_miniproj;
import java.util.*;

class Recipe {

	private String name;
	private String cuisine;
	private List<String> ingredients;
	private int cookingTime;
	private int servings;
	private int popularity;
	public Recipe(String name, String cuisine, List<String> ingredients, int cookingTime, int servings, int popularity) {
		try {
			if (name.isEmpty() || cuisine.isEmpty()||  ingredients.isEmpty()) {
				throw new IllegalArgumentException("Invalid input: Recipe details cannot be null or empty");
			}
			this.name = name;
			this.cuisine = cuisine;
			this.ingredients = ingredients;
			this.cookingTime = cookingTime;
			this.servings = servings;
			this.popularity = popularity;
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}
	}
	// getters method is used to get respective values by return
	public String getName() {
		return name;
	}

	public String getCuisine() {
		return cuisine;
	}

	public List<String> getIngredients() {
		return ingredients;
	}

	public int getPopularity() {
		return popularity;
	}

	// Format specifier explanation:
	// |       : Literal character, used to visually separate fields in the output.
	// %-20s   : Formats a string to be left-aligned within a 20-character field.
    //		           - %        : Indicates a format specifier.
    //		           - -        : Left-aligns the content.
    //		           - 20       : Sets a minimum width of 20 characters for the field.
    //		           - s        : Specifies that the argument is a string.
	// This ensures the string occupies exactly 20 characters, padding with spaces if necessary
	public String toString() {
		return String.format("| %-20s | %-15s | %-30s | %-15d | %-8d | %-10d |", name, cuisine, String.join(", ", ingredients), cookingTime, servings, popularity);
	}

}


class RecipeNode {
	Recipe recipe;
	RecipeNode left, right;

	// constructor to pass values
	public RecipeNode(Recipe recipe) {
		try {
			if (recipe == null) {
				throw new IllegalArgumentException("Recipe cannot be null");
			}

			this.recipe = recipe;
			left = right = null;
		} catch (IllegalArgumentException e) {

			System.err.println(e.getMessage());

		}
	}
}


class RecipeBST {
	RecipeNode root;
	public void insert(Recipe recipe) {
		try {
			if (recipe == null) {
				throw new IllegalArgumentException("Cannot insert a null recipe");
			}
			root = insertRecursive(root, recipe);
		}
		catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}
	}

	private RecipeNode insertRecursive(RecipeNode root, Recipe recipe) {

		if (root == null) {
			root = new RecipeNode(recipe);
			return root;
		}
		if (recipe.getName().compareToIgnoreCase(root.recipe.getName()) < 0) {
			//checks alphabetically Recipe name and if Alphabet value less than root goes to left subtree
			root.left = insertRecursive(root.left, recipe);
		} else if (recipe.getName().compareToIgnoreCase(root.recipe.getName()) > 0) {
			//checks alphabetically Recipe name and if Alphabet value more than root goes to Right subtree
			root.right = insertRecursive(root.right, recipe);
		}
		return root;
	}

	public Recipe search(String name) {

		try {
			if (name == null || name.trim().isEmpty()) {
				throw new IllegalArgumentException("Recipe name cannot be null or empty");
			}

			RecipeNode resultNode = searchRecursive(root, name);
			if (resultNode != null) {
				// RECIPE FOUND
				return resultNode.recipe;
			} else {
				//Throws custom exception when recipe not found
				throw new NoSuchElementException("Recipe not found");
			}
		} catch (IllegalArgumentException | NoSuchElementException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	private RecipeNode searchRecursive(RecipeNode root, String name) {
		if (root == null) {
			return null;
		}
		if (root.recipe.getName().equalsIgnoreCase(name)) {
			return root;
		}
		if (name.compareToIgnoreCase(root.recipe.getName()) < 0) {
			return searchRecursive(root.left, name);
		} else {
			return searchRecursive(root.right, name);
		}
	}

	public void displayAllRecipes() {
		displayAllRecipesRecursive(root);
	}

	private void displayAllRecipesRecursive(RecipeNode root) {
		if (root != null) {
			//Inorder Traversal (LVR)
			displayAllRecipesRecursive(root.left);
			System.out.println(root.recipe);
			displayAllRecipesRecursive(root.right);
		}
	}
	
	// Delete a recipe by name
	public void delete(String name) {
        root = deleteRecursive(root, name);
    }

    private RecipeNode deleteRecursive(RecipeNode root, String name) {
        if (root == null) {
            System.out.println("Recipe not found.");
            return null;
        }

        if (name.compareToIgnoreCase(root.recipe.getName()) < 0) {
            root.left = deleteRecursive(root.left, name);
        } else if (name.compareToIgnoreCase(root.recipe.getName()) > 0) {
            root.right = deleteRecursive(root.right, name);
        } else {
            // Node with only one child or no child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Node with two children: Get the in-order successor (smallest in the right subtree)
            root.recipe = findMin(root.right).recipe;
            root.right = deleteRecursive(root.right, root.recipe.getName());
        }
        return root;
    }

    private RecipeNode findMin(RecipeNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

}


class Stack {
	private List<Recipe> stack = new ArrayList<>();
	public void push(Recipe recipe) {
		try {
			if (recipe == null) {
				throw new IllegalArgumentException("Cannot push a null recipe");
			}
			stack.add(recipe);
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}
	}

	public void pop() {
		try {
			if (stack.isEmpty()) {
				throw new NoSuchElementException("Stack is empty, cannot pop");
			}
			stack.remove(stack.size() - 1);
			// NoSuchElementException is inbuilt function if required values is not found
		} catch (NoSuchElementException e) {
			System.err.println(e.getMessage());
		}
	}

	public void display() {
		if (stack.isEmpty()) {
			System.out.println("No recently viewed recipes.");
		} else {
			System.out.println("Recently Viewed Recipes (most recent first):");
			for (int i = stack.size() - 1; i >= 0; i--) {
				System.out.println(stack.get(i));
			}
		}
	}
}


class CircularQueue {
	private Recipe[] queue;
	private int front, rear, size;
	public CircularQueue(int capacity) {
		try {
			if (capacity <= 0) {
				throw new IllegalArgumentException("Queue capacity must be positive");
			}
			queue = new Recipe[capacity];
			front = rear = -1;
			size = 0;
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}
	}

	public void enqueue(Recipe recipe) {
		try {
			if (recipe == null) {
				throw new IllegalArgumentException("Cannot enqueue a null recipe");
			}
			if (size == queue.length) {
				throw new IllegalStateException("Queue is full.");
			}
			rear = (rear + 1) % queue.length;
			queue[rear] = recipe;
			if (front == -1) {
				front = rear;
			}
			size++;
		} catch (IllegalArgumentException | IllegalStateException e) {
			System.err.println(e.getMessage());
		}
	}

	public void dequeue() {
		try {
			if (size == 0) {
				throw new IllegalStateException("Queue is empty.");
			}
			front = (front + 1) % queue.length;
			size--;
		} catch (IllegalStateException e) {
			System.err.println(e.getMessage());
		}
	}

	public void display() {
		if (size == 0) {
			System.out.println("Queue is empty.");
			return;
		}
		System.out.println("Meal Planning Queue:");
		int i = front;
		do {
			System.out.println(queue[i]);
			i = (i + 1) % queue.length;
		} while (i != (rear + 1) % queue.length);
	}
}


class RecipeSort {
	private static final Recipe[] List = null;

	public static void bubbleSortByIngredients(List<Recipe> recipes) {
		try {
			if (recipes == null || recipes.isEmpty()) {
				throw new IllegalArgumentException("Recipe list cannot be null or empty");
			}
			int n = recipes.size();
			for (int i = 0; i < n - 1; i++) {
				for (int j = 0; j < n - i - 1; j++) {
					if (recipes.get(j).getIngredients().size() > recipes.get(j + 1).getIngredients().size()) {

						Recipe temp= List[j];
						recipes.set(j, recipes.get(j + 1));  // Set recipe at index j to recipe at j+1
						recipes.set(j + 1, recipes.get(j));  // Set recipe at index j+1 to recipe at j
					}
				}
			}
		} catch (IllegalArgumentException e) {

			System.err.println(e.getMessage());

		}
	}

	public static void insertionSortByPopularity(List<Recipe> recipes) {
		try {
			if (recipes == null || recipes.isEmpty()) {
				throw new IllegalArgumentException("Recipe list cannot be null or empty");
			}
			for (int i = 1; i < recipes.size(); i++) {
				Recipe key = recipes.get(i);
				int j = i - 1;
				while (j >= 0 && recipes.get(j).getPopularity() < key.getPopularity()) {
					recipes.set(j + 1, recipes.get(j));
					j = j - 1;
				}
				recipes.set(j + 1, key);
			}
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}
	}

	public static void quickSortByCuisine(List<Recipe> recipes, int low, int high) {
		try {
			if (recipes == null || recipes.isEmpty()) {
				throw new IllegalArgumentException("Recipe list cannot be null or empty");
			}
			if (low < high) {
				int pi = partitionByCuisine(recipes, low, high);
				quickSortByCuisine(recipes, low, pi - 1);
				quickSortByCuisine(recipes, pi + 1, high);
			}
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}
	}


	private static int partitionByCuisine(List<Recipe> recipes, int low, int high) {
		String pivot = recipes.get(high).getCuisine();
		int i = (low - 1);
		for (int j = low; j < high; j++) {
			if (recipes.get(j).getCuisine().compareTo(pivot) < 0) {
				i++;
				Collections.swap(recipes, i, j);
			}
		}
		Collections.swap(recipes, i + 1, high);
		return i + 1;
	}

	public static void printRecipes(List<Recipe> recipes) {
		try {
			if (recipes == null || recipes.isEmpty()) {
				throw new IllegalArgumentException("Recipe list cannot be null or empty");
			}
			// Print table header for better clarity
			System.out.printf("| %-20s | %-15s | %-30s | %-15s | %-8s | %-10s |\n", "Name", "Cuisine", "Ingredients", "Cooking Time", "Servings", "Popularity");
			System.out.println("--------------------------------------------------------------");
			for (Recipe recipe : recipes) {
				System.out.println(recipe);
			}
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());

		}
	}
}


class FavoritesManager {
	private LinkedList<Recipe> favoriteRecipes;
	private RecipeBST recipeBST;
	public FavoritesManager(RecipeBST recipeBST) {
		try {
			if (recipeBST == null) {
				throw new IllegalArgumentException("RecipeBST cannot be null");
			}
			this.favoriteRecipes = new LinkedList<>();
			this.recipeBST = recipeBST;
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void addFavoriteRecipeByName(String recipeName) {
		try {
			if (recipeName == null || recipeName.trim().isEmpty()) {
				throw new IllegalArgumentException("Recipe name cannot be null or empty");
			}
			Recipe foundRecipe = recipeBST.search(recipeName);
			if (foundRecipe != null) {
				favoriteRecipes.add(foundRecipe);
				System.out.println("Added to favorites: " + foundRecipe.getName());
			} else {
				throw new NoSuchElementException("Recipe not found in BST");
			}
		} catch (IllegalArgumentException | NoSuchElementException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void displayFavoriteRecipes() {
		if (favoriteRecipes.isEmpty()) {
			System.out.println("No favorite recipes added.");
		} else {
			System.out.println("Favorite Recipes:");
			for (Recipe recipe : favoriteRecipes) {
				System.out.println("- " + recipe.getName() + " (" + recipe.getCuisine() + ")");
			}
		}
	}
}


public class DS_miniproj {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\n----- RECIPE MANAGER APPLICATION ----");
		RecipeBST recipeBST = new RecipeBST();
		Stack recentRecipes = new Stack();
		CircularQueue mealQueue = new CircularQueue(5);
		RecipeSort sort = new RecipeSort();
		FavoritesManager fav = new FavoritesManager(recipeBST);
		List<Recipe> recipeList = new ArrayList<>();

		// Hardcoded 10 recipes initially
		Recipe[] initialRecipes = {
			new Recipe("Pasta", "Italian", Arrays.asList("Pasta", "Tomato", "Cheese"), 30, 4, 8),
			new Recipe("Sushi", "Japanese", Arrays.asList("Rice", "Fish", "Seaweed"), 20, 2, 9),
			new Recipe("Pizza", "Italian", Arrays.asList("Dough", "Cheese", "Tomato"), 40, 6, 7),
			new Recipe("Tacos", "Mexican", Arrays.asList("Taco Shell", "Beef", "Cheese"), 15, 2, 6),
			new Recipe("Burger", "American", Arrays.asList("Bun", "Beef Patty", "Lettuce"), 25, 4, 7),
			new Recipe("Curry", "Indian", Arrays.asList("Chicken", "Spices", "Rice"), 45, 4, 9),
			new Recipe("Salad", "Mediterranean", Arrays.asList("Lettuce", "Olives", "Feta"), 10, 2, 6),
			new Recipe("Pancakes", "American", Arrays.asList("Flour", "Eggs", "Milk"), 20, 4, 8),
			new Recipe("Ramen", "Japanese", Arrays.asList("Noodles", "Broth", "Egg"), 30, 2, 8),
			new Recipe("Fried Rice", "Chinese", Arrays.asList("Rice", "Egg", "Vegetables"), 25, 4, 7)
		};
		// Add the hardcoded recipes to BST and List
		for (Recipe recipe : initialRecipes) {
			recipeBST.insert(recipe);
			recipeList.add(recipe);
		}
		
		int choice = 0;
		do {
			System.out.println("\n\n--------------------------------------------------");
			System.out.println("Menu:");
			System.out.println("1. Add Recipe");
			System.out.println("2. Display All Recipes");
			System.out.println("3. Search Recipe by Name");
			System.out.println("4. Add Recipe to Meal Planning Queue");
			System.out.println("5. Display Meal Planning Queue");
			System.out.println("6. Display Recently Viewed Recipes");
			System.out.println("7. Sort Recipes");
			System.out.println("8. Add Recipe to Favourites");
			System.out.println("9. Display Favourites");
			System.out.println("10. Delete a Recipe");
			System.out.println("11. Exit");
			System.out.println("--------------------------------------------------");

			try {
				System.out.print("Enter your choice: ");
				choice = scanner.nextInt();
				scanner.nextLine(); // Consume newline character

				switch (choice) {
				case 1:
					// Add a recipe to BST and List
					try {
						System.out.print("Enter recipe name: ");
						String name = scanner.nextLine();
						System.out.print("Enter cuisine: ");
						String cuisine = scanner.nextLine();

						int cookingTime = 0;
						while (true) {
							try {
								System.out.print("Enter cooking time (in minutes): ");
								cookingTime = scanner.nextInt();
								scanner.nextLine(); // Consume newline
								break;
							} catch (InputMismatchException e) {
								System.out.println("Invalid input! Please enter an integer for cooking time.");
								scanner.nextLine(); // Clear invalid input
							}
						}

						int servings = 0;
						while (true) {
							try {
								System.out.print("Enter servings: ");
								servings = scanner.nextInt();
								scanner.nextLine(); // Consume newline
								break;
							} catch (InputMismatchException e) {
								System.out.println("Invalid input! Please enter an integer for servings.");
								scanner.nextLine(); // Clear invalid input
							}
						}

						int popularity = 0;
						while (true) {
							try {
								System.out.print("Enter popularity (1-10): ");
								popularity = scanner.nextInt();
								scanner.nextLine(); // Consume newline
								if (popularity < 1 || popularity > 10) {
									System.out.println("Popularity must be between 1 and 10.");
								} else {
									break;
								}
							} catch (InputMismatchException e) {
								System.out.println("Invalid input! Please enter an integer for popularity.");
								scanner.nextLine(); // Clear invalid input
							}
						}

						System.out.print("Enter ingredients (comma separated): ");
						String ingredientsLine = scanner.nextLine();
						List<String> ingredients = Arrays.asList(ingredientsLine.split(","));
						Recipe newRecipe = new Recipe(name, cuisine, ingredients, cookingTime, servings, popularity);
						recipeBST.insert(newRecipe);
						recipeList.add(newRecipe);
						System.out.println("Recipe Added successfully.");
					} catch (Exception e) {
						System.out.println("Error occurred while adding the recipe: " + e.getMessage());
					}
					break;

				case 2:
					try {
						System.out.println("All Recipes:");
						System.out.println(String.format("| %-20s | %-15s | %-30s | %-15s | %-8s | %-10s |", "Name", "Cuisine", "Ingredients", "Cooking Time", "Servings", "Popularity"));
						System.out.println("----------------------------------------------------------------------------------------------------------------------------");
						recipeBST.displayAllRecipes();
					} catch (Exception e) {
						System.out.println("Error occurred while displaying recipes: " + e.getMessage());
					}
					break;

				case 3:
					try {
						System.out.print("Enter the recipe name to search: ");
						String searchName = scanner.nextLine();
						Recipe foundRecipe = recipeBST.search(searchName);
						if (foundRecipe != null) {
							System.out.println("Found: " + foundRecipe);
							recentRecipes.push(foundRecipe);
						}
					} catch (Exception e) {
						System.out.println("Error occurred while searching for the recipe: " + e.getMessage());
					}
					break;
					
				case 4:
					try {
						System.out.print("Enter the recipe name to add to meal planning queue: ");
						String queueName = scanner.nextLine();
						Recipe recipeForQueue = recipeBST.search(queueName);
						if (recipeForQueue != null) {
							mealQueue.enqueue(recipeForQueue);
							System.out.println("Added to meal planning queue.");
						} else {
							System.out.println("Recipe not found.");
						}
					} catch (Exception e) {
						System.out.println("Error occurred while adding to the meal planning queue: " + e.getMessage());
					}
					break;

				case 5:
					try {
						mealQueue.display();
					} catch (Exception e) {
						System.out.println("Error occurred while displaying the meal planning queue: " + e.getMessage());
					}
					break;
					
				case 6:
					try {
						recentRecipes.display();
					} catch (Exception e) {
						System.out.println("Error occurred while displaying recently viewed recipes: " + e.getMessage());
					}
					break;

				case 7:
					try {
						System.out.println("Sort Recipes:");
						System.out.println("1. Sort by No. of Ingredients (Bubble Sort)");
						System.out.println("2. Sort by Popularity (Insertion Sort)");
						System.out.println("3. Sort by Cuisine alphabetically (Quick Sort)");
						System.out.print("Enter your choice: ");
						int sortChoice = scanner.nextInt();
						scanner.nextLine(); // Consume newline

						switch (sortChoice) {
						case 1:
							RecipeSort.bubbleSortByIngredients(recipeList);
							RecipeSort.printRecipes(recipeList);
							break;
						case 2:
							RecipeSort.insertionSortByPopularity(recipeList);
							RecipeSort.printRecipes(recipeList);
							break;
						case 3:
							RecipeSort.quickSortByCuisine(recipeList, 0, recipeList.size() - 1);
							RecipeSort.printRecipes(recipeList);
							break;
						default:
							System.out.println("Invalid choice.");
							break;
						}
					} catch (Exception e) {
						System.out.println("Error occurred while sorting recipes: " + e.getMessage());
					}
					break;

				case 8:
					try {
						System.out.print("Enter the recipe name to add to favorites: ");
						String favName = scanner.nextLine();
						fav.addFavoriteRecipeByName(favName);
					} catch (Exception e) {
						System.out.println("Error occurred while adding recipe to favorites: " + e.getMessage());
					}
					break;
					
				case 9:
					try {
						fav.displayFavoriteRecipes();
					} catch (Exception e) {
						System.out.println("Error occurred while displaying favorite recipes: " + e.getMessage());
					}
					break;
					
				case 10 : 
					System.out.println("Enter Recipe Name to delete");
				          String r=scanner.nextLine();
//				          recipeBST.search(r);
					      recipeBST.delete(r);
//				          recipeList.remove(r);
				          break;
				          
				case 11:
				    
					System.out.println("Exiting the application...");
					break;
					
				default:
					System.out.println("Invalid choice! Please try again.");
					break;
				}
				
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a number.");
				scanner.nextLine(); // Clear the invalid input
			}
			  
		} while (choice != 11);
		scanner.close();
		
	}
}

/*
 * 
 * OUTPUT
 * 
----- RECIPE MANAGER APPLICATION ----


--------------------------------------------------
Menu:
1. Add Recipe
2. Display All Recipes
3. Search Recipe by Name
4. Add Recipe to Meal Planning Queue
5. Display Meal Planning Queue
6. Display Recently Viewed Recipes
7. Sort Recipes
8. Add Recipe to Favourites
9. Display Favourites
10. Delete a Recipe
11. Exit
--------------------------------------------------
Enter your choice: 2
All Recipes:
| Name                 | Cuisine         | Ingredients                    | Cooking Time    | Servings | Popularity |
----------------------------------------------------------------------------------------------------------------------------
| Burger               | American        | Bun, Beef Patty, Lettuce       | 25              | 4        | 7          |
| Curry                | Indian          | Chicken, Spices, Rice          | 45              | 4        | 9          |
| Fried Rice           | Chinese         | Rice, Egg, Vegetables          | 25              | 4        | 7          |
| Pancakes             | American        | Flour, Eggs, Milk              | 20              | 4        | 8          |
| Pasta                | Italian         | Pasta, Tomato, Cheese          | 30              | 4        | 8          |
| Pizza                | Italian         | Dough, Cheese, Tomato          | 40              | 6        | 7          |
| Ramen                | Japanese        | Noodles, Broth, Egg            | 30              | 2        | 8          |
| Salad                | Mediterranean   | Lettuce, Olives, Feta          | 10              | 2        | 6          |
| Sushi                | Japanese        | Rice, Fish, Seaweed            | 20              | 2        | 9          |
| Tacos                | Mexican         | Taco Shell, Beef, Cheese       | 15              | 2        | 6          |


--------------------------------------------------
Menu:
1. Add Recipe
2. Display All Recipes
3. Search Recipe by Name
4. Add Recipe to Meal Planning Queue
5. Display Meal Planning Queue
6. Display Recently Viewed Recipes
7. Sort Recipes
8. Add Recipe to Favourites
9. Display Favourites
10. Delete a Recipe
11. Exit
--------------------------------------------------
Enter your choice: 1
Enter recipe name: pani puri
Enter cuisine: indian
Enter cooking time (in minutes): 60
Enter servings: 4
Enter popularity (1-10): 10
Enter ingredients (comma separated): puri, imli chutney, sev, ragda, green chutney
Recipe Added successfully.


--------------------------------------------------
Menu:
1. Add Recipe
2. Display All Recipes
3. Search Recipe by Name
4. Add Recipe to Meal Planning Queue
5. Display Meal Planning Queue
6. Display Recently Viewed Recipes
7. Sort Recipes
8. Add Recipe to Favourites
9. Display Favourites
10. Delete a Recipe
11. Exit
--------------------------------------------------
Enter your choice: 2
All Recipes:
| Name                 | Cuisine         | Ingredients                    | Cooking Time    | Servings | Popularity |
----------------------------------------------------------------------------------------------------------------------------
| Burger               | American        | Bun, Beef Patty, Lettuce       | 25              | 4        | 7          |
| Curry                | Indian          | Chicken, Spices, Rice          | 45              | 4        | 9          |
| Fried Rice           | Chinese         | Rice, Egg, Vegetables          | 25              | 4        | 7          |
| Pancakes             | American        | Flour, Eggs, Milk              | 20              | 4        | 8          |
| pani puri            | indian          | puri,  imli chutney,  sev,  ragda,  green chutney | 60              | 4        | 10         |
| Pasta                | Italian         | Pasta, Tomato, Cheese          | 30              | 4        | 8          |
| Pizza                | Italian         | Dough, Cheese, Tomato          | 40              | 6        | 7          |
| Ramen                | Japanese        | Noodles, Broth, Egg            | 30              | 2        | 8          |
| Salad                | Mediterranean   | Lettuce, Olives, Feta          | 10              | 2        | 6          |
| Sushi                | Japanese        | Rice, Fish, Seaweed            | 20              | 2        | 9          |
| Tacos                | Mexican         | Taco Shell, Beef, Cheese       | 15              | 2        | 6          |


--------------------------------------------------
Menu:
1. Add Recipe
2. Display All Recipes
3. Search Recipe by Name
4. Add Recipe to Meal Planning Queue
5. Display Meal Planning Queue
6. Display Recently Viewed Recipes
7. Sort Recipes
8. Add Recipe to Favourites
9. Display Favourites
10. Delete a Recipe
11. Exit
--------------------------------------------------
Enter your choice: 3
Enter the recipe name to search: pizza
Found: | Pizza                | Italian         | Dough, Cheese, Tomato          | 40              | 6        | 7          |


--------------------------------------------------
Menu:
1. Add Recipe
2. Display All Recipes
3. Search Recipe by Name
4. Add Recipe to Meal Planning Queue
5. Display Meal Planning Queue
6. Display Recently Viewed Recipes
7. Sort Recipes
8. Add Recipe to Favourites
9. Display Favourites
10. Delete a Recipe
11. Exit
--------------------------------------------------
Enter your choice: palak
Invalid input. Please enter a number.


--------------------------------------------------
Menu:
1. Add Recipe
2. Display All Recipes
3. Search Recipe by Name
4. Add Recipe to Meal Planning Queue
5. Display Meal Planning Queue
6. Display Recently Viewed Recipes
7. Sort Recipes
8. Add Recipe to Favourites
9. Display Favourites
10. Delete a Recipe
11. Exit
--------------------------------------------------
Enter your choice: 3
Enter the recipe name to search: palak
Recipe not found


--------------------------------------------------
Menu:
1. Add Recipe
2. Display All Recipes
3. Search Recipe by Name
4. Add Recipe to Meal Planning Queue
5. Display Meal Planning Queue
6. Display Recently Viewed Recipes
7. Sort Recipes
8. Add Recipe to Favourites
9. Display Favourites
10. Delete a Recipe
11. Exit
--------------------------------------------------
Enter your choice: 4
Enter the recipe name to add to meal planning queue: pizza
Added to meal planning queue.


--------------------------------------------------
Menu:
1. Add Recipe
2. Display All Recipes
3. Search Recipe by Name
4. Add Recipe to Meal Planning Queue
5. Display Meal Planning Queue
6. Display Recently Viewed Recipes
7. Sort Recipes
8. Add Recipe to Favourites
9. Display Favourites
10. Delete a Recipe
11. Exit
--------------------------------------------------
Enter your choice: 5
Meal Planning Queue:
| Pizza                | Italian         | Dough, Cheese, Tomato          | 40              | 6        | 7          |


--------------------------------------------------
Menu:
1. Add Recipe
2. Display All Recipes
3. Search Recipe by Name
4. Add Recipe to Meal Planning Queue
5. Display Meal Planning Queue
6. Display Recently Viewed Recipes
7. Sort Recipes
8. Add Recipe to Favourites
9. Display Favourites
10. Delete a Recipe
11. Exit
--------------------------------------------------
Enter your choice: pani puri
Invalid input. Please enter a number.


--------------------------------------------------
Menu:
1. Add Recipe
2. Display All Recipes
3. Search Recipe by Name
4. Add Recipe to Meal Planning Queue
5. Display Meal Planning Queue
6. Display Recently Viewed Recipes
7. Sort Recipes
8. Add Recipe to Favourites
9. Display Favourites
10. Delete a Recipe
11. Exit
--------------------------------------------------
Enter your choice: 5
Meal Planning Queue:
| Pizza                | Italian         | Dough, Cheese, Tomato          | 40              | 6        | 7          |


--------------------------------------------------
Menu:
1. Add Recipe
2. Display All Recipes
3. Search Recipe by Name
4. Add Recipe to Meal Planning Queue
5. Display Meal Planning Queue
6. Display Recently Viewed Recipes
7. Sort Recipes
8. Add Recipe to Favourites
9. Display Favourites
10. Delete a Recipe
11. Exit
--------------------------------------------------
Enter your choice: 4
Enter the recipe name to add to meal planning queue: pani puri
Added to meal planning queue.


--------------------------------------------------
Menu:
1. Add Recipe
2. Display All Recipes
3. Search Recipe by Name
4. Add Recipe to Meal Planning Queue
5. Display Meal Planning Queue
6. Display Recently Viewed Recipes
7. Sort Recipes
8. Add Recipe to Favourites
9. Display Favourites
10. Delete a Recipe
11. Exit
--------------------------------------------------
Enter your choice: 5
Meal Planning Queue:
| Pizza                | Italian         | Dough, Cheese, Tomato          | 40              | 6        | 7          |
| pani puri            | indian          | puri,  imli chutney,  sev,  ragda,  green chutney | 60              | 4        | 10         |


--------------------------------------------------
Menu:
1. Add Recipe
2. Display All Recipes
3. Search Recipe by Name
4. Add Recipe to Meal Planning Queue
5. Display Meal Planning Queue
6. Display Recently Viewed Recipes
7. Sort Recipes
8. Add Recipe to Favourites
9. Display Favourites
10. Delete a Recipe
11. Exit
--------------------------------------------------
Enter your choice: 6
Recently Viewed Recipes (most recent first):
| Pizza                | Italian         | Dough, Cheese, Tomato          | 40              | 6        | 7          |


--------------------------------------------------
Menu:
1. Add Recipe
2. Display All Recipes
3. Search Recipe by Name
4. Add Recipe to Meal Planning Queue
5. Display Meal Planning Queue
6. Display Recently Viewed Recipes
7. Sort Recipes
8. Add Recipe to Favourites
9. Display Favourites
10. Delete a Recipe
11. Exit
--------------------------------------------------
Enter your choice: 7
Sort Recipes:
1. Sort by No. of Ingredients (Bubble Sort)
2. Sort by Popularity (Insertion Sort)
3. Sort by Cuisine alphabetically (Quick Sort)
Enter your choice: 1
| Name                 | Cuisine         | Ingredients                    | Cooking Time    | Servings | Popularity |
--------------------------------------------------------------
| Pasta                | Italian         | Pasta, Tomato, Cheese          | 30              | 4        | 8          |
| Sushi                | Japanese        | Rice, Fish, Seaweed            | 20              | 2        | 9          |
| Pizza                | Italian         | Dough, Cheese, Tomato          | 40              | 6        | 7          |
| Tacos                | Mexican         | Taco Shell, Beef, Cheese       | 15              | 2        | 6          |
| Burger               | American        | Bun, Beef Patty, Lettuce       | 25              | 4        | 7          |
| Curry                | Indian          | Chicken, Spices, Rice          | 45              | 4        | 9          |
| Salad                | Mediterranean   | Lettuce, Olives, Feta          | 10              | 2        | 6          |
| Pancakes             | American        | Flour, Eggs, Milk              | 20              | 4        | 8          |
| Ramen                | Japanese        | Noodles, Broth, Egg            | 30              | 2        | 8          |
| Fried Rice           | Chinese         | Rice, Egg, Vegetables          | 25              | 4        | 7          |
| pani puri            | indian          | puri,  imli chutney,  sev,  ragda,  green chutney | 60              | 4        | 10         |


--------------------------------------------------
Menu:
1. Add Recipe
2. Display All Recipes
3. Search Recipe by Name
4. Add Recipe to Meal Planning Queue
5. Display Meal Planning Queue
6. Display Recently Viewed Recipes
7. Sort Recipes
8. Add Recipe to Favourites
9. Display Favourites
10. Delete a Recipe
11. Exit
--------------------------------------------------
Enter your choice: 7
Sort Recipes:
1. Sort by No. of Ingredients (Bubble Sort)
2. Sort by Popularity (Insertion Sort)
3. Sort by Cuisine alphabetically (Quick Sort)
Enter your choice: 2
| Name                 | Cuisine         | Ingredients                    | Cooking Time    | Servings | Popularity |
--------------------------------------------------------------
| pani puri            | indian          | puri,  imli chutney,  sev,  ragda,  green chutney | 60              | 4        | 10         |
| Sushi                | Japanese        | Rice, Fish, Seaweed            | 20              | 2        | 9          |
| Curry                | Indian          | Chicken, Spices, Rice          | 45              | 4        | 9          |
| Pasta                | Italian         | Pasta, Tomato, Cheese          | 30              | 4        | 8          |
| Pancakes             | American        | Flour, Eggs, Milk              | 20              | 4        | 8          |
| Ramen                | Japanese        | Noodles, Broth, Egg            | 30              | 2        | 8          |
| Pizza                | Italian         | Dough, Cheese, Tomato          | 40              | 6        | 7          |
| Burger               | American        | Bun, Beef Patty, Lettuce       | 25              | 4        | 7          |
| Fried Rice           | Chinese         | Rice, Egg, Vegetables          | 25              | 4        | 7          |
| Tacos                | Mexican         | Taco Shell, Beef, Cheese       | 15              | 2        | 6          |
| Salad                | Mediterranean   | Lettuce, Olives, Feta          | 10              | 2        | 6          |


--------------------------------------------------
Menu:
1. Add Recipe
2. Display All Recipes
3. Search Recipe by Name
4. Add Recipe to Meal Planning Queue
5. Display Meal Planning Queue
6. Display Recently Viewed Recipes
7. Sort Recipes
8. Add Recipe to Favourites
9. Display Favourites
10. Delete a Recipe
11. Exit
--------------------------------------------------
Enter your choice: 7
Sort Recipes:
1. Sort by No. of Ingredients (Bubble Sort)
2. Sort by Popularity (Insertion Sort)
3. Sort by Cuisine alphabetically (Quick Sort)
Enter your choice: 2
| Name                 | Cuisine         | Ingredients                    | Cooking Time    | Servings | Popularity |
--------------------------------------------------------------
| pani puri            | indian          | puri,  imli chutney,  sev,  ragda,  green chutney | 60              | 4        | 10         |
| Sushi                | Japanese        | Rice, Fish, Seaweed            | 20              | 2        | 9          |
| Curry                | Indian          | Chicken, Spices, Rice          | 45              | 4        | 9          |
| Pasta                | Italian         | Pasta, Tomato, Cheese          | 30              | 4        | 8          |
| Pancakes             | American        | Flour, Eggs, Milk              | 20              | 4        | 8          |
| Ramen                | Japanese        | Noodles, Broth, Egg            | 30              | 2        | 8          |
| Pizza                | Italian         | Dough, Cheese, Tomato          | 40              | 6        | 7          |
| Burger               | American        | Bun, Beef Patty, Lettuce       | 25              | 4        | 7          |
| Fried Rice           | Chinese         | Rice, Egg, Vegetables          | 25              | 4        | 7          |
| Tacos                | Mexican         | Taco Shell, Beef, Cheese       | 15              | 2        | 6          |
| Salad                | Mediterranean   | Lettuce, Olives, Feta          | 10              | 2        | 6          |


--------------------------------------------------
Menu:
1. Add Recipe
2. Display All Recipes
3. Search Recipe by Name
4. Add Recipe to Meal Planning Queue
5. Display Meal Planning Queue
6. Display Recently Viewed Recipes
7. Sort Recipes
8. Add Recipe to Favourites
9. Display Favourites
10. Delete a Recipe
11. Exit
--------------------------------------------------
Enter your choice: 8
Enter the recipe name to add to favorites: pani puri
Added to favorites: pani puri


--------------------------------------------------
Menu:
1. Add Recipe
2. Display All Recipes
3. Search Recipe by Name
4. Add Recipe to Meal Planning Queue
5. Display Meal Planning Queue
6. Display Recently Viewed Recipes
7. Sort Recipes
8. Add Recipe to Favourites
9. Display Favourites
10. Delete a Recipe
11. Exit
--------------------------------------------------
Enter your choice: 9
Favorite Recipes:
- pani puri (indian)


--------------------------------------------------
Menu:
1. Add Recipe
2. Display All Recipes
3. Search Recipe by Name
4. Add Recipe to Meal Planning Queue
5. Display Meal Planning Queue
6. Display Recently Viewed Recipes
7. Sort Recipes
8. Add Recipe to Favourites
9. Display Favourites
10. Delete a Recipe
11. Exit
--------------------------------------------------
Enter your choice: 10
Enter Recipe Name to delete
pizza


--------------------------------------------------
Menu:
1. Add Recipe
2. Display All Recipes
3. Search Recipe by Name
4. Add Recipe to Meal Planning Queue
5. Display Meal Planning Queue
6. Display Recently Viewed Recipes
7. Sort Recipes
8. Add Recipe to Favourites
9. Display Favourites
10. Delete a Recipe
11. Exit
--------------------------------------------------
Enter your choice: 2
All Recipes:
| Name                 | Cuisine         | Ingredients                    | Cooking Time    | Servings | Popularity |
----------------------------------------------------------------------------------------------------------------------------
| Burger               | American        | Bun, Beef Patty, Lettuce       | 25              | 4        | 7          |
| Curry                | Indian          | Chicken, Spices, Rice          | 45              | 4        | 9          |
| Fried Rice           | Chinese         | Rice, Egg, Vegetables          | 25              | 4        | 7          |
| Pancakes             | American        | Flour, Eggs, Milk              | 20              | 4        | 8          |
| pani puri            | indian          | puri,  imli chutney,  sev,  ragda,  green chutney | 60              | 4        | 10         |
| Pasta                | Italian         | Pasta, Tomato, Cheese          | 30              | 4        | 8          |
| Ramen                | Japanese        | Noodles, Broth, Egg            | 30              | 2        | 8          |
| Salad                | Mediterranean   | Lettuce, Olives, Feta          | 10              | 2        | 6          |
| Sushi                | Japanese        | Rice, Fish, Seaweed            | 20              | 2        | 9          |
| Tacos                | Mexican         | Taco Shell, Beef, Cheese       | 15              | 2        | 6          |


--------------------------------------------------
Menu:
1. Add Recipe
2. Display All Recipes
3. Search Recipe by Name
4. Add Recipe to Meal Planning Queue
5. Display Meal Planning Queue
6. Display Recently Viewed Recipes
7. Sort Recipes
8. Add Recipe to Favourites
9. Display Favourites
10. Delete a Recipe
11. Exit
--------------------------------------------------
Enter your choice: 11
Exiting the application...

 **/
