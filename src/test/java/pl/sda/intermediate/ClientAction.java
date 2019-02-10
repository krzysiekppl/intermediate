package pl.sda.intermediate;

import java.util.Random;

public class ClientAction implements Runnable {


    @Override
    public void run() {
        Integer howMuch = new Random().nextInt(101);

        Bank.withDraw(howMuch);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Bank.deposit(howMuch);
    }
}
