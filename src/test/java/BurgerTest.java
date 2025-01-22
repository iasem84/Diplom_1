import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    private Bun bun;
    @Mock
    private Ingredient onion, cheese;

    private Burger burger;

    @Before
    public void initBurger() {
        burger = new Burger();
        Mockito.when(bun.getPrice()).thenReturn(200f);
        Mockito.when(onion.getPrice()).thenReturn(57f);
        Mockito.when(bun.getName()).thenReturn("black bun");
        Mockito.when(cheese.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(cheese.getName()).thenReturn("cheese");
        Mockito.when(cheese.getPrice()).thenReturn(30f);
    }

    @Test
    public void isIngredientAddedTest() {
        int expectedSize = burger.ingredients.size() + 1;
        burger.addIngredient(onion);
        assertEquals("Неверное количесво ингредиентов", expectedSize, burger.ingredients.size());
    }

    @Test
    public void isIngredientRemovedTest() {
        burger.ingredients.add(onion);
        int expectedSize = burger.ingredients.size() - 1;
        burger.removeIngredient(0);
        assertEquals("Неверное количесво ингредиентов", expectedSize, burger.ingredients.size());
    }

    @Test
    public void isIngredientsMovedTest() {
        burger.ingredients.addAll(Arrays.asList(onion, cheese));
        List<Ingredient> expectedList = new ArrayList<>(Arrays.asList(cheese, onion));
        burger.moveIngredient(0, 1);
        assertEquals("Ингредиенты не поменялись местами", expectedList, burger.ingredients);
    }

    @Test
    public void getBurgersPriceTest() {
        burger.setBuns(bun);
        burger.ingredients.add(onion);
        float expectedPrice = 457f;
        assertEquals("Неверный расчет стоимости", expectedPrice, burger.getPrice(), 0);
    }

    @Test
    public void getBurgersReceiptTest() {
        burger.setBuns(bun);
        burger.ingredients.add(cheese);

        String expectedReceipt = String.format("(==== %s ====)%n", "black bun") +
                String.format("= %s %s =%n", "filling", "cheese") +
                String.format("(==== %s ====)%n", "black bun") +
                String.format("%nPrice: %f%n", 430f);

        assertEquals("Рецепт не соответствует ожидаемому", expectedReceipt, burger.getReceipt());
    }
}
