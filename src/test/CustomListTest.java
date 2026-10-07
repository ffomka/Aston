package test;

import io.CustomList;

/**
 * Тесты для CustomList<T>.
 */
public class CustomListTest {

    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {
        System.out.println("========== CustomListTest ==========");

        testEmptyList();
        testAddAndGet();
        testSize();
        testAddAtSpecificIndex();
        testRemoveByIndex();
        testRemoveFirst();
        testRemoveLast();
        testRemoveMiddle();
        testIterator();
        testIteratorEmpty();
        testToStringEmpty();
        testToStringWithElements();
        testGrowCapacity();
        testIndexOutOfBounds();
        testNegativeIndex();
        testAddNegativeIndex();
        testAddOutOfBounds();

        printSummary();
    }

    private static void testEmptyList() {
        System.out.println("\n[Тест] Пустой список");
        CustomList<String> list = new CustomList<>();

        assertEquals(0, list.size(), "размер пустого списка");
        pass("Пустой список создан");
    }

    private static void testAddAndGet() {
        System.out.println("\n[Тест] add() и get()");
        CustomList<String> list = new CustomList<>();

        list.add("first");
        list.add("second");
        list.add("third");

        assertEquals(3, list.size(), "размер после 3 add");
        assertEquals("first", list.get(0), "индекс 0");
        assertEquals("second", list.get(1), "индекс 1");
        assertEquals("third", list.get(2), "индекс 2");

        pass("add/get работают корректно");
    }

    private static void testSize() {
        System.out.println("\n[Тест] size()");
        CustomList<Integer> list = new CustomList<>();

        assertEquals(0, list.size(), "начальный размер");

        list.add(1);
        assertEquals(1, list.size(), "после 1 add");

        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        assertEquals(5, list.size(), "после 5 add");

        pass("size() корректен");
    }

    private static void testAddAtSpecificIndex() {
        System.out.println("\n[Тест] add(index, element)");
        CustomList<String> list = new CustomList<>();

        list.add("A");
        list.add("C");
        list.add("D");

        list.add(1, "B");

        assertEquals(4, list.size(), "размер после вставки");
        assertEquals("A", list.get(0), "индекс 0");
        assertEquals("B", list.get(1), "индекс 1");
        assertEquals("C", list.get(2), "индекс 2");
        assertEquals("D", list.get(3), "индекс 3");

        // Вставка в начало
        list.add(0, "Z");
        assertEquals("Z", list.get(0), "вставка в начало");
        assertEquals("A", list.get(1), "индекс 1 после вставки в начало");

        // Вставка в конец
        list.add(list.size(), "END");
        assertEquals("END", list.get(list.size() - 1), "вставка в конец");

        pass("add(index, element) работает корректно");
    }

    private static void testRemoveByIndex() {
        System.out.println("\n[Тест] remove(index)");
        CustomList<String> list = new CustomList<>();

        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");

        String removed = list.remove(1);
        assertEquals("B", removed, "удалённый элемент");
        assertEquals(3, list.size(), "размер после удаления");

        assertEquals("A", list.get(0), "индекс 0 после удаления");
        assertEquals("C", list.get(1), "индекс 1 после удаления (сдвиг)");
        assertEquals("D", list.get(2), "индекс 2 после удаления");

        pass("remove(index) работает корректно");
    }

    private static void testRemoveFirst() {
        System.out.println("\n[Тест] remove(0) — первый элемент");
        CustomList<String> list = new CustomList<>();

        list.add("first");
        list.add("second");
        list.add("third");

        String removed = list.remove(0);
        assertEquals("first", removed, "удалённый первый");
        assertEquals(2, list.size(), "размер после удаления первого");
        assertEquals("second", list.get(0), "индекс 0 после удаления первого");
        assertEquals("third", list.get(1), "индекс 1 после удаления первого");

        pass("remove(0) работает корректно");
    }

    private static void testRemoveLast() {
        System.out.println("\n[Тест] remove(last) — последний элемент");
        CustomList<String> list = new CustomList<>();

        list.add("first");
        list.add("second");
        list.add("third");

        String removed = list.remove(2);
        assertEquals("third", removed, "удалённый последний");
        assertEquals(2, list.size(), "размер после удаления последнего");
        assertEquals("second", list.get(1), "последний элемент после удаления");

        pass("remove(last) работает корректно");
    }

    private static void testRemoveMiddle() {
        System.out.println("\n[Тест] remove(middle) — средний элемент");
        CustomList<String> list = new CustomList<>();

        for (int i = 0; i < 10; i++) {
            list.add(String.valueOf(i));
        }

        String removed = list.remove(5);
        assertEquals("5", removed, "удалённый средний");
        assertEquals(9, list.size(), "размер после удаления");

        assertEquals("4", list.get(4), "индекс 4 после удаления");
        assertEquals("6", list.get(5), "индекс 5 после удаления (сдвиг)");
        assertEquals("9", list.get(8), "последний элемент после удаления");

        pass("remove(middle) работает корректно");
    }

    private static void testIterator() {
        System.out.println("\n[Тест] iterator()");
        CustomList<Integer> list = new CustomList<>();

        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(50);

        int expected = 10;
        for (Integer value : list) {
            assertEquals(expected, value, "итератор: значение");
            expected += 10;
        }

        pass("iterator() проходит все элементы");
    }

    private static void testIteratorEmpty() {
        System.out.println("\n[Тест] iterator() пустого списка");
        CustomList<String> list = new CustomList<>();

        int count = 0;
        for (String item : list) {
            count++;
        }

        assertEquals(0, count, "итератор пустого списка");
        pass("iterator() пустого списка не итерирует");
    }

    private static void testToStringEmpty() {
        System.out.println("\n[Тест] toString() пустого списка");
        CustomList<String> list = new CustomList<>();
        String str = list.toString();

        assertEquals("[]", str, "toString пустого списка");
    }

    private static void testToStringWithElements() {
        System.out.println("\n[Тест] toString() со элементами");
        CustomList<String> list = new CustomList<>();

        list.add("A");
        list.add("B");
        list.add("C");

        String str = list.toString();

        if (str.contains("A") && str.contains("B") && str.contains("C")) {
            pass("toString содержит все элементы");
        } else {
            fail("toString не содержит все элементы: " + str);
        }

        if (str.startsWith("[") && str.endsWith("]")) {
            pass("toString в квадратных скобках");
        } else {
            fail("toString не в квадратных скобках: " + str);
        }
    }

    private static void testGrowCapacity() {
        System.out.println("\n[Тест] Динамическое расширение (grow)");
        CustomList<String> list = new CustomList<>();

        // INITIAL_CAPACITY = 10, добавляем больше для проверки grow()
        for (int i = 0; i < 25; i++) {
            list.add("item-" + i);
        }

        assertEquals(25, list.size(), "размер после 25 add");

        for (int i = 0; i < 25; i++) {
            assertEquals("item-" + i, list.get(i), "индекс " + i);
        }

        pass("Динамическое расширение работает корректно (25 элементов)");
    }

    private static void testIndexOutOfBounds() {
        System.out.println("\n[Тест] IndexOutOfBoundsException");
        CustomList<String> list = new CustomList<>();

        list.add("A");
        list.add("B");

        try {
            list.get(5);
            fail("Ожидалось IndexOutOfBoundsException для get(5)");
        } catch (IndexOutOfBoundsException e) {
            pass("IndexOutOfBoundsException для get(5)");
        }

        try {
            list.remove(10);
            fail("Ожидалось IndexOutOfBoundsException для remove(10)");
        } catch (IndexOutOfBoundsException e) {
            pass("IndexOutOfBoundsException для remove(10)");
        }
    }

    private static void testNegativeIndex() {
        System.out.println("\n[Тест] Negative index");
        CustomList<String> list = new CustomList<>();

        list.add("A");

        try {
            list.get(-1);
            fail("Ожидалось IndexOutOfBoundsException для get(-1)");
        } catch (IndexOutOfBoundsException e) {
            pass("IndexOutOfBoundsException для get(-1)");
        }

        try {
            list.remove(-1);
            fail("Ожидалось IndexOutOfBoundsException для remove(-1)");
        } catch (IndexOutOfBoundsException e) {
            pass("IndexOutOfBoundsException для remove(-1)");
        }
    }

    private static void testAddNegativeIndex() {
        System.out.println("\n[Тест] add(negativeIndex)");
        CustomList<String> list = new CustomList<>();

        list.add("A");

        try {
            list.add(-1, "B");
            fail("Ожидалось IndexOutOfBoundsException для add(-1, ...)");
        } catch (IndexOutOfBoundsException e) {
            pass("IndexOutOfBoundsException для add(-1, ...)");
        }
    }

    private static void testAddOutOfBounds() {
        System.out.println("\n[Тест] add(超出范围 index)");
        CustomList<String> list = new CustomList<>();

        list.add("A");

        try {
            list.add(5, "B");
            fail("Ожидалось IndexOutOfBoundsException для add(5, ...)");
        } catch (IndexOutOfBoundsException e) {
            pass("IndexOutOfBoundsException для add(5, ...)");
        }
    }

    private static void assertEquals(int expected, int actual, String message) {
        if (expected != actual) {
            fail(message + ": expected " + expected + " but got " + actual);
        } else {
            pass(message + " == " + expected);
        }
    }

    private static void assertEquals(String expected, String actual, String message) {
        if (!expected.equals(actual)) {
            fail(message + ": expected '" + expected + "' but got '" + actual + "'");
        } else {
            pass(message + " == '" + expected + "'");
        }
    }

    private static void pass(String message) {
        passed++;
        System.out.println("  ✓ PASS: " + message);
    }

    private static void fail(String message) {
        failed++;
        System.out.println("  ✗ FAIL: " + message);
    }

    private static void assertEquals(boolean condition, String message) {
        if (condition) {
            pass(message);
        } else {
            fail(message);
        }
    }

    private static void printSummary() {
        System.out.println("\n========================================");
        System.out.println("ИТОГО: passed=" + passed + ", failed=" + failed);
        if (failed == 0) {
            System.out.println("Все тесты CustomListTest пройдены!");
        } else {
            System.out.println("Есть неудачные тесты!");
            System.exit(1);
        }
        System.out.println("========================================");
    }
}
