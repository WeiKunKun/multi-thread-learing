package org.kun.multi_thread_learning.comsumer_producer;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private BlockingQueue<String> bq;

    public Producer(BlockingQueue<String> bq) {
        this.bq = bq;
    }

    @Override
    public void run() {
        String[] contents = new String[] {"java", "python", "c"};

        for (int i = 0; i < 9999; i++) {
            try {
                System.out.println("开始生产" + bq);
                bq.put(contents[i % 3]);
                System.out.println("生产完成" + bq);

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

}
