package ru.aston.hometask3.chainofresponsibility;

/**
 * Абстрактный обработчик цепочки обязанностей.
 * Каждый обработчик может передать запрос дальше по цепочке.
 */
public abstract class OrderHandler {
    protected OrderHandler nextHandler; // следующий обработчик в цепочке

    /**
     * Устанавливает следующего обработчика в цепочке.
     */
    public void setNext(OrderHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    /**
     * Обрабатывает заказ, если может, или передаёт дальше.
     * @param orderDescription описание заказа
     * @return true, если заказ успешно обработан
     */
    public abstract boolean handle(String orderDescription);

    /**
     * Передаёт запрос следующему обработчику.
     */
    protected boolean forward(String orderDescription) {
        if (nextHandler != null) {
            return nextHandler.handle(orderDescription);
        }
        return true; // конец цепочки — всё обработано
    }
}
