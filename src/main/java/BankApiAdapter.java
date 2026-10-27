import java.math.BigDecimal;

/**
 * Адаптер, который преобразует интерфейс OldBankApi в PaymentStrategy.
 * Позволяет использовать старый банковский API как стратегию оплаты.
 */
public class BankApiAdapter implements PaymentStrategy {

    private final OldBankApi oldBankApi; // адаптируемый объект

    public BankApiAdapter(OldBankApi oldBankApi) {
        this.oldBankApi = oldBankApi;
    }

    @Override
    public boolean pay(BigDecimal amount) {
        System.out.println("   🔄 [Адаптер] Конвертирую " + amount + "₽ в копейки для OldBankApi...");
        BigDecimal kopecks = amount.multiply(new BigDecimal("100"));
        return oldBankApi.executeTransaction(kopecks.intValueExact());
    }

    @Override
    public String getName() {
        return "Банковский перевод (OldBankApi)";
    }
}
