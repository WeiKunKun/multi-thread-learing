package org.kun.multi_thread_learning.synchronize_method;

public class DepositThread implements Runnable {

    private Account account;

    private double depositAmount;

    public DepositThread(Account account, double depositAmount) {
        this.account = account;
        this.depositAmount = depositAmount;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                account.deposit(depositAmount);
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
