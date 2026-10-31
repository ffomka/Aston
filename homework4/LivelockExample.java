import java.util.concurrent.locks.ReentrantLock;

public class LivelockExample {

    // Два ресурса с блокировками
    static ReentrantLock resource1 = new ReentrantLock();
    static ReentrantLock resource2 = new ReentrantLock();

    public static void main(String[] args) {
        System.out.println("Запуск демонстрации Livelock...");

        // Поток 1: сначала пытается захватить resource1, потом resource2
        Thread thread1 = new Thread(() -> {
            int attempt = 1;
            while (true) {
                if (resource1.tryLock()) {
                    System.out.println("Поток 1: захватил resource1 (попытка " + attempt + ")");
                    try {
                        Thread.sleep(50);

                        // Пытаемся захватить resource2
                        if (resource2.tryLock()) {
                            System.out.println("Поток 1: захватил resource2 — работа выполнена!");
                            resource2.unlock();
                            break; // Успех — выходим
                        }

                        // resource2 не доступен — отпускаем resource1 и повторяем
                        System.out.println("Поток 1: resource2 занят — отпускаю resource1 и повторяю попытку");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        resource1.unlock();
                    }

                    attempt++;
                    try {
                        Thread.sleep((long) (Math.random() * 100));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Поток 1");

        // Поток 2: сначала пытается захватить resource2, потом resource1
        Thread thread2 = new Thread(() -> {
            int attempt = 1;
            while (true) {
                if (resource2.tryLock()) {
                    System.out.println("Поток 2: захватил resource2 (попытка " + attempt + ")");
                    try {
                        Thread.sleep(50);

                        // Пытаемся захватить resource1
                        if (resource1.tryLock()) {
                            System.out.println("Поток 2: захватил resource1 — работа выполнена!");
                            resource1.unlock();
                            break; // Успех — выходим
                        }

                        // resource1 не доступен — отпускаем resource2 и повторяем
                        System.out.println("Поток 2: resource1 занят — отпускаю resource2 и повторяю попытку");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        resource2.unlock();
                    }

                    attempt++;
                    try {
                        Thread.sleep((long) (Math.random() * 100));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Поток 2");

        thread1.start();
        thread2.start();
    }
}
