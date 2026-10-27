package ru.aston.hometask3.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Стратегия оплаты криптовалютой.
 */
public class CryptoPaymentStrategy implements PaymentStrategy {

    private static final BigDecimal RATE = new BigDecimal("3500");

    @Override
    public boolean pay(BigDecimal amount) {
        BigDecimal cryptoAmount = amount.divide(RATE, 6, RoundingMode.HALF_UP);
        System.out.printf("   🪙 Оплачено %.6f USDT (эквивалент %s₽) криптовалютой%n",
                cryptoAmount.doubleValue(), amount);
        return true;
    }

    @Override
    public String getName() {
        return "Криптовалюта (USDT)";
    }
}
