package org.kun.multi_thread_learning.synchronize;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DrawThread implements Runnable
{
    private String name;
    
    private Account account;
    
    private double drawAmount;
    
    private final Lock lock = new ReentrantLock();
    
    public DrawThread(String name, Account account, double drawAmount)
    {
        Thread.currentThread().setName(name);
        this.name = name;
        this.account = account;
        this.drawAmount = drawAmount;
    }
    
    @Override
    public void run()
    {
//        // 同步代码块
//        synchronized (account)
//        {
//            if (account.getBalance() >= drawAmount)
//            {
//                
//                System.out.println(name + " draw " + drawAmount + " in " + account.getAccountNo() + " successfully");
//                
//                try
//                {
//                    Thread.sleep(10);
//                }
//                catch (InterruptedException e)
//                {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//                account.setBalance(account.getBalance() - drawAmount);
//                System.out.println("The balance of " + account.getAccountNo() + " is " + account.getBalance());
//            }
//            else
//            {
//                System.out.println(name + " draw " + drawAmount + " in " + account.getAccountNo() + "failed");
//                System.out.println("The balance of " + account.getAccountNo() + " is " + account.getBalance());
//            }
//        }
        
        // 同步锁
        lock.lock();
        try
        {
            if (account.getBalance() >= drawAmount)
            {
                
                System.out.println(name + " draw " + drawAmount + " in " + account.getAccountNo() + " successfully");
                
                try
                {
                    Thread.sleep(10);
                }
                catch (InterruptedException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                account.setBalance(account.getBalance() - drawAmount);
                System.out.println("The balance of " + account.getAccountNo() + " is " + account.getBalance());
            }
            else
            {
                System.out.println(name + " draw " + drawAmount + " in " + account.getAccountNo() + " failed");
                System.out.println("The balance of " + account.getAccountNo() + " is " + account.getBalance());
            }
        }
        finally
        {
            lock.unlock();
        }
        
    }
    
}
