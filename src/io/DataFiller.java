package io;

import java.util.List;

/**
 * Интерфейс для заполнения коллекции объектов заданного размера.
 *
 * @param <T> тип заполняемых элементов
 */
public interface DataFiller<T> {

    /**
     * Заполняет и возвращает список из size элементов.
     *
     * @param size количество элементов
     * @return список заполненных элементов
     */
    List<T> fill(int size);
}
