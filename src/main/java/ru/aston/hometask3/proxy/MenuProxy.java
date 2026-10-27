package ru.aston.hometask3.proxy;

/**
 * Прокси для меню.
 * Реализует кэширование и проверку авторизации пользователя.
 * Реальный объект создаётся лениво (только при первом запросе).
 */
public class MenuProxy implements Menu {

    private RealMenu realMenu;           // реальный объект (ленивая инициализация)
    private boolean isAuthenticated;     // статус авторизации
    private String cachedMenu;           // кэш результата

    /**
     * @param isAuthenticated статус авторизации пользователя
     */
    public MenuProxy(boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
        this.cachedMenu = null;
    }

    @Override
    public String getMenu() {
        // 1. Проверка авторизации
        if (!isAuthenticated) {
            return "🔒 Доступ к меню запрещён. Пожалуйста, авторизуйтесь.";
        }

        // 2. Проверка кэша — если меню уже загружено, возвращаем из кэша
        if (cachedMenu != null) {
            System.out.println("   ⚡ Меню взято из кэша (без загрузки из БД)");
            return cachedMenu;
        }

        // 3. Ленивая загрузка — создаём реальный объект только при первом вызове
        System.out.println("   🔄 Реального объекта ещё нет, создаём...");
        realMenu = new RealMenu();
        cachedMenu = realMenu.getMenu();

        return cachedMenu;
    }
}
