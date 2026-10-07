package strategy;

import java.util.List;

/**
 * Стратегия сортировки для элементов списка.
 *
 * @param <T> тип сортируемых элементов
 */
public interface SortStrategy<T> {

    /**
     * Сортирует список на месте.
     *
     * @param list список для сортировки
     */
    void sort(List<T> list);
}
