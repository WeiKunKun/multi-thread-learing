package org.kun.multi_thread_learning.thread_control;

//sleep()方法比yield()方法有更好的可移植性，通常不建议使用yield方法来控制并发线程
public class YieldTest
{
    
    public static class YieldThread implements Runnable
    {
        
        private int i = 0;
        
        @Override
        public void run()
        {
            for (; i < 100; i++)
            {
                System.out.println(Thread.currentThread().getName() + " " + i);
                if (i == 20)
                {
                    Thread.yield();
                }
            }
            
        }
        
    }
    
    public static void main(String[] args)
    {
        YieldThread yt = new YieldThread();
        Thread higherPriorityThread = new Thread(yt, "higher priority thread");
        Thread lowerPriorityThread = new Thread(yt, "lower priority thread");
        higherPriorityThread.setPriority(Thread.MAX_PRIORITY);
        lowerPriorityThread.setPriority(Thread.MIN_PRIORITY);
        higherPriorityThread.start();
        lowerPriorityThread.start();
        
    }
    
}
