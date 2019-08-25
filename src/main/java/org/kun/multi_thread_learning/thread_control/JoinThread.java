package org.kun.multi_thread_learning.thread_control;

/**
 * 
 * @author kun Join()方法通常由使用线程的程序调用，将大问题划分成许多小问题，每个小问题，每个小问题分配一个线程。 k当所有的小问题都得到处理后，在调用主线程进一步操作。
 */

public class JoinThread
{
    
    public static void main(String[] args)
    {
        for (int i = 0; i < 100; i++)
        {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 20)
            {
                Thread thread = new Thread(() -> {
                    int j = 0;
                    for (; j < 100; j++)
                    {
                        System.out.println(Thread.currentThread().getName() + " " + j);
                    }
                });
                thread.start();
                try
                {
                    thread.join(1);
                }
                catch (InterruptedException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        
    }
    
}
