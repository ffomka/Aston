package strategy;

import java.util.Comparator;
import java.util.List;

/**
 * Сортировка вставками.
 */
public class InsertionSortStrategy<T> implements SortStrategy<T> {

    private final Comparator<T> comparator;

    public InsertionSortStrategy(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void sort(List<T> list) {
        int n = list.size();
        for (int i = 1; i < n; i++) {
            T key = list.get(i);
            int j = i - 1;

            while (j >= 0 && comparator.compare(list.get(j), key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }

            list.set(j + 1, key);
        }
    }
}
