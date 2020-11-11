package recipebook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

@SpringBootApplication
public class Main {
    @Autowired
    private RecipeRepository recipeRepository;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            printMenu();
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextInt()) {
                switch (scanner.nextInt()) {
                    case 1:
                        findRecipe(scanner);
                        break;
                    case 2:
                        addRecipe(scanner);
                        break;
                    case 3:
                        deleteRecipe(scanner);
                        break;
                    default:
                        return;
                }
                printMenu();
            }
        };
    }

    private static void printMenu() {
        System.out.println("Введите номер действия из перечисленных:");
        System.out.println("(1) найти рецепт;");
        System.out.println("(2) добавить рецепт;");
        System.out.println("(3) удалить рецепт.");
        System.out.println("Для выхода введите любой другой символ.");
    }

    private static void printIngredients(Recipe recipe) {
        for (Ingredient ingredient : recipe.getIngredients()) {
            System.out.println(ingredient.getName() + " " + ingredient.getAmount());
        }
    }

    private void findRecipe(Scanner scanner) {
        System.out.println("Введите название или часть названия блюда.");
        List<Recipe> recipes = recipeRepository.findByNameContaining(scanner.next());
        if (recipes.isEmpty()) {
            System.out.println("Рецепт не найден.");
        } else {
            for (Recipe recipe : recipes) {
                System.out.println();
                System.out.println(recipe.getName());
                printIngredients(recipe);
            }
        }
    }

    private void addRecipe(Scanner scanner) {
        System.out.println("Введите название блюда.");
        Recipe recipe = new Recipe();
        recipe.setName(scanner.next());
        System.out.println("Введите ингредиенты, чередуя с их количеством. Для завершения введите \"выход\".");
        Set<Ingredient> ingredients = new HashSet<>();
        while (!scanner.hasNext("выход")) {
            Ingredient ingredient = new Ingredient();
            ingredient.setName(scanner.next());
            ingredient.setAmount(scanner.next());
            ingredients.add(ingredient);
        }
        scanner.next();
        recipe.setIngredients(ingredients);
        recipeRepository.save(recipe);
    }

    private void deleteRecipe(Scanner scanner) {
        System.out.println("Введите название блюда.");
        List<Recipe> recipes = recipeRepository.findByName(scanner.next());
        if (recipes.isEmpty()) {
            System.out.println("Рецепт не найден.");
        } else {
            int number;
            if (recipes.size() > 1) {
                System.out.println("Найдено несколько рецептов блюд с таким названием. Введите номер рецепта для его удаления.");
                for (Recipe recipe : recipes) {
                    System.out.println();
                    System.out.println(recipe.getName());
                    printIngredients(recipe);
                }
                number = scanner.nextInt() - 1;
            } else {
                number = 0;
            }
            recipeRepository.delete(recipes.get(number));
            System.out.println("Рецепт удалён.");
        }
    }
}
