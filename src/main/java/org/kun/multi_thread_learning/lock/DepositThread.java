package org.kun.multi_thread_learning.lock;

public class DepositThread implements Runnable
{
    
    private Account account;
    
    private double depositAmount;
    
    public DepositThread(String name, Account account, double depositAmount)
    {
        Thread.currentThread().setName(name);
        this.account = account;
        this.depositAmount = depositAmount;
    }
    
    @Override
    public void run()
    {
        account.deposit(depositAmount);
    }
    
}
