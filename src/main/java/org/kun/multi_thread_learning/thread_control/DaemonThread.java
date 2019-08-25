package org.kun.multi_thread_learning.thread_control;

public class DaemonThread
{
    
    public static void main(String[] args)
    {
        Thread thread = new Thread(() -> {
            int j = 0;
            for (; j < 1000; j++)
            {
                System.out.println(Thread.currentThread().getName() + " " + j);
            }
        });
        // 设置为后台线程(守护线程，精灵线程)
        thread.setDaemon(true);
        thread.setName("Daemon thread");
        thread.start();
        System.out.println(Thread.currentThread().getName());
        // 程序执行到此处，当前线程（main线程）结束，后台线程也应该结束
        
    }
    
}
