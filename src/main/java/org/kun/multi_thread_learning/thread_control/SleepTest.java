package org.kun.multi_thread_learning.thread_control;

import java.util.Date;

//sleep方法并不会释放当前锁

public class SleepTest
{
    
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        for (int i = 0; i < 10; i++)
        {
            System.out.println("current time is " + new Date());
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
}
