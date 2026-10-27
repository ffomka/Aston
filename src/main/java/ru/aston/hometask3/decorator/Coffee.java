package ru.aston.hometask3.decorator;

/**
 * Интерфейс Coffee — основа паттерна Декоратор.
 * Определяет методы getDescription() и getPrice().
 */
public interface Coffee {
    String getDescription();
    int getPrice();
}
