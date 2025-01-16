import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientTypeTest {
    private final String type;

    public IngredientTypeTest(String type){
        this.type = type;
    }

    @Parameterized.Parameters(name = "Ingredients type: {0}")
    public static Object[][] ingredientTypeData() {
        return new Object[][]{
                {IngredientType.FILLING.name()},
                {IngredientType.SAUCE.name()}
        };
    }

    @Test
    public void countOfIngredientsTypeTest() {
        int expectedCount = 2;
        int actualCount = IngredientType.values().length;
        assertEquals("Количество типов ингредиентов не соответсвует переданному", expectedCount, actualCount);
    }

    @Test
    public void nameOfIngredientsTypeTest() {
        String actualType = String.valueOf(IngredientType.valueOf(type));
        assertEquals("Тип ингредиента не соответсвует переданному", type, actualType);
    }
}
