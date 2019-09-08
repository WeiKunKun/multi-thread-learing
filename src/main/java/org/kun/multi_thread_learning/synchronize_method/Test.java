package org.kun.multi_thread_learning.synchronize_method;

public class Test {
    private static final String ACCOUNT_NO = "666666";

    private static final double BALANCE = 0;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1; i++) {
            double drawAmount = 800;
            Account account = new Account(ACCOUNT_NO, BALANCE);
            DrawThread dt = new DrawThread(account, drawAmount);
            DrawThread dt1 = new DrawThread(account, drawAmount);
            DepositThread dt2 = new DepositThread(account, drawAmount);
            Thread t1 = new Thread(dt);
            Thread t2 = new Thread(dt1);
            Thread t3 = new Thread(dt2);

            t1.start();
            t3.start();
            t2.start();
            // Thread.sleep(10);

            System.out.println("\n");
        }
    }
}
