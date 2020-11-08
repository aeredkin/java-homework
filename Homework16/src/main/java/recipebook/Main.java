package recipebook;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(org.h2.Driver.class);
        dataSource.setUsername("rb");
        dataSource.setUrl("jdbc:h2:file:./recipebook");
        dataSource.setPassword("rbPassword");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS RECIPEBOOK(ID IDENTITY PRIMARY KEY, NAME VARCHAR(40) NOT NULL)");
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS INGREDIENTS(ID LONG, NAME VARCHAR(40) NOT NULL, AMOUNT VARCHAR(10) NOT NULL)");

        System.out.println("Введите номер действия из перечисленных:");
        System.out.println("(1) найти рецепт;");
        System.out.println("(2) добавить рецепт;");
        System.out.println("(3) удалить рецепт.");
        Scanner scanner = new Scanner(System.in);
        List<Recipe> recipes;
        Number id;
        switch (scanner.nextInt()) {
            case 1:
                System.out.println("Введите название или часть названия блюда.");
                recipes = jdbcTemplate.query("SELECT * FROM RECIPEBOOK WHERE NAME LIKE ?",
                        new Object[]{"%" + scanner.next() + "%"}, Main::mapRecipeRow);
                if (recipes.isEmpty()) {
                    System.out.println("Рецепт не найден.");
                } else {
                    for (Recipe recipe : recipes) {
                        System.out.println();
                        System.out.println(recipe.getName());
                        printIngredients(jdbcTemplate, recipe);
                    }
                }
                break;
            case 2:
                System.out.println("Введите название рецепта.");
                HashMap<String, String> insertingRecipeName = new HashMap<>();
                insertingRecipeName.put("NAME", scanner.next());
                SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(dataSource);
                jdbcInsert.withTableName("RECIPEBOOK").usingGeneratedKeyColumns("ID");
                id = jdbcInsert.executeAndReturnKey(insertingRecipeName);

                System.out.println("Введите ингредиенты, чередуя с их количеством. Для завершения введите \"выход\".");
                while (!scanner.hasNext("выход")) {
                    jdbcTemplate.update("INSERT INTO INGREDIENTS(ID, NAME, AMOUNT) VALUES(?, ?, ?)",
                            id, scanner.next(), scanner.next());
                }
                break;
            case 3:
                System.out.println("Введите название блюда.");
                recipes = jdbcTemplate.query("SELECT * FROM RECIPEBOOK WHERE NAME = ?",
                        new Object[]{scanner.next()}, Main::mapRecipeRow);
                if (recipes.isEmpty()) {
                    System.out.println("Рецепт не найден.");
                } else {
                    if (recipes.size() > 1) {
                        System.out.println("Найдено несколько рецептов. Введите идентификатор рецепта для его удаления");
                        for (Recipe recipe : recipes) {
                            System.out.println();
                            System.out.println(recipe.getId() + " " + recipe.getName());
                            printIngredients(jdbcTemplate, recipe);
                        }
                        id = scanner.nextLong();
                    } else {
                        id = recipes.get(0).getId();
                    }
                    jdbcTemplate.update("DELETE FROM INGREDIENTS WHERE ID = ?", id);
                    jdbcTemplate.update("DELETE FROM RECIPEBOOK WHERE ID = ?", id);
                }
                break;
        }
    }

    private static Recipe mapRecipeRow(ResultSet resultSet, int i) throws SQLException {
        Recipe recipe = new Recipe();
        recipe.setId(resultSet.getLong("ID"));
        recipe.setName(resultSet.getString("NAME"));
        return recipe;
    }

    private static Ingredient mapIngredientRow(ResultSet resultSet, int i) throws SQLException {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(resultSet.getLong("ID"));
        ingredient.setName(resultSet.getString("NAME"));
        ingredient.setAmount(resultSet.getString("AMOUNT"));
        return ingredient;
    }

    private static void printIngredients(JdbcTemplate jdbcTemplate, Recipe recipe) {
        List<Ingredient> ingredients = jdbcTemplate.query("SELECT * FROM INGREDIENTS WHERE ID = ?",
                new Object[]{recipe.getId()}, Main::mapIngredientRow);
        for (Ingredient ingredient : ingredients) {
            System.out.println(ingredient.getName() + " " + ingredient.getAmount());
        }
    }
}
