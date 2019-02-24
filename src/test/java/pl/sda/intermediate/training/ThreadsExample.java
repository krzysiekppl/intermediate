package pl.sda.intermediate.training;

import org.junit.jupiter.api.Test;

public class ThreadsExample {


    @Test
    void runnableBasics() {
        Runnable runnable = () -> System.out.println(Thread
                .currentThread()
                .getName() + " Lambda Runnable");

        Runnable runnableAnonymous = () -> System.out.println(Thread.currentThread().getName() + " Anonymous");

        Runnable myRunnable = new MyRunnable();

        myRunnable.run();
        runnableAnonymous.run();
        runnable.run();

        Thread thread1 = new Thread(myRunnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnableAnonymous);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
