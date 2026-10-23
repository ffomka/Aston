/**
 * Стратегия оплаты наличными.
 */
public class CashPaymentStrategy implements PaymentStrategy {

    @Override
    public boolean pay(int amount) {
        System.out.println("   💵 Оплачено " + amount + "₽ наличными");
        return true;
    }

    @Override
    public String getName() {
        return "Наличные";
    }
}
