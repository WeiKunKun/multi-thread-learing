package org.kun.multi_thread_learning.future;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

import org.checkerframework.checker.nullness.qual.Nullable;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

/**
 * @author kun https://www.cnblogs.com/boothsun/p/7881028.html
 * @date 2019/09/17
 */
public class ListenableFutureTest {

    public static void main(String[] args) {
        ListeningExecutorService pool = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
        long startTime = System.currentTimeMillis();
        ListenableFuture<Boolean> booleanTask = pool.submit(new Callable<Boolean>() {

            @Override
            public Boolean call() throws Exception {
                System.out.println("Enter booleanTask");
                Thread.sleep(3000);
                System.out.println("booleanTask after sleep");
                return true;
            }

        });

        ListenableFuture<String> stringTask = pool.submit(new Callable<String>() {

            @Override
            public String call() throws Exception {
                System.out.println("Enter stringTask");
                return "kunkun";
            }

        });

        ListenableFuture<Integer> integerTask = pool.submit(new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                System.out.println("Enter integerTask");
                return 9;
            }

        });

        Futures.addCallback(booleanTask, new FutureCallback<Boolean>() {

            @Override
            public void onSuccess(@Nullable Boolean result) {
                System.out.println("booleanTask: " + result);
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        }, pool);

        Futures.addCallback(stringTask, new FutureCallback<String>() {

            @Override
            public void onSuccess(@Nullable String result) {
                System.out.println("stringTask: " + result);
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }

        }, pool);

        Futures.addCallback(integerTask, new FutureCallback<Integer>() {

            @Override
            public void onSuccess(@Nullable Integer result) {
                System.out.println("integerTask: " + result);
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }

        }, pool);

        long endTime = System.currentTimeMillis();

        System.out.println("Tasks spend " + (endTime - startTime) + " milissecond");
    }

}
