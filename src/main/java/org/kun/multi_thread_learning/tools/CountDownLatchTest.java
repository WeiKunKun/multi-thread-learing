package org.kun.multi_thread_learning.tools;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author kun
 * @date 2019/09/19
 */
public class CountDownLatchTest {

    private static final int NUM_OF_STUDENT = 20;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        CountDownLatch studentLatch = new CountDownLatch(NUM_OF_STUDENT);
        CountDownLatch teacherLatch = new CountDownLatch(1);
        Teacher teacher = new Teacher("王老师", teacherLatch);
        threadPool.submit(teacher);
        teacherLatch.await();
        Student temp;
        for (int i = 0; i < NUM_OF_STUDENT; i++) {
            temp = new Student("学生" + i, studentLatch);
            threadPool.submit(temp);
        }
        studentLatch.await();
        System.out.println("老师开始收卷子");

    }

}

class Student implements Runnable {

    private String name;
    private CountDownLatch latch;

    public Student(String name, CountDownLatch latch) {
        this.name = name;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            System.out.println(name + "开始答题");
            Thread.sleep(10 * 1000);
            System.out.println(name + "答题结束");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            latch.countDown();
        }

    }

}

class Teacher implements Runnable {

    private String name;
    private CountDownLatch latch;

    public Teacher(String name, CountDownLatch latch) {
        this.name = name;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            System.out.println(name + "开始发卷子");
            Thread.sleep(10 * 1000);
            System.out.println(name + "发卷子结束");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            latch.countDown();
        }
    }

}