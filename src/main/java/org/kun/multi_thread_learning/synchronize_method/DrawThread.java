package org.kun.multi_thread_learning.synchronize_method;

public class DrawThread implements Runnable {

    private Account account;

    private double drawAmount;

    public DrawThread(Account account, double drawAmount) {
        this.account = account;
        this.drawAmount = drawAmount;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                account.draw(drawAmount);
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
