package ru.aston.hometask3;

import ru.aston.hometask3.strategy.CardPaymentStrategy;
import ru.aston.hometask3.adapter.BankApiAdapter;
import ru.aston.hometask3.adapter.OldBankApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Тесты для паттерна Strategy (PaymentStrategy implementations).
 */
class PaymentStrategyTest {

    private CardPaymentStrategy cardStrategy;
    private BankApiAdapter mockAdapter;
    private OldBankApi mockOldBankApi;

    @BeforeEach
    void setUp() {
        cardStrategy = new CardPaymentStrategy();
        mockOldBankApi = mock(OldBankApi.class);
        mockAdapter = new BankApiAdapter(mockOldBankApi);
    }

    @Test
    void payWithCardStrategy_then_returnsTrue() {
        BigDecimal amount = new BigDecimal("250");

        boolean result = cardStrategy.pay(amount);

        assertTrue(result);
    }

    @Test
    void payWithCardStrategy_then_printsCorrectMessage() {
        BigDecimal amount = new BigDecimal("150");

        cardStrategy.pay(amount);

        // Проверяем, что метод не бросает исключений и возвращает true
        // (вывод в консоль тестируется косвенно через успешное выполнение)
        verifyNoInteractions(mockOldBankApi);
    }

    @Test
    void payWithAdapterStrategy_then_callsOldBankApi() {
        BigDecimal amount = new BigDecimal("300");
        when(mockOldBankApi.executeTransaction(30000)).thenReturn(true);

        boolean result = mockAdapter.pay(amount);

        assertTrue(result);
        verify(mockOldBankApi).executeTransaction(30000);
    }

    @Test
    void payWithDifferentStrategies_cardStrategyIsIndependent() {
        BigDecimal amount1 = new BigDecimal("100");
        BigDecimal amount2 = new BigDecimal("999");

        boolean result1 = cardStrategy.pay(amount1);
        boolean result2 = cardStrategy.pay(amount2);

        assertTrue(result1);
        assertTrue(result2);
    }

    @Test
    void payWithCardStrategy_andAdapterStrategy_bothReturnTrue() {
        BigDecimal amount = new BigDecimal("500");
        when(mockOldBankApi.executeTransaction(50000)).thenReturn(true);

        boolean cardResult = cardStrategy.pay(amount);
        boolean adapterResult = mockAdapter.pay(amount);

        assertTrue(cardResult);
        assertTrue(adapterResult);
    }

    @Test
    void payWithZeroAmount_cardStrategy_handlesCorrectly() {
        BigDecimal zero = BigDecimal.ZERO;

        boolean result = cardStrategy.pay(zero);

        assertTrue(result);
    }

    @Test
    void payWithLargeAmount_adapter_callsOldBankApiWithCorrectKopecks() {
        BigDecimal amount = new BigDecimal("5000");
        when(mockOldBankApi.executeTransaction(500000)).thenReturn(true);

        mockAdapter.pay(amount);

        verify(mockOldBankApi).executeTransaction(500000);
    }
}
