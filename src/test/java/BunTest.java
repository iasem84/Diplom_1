import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BunTest {

    Bun bun;
    private final String name;
    private final float price;

    public BunTest(String name, float price){
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "buns name: {0}, buns price: {1}")
    public static Object[][] bunData() {
        return new Object[][]{
                {"black bun", 100.45f},
                {"", 500.87f},
                {null, 0f},
                {"Безглютеновая булочка", -60f},
                {"white%bun", 0.0000000001f},
                {"white bunwhite bunwhite bunwhite bunwhite bunwhite bunwhite bunwhite bun",
                        0.01f},
                {"w", 987677889996554433366799.99f},
        };
    }

    @Before
    public void initBun(){
        bun = new Bun(name, price);
    }

    @Test
    public void getNameTest(){
        String actualName = bun.getName();
        assertEquals("Название булки не соответствует переданному", name, actualName);
    }

    @Test
    public void getPriceTest(){
        float actualPrice = bun.getPrice();
        assertEquals("Цена булки не соответствует переданному значению", price, actualPrice, 0);
    }
}
