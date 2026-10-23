/**
 * Реальная реализация меню — «тяжёлый» объект.
 * Имитирует загрузку данных из базы данных.
 */
public class RealMenu implements Menu {

    public RealMenu() {
        // Имитация тяжёлой загрузки из базы данных
        System.out.println("   🐌 Загрузка меню из базы данных...");
        try {
            Thread.sleep(500); // имитация задержки загрузки
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("   ✅ Меню загружено!");
    }

    @Override
    public String getMenu() {
        return "📖 Меню:\n" +
                "   1. Эспрессо — 120₽\n" +
                "   2. Капучино — 180₽\n" +
                "   3. Латте — 200₽\n" +
                "   4. Американо — 100₽\n" +
                "   5. Раф — 220₽";
    }
}
