package ru.aston.hometask3;

import ru.aston.hometask3.decorator.Coffee;
import ru.aston.hometask3.decorator.SimpleCoffee;
import ru.aston.hometask3.decorator.MilkDecorator;
import ru.aston.hometask3.decorator.SyrupDecorator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для паттерна Decorator (Coffee + MilkDecorator + SyrupDecorator).
 */
class CoffeeDecoratorTest {

    @Test
    void getSimpleCoffeePrice_then_returns100() {
        Coffee coffee = new SimpleCoffee();

        assertEquals(100, coffee.getPrice());
        assertEquals("☕ Простой кофе", coffee.getDescription());
    }

    @Test
    void addOneMilk_then_increasesPriceBy30() {
        Coffee coffee = new SimpleCoffee();
        coffee = new MilkDecorator(coffee);

        assertEquals(130, coffee.getPrice());
        assertTrue(coffee.getDescription().contains("молоко"));
    }

    @Test
    void addTwoMilk_then_increasesPriceBy60() {
        Coffee coffee = new SimpleCoffee();
        coffee = new MilkDecorator(coffee);
        coffee = new MilkDecorator(coffee);

        assertEquals(160, coffee.getPrice());
        // Описание должно содержать "молоко" дважды
        int count = countOccurrences(coffee.getDescription(), "молоко");
        assertEquals(2, count);
    }

    @Test
    void addMilkAndSyrup_then_increasesPriceBy70() {
        Coffee coffee = new SimpleCoffee();
        coffee = new MilkDecorator(coffee);
        coffee = new SyrupDecorator(coffee);

        assertEquals(170, coffee.getPrice());
        assertTrue(coffee.getDescription().contains("молоко"));
        assertTrue(coffee.getDescription().contains("сироп"));
    }

    @Test
    void addSyrupThenMilk_then_increasesPriceBy70() {
        Coffee coffee = new SimpleCoffee();
        coffee = new SyrupDecorator(coffee);
        coffee = new MilkDecorator(coffee);

        assertEquals(170, coffee.getPrice());
    }

    @Test
    void addTwoMilkAndTwoSyrup_then_increasesPriceBy140() {
        Coffee coffee = new SimpleCoffee();
        coffee = new MilkDecorator(coffee);
        coffee = new MilkDecorator(coffee);
        coffee = new SyrupDecorator(coffee);
        coffee = new SyrupDecorator(coffee);

        assertEquals(240, coffee.getPrice());
    }

    @Test
    void decoratorChainPreservesDescriptionOrder() {
        Coffee coffee = new SimpleCoffee();
        coffee = new MilkDecorator(coffee);
        coffee = new SyrupDecorator(coffee);

        String description = coffee.getDescription();
        // "Простой кофе" должен идти до "молоко", а "молоко" до "сироп"
        int simplePos = description.indexOf("Простой кофе");
        int milkPos = description.indexOf("молоко");
        int syrupPos = description.indexOf("сироп");

        assertTrue(simplePos < milkPos);
        assertTrue(milkPos < syrupPos);
    }

    private int countOccurrences(String text, String sub) {
        int count = 0;
        int index = 0;
        while ((index = text.indexOf(sub, index)) != -1) {
            count++;
            index += sub.length();
        }
        return count;
    }
}
