package praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.awt.*;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    Burger burger = new Burger();
    @Mock
    Bun bun;
    @Mock
    Ingredient oneIngredient, twoIngredient;

    //проверить добавление булки
    @Test
    public void shouldBeTwoBunsEquals() {
        burger.setBuns(bun);
        Assert.assertEquals("Ошибка: разные булочки!", bun, burger.bun);
    }
    //проверить добавление ингридиента
    @Test
    public void shouldBeTwoIngredientsEquals() {
        burger.addIngredient(oneIngredient);
        Assert.assertEquals("Ингредиент не был добавлен", oneIngredient, burger.ingredients.get(0));
    }
    //проверить удаление ингридиента
    @Test
    public void removeIngredientFromBurger(){
        burger.addIngredient(oneIngredient);
        burger.removeIngredient(0);
        Assert.assertTrue("Ингредиент не был удален", burger.ingredients.isEmpty());
    }
    //проверить перемещение ингридиента
    @Test
    public void moveIngredient() {
        burger.addIngredient(oneIngredient);
        burger.addIngredient(twoIngredient);
        burger.moveIngredient(0, 1);
        Assert.assertEquals("Ингредиент не был перемещен", oneIngredient, burger.ingredients.get(1));

    }
    //сравнить стоимость бургера
    @Test
    public void shouldBeTwoBurgerPriceEquals() {
        burger.setBuns(bun);
        Mockito.when(bun.getPrice()).thenReturn(100f);
        burger.addIngredient(oneIngredient);
        Mockito.when(oneIngredient.getPrice()).thenReturn(200f);
        burger.addIngredient(twoIngredient);
        Mockito.when(twoIngredient.getPrice()).thenReturn(300f);
        float expectedPrice = bun.getPrice()*2 + oneIngredient.getPrice() + twoIngredient.getPrice();
        Assert.assertEquals("Стоимость не совпадает", expectedPrice, burger.getPrice(), 0);
    }
    //проверить чек с информацией о бургере
    @Test
    public void shouldBeTwoReceptsEquals() {
        burger.setBuns(bun);
        Mockito.when(bun.getName()).thenReturn("white bun");
        Mockito.when(bun.getPrice()).thenReturn(200f);
        burger.addIngredient(twoIngredient);
        Mockito.when(twoIngredient.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(twoIngredient.getName()).thenReturn("sausage");
        Mockito.when(twoIngredient.getPrice()).thenReturn(300f);
        String actualRecept = burger.getReceipt();
        String expectedRecept = String.format("(==== white bun ====)%n= filling sausage =%n(==== white bun ====)%n%nPrice: 700,000000%n");
        Assert.assertEquals("Данные не совподают!",expectedRecept, actualRecept);
    }

}