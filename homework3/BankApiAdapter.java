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
    public boolean pay(int amount) {
        System.out.println("   🔄 [Адаптер] Конвертирую " + amount + "₽ в копейки для OldBankApi...");
        int kopecks = amount * 100; // конвертация рублей в копейки
        return oldBankApi.executeTransaction(kopecks);
    }

    @Override
    public String getName() {
        return "Банковский перевод (OldBankApi)";
    }
}
