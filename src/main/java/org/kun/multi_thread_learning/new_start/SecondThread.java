package org.kun.multi_thread_learning.new_start;

public class SecondThread implements Runnable
{
    
    private int i;
    
    @Override
    public void run()
    {
        for (; i < 100; i++)
        {
            // TODO Auto-generated method stub
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
    
    public static void main(String[] args)
    {
        for (int i = 0; i < 100; i++)
        {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 3)
            {
                
                SecondThread st = new SecondThread();
                new Thread(st, "wei").start();
                new Thread(st, "kun").start();
            }
        }
    }
    
}
