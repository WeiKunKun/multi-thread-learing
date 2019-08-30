package org.kun.multi_thread_learning.lock;

public class Test
{
    private static final String ACCOUNT_NO = "666666";
    
    private static final double BALANCE = 1000;
    
    public static void main(String[] args)
        throws InterruptedException
    {
        for (int i = 0; i < 1; i++)
        {
            double drawAmount = 800;
            Account account = new Account(ACCOUNT_NO, BALANCE);
            DrawThread dt = new DrawThread("zhang san", account, drawAmount);
            DepositThread dt1 = new DepositThread("li si", account, 800);
            Thread t1 = new Thread(dt);
            Thread t2 = new Thread(dt);
            Thread t3 = new Thread(dt1);
            
            t1.start();
            t2.start();
            Thread.sleep(10);
            t3.start();
            System.out.println("acb\n");
        }
    }
}
