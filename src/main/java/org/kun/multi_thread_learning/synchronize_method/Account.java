package org.kun.multi_thread_learning.synchronize_method;

public class Account
{
    private String accountNo;
    
    private double balance;
    
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
    
    public Account(String accountNo, double balance)
    {
        this.accountNo = accountNo;
        this.balance = balance;
    }
    
    public int hashCode()
    {
        return accountNo.hashCode();
    }
    
    public synchronized void draw(double drawAmount)
    {
        if (balance >= drawAmount)
        {
            
            System.out.println(
                Thread.currentThread().getName() + " draw " + drawAmount + " in " + accountNo + " successfully");
            
            try
            {
                Thread.sleep(10);
            }
            catch (InterruptedException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            balance -= drawAmount;
            System.out.println("The balance of " + accountNo + " is " + balance);
        }
        else
        {
            System.out
                .println(Thread.currentThread().getName() + " draw " + drawAmount + " in " + accountNo + " failed");
            System.out.println("The balance of " + accountNo + " is " + balance);
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
