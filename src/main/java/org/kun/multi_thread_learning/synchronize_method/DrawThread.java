package org.kun.multi_thread_learning.synchronize_method;

public class DrawThread implements Runnable
{
    
    private Account account;
    
    private double drawAmount;
    
    public DrawThread(String name, Account account, double drawAmount)
    {
        Thread.currentThread().setName(name);
        this.account = account;
        this.drawAmount = drawAmount;
    }
    
    @Override
    public void run()
    {
        account.draw(drawAmount);
    }
    
}
