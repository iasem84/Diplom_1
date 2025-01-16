import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientTest {

    Ingredient ingredient;
    private final IngredientType type;
    private final String name;
    private final float price;

    public IngredientTest(IngredientType type, String name, float price){
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "Ingredient type: {0}, ingredient name: {1}, ingredient price: {2}")
    public static Object[][] ingredientData() {
        return new Object[][]{
                {IngredientType.SAUCE, "sour cream", 100.45f},
                {IngredientType.FILLING, "dinosaur", 200f}
        };
    }

    @Before
    public void initIngredient(){
        ingredient = new Ingredient(type, name, price);
    }

    @Test
    public void getPriceTest(){
        float actualPrice = ingredient.getPrice();
        assertEquals("Цена ингредиента не соответствует переданному значению", price, actualPrice, 0);
    }

    @Test
    public void getNameTest(){
        String actualName = ingredient.getName();
        assertEquals("Наименование ингредиента не соответствует переданному значению", name, actualName);
    }

    @Test
    public void getIngredientTypeTest(){
        IngredientType actualType = ingredient.getType();
        assertEquals("Тип ингредиента не соответствует переданному значению", type, actualType);
    }
}
