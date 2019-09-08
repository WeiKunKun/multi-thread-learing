package org.kun.multi_thread_learning.comsumer_producer;

import java.util.concurrent.BlockingQueue;

public class Comsumer implements Runnable {
    private BlockingQueue<String> bq;

    public Comsumer(BlockingQueue<String> bq) {
        this.bq = bq;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("开始消费" + bq);
                bq.take();
                System.out.println("消费完成" + bq);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

}
