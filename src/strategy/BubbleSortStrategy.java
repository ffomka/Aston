package strategy;

import java.util.Comparator;
import java.util.List;

/**
 * Пузырьковая сортировка.
 */
public class BubbleSortStrategy<T> implements SortStrategy<T> {

    private final Comparator<T> comparator;

    public BubbleSortStrategy(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void sort(List<T> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (comparator.compare(list.get(j), list.get(j + 1)) > 0) {
                    T temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }
}
