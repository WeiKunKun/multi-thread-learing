package org.kun.multi_thread_learning.comsumer_producer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestComProd {
    public static void main(String[] args) {
        BlockingQueue<String> bq = new ArrayBlockingQueue<>(2);
        Comsumer comsumer = new Comsumer(bq);
        Producer producer = new Producer(bq);
        Producer producer1 = new Producer(bq);
        Producer producer2 = new Producer(bq);
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(producer);
        executorService.execute(producer1);
        executorService.execute(producer2);
        executorService.execute(comsumer);
    }
}
