public class AlternatingPrinter {

    static Object lock = new Object();
    static boolean isFirstTurn = true;

    public static void main(String[] args) {

        // Поток 1 — выводит "1"
        Thread thread1 = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (!isFirstTurn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    System.out.print("1");
                    isFirstTurn = false;
                    lock.notifyAll();
                }
            }
        }, "Поток 1");

        // Поток 2 — выводит "2"
        Thread thread2 = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (isFirstTurn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    System.out.print("2");
                    isFirstTurn = true;
                    lock.notifyAll();
                }
            }
        }, "Поток 2");

        thread1.start();
        thread2.start();
    }
}
