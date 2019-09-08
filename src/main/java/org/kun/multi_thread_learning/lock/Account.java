package org.kun.multi_thread_learning.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private String accountNo;

    private double balance;

    private Lock lock;

    private Condition condition;

    public Account(String accountNo, double balance) {
        lock = new ReentrantLock();
        condition = lock.newCondition();
        this.accountNo = accountNo;
        this.balance = balance;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int hashCode() {
        return accountNo.hashCode();
    }

    public void draw(double drawAmount) throws InterruptedException {
        lock.lock();
        try {
            while (balance < drawAmount) {
                System.out
                    .println(Thread.currentThread().getName() + " draw " + drawAmount + " in " + accountNo + " failed");
                System.out.println("The balance of " + accountNo + " is " + balance);
                condition.await();
            }

            System.out.println(
                Thread.currentThread().getName() + " draw " + drawAmount + " in " + accountNo + " successfully");
            balance -= drawAmount;
            System.out.println("The balance of " + accountNo + " is " + balance);
        } finally {
            lock.unlock();
        }
    }

    public synchronized void deposit(double depositAmount) {
        lock.lock();
        try {
            balance += depositAmount;
            System.out.println("deposit " + depositAmount + " and the balance is" + balance);
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && obj instanceof Account) {
            Account target = (Account)obj;
            return target.getAccountNo().equals(accountNo);
        }
        return false;
    }
}
