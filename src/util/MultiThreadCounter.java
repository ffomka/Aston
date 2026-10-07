package util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Многопоточный счётчик вхождений элемента в списке.
 * <p>
 * Разбивает список на N частей, каждую обрабатывает в отдельном потоке,
 * суммирует результаты через AtomicInteger.
 *
 * @param <T> тип элементов
 */
public class MultiThreadCounter<T> {

    /**
     * Считает количество вхождений target-элемента в списке, используя ��аданное число потоков.
     *
     * @param list   исходный список
     * @param target искомый элемент
     * @param threads количество потоков для обработки
     * @return количество вхождений
     */
    public int countOccurrences(List<T> list, T target, int threads) {
        if (list == null || list.isEmpty()) {
            return 0;
        }

        if (threads <= 0) {
            throw new IllegalArgumentException("Количество потоков должно быть больше 0");
        }

        int size = list.size();

        // Если потоков больше, чем элементов — используем 1 поток
        int actualThreads = Math.min(threads, size);

        // Разбиваем список на части
        List<List<T>> partitions = partitionList(list, actualThreads);

        // Общий счётчик результатов
        AtomicInteger totalCount = new AtomicInteger(0);

        // Список потоков для ожидания завершения
        List<Thread> workerThreads = new ArrayList<>(actualThreads);

        System.out.println("Запуск " + actualThreads + " потоков для обработки " + size + " элементов...");

        // Создаём и запускаем потоки
        for (int i = 0; i < actualThreads; i++) {
            List<T> partition = partitions.get(i);
            T targetForThread = target; // final-переменная для лямбды/лямбда-захвата

            Thread thread = new Thread(() -> {
                int localCount = countInPartition(partition, targetForThread);
                totalCount.addAndGet(localCount);
                System.out.println("Поток " + Thread.currentThread().getName()
                        + ": найдено " + localCount + " вхождений (подсписок ["
                        + (i * (size / actualThreads)) + "-"
                        + ((i + 1) * (size / actualThreads) - 1) + "])");
            }, "counter-thread-" + i);

            workerThreads.add(thread);
            thread.start();
        }

        // Ожидаем завершения всех потоков
        for (Thread thread : workerThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Поток прерван при ожидании", e);
            }
        }

        int result = totalCount.get();
        System.out.println("Результат подсчёта: " + result + " вхождений элемента " + target);
        return result;
    }

    /**
     * Разбивает список на заданное количество примерно равных частей.
     *
     * @param list исходный список
     * @param parts количество частей
     * @return список под списков
     */
    private List<List<T>> partitionList(List<T> list, int parts) {
        List<List<T>> partitions = new ArrayList<>(parts);
        int size = list.size();
        int chunkSize = size / parts;
        int remainder = size % parts;

        int fromIndex = 0;
        for (int i = 0; i < parts; i++) {
            int toIndex = fromIndex + chunkSize + (i < remainder ? 1 : 0);
            partitions.add(new ArrayList<>(list.subList(fromIndex, toIndex)));
            fromIndex = toIndex;
        }

        return partitions;
    }

    /**
     * Считает вхождения target в одном подсписке.
     *
     * @param partition подсписок для обработки
     * @param target искомый элемент
     * @return количество вхождений
     */
    private int countInPartition(List<T> partition, T target) {
        int count = 0;
        for (T element : partition) {
            if (element.equals(target)) {
                count++;
            }
        }
        return count;
    }
}
