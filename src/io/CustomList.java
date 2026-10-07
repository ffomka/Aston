package io;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Кастомная коллекция — обёртка над массивом с динамическим расширением.
 * Реализует собственные add, get, size, iterator.
 *
 * @param <T> тип элементов
 */
public class CustomList<T> implements Iterable<T> {

    private static final int INITIAL_CAPACITY = 10;

    private Object[] elements;
    private int size;

    public CustomList() {
        this.elements = new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    /**
     * Добавляет элемент в конец списка.
     *
     * @param element добавляемый элемент
     */
    @SuppressWarnings("unchecked")
    public void add(T element) {
        if (size == elements.length) {
            grow();
        }
        elements[size++] = element;
    }

    /**
     * Возвращает элемент по индексу.
     *
     * @param index индекс элемента
     * @return элемент
     * @throws IndexOutOfBoundsException если индекс вне диапазона
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) elements[index];
    }

    /**
     * Возвращает количество элементов.
     *
     * @return размер списка
     */
    public int size() {
        return size;
    }

    /**
     * Удаляет элемент по индексу, сдвигая остальные.
     *
     * @param index индекс удаляемого элемента
     * @return удалённый элемент
     * @throws IndexOutOfBoundsException если индекс вне диапазона
     */
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        checkIndex(index);
        T oldValue = (T) elements[index];
        int numMoved = size - index - 1;

        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }

        elements[--size] = null;
        return oldValue;
    }

    /**
     * Вставляет элемент по индексу, сдвигая остальные.
     *
     * @param index   индекс вставки
     * @param element вставляемый элемент
     * @throws IndexOutOfBoundsException если индекс вне диапазона
     */
    @SuppressWarnings("unchecked")
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (size == elements.length) {
            grow();
        }

        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) elements[cursor++];
            }
        };
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append('[');

        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(elements[i]);
        }

        sb.append(']');
        return sb.toString();
    }

    /**
     * Проверяет корректность индекса.
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    /**
     * Расширяет внутренний массив в 2 раза.
     */
    @SuppressWarnings("unchecked")
    private void grow() {
        int newCapacity = elements.length * 2;
        elements = new Object[newCapacity];
    }
}
