package ru.aston.hometask3.decorator;

/**
 * Декоратор — добавление сиропа к кофе.
 * Расширяет описание и увеличивает цену.
 */
public class SyrupDecorator extends CoffeeDecorator {

    public SyrupDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + " + сироп";
    }

    @Override
    public int getPrice() {
        return decoratedCoffee.getPrice() + 40; // сироп добавляет 40 к цене
    }
}
