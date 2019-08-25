package org.kun.multi_thread_learning.synchronize_method;

public class Test
{
    private static final String ACCOUNT_NO = "666666";
    
    private static final double BALANCE = 1000;
    
    public static void main(String[] args)
    {
        for (int i = 0; i < 1; i++)
        {
            double drawAmount = 800;
            Account account = new Account(ACCOUNT_NO, BALANCE);
            DrawThread dt = new DrawThread("zhang san", account, drawAmount);
            Thread t1 = new Thread(dt);
            Thread t2 = new Thread(dt);
            t1.start();
            t2.start();
            System.out.println("\n");
        }
    }
}
