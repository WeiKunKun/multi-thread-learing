package org.kun.multi_thread_learning.synchronize_method;

public class Account
{
    private String accountNo;
    
    private double balance;
    
    private boolean hasBalance = false;
    
    public Account(String accountNo, double balance)
    {
        this.accountNo = accountNo;
        this.balance = balance;
    }
    
    public String getAccountNo()
    {
        return accountNo;
    }
    
    public double getBalance()
    {
        return balance;
    }
    
    public void setAccountNo(String accountNo)
    {
        this.accountNo = accountNo;
    }
    
    public void setBalance(double balance)
    {
        this.balance = balance;
    }
    
    public int hashCode()
    {
        return accountNo.hashCode();
    }
    
    public synchronized void draw(double drawAmount)
        throws InterruptedException
    {
        if (!hasBalance)
        {
            System.out.println(Thread.currentThread().getName() + " this account doesn't have balance");
            wait();
        }
        else
        {
            System.out.println(
                Thread.currentThread().getName() + " draw " + drawAmount + " in " + accountNo + " successfully");
            balance -= drawAmount;
            hasBalance = false;
            notifyAll();
        }
        
    }
    
    public synchronized void deposit(double depositAmount)
        throws InterruptedException
    {
        if (hasBalance)
        {
            System.out.println(Thread.currentThread().getName() + " this account already have balance");
            wait();
        }
        else
        {
            balance += depositAmount;
            System.out.println(Thread.currentThread().getName() + "deposit " + depositAmount);
            hasBalance = true;
            notifyAll();
        }
        
    }
    
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj != null && obj instanceof Account)
        {
            Account target = (Account)obj;
            return target.getAccountNo().equals(accountNo);
        }
        return false;
    }
}
