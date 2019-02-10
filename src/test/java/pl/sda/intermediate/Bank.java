package pl.sda.intermediate;

import java.util.concurrent.atomic.AtomicInteger;

public class Bank {

    static Integer cash = 1000;
    static Integer counter = 0;
    static AtomicInteger atomicCash = new AtomicInteger(1000);
    static AtomicInteger atomicCounter = new AtomicInteger(0);


    public static void withDraw(Integer howMuch) {
        cash = cash - howMuch;
        atomicCash.addAndGet(-howMuch);
    }

    public static void deposit(Integer howMuch) {
        atomicCash.addAndGet(howMuch);
        cash = cash + howMuch;

        atomicCounter.addAndGet(1);
        ++counter;
    }
}
