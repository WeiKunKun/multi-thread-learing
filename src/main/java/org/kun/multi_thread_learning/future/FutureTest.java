package org.kun.multi_thread_learning.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author kun
 * @date 2019/09/17
 */
public class FutureTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService pool = Executors.newCachedThreadPool();
        long startTime = System.currentTimeMillis();
        Future<Boolean> booleanTask = pool.submit(new Callable<Boolean>() {

            @Override
            public Boolean call() throws Exception {
                System.out.println("Enter booleanTask");
                Thread.sleep(3000);
                System.out.println("booleanTask after sleep");
                return true;
            }

        });

        Future<String> stringTask = pool.submit(new Callable<String>() {

            @Override
            public String call() throws Exception {
                System.out.println("Enter stringTask");
                return "kunkun";
            }

        });

        Future<Integer> integerTask = pool.submit(new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                System.out.println("Enter integerTask");
                return 9;
            }

        });

        while (true) {
            if (booleanTask.isDone() && !booleanTask.isCancelled()) {
                System.out.println("stringTask: " + stringTask.get());
                break;
            }
        }

        while (true) {
            if (booleanTask.isDone() && !booleanTask.isCancelled()) {
                System.out.println("integerTask: " + integerTask.get());
                break;
            }
        }

        while (true) {
            if (booleanTask.isDone() && !booleanTask.isCancelled()) {
                System.out.println("booleanTask: " + booleanTask.get());
                break;
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("The tasks spend " + (endTime - startTime) + " milissecond");
    }
}
