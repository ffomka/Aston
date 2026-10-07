package strategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.IntFunction;
import java.util.List;
import java.util.function.ToIntFunction;

/**
 * Стратегия сортировки чётных/нечётных элементов.
 * <p>
 * Элементы с ЧЁТНЫМ значением числового поля сортируются в натуральном порядке
 * (по заданному компаратору). Элементы с НЕЧЁТНЫМ значением остаются на
 * исходных позициях.
 *
 * @param <T> тип сортируемых элементов
 */
public class EvenOddSortStrategy<T> implements SortStrategy<T> {

    private final Comparator<T> comparator;
    private final ToIntFunction<T> valueExtractor;

    /**
     * Конструктор.
     *
     * @param comparator      компаратор для сортировки чётных элементов
     * @param valueExtractor  функция извлечения числового значения для определения чётности
     */
    public EvenOddSortStrategy(Comparator<T> comparator, ToIntFunction<T> valueExtractor) {
        this.comparator = comparator;
        this.valueExtractor = valueExtractor;
    }

    @Override
    public void sort(List<T> list) {
        if (list == null || list.size() <= 1) {
            return;
        }

        // 1. Собираем индексы чётных элементов
        List<Integer> evenIndices = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (isEven(valueExtractor.applyAsInt(list.get(i)))) {
                evenIndices.add(i);
            }
        }

        // 2. Извлекаем значения чётных элементов
        List<T> evenElements = new ArrayList<>(evenIndices.size());
        for (int index : evenIndices) {
            evenElements.add(list.get(index));
        }

        // 3. Сортируем чётные элементы пузырьковой сортировкой
        bubbleSort(evenElements);

        // 4. Возвращаем отсортированные значения обратно на те же индексы
        for (int i = 0; i < evenIndices.size(); i++) {
            list.set(evenIndices.get(i), evenElements.get(i));
        }
    }

    /**
     * Проверяет, является ли число чётным.
     *
     * @param value числовое значение
     * @return true, если число чётное
     */
    private boolean isEven(int value) {
        return value % 2 == 0;
    }

    /**
     * Пузырьковая сортировка для чётных элементов.
     *
     * @param elements список чётных элементов
     */
    private void bubbleSort(List<T> elements) {
        int n = elements.size();
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (comparator.compare(elements.get(j), elements.get(j + 1)) > 0) {
                    T temp = elements.get(j);
                    elements.set(j, elements.get(j + 1));
                    elements.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }
}
