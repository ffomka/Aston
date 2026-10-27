/**
 * Базовый класс — простой кофе без добавок.
 * Реализация интерфейса Coffee.
 */
public class SimpleCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "☕ Простой кофе";
    }

    @Override
    public int getPrice() {
        return 100; // базовая цена
    }
}
