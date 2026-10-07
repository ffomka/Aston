<img width="474" height="442" alt="Screenshot_6" src="https://github.com/user-attachments/assets/bb8c3700-65de-4579-91bf-7cb506668bf4" />

# StudentManager — Консольное приложение для управления студентами

## Описание

Учебное консольное приложение на **Java 17+** для управления коллекцией студентов.
Реализует собственные алгоритмы сортировки, паттерны Builder и Strategy,
многопоточный подсчёт вхождений, кастомную коллекцию и загрузку данных
из различных источников.

### Ключевые возможности

- **Три источника данных**: случайная генерация, чтение из файла, ручной ввод
- **Четыре алгоритма сортировки**: пузырьковая, быстрая, вставками, чётных/нечётных
- **Сортировка по трём полям**: номер группы, средний балл, номер зачетной книжки
- **Многопоточный подсчёт** вхождений элемента в списке
- **Сохранение** результатов в файл (режим добавления)
- **Валидация** всех входных данных

---

## Структура пакетов

```
src/
├── Main.java                          # Точка входа
├── model/
│   └── Student.java                   # Класс студента (Builder встроенный)
├── builder/
│   └── StudentBuilder.java            # Отдельный Builder для Student
├── util/
│   ├── StudentValidator.java          # Валидация Student
│   └── MultiThreadCounter.java        # Многопоточный подсчёт вхождений
├── comparator/
│   ├── StudentByGroupNumberComparator.java
│   ├── StudentByAverageGradeComparator.java
│   └── StudentByRecordBookNumberComparator.java
├── strategy/
│   ├── SortStrategy.java              # Интерфейс стратегии сортировки
│   ├── BubbleSortStrategy.java        # Пузырьковая сортировка
│   ├── QuickSortStrategy.java         # Быстрая сортировка
│   ├── InsertionSortStrategy.java     # Сортировка вставками
│   └── EvenOddSortStrategy.java       # Сортировка чётных/нечётных
├── io/
│   ├── DataFiller.java                # Интерфейс заполнения коллекции
│   ├── RandomStudentFiller.java       # Генерация случайных студентов
│   ├── FileStudentFiller.java         # Чтение из файла
│   ├── ManualStudentFiller.java       # Ручной ввод с консоли
│   ├── CustomList.java                # Кастомная коллекция (обёртка над массивом)
│   └── FileWriterUtil.java            # Утилита записи в файл (append)
├── menu/
│   └── ConsoleMenu.java               # Консольное меню приложения
└── test/
    ├── StudentBuilderTest.java        # Тесты Builder и Student
    ├── SortStrategyTest.java          # Тесты всех сортировок
    ├── EvenOddSortStrategyTest.java   # Тесты Even-Odd Sort
    ├── FileFillerTest.java            # Тесты FileStudentFiller
    ├── MultiThreadCounterTest.java    # Тесты MultiThreadCounter
    └── CustomListTest.java            # Тесты CustomList
```

---

## Требования

- **Java 17+** (JDK)
- **Git** (для работы с ветвлением)

---

## Как запустить

### 1. Компиляция

```bash
javac -encoding UTF-8 -d out src/model/Student.java \
  src/builder/StudentBuilder.java \
  src/util/StudentValidator.java src/util/MultiThreadCounter.java \
  src/comparator/*.java src/strategy/*.java \
  src/io/*.java src/menu/ConsoleMenu.java src/Main.java
```

### 2. Запуск приложения

```bash
java -cp out Main
```

### 3. Запуск тестов

```bash
java -cp out test.StudentBuilderTest
java -cp out test.SortStrategyTest
java -cp out test.EvenOddSortStrategyTest
java -cp out test.FileFillerTest
java -cp out test.MultiThreadCounterTest
java -cp out test.CustomListTest
```

---

## Пример работы

### Меню

```
========================================
           ГЛАВНОЕ МЕНЮ
========================================
 1. Выбрать источник данных (файл / рандом / вручную)
 2. Задать длину коллекции
 3. Выбрать алгоритм сортировки
 4. Выбрать поле сортировки
 5. Выполнить сортировку
 6. Сохранить результат в файл
 7. Подсчитать вхождения элемента
 8. Показать текущую коллекцию
 0. Выход
========================================
```

### Пример ввода данных (случайная генерация)

```
Выберите действие: 1
--- Выбор источника данных ---
 1. Случайные данные (RandomStudentFiller)
 2. Из файла (FileStudentFiller)
 3. Вручную с консоли (ManualStudentFiller)
Ваш выбор: 1
Выбран: Случайные данные

Выберите действие: 2
Введите количество студентов: 5
Заполнение коллекции (5 элементов)...
Коллекция заполнена. Всего студентов: 5
```

### Пример вывода коллекции

```
--- Текущая коллекция (5 студентов) ---
  [1] Student{groupNumber=301, averageGrade=3.2, recordBookNumber=500}
  [2] Student{groupNumber=100, averageGrade=4.8, recordBookNumber=200}
  [3] Student{groupNumber=500, averageGrade=2.5, recordBookNumber=800}
  [4] Student{groupNumber=200, averageGrade=4.0, recordBookNumber=100}
  [5] Student{groupNumber=400, averageGrade=3.7, recordBookNumber=600}
-----------------------------------------------
```

### Пример сортировки

```
Выберите действие: 3
--- Выбор алгоритма сортировки ---
 1. Bubble Sort (пузырьковая)
 2. Quick Sort (быстрая)
 3. Insertion Sort (вставками)
 4. Even-Odd Sort (чётные/нечётные)
Ваш выбор: 1
Выбран: Bubble Sort

Выберите действие: 4
--- Выбор поля сортировки ---
 1. Номер группы (groupNumber)
 2. Средний балл (averageGrade)
 3. Номер зачетной книжки (recordBookNumber)
Ваш выбор: 1
Выбрано поле: Номер группы

Выберите действие: 5
--- Выполнение сортировки ---
Алгоритм: BubbleSortStrategy
Поле: groupNumber
Элементов: 5
Сортировка завершена за 0.123 мс
```

### Пример файла данных

Формат строки: `groupNumber;averageGrade;recordBookNumber`

```
100;4.5;100001
200;3.8;200002
300;4.2;300003
400;5.0;400004
500;2.5;500005
```

---

## Дополнительные задания

| № | Задание | Статус |
|---|---------|--------|
| 1 | Паттерн Builder для Student | ✅ Выполнено |
| 2 | Паттерн Strategy (Bubble, Quick, Insertion) | ✅ Выполнено |
| 3 | Сортировка по всем трём полям | ✅ Выполнено |
| 4 | Кастомная коллекция CustomList<T> | ✅ Выполнено |
| 5 | Многопоточный подсчёт вхождений | ✅ Выполнено |
| 6 | Сортировка чётных/нечётных (EvenOddSort) | ✅ Выполнено |
| 7 | Загрузка из файла с валидацией | ✅ Выполнено |
| 8 | Тесты без JUnit (main-методы) | ✅ Выполнено |

---

## Ограничения

- Не используются `Collections.sort`, `Arrays.sort`, `Stream.sorted`, `Collections.binarySearch`
- Компариторы (`Comparator`) разрешены
- Все алгоритмы сортировки реализованы вручную
- Многопоточность реализована через `Thread` + `AtomicInteger`
