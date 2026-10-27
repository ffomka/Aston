/**
 * Стратегия оплаты банковской картой.
 */
public class CardPaymentStrategy implements PaymentStrategy {

    @Override
    public boolean pay(int amount) {
        System.out.println("   💳 Оплачено " + amount + "₽ банковской картой");
        return true;
    }

    @Override
    public String getName() {
        return "Банковская карта";
    }
}
