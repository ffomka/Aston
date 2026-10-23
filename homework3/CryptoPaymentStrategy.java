/**
 * Стратегия оплаты криптовалютой.
 */
public class CryptoPaymentStrategy implements PaymentStrategy {

    @Override
    public boolean pay(int amount) {
        double cryptoAmount = amount / 3500.0; // примерный курс
        System.out.printf("   🪙 Оплачено %.6f USDT (эквивалент %d₽) криптовалютой%n", cryptoAmount, amount);
        return true;
    }

    @Override
    public String getName() {
        return "Криптовалюта (USDT)";
    }
}
