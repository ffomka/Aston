package ru.aston.hometask3.strategy;

import java.math.BigDecimal;

/**
 * Интерфейс стратегии оплаты.
 * Определяет метод оплаты, который могут реализовывать разные способы.
 */
public interface PaymentStrategy {
    /**
     * Осуществляет оплату указанной суммы.
     * @param amount сумма в рублях
     * @return true, если оплата прошла успешно
     */
    boolean pay(BigDecimal amount);

    /**
     * Возвращает описание способа оплаты.
     */
    String getName();
}
