public class DeadlockExample {

    // Два объекта-замка
    static Object lock1 = new Object();
    static Object lock2 = new Object();

    public static void main(String[] args) {
        System.out.println("Запуск демонстрации Deadlock...");

        // Первый поток: lock1 -> lock2
        Thread thread1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Поток 1: захватил lock1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Поток 1: пытается захватить lock2...");
                synchronized (lock2) {
                    System.out.println("Поток 1: захватил lock2");
                }
            }
        }, "Поток 1");

        // Второй поток: lock2 -> lock1
        Thread thread2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("Поток 2: захватил lock2");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Поток 2: пытается захватить lock1...");
                synchronized (lock1) {
                    System.out.println("Поток 2: захватил lock1");
                }
            }
        }, "Поток 2");

        // Запускаем оба потока
        thread1.start();
        thread2.start();
    }
}
