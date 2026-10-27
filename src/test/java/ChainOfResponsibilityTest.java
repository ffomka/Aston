package ru.aston.hometask3;

import ru.aston.hometask3.chainofresponsibility.IngredientCheckHandler;
import ru.aston.hometask3.chainofresponsibility.PaymentCheckHandler;
import ru.aston.hometask3.chainofresponsibility.OrderHandler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для паттерна Chain of Responsibility (OrderHandler chain).
 */
class ChainOfResponsibilityTest {

    @Test
    void handlePaidOrder_thenReturnTrue() {
        IngredientCheckHandler ingredientHandler = new IngredientCheckHandler();
        PaymentCheckHandler paymentHandler = new PaymentCheckHandler(true);

        ingredientHandler.setNext(paymentHandler);

        boolean result = ingredientHandler.handle("Латте с сиропом");

        assertTrue(result);
    }

    @Test
    void handleUnpaidOrder_thenReturnFalse() {
        IngredientCheckHandler ingredientHandler = new IngredientCheckHandler();
        PaymentCheckHandler paymentHandler = new PaymentCheckHandler(false);

        ingredientHandler.setNext(paymentHandler);

        boolean result = ingredientHandler.handle("Капучино");

        assertFalse(result);
    }

    @Test
    void handleWithLongChain_thenReturnTrue() {
        IngredientCheckHandler ingredientHandler = new IngredientCheckHandler();
        PaymentCheckHandler paymentHandler = new PaymentCheckHandler(true);
        OrderHandler finalHandler = new OrderHandler() {
            @Override
            public boolean handle(String orderDescription) {
                return true; // финальный обработчик всегда успешен
            }
        };

        ingredientHandler.setNext(paymentHandler);
        paymentHandler.setNext(finalHandler);

        boolean result = ingredientHandler.handle("Эспрессо");

        assertTrue(result);
    }

    @Test
    void handleWithSingleHandler_thenReturnTrue() {
        PaymentCheckHandler paymentHandler = new PaymentCheckHandler(true);

        boolean result = paymentHandler.handle("Американо");

        assertTrue(result);
    }

    @Test
    void handleWithSingleUnpaidHandler_thenReturnFalse() {
        PaymentCheckHandler paymentHandler = new PaymentCheckHandler(false);

        boolean result = paymentHandler.handle("Раф");

        assertFalse(result);
    }

    @Test
    void handleWithTwoUnpaidHandlers_thenReturnFalse() {
        IngredientCheckHandler ingredientHandler = new IngredientCheckHandler();
        PaymentCheckHandler paymentHandler = new PaymentCheckHandler(false);

        ingredientHandler.setNext(paymentHandler);

        boolean result = ingredientHandler.handle("Латте");

        assertFalse(result);
    }

    @Test
    void handleWithEmptyChain_thenReturnTrue() {
        // Если nextHandler null, forward() возвращает true
        OrderHandler handler = new OrderHandler() {
            @Override
            public boolean handle(String orderDescription) {
                return forward(orderDescription);
            }
        };

        boolean result = handler.handle("Тестовый заказ");

        assertTrue(result);
    }

    @Test
    void handleDifferentOrders_ingredientHandlerAlwaysPasses() {
        // IngredientCheckHandler всегда возвращает true (ингредиенты в наличии)
        // и передаёт запрос дальше
        PaymentCheckHandler paymentHandler = new PaymentCheckHandler(true);
        IngredientCheckHandler ingredientHandler = new IngredientCheckHandler();

        ingredientHandler.setNext(paymentHandler);

        boolean result1 = ingredientHandler.handle("Эспрессо");
        boolean result2 = ingredientHandler.handle("Капучино");
        boolean result3 = ingredientHandler.handle("Латте с карамелью");

        assertTrue(result1);
        assertTrue(result2);
        assertTrue(result3);
    }
}
