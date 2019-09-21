package org.kun.multi_thread_learning.tools;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author kun
 * @date 2019/09/21
 */
public class CyclicBarrierTest {

    private static int COUNT_OF_SPORTER = 8;

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(COUNT_OF_SPORTER, () -> {
            System.err.println("所有运动员都到达栅栏");
        });
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(COUNT_OF_SPORTER);
        for (int i = 0; i < 8; i++) {
            Sporter sporter = new Sporter("第" + (i + 1) + "跑道运动员", barrier);
            fixedThreadPool.execute(sporter);
        }
        fixedThreadPool.shutdown();
    }

}

class Sporter implements Runnable {

    private String name;
    private CyclicBarrier barrier;

    /**
     * @param name
     *            name of the sporter
     * @param barrier
     */
    public Sporter(String name, CyclicBarrier barrier) {
        super();
        this.name = name;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            Random random = new Random();
            // 模拟到达栅栏A运行的的时间
            Thread.sleep(random.nextInt(10) * 1000);
            System.out.println(name + "到达栅栏A");
            // 到达栅栏A，等待其他运动员同时出发，向下一个栅栏进发
            barrier.await();

            // 冲破栅栏A，向下一个栅栏B进发
            System.out.println("运动员" + name + "冲破栅栏A，向下一个栅栏B进发");
            // 模拟从栅栏A到达栅栏B运行的的时间
            Thread.sleep(random.nextInt(10) * 1000);
            System.out.println(name + "到达栅栏B");
            // 到达终点栅栏B，等待其他运动员一起庆祝颁奖
            barrier.await();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}