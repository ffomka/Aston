# Инструкция по Git-ветвлению для командной работы

## Сценарий: N участников, каждый отвечает за свой модуль

### 1. Инициализация репозитория (если ещё не сделано)

```bash
cd C:/ASTON/AST_Test
git init
git add .
git commit -m "Initial commit: структура проекта"
```

### 2. Создание веток для участников

Предположим, **4 участника**. Каждая ветка отвечает за свой модуль:

```bash
# Ветка участника 1 — модель и builder
git checkout -b feature/model

# Ветка участника 2 — стратегии сортировки и компараторы
git checkout -b feature/strategy

# Ветка участника 3 — ввод-вывод и кастомная коллекция
git checkout -b feature/io

# Ветка участника 4 — меню и тесты
git checkout -b feature/menu
git checkout -b feature/tests
```

### 3. Работа в каждой ветке

#### feature/model (участник 1)

```bash
git checkout feature/model

# Создаёт:
#   src/model/Student.java
#   src/builder/StudentBuilder.java
#   src/util/StudentValidator.java

git add src/model/ src/builder/ src/util/StudentValidator.java
git commit -m "model: добавить класс Student с Builder и валидатор"
```

#### feature/strategy (участник 2)

```bash
git checkout feature/strategy

# Создаёт:
#   src/comparator/*.java
#   src/strategy/SortStrategy.java
#   src/strategy/BubbleSortStrategy.java
#   src/strategy/QuickSortStrategy.java
#   src/strategy/InsertionSortStrategy.java
#   src/strategy/EvenOddSortStrategy.java

git add src/comparator/ src/strategy/
git commit -m "strategy: добавить интерфейсы, реализации сортировок и компараторы"
```

#### feature/io (участник 3)

```bash
git checkout feature/io

# Создаёт:
#   src/io/DataFiller.java
#   src/io/RandomStudentFiller.java
#   src/io/FileStudentFiller.java
#   src/io/ManualStudentFiller.java
#   src/io/CustomList.java
#   src/io/FileWriterUtil.java

git add src/io/
git commit -m "io: добавить DataFiller, fillers, CustomList, FileWriterUtil"
```

#### feature/menu (участник 4)

```bash
git checkout feature/menu

# Создаёт:
#   src/menu/ConsoleMenu.java
#   src/Main.java

git add src/menu/ src/Main.java
git commit -m "menu: добавить консольное меню и точку входа"
```

#### feature/tests (участник 5, если есть)

```bash
git checkout feature/tests

# Создаёт:
#   src/test/StudentBuilderTest.java
#   src/test/SortStrategyTest.java
#   src/test/EvenOddSortStrategyTest.java
#   src/test/FileFillerTest.java
#   src/test/MultiThreadCounterTest.java
#   src/test/CustomListTest.java

git add src/test/
git commit -m "tests: добавить все тесты без JUnit"
```

### 4. Слияние всех веток в main

Порядок слияния — от зависимостей к зависимым:

```bash
# Возвращаемся в main
git checkout main

# 1. Сначала model (зависят все остальные)
git merge feature/model --no-edit

# 2. Затем strategy (зависит от model)
git merge feature/strategy --no-edit

# 3. Затем io (зависит от model, builder)
git merge feature/io --no-edit

# 4. Затем menu (зависит от model, strategy, io)
git merge feature/menu --no-edit

# 5. Затем tests (зависит от всего)
git merge feature/tests --no-edit
```

### 5. Проверка после слияния

```bash
# Проверить статус
git status

# Проверить логи
git log --oneline

# Удалить рабочие ветки (опционально)
git branch -d feature/model feature/strategy feature/io feature/menu feature/tests
```

### 6. Альтернатива: один участник, несколько модулей

Если **1 участник**, но нужно показать работу с ветками:

```bash
# Модель
git checkout -b feature/model
# ... код ...
git add -A && git commit -m "model: Student, Builder, Validator"

# Стратегии
git checkout main
git checkout -b feature/strategy
# ... код ...
git add -A && git commit -m "strategy: SortStrategy, comparators"

# IO
git checkout main
git checkout -b feature/io
# ... код ...
git add -A && git commit -m "io: DataFiller, CustomList, fillers"

# Меню
git checkout main
git checkout -b feature/menu
# ... код ...
git add -A && git commit -m "menu: ConsoleMenu, Main"

# Тесты
git checkout main
git checkout -b feature/tests
# ... код ...
git add -A && git commit -m "tests: все тесты"

# Слияние
git checkout main
git merge feature/model --no-edit
git merge feature/strategy --no-edit
git merge feature/io --no-edit
git merge feature/menu --no-edit
git merge feature/tests --no-edit
```

### 7. Разрешение конфликтов (если возникли)

```bash
# Если есть конфликты, git покажет их:
git status  # покажет CONFLICT

# Открыть конфликтующий файл, найти <<<<<<<, =======, >>>>>>>
# Внести правки, затем:
git add <конфликтующий_файл>
git commit -m "Merge: разрешить конфликты"

# Повторить для каждой ветки
```

### 8. Пример итоговой истории коммитов

```
abc1234 (HEAD -> main) tests: все тесты
def5678 menu: ConsoleMenu, Main
ghi9012 io: DataFiller, CustomList, fillers
jkl3456 strategy: SortStrategy, comparators
mno7890 model: Student, Builder, Validator
pqr1234 Initial commit: структура проекта
```
