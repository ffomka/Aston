/**
 * Декоратор — добавление молока к кофе.
 * Расширяет описание и увеличивает цену.
 */
public class MilkDecorator extends CoffeeDecorator {

    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + " + молоко";
    }

    @Override
    public int getPrice() {
        return decoratedCoffee.getPrice() + 30; // молоко добавляет 30 к цене
    }
}
