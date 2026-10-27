/**
 * Сторонний (чужой) класс — старый банковский API.
 * Имеет неудобный интерфейс, который мы не можем изменить.
 */
public class OldBankApi {

    /**
     * Выполняет транзакцию через старый API.
     * @param moneyInKopecks сумма в копейках (целое число)
     * @return true, если транзакция прошла
     */
    public boolean executeTransaction(int moneyInKopecks) {
        // Симуляция задержки старого API
        System.out.println("   🏦 [OldBankApi] Обработка транзакции: " + moneyInKopecks + " копеек...");
        try {
            Thread.sleep(100); // имитация сетевого запроса
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return true;
    }

    /**
     * Проверяет баланс счёта.
     * @param accountId номер счёта
     * @return true, если средств достаточно
     */
    public boolean checkAccount(String accountId) {
        System.out.println("   🏦 [OldBankApi] Проверка счёта: " + accountId);
        return true;
    }
}
