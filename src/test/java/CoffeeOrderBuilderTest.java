import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для паттерна Builder (CoffeeOrder).
 */
class CoffeeOrderBuilderTest {

    @Test
    void buildCompleteOrder_then_returnsValidCoffeeOrder() {
        CoffeeOrder order = CoffeeOrder.builder()
                .coffeeType(CoffeeOrder.CoffeeType.CAPPUCCINO)
                .size(CoffeeOrder.Size.LARGE)
                .milk(CoffeeOrder.MilkType.OATMEAL)
                .syrup(CoffeeOrder.SyrupType.VANILLA)
                .extras("Двойная shot")
                .build();

        assertNotNull(order);
        assertEquals(CoffeeOrder.CoffeeType.CAPPUCCINO, order.getCoffeeType());
        assertEquals(CoffeeOrder.Size.LARGE, order.getSize());
        assertEquals(CoffeeOrder.MilkType.OATMEAL, order.getMilk());
        assertEquals(CoffeeOrder.SyrupType.VANILLA, order.getSyrup());
        assertEquals("Двойная shot", order.getExtras());
    }

    @Test
    void buildOrderWithoutCoffeeType_then_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
                CoffeeOrder.builder()
                        .size(CoffeeOrder.Size.MEDIUM)
                        .milk(CoffeeOrder.MilkType.REGULAR)
                        .syrup(CoffeeOrder.SyrupType.NONE)
                        .build()
        );
    }

    @Test
    void buildOrderWithoutSize_then_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
                CoffeeOrder.builder()
                        .coffeeType(CoffeeOrder.CoffeeType.LATTE)
                        .milk(CoffeeOrder.MilkType.SOY)
                        .syrup(CoffeeOrder.SyrupType.CARAMEL)
                        .build()
        );
    }

    @Test
    void buildOrderWithoutMilk_then_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
                CoffeeOrder.builder()
                        .coffeeType(CoffeeOrder.CoffeeType.ESPRESSO)
                        .size(CoffeeOrder.Size.SMALL)
                        .syrup(CoffeeOrder.SyrupType.CHOCOLATE)
                        .build()
        );
    }

    @Test
    void buildOrderWithoutSyrup_then_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
                CoffeeOrder.builder()
                        .coffeeType(CoffeeOrder.CoffeeType.AMERICANO)
                        .size(CoffeeOrder.Size.MEDIUM)
                        .milk(CoffeeOrder.MilkType.ALMOND)
                        .build()
        );
    }

    @Test
    void buildOrderWithAllDefaults_then_returnsValidCoffeeOrder() {
        CoffeeOrder order = CoffeeOrder.builder()
                .coffeeType(CoffeeOrder.CoffeeType.RAFF)
                .size(CoffeeOrder.Size.SMALL)
                .milk(CoffeeOrder.MilkType.REGULAR)
                .syrup(CoffeeOrder.SyrupType.NONE)
                .build();

        assertNotNull(order);
        assertEquals(CoffeeOrder.CoffeeType.RAFF, order.getCoffeeType());
        assertEquals(CoffeeOrder.Size.SMALL, order.getSize());
        assertEquals(CoffeeOrder.MilkType.REGULAR, order.getMilk());
        assertEquals(CoffeeOrder.SyrupType.NONE, order.getSyrup());
        assertNull(order.getExtras());
    }
}
