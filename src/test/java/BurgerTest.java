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
    Bun bun;
    @Mock
    Ingredient onion, cheese;

    Burger burger;

    @Before
    public void initBurger() {
        burger = new Burger();
    }

    @Test
    public void isIngredientAddedTest() {
        int expectedSize = burger.ingredients.size() + 1;
        burger.addIngredient(onion);
        assertEquals(expectedSize, burger.ingredients.size());
    }

    @Test
    public void isIngredientRemovedTest() {
        burger.ingredients.add(onion);
        int expectedSize = burger.ingredients.size() - 1;
        burger.removeIngredient(0);
        assertEquals(expectedSize, burger.ingredients.size());
    }

    @Test
    public void isIngredientsMovedTest() {
        burger.ingredients.addAll(Arrays.asList(onion, cheese));
        List<Ingredient> expectedList = new ArrayList<>(Arrays.asList(cheese, onion));
        burger.moveIngredient(0, 1);
        assertEquals(expectedList, burger.ingredients);
    }

    @Test
    public void getBurgersPriceTest() {
        burger.setBuns(bun);
        burger.ingredients.add(onion);
        Mockito.when(bun.getPrice()).thenReturn(200f);
        Mockito.when(onion.getPrice()).thenReturn(57f);
        float expectedPrice = 457f;
        assertEquals(expectedPrice, burger.getPrice(), 0);
    }

    @Test
    public void getBurgersReceiptTest() {
        burger.setBuns(bun);
        burger.ingredients.add(cheese);
        Mockito.when(bun.getName()).thenReturn("black bun");
        Mockito.when(bun.getPrice()).thenReturn(100f);
        Mockito.when(cheese.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(cheese.getName()).thenReturn("cheese");
        Mockito.when(cheese.getPrice()).thenReturn(30f);

        String expectedReceipt = String.format("(==== %s ====)%n", "black bun") +
                String.format("= %s %s =%n", "filling", "cheese") +
                String.format("(==== %s ====)%n", "black bun") +
                String.format("%nPrice: %f%n", 230f);

        assertEquals(expectedReceipt, burger.getReceipt());
    }

}
