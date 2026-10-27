package ru.aston.hometask3.strategy;

import java.math.BigDecimal;

/**
 * Стратегия оплаты банковской картой.
 */
public class CardPaymentStrategy implements PaymentStrategy {

    @Override
    public boolean pay(BigDecimal amount) {
        System.out.println("   💳 Оплачено " + amount + "₽ банковской картой");
        return true;
    }

    @Override
    public String getName() {
        return "Банковская карта";
    }
}
