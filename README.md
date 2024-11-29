# Smart Recipe Manager

The **Smart Recipe Manager** is a Java-based application designed to manage recipes efficiently. This project demonstrates the use of various data structures like Binary Search Trees (BST), Stacks, Circular Queues, and Sorting Algorithms to create an interactive and feature-rich recipe management system. Users can add, view, search, and manage their favorite recipes while leveraging advanced functionalities like meal planning and sorting recipes by popularity, ingredients, or cuisine.


## Features

### For Users:
- **Add Recipes**: Enter details like name, cuisine, ingredients, cooking time, servings, and popularity.
- **Search Recipes**: Look up a recipe by its name.
- **Display Recipes**: View all recipes stored in the system in a formatted table.
- **Sort Recipes**: Sort recipes by:
  - Number of ingredients (Bubble Sort)
  - Popularity (Insertion Sort)
  - Cuisine (Quick Sort)
- **Manage Favorites**: Add recipes to a list of favorites for quick access.
- **Meal Planning Queue**: Add recipes to a queue to plan meals for the week.
- **Recently Viewed Recipes**: View the stack of recently accessed recipes.

### For Developers:
- **Data Structure Integration**: 
  - Binary Search Tree for efficient recipe storage and retrieval.
  - Stack for managing recently viewed recipes.
  - Circular Queue for meal planning.
  - Sorting algorithms for customizable recipe sorting.
- **Error Handling**: Validates user inputs and handles edge cases to prevent crashes.
- **Scalable Design**: Modular structure with extensible components for future enhancements.

---

## Technology Stack
- **Language**: Java
- **IDE**: Any Java-supported IDE (e.g., IntelliJ IDEA, Eclipse, NetBeans)
- **Data Structures**: Binary Search Tree, Stack, Circular Queue
- **Algorithms**: Bubble Sort, Insertion Sort, Quick Sort

---

## Project Architecture
1. **Recipe Class**: Represents individual recipes with properties like name, cuisine, ingredients, cooking time, servings, and popularity.
2. **Binary Search Tree**: Used to store and manage recipes efficiently.
3. **Stack**: Keeps track of recently viewed recipes for quick access.
4. **Circular Queue**: Manages a fixed-size queue for meal planning.
5. **Sorting Module**: Provides various sorting options for recipes based on user preferences.

---

## Usage

### 1. Run the Application
- Clone this repository and open it in a Java-supported IDE.
- Compile and run the `DS_miniproj` class.

### 2. Interact with the Menu
The main menu provides options like:
1. Add a recipe.
2. Display all recipes.
3. Search for a recipe by name.
4. Add recipes to the meal planning queue.
5. View meal planning queue.
6. Display recently viewed recipes.
7. Sort recipes.
8. Add recipes to favorites.
9. Display favorite recipes.
10. Delete a recipe.
11. Exit the application.

### 3. Input Validation
- Enter valid inputs for recipe details like cooking time, servings, and popularity.
- Handle invalid inputs gracefully with clear error messages.

---

## Example Output
```text
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
Enter your choice: 2
All Recipes:
| Name         | Cuisine       | Ingredients           | Cooking Time | Servings | Popularity |
| Pasta        | Italian       | Pasta, Tomato, Cheese | 30           | 4        | 8          |
| Sushi        | Japanese      | Rice, Fish, Seaweed   | 20           | 2        | 9          |
