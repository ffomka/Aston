/**
 * Главный класс — демонстрация 6 паттернов проектирования.
 * Предметная область: Система заказа кофе.
 */
public class Main {

    // Разделитель для красивого вывода
    private static final String SEPARATOR = "═══════════════════════════════════════════════════════";

    public static void main(String[] args) {
        System.out.println(SEPARATOR);
        System.out.println("       ДЕМОНСТРАЦИЯ 6 ПАТТЕРНОВ ПРОЕКТИРОВАНИЯ");
        System.out.println("       Предметная область: Система заказа кофе");
        System.out.println(SEPARATOR);
        System.out.println();

        // =============================================
        // 1. BUILDER — Сборка заказа кофе
        // =============================================
        demonstrateBuilder();

        // =============================================
        // 2. DECORATOR — Добавки к кофе
        // =============================================
        demonstrateDecorator();

        // =============================================
        // 3. STRATEGY — Способы оплаты
        // =============================================
        demonstrateStrategy();

        // =============================================
        // 4. ADAPTER — Адаптация старого банковского API
        // =============================================
        demonstrateAdapter();

        // =============================================
        // 5. CHAIN OF RESPONSIBILITY — Обработка заказа
        // =============================================
        demonstrateChainOfResponsibility();

        // =============================================
        // 6. PROXY — Меню с кэшированием и авторизацией
        // =============================================
        demonstrateProxy();

        System.out.println();
        System.out.println(SEPARATOR);
        System.out.println("       ВСЕ ПАТТЕРНЫ ДЕМОНСТРИРОВАНЫ УСПЕШНО ✅");
        System.out.println(SEPARATOR);
    }

    // =============================================
    // ПАТТЕРН 1: BUILDER
    // =============================================
    private static void demonstrateBuilder() {
        System.out.println("┌" + "─".repeat(66) + "┐");
        System.out.println("│  1. ПАТТЕРН BUILDER — Сборка заказа кофе                  " + " ".repeat(27) + "│");
        System.out.println("└" + "─".repeat(66) + "┘");
        System.out.println();

        // Собираем заказ с помощью Builder
        CoffeeOrder order = new CoffeeOrder.CoffeeOrderBuilder("Капучино")
                .size("Large")
                .milk("Овсяное")
                .syrup("Ванильный")
                .extras("Двойная shot")
                .build();

        System.out.println(order);
        System.out.println();
        System.out.println("   💡 Builder позволяет пошагово создавать сложный объект,");
        System.out.println("      избегая конструкторы с множеством параметров.");
        System.out.println();
    }

    // =============================================
    // ПАТТЕРН 2: DECORATOR
    // =============================================
    private static void demonstrateDecorator() {
        System.out.println("┌" + "─".repeat(66) + "┐");
        System.out.println("│  2. ПАТТЕРН DECORATOR — Добавки к кофе                    " + " ".repeat(27) + "│");
        System.out.println("└" + "─".repeat(66) + "┘");
        System.out.println();

        // Начинаем с простого кофе
        Coffee coffee = new SimpleCoffee();
        System.out.println("   Базовый кофе: " + coffee.getDescription() + " | Цена: " + coffee.getPrice() + "₽");
        System.out.println();

        // Добавляем молоко
        coffee = new MilkDecorator(coffee);
        System.out.println("   + Молоко:      " + coffee.getDescription() + " | Цена: " + coffee.getPrice() + "₽");
        System.out.println();

        // Добавляем сироп
        coffee = new SyrupDecorator(coffee);
        System.out.println("   + Сироп:       " + coffee.getDescription() + " | Цена: " + coffee.getPrice() + "₽");
        System.out.println();

        // Добавляем ещё раз молоко (можно комбинировать!)
        coffee = new MilkDecorator(coffee);
        System.out.println("   + Ещё молоко:  " + coffee.getDescription() + " | Цена: " + coffee.getPrice() + "₽");
        System.out.println();

        System.out.println("   💡 Декораторы позволяют динамически расширять");
        System.out.println("      поведение объекта без изменения исходного класса.");
        System.out.println();
    }

    // =============================================
    // ПАТТЕРН 3: STRATEGY
    // =============================================
    private static void demonstrateStrategy() {
        System.out.println("┌" + "─".repeat(66) + "┐");
        System.out.println("│  3. ПАТТЕРН STRATEGY — Способы оплаты                       " + " ".repeat(27) + "│");
        System.out.println("└" + "─".repeat(66) + "┘");
        System.out.println();

        int orderTotal = 250; // сумма заказа
        System.out.println("   Сумма заказа: " + orderTotal + "₽");
        System.out.println();

        // Создаем контекст с разными стратегиями
        PaymentContext context = new PaymentContext();

        // Оплата картой
        PaymentStrategy cardStrategy = new CardPaymentStrategy();
        System.out.println("   Способ: " + cardStrategy.getName());
        context.setPaymentStrategy(cardStrategy);
        context.processPayment(orderTotal);
        System.out.println();

        // Оплата наличными
        PaymentStrategy cashStrategy = new CashPaymentStrategy();
        System.out.println("   Способ: " + cashStrategy.getName());
        context.setPaymentStrategy(cashStrategy);
        context.processPayment(orderTotal);
        System.out.println();

        // Оплата криптовалютой
        PaymentStrategy cryptoStrategy = new CryptoPaymentStrategy();
        System.out.println("   Способ: " + cryptoStrategy.getName());
        context.setPaymentStrategy(cryptoStrategy);
        context.processPayment(orderTotal);
        System.out.println();

        System.out.println("   💡 Стратегия позволяет выбирать алгоритм выполнения");
        System.out.println("      задачи во время выполнения программы.");
        System.out.println();
    }

    /**
     * Контекст, использующий стратегию оплаты.
     */
    static class PaymentContext {
        private PaymentStrategy paymentStrategy;

        public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
            this.paymentStrategy = paymentStrategy;
        }

        public void processPayment(int amount) {
            paymentStrategy.pay(amount);
        }
    }

    // =============================================
    // ПАТТЕРН 4: ADAPTER
    // =============================================
    private static void demonstrateAdapter() {
        System.out.println("┌" + "─".repeat(66) + "┐");
        System.out.println("│  4. ПАТТЕРН ADAPTER — Адаптация старого API               " + " ".repeat(27) + "│");
        System.out.println("└" + "─".repeat(66) + "┘");
        System.out.println();

        int orderTotal = 300;
        System.out.println("   Сумма заказа: " + orderTotal + "₽");
        System.out.println();

        // Создаём старый банковский API (чужой, неудобный интерфейс)
        OldBankApi oldBankApi = new OldBankApi();

        // Адаптируем его к нашему интерфейсу PaymentStrategy
        PaymentStrategy adapter = new BankApiAdapter(oldBankApi);

        System.out.println("   Способ: " + adapter.getName());
        adapter.pay(orderTotal);
        System.out.println();

        System.out.println("   💡 Адаптер позволяет объектам с несовместимыми интерфейсами");
        System.out.println("      работать вместе, преобразуя интерфейс одного класса.");
        System.out.println();
    }

    // =============================================
    // ПАТТЕРН 5: CHAIN OF RESPONSIBILITY
    // =============================================
    private static void demonstrateChainOfResponsibility() {
        System.out.println("┌" + "─".repeat(66) + "┐");
        System.out.println("│  5. ПАТТЕРН CHAIN OF RESPONSIBILITY — Обработка заказа     " + " ".repeat(25) + "│");
        System.out.println("└" + "─".repeat(66) + "┘");
        System.out.println();

        String orderDescription = "Латте с сиропом";
        System.out.println("   Заказ: " + orderDescription);
        System.out.println();

        // Строим цепочку обработчиков
        IngredientCheckHandler ingredientHandler = new IngredientCheckHandler();
        PaymentCheckHandler paymentHandler = new PaymentCheckHandler(true); // заказ оплачен

        ingredientHandler.setNext(paymentHandler);

        System.out.println("   Цепочка: [Проверка ингредиентов] → [Проверка оплаты]");
        System.out.println();

        boolean result = ingredientHandler.handle(orderDescription);

        System.out.println();
        if (result) {
            System.out.println("   🎉 Заказ успешно обработан и готов к приготовлению!");
        } else {
            System.out.println("   🚫 Заказ отклонён на одном из этапов.");
        }
        System.out.println();

        // Демонстрация отклонения (не оплачен)
        System.out.println("   --- Вариант 2: Неоплаченный заказ ---");
        PaymentCheckHandler unpaidHandler = new PaymentCheckHandler(false);
        ingredientHandler.setNext(unpaidHandler);
        boolean result2 = ingredientHandler.handle(orderDescription);

        System.out.println();
        if (result2) {
            System.out.println("   🎉 Заказ успешно обработан!");
        } else {
            System.out.println("   🚫 Заказ отклонён: не оплачен!");
        }
        System.out.println();

        System.out.println("   💡 Цепочка обязанностей позволяет передать запрос");
        System.out.println("      по цепочке обработчиков, пока один из них не обработает его.");
        System.out.println();
    }

    // =============================================
    // ПАТТЕРН 6: PROXY
    // =============================================
    private static void demonstrateProxy() {
        System.out.println("┌" + "─".repeat(66) + "┐");
        System.out.println("│  6. ПАТТЕРН PROXY — Меню с кэшированием и доступом        " + " ".repeat(26) + "│");
        System.out.println("└" + "─".repeat(66) + "┘");
        System.out.println();

        // 6a. Неавторизованный пользователь
        System.out.println("   6a. Неавторизованный пользователь:");
        Menu unauthorizedProxy = new MenuProxy(false);
        String menu1 = unauthorizedProxy.getMenu();
        System.out.println("   " + menu1);
        System.out.println();

        // 6b. Авторизованный пользователь — первое обращение (тяжёлая загрузка)
        System.out.println("   6b. Авторизованный пользователь — 1-й запрос (загрузка):");
        Menu authorizedProxy = new MenuProxy(true);
        String menu2 = authorizedProxy.getMenu();
        System.out.println("   " + menu2);
        System.out.println();

        // 6c. Авторизованный пользователь — второе обращение (кэш)
        System.out.println("   6c. Авторизованный пользователь — 2-й запрос (кэш):");
        String menu3 = authorizedProxy.getMenu();
        System.out.println("   " + menu3);
        System.out.println();

        System.out.println("   💡 Прокси контролирует доступ к реальному объекту,");
        System.out.println("      добавляя дополнительную логику (кэш, проверка прав).");
        System.out.println();
    }
}
