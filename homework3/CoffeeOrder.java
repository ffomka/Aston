/**
 * Класс заказа кофе — результат работы Builder-а.
 * Содержит все параметры заказа: тип кофе, размер, добавки.
 */
public class CoffeeOrder {
    private final String coffeeType;    // Тип кофе
    private final String size;          // Размер
    private final String milk;          // Тип молока
    private final String syrup;         // Сироп
    private final String extras;        // Дополнительные добавки

    // Приватный конструктор — создание только через Builder
    private CoffeeOrder(CoffeeOrderBuilder builder) {
        this.coffeeType = builder.coffeeType;
        this.size = builder.size;
        this.milk = builder.milk;
        this.syrup = builder.syrup;
        this.extras = builder.extras;
    }

    /**
     * Внутренний класс-Builder для пошагового создания заказа.
     */
    public static class CoffeeOrderBuilder {
        private String coffeeType;
        private String size;
        private String milk;
        private String syrup;
        private String extras;

        public CoffeeOrderBuilder(String coffeeType) {
            this.coffeeType = coffeeType;
        }

        public CoffeeOrderBuilder size(String size) {
            this.size = size;
            return this; // цепочка вызовов
        }

        public CoffeeOrderBuilder milk(String milk) {
            this.milk = milk;
            return this;
        }

        public CoffeeOrderBuilder syrup(String syrup) {
            this.syrup = syrup;
            return this;
        }

        public CoffeeOrderBuilder extras(String extras) {
            this.extras = extras;
            return this;
        }

        public CoffeeOrder build() {
            return new CoffeeOrder(this);
        }
    }

    // Геттеры
    public String getCoffeeType() { return coffeeType; }
    public String getSize() { return size; }
    public String getMilk() { return milk; }
    public String getSyrup() { return syrup; }
    public String getExtras() { return extras; }

    @Override
    public String toString() {
        return "📋 Заказ:\n" +
                "   Кофе: " + coffeeType + "\n" +
                "   Размер: " + size + "\n" +
                "   Молоко: " + milk + "\n" +
                "   Сироп: " + syrup + "\n" +
                "   Добавки: " + extras;
    }
}
