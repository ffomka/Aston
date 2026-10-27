/**
 * Класс заказа кофе — результат работы Builder-а.
 * Содержит все параметры заказа: тип кофе, размер, добавки.
 */
public class CoffeeOrder {

    private final CoffeeType coffeeType;
    private final Size size;
    private final MilkType milk;
    private final SyrupType syrup;
    private final String extras;

    private CoffeeOrder(CoffeeOrderBuilder builder) {
        this.coffeeType = builder.coffeeType;
        this.size = builder.size;
        this.milk = builder.milk;
        this.syrup = builder.syrup;
        this.extras = builder.extras;
    }

    public CoffeeType getCoffeeType() { return coffeeType; }
    public Size getSize() { return size; }
    public MilkType getMilk() { return milk; }
    public SyrupType getSyrup() { return syrup; }
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

    public static CoffeeOrderBuilder builder() {
        return new CoffeeOrderBuilder();
    }

    public enum Size {
        SMALL("Small"),
        MEDIUM("Medium"),
        LARGE("Large");

        private final String label;

        Size(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    public enum MilkType {
        REGULAR("Обычное"),
        OATMEAL("Овсяное"),
        SOY("Соевое"),
        ALMOND("Миндальное");

        private final String label;

        MilkType(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    public enum SyrupType {
        NONE("Без сиропа"),
        VANILLA("Ванильный"),
        CARAMEL("Карамельный"),
        CHOCOLATE("Шоколадный");

        private final String label;

        SyrupType(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    public enum CoffeeType {
        ESPRESSO("Эспрессо"),
        CAPPUCCINO("Капучино"),
        LATTE("Латте"),
        AMERICANO("Американо"),
        RAFF("Раф");

        private final String label;

        CoffeeType(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    public static class CoffeeOrderBuilder {
        private CoffeeType coffeeType;
        private Size size;
        private MilkType milk;
        private SyrupType syrup;
        private String extras;

        private CoffeeOrderBuilder() {
        }

        public CoffeeOrderBuilder coffeeType(CoffeeType coffeeType) {
            this.coffeeType = coffeeType;
            return this;
        }

        public CoffeeOrderBuilder size(Size size) {
            this.size = size;
            return this;
        }

        public CoffeeOrderBuilder milk(MilkType milk) {
            this.milk = milk;
            return this;
        }

        public CoffeeOrderBuilder syrup(SyrupType syrup) {
            this.syrup = syrup;
            return this;
        }

        public CoffeeOrderBuilder extras(String extras) {
            this.extras = extras;
            return this;
        }

        public CoffeeOrder build() {
            if (coffeeType == null) {
                throw new IllegalArgumentException("Тип кофе обязателен");
            }
            if (size == null) {
                throw new IllegalArgumentException("Размер обязателен");
            }
            if (milk == null) {
                throw new IllegalArgumentException("Тип молока обязателен");
            }
            if (syrup == null) {
                throw new IllegalArgumentException("Сироп обязателен");
            }
            return new CoffeeOrder(this);
        }
    }
}
