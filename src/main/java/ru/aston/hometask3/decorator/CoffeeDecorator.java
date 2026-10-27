package ru.aston.hometask3.decorator;

/**
 * Базовый абстрактный декоратор.
 * Обертывает объект Coffee и делегирует ему вызовы.
 */
public abstract class CoffeeDecorator implements Coffee {
    protected final Coffee decoratedCoffee; // оборачиваемый кофе

    protected CoffeeDecorator(Coffee decoratedCoffee) {
        this.decoratedCoffee = decoratedCoffee;
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription();
    }

    @Override
    public int getPrice() {
        return decoratedCoffee.getPrice();
    }
}
