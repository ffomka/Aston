/**
 * Обработчик проверки оплаты.
 * Проверяет, оплачен ли заказ.
 */
public class PaymentCheckHandler extends OrderHandler {

    private boolean isPaid; // флаг оплаты (упрощённо)

    public PaymentCheckHandler(boolean isPaid) {
        this.isPaid = isPaid;
    }

    @Override
    public boolean handle(String orderDescription) {
        System.out.println("   💰 [PaymentCheck] Проверка оплаты для: " + orderDescription);

        if (isPaid) {
            System.out.println("   ✅ [PaymentCheck] Заказ оплачен!");
            return forward(orderDescription); // передаём дальше (или завершаем)
        } else {
            System.out.println("   ❌ [PaymentCheck] Заказ НЕ оплачен! Отклонено.");
            return false;
        }
    }
}
