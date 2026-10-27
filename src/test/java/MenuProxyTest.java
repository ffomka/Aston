import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Тесты для паттерна Proxy (MenuProxy).
 */
class MenuProxyTest {

    private MenuProxy proxy;

    @BeforeEach
    void setUp() {
        proxy = new MenuProxy(true); // авторизованный пользователь
    }

    @Test
    void getFirstMenuRequest_then_createsRealMenu() {
        String menu1 = proxy.getMenu();

        assertNotNull(menu1);
        assertTrue(menu1.contains("Меню:"));
        assertTrue(menu1.contains("Эспрессо"));
        assertTrue(menu1.contains("Капучино"));
    }

    @Test
    void getSecondMenuRequest_then_returnsFromCache() {
        String menu1 = proxy.getMenu();
        String menu2 = proxy.getMenu();

        assertNotNull(menu1);
        assertNotNull(menu2);
        assertSame(menu1, menu2); // тот же объект из кэша
    }

    @Test
    void getUnauthenticatedUserMenu_then_deniesAccess() {
        MenuProxy unauthorizedProxy = new MenuProxy(false);
        String result = unauthorizedProxy.getMenu();

        assertTrue(result.contains("Доступ к меню запрещён"));
    }

    @Test
    void getAuthenticatedUserMenu_then_returnsMenuContent() {
        String menu = proxy.getMenu();

        assertTrue(menu.contains("Эспрессо"));
        assertTrue(menu.contains("120₽"));
        assertTrue(menu.contains("Капучино"));
        assertTrue(menu.contains("180₽"));
        assertTrue(menu.contains("Латте"));
        assertTrue(menu.contains("200₽"));
        assertTrue(menu.contains("Американо"));
        assertTrue(menu.contains("100₽"));
        assertTrue(menu.contains("Раф"));
        assertTrue(menu.contains("220₽"));
    }
}
