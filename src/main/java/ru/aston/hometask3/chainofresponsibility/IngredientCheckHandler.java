package ru.aston.hometask3.chainofresponsibility;

/**
 * Обработчик проверки ингредиентов.
 * Проверяет, есть ли в наличии все необходимые ингредиенты.
 */
public class IngredientCheckHandler extends OrderHandler {

    @Override
    public boolean handle(String orderDescription) {
        System.out.println("   📦 [IngredientCheck] Проверка наличия ингредиентов для: " + orderDescription);

        // Симуляция проверки — ингредиенты всегда в наличии
        boolean ingredientsAvailable = true;

        if (ingredientsAvailable) {
            System.out.println("   ✅ [IngredientCheck] Все ингредиенты в наличии!");
            return forward(orderDescription); // передаём дальше
        } else {
            System.out.println("   ❌ [IngredientCheck] Недостаточно ингредиентов!");
            return false;
        }
    }
}
