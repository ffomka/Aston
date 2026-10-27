import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Тесты для паттерна Adapter (BankApiAdapter).
 */
class BankApiAdapterTest {

    private OldBankApi mockOldBankApi;
    private BankApiAdapter adapter;

    @BeforeEach
    void setUp() {
        mockOldBankApi = mock(OldBankApi.class);
        adapter = new BankApiAdapter(mockOldBankApi);
    }

    @Test
    void payWith300Rubles_then_callsOldBankApiWith30000Kopecks() {
        BigDecimal amount = new BigDecimal("300");
        when(mockOldBankApi.executeTransaction(30000)).thenReturn(true);

        boolean result = adapter.pay(amount);

        assertTrue(result);
        verify(mockOldBankApi).executeTransaction(30000);
    }

    @Test
    void payWith100Rubles50Kopecks_then_callsOldBankApiWith10050Kopecks() {
        BigDecimal amount = new BigDecimal("100.50");
        when(mockOldBankApi.executeTransaction(10050)).thenReturn(true);

        boolean result = adapter.pay(amount);

        assertTrue(result);
        verify(mockOldBankApi).executeTransaction(10050);
    }

    @Test
    void payWithZeroAmount_then_callsOldBankApiWithZeroKopecks() {
        BigDecimal amount = BigDecimal.ZERO;
        when(mockOldBankApi.executeTransaction(0)).thenReturn(true);

        boolean result = adapter.pay(amount);

        assertTrue(result);
        verify(mockOldBankApi).executeTransaction(0);
    }

    @Test
    void payWithLargeAmount_then_callsOldBankApiWithCorrectKopecks() {
        BigDecimal amount = new BigDecimal("10000");
        when(mockOldBankApi.executeTransaction(1000000)).thenReturn(true);

        boolean result = adapter.pay(amount);

        assertTrue(result);
        verify(mockOldBankApi).executeTransaction(1000000);
    }

    @Test
    void payWhenOldBankApiReturnsFalse_then_returnsFalse() {
        BigDecimal amount = new BigDecimal("500");
        when(mockOldBankApi.executeTransaction(50000)).thenReturn(false);

        boolean result = adapter.pay(amount);

        assertFalse(result);
        verify(mockOldBankApi).executeTransaction(50000);
    }

    @Test
    void getName_then_returnsDescriptiveName() {
        String name = adapter.getName();

        assertNotNull(name);
        assertTrue(name.contains("OldBankApi"));
    }
}
