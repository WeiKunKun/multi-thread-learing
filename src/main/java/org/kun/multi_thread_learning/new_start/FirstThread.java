package org.kun.multi_thread_learning.new_start;

public class FirstThread extends Thread {

    private int i;

    public FirstThread(String name) {
        super(name);
    }

    public void run() {
        for (; i < 100; i++) {
            System.out.println(getName() + " " + i);
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 45) {
                new FirstThread("wei").start();
                new FirstThread("kun").start();
            }
        }
    }

}
