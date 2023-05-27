package com.example.bank.model;

public class Account {

    /*
    CREATE table bank(
    account_number varchar(100),
    name varchar(100),
    balance int,
    account_type varchar(100));
     */
    private String accountNum;
    private String name;
    private int balance = 0;
    private String accountType;

    public Account() {}

    public Account(String accountNum, String name, int balance, String accountType) {
        super();
        this.accountNum = accountNum;
        this.accountType = accountType;
        this.name = name;
        this.balance = balance;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setWithdrawBalance(int money) throws Exception {
        if (this.balance < money) {
            throw new Exception("Withdraw money cannot be bigger than balance!");
        }
        this.balance -= money;
    }

    public void setDepositBalance(int money) throws Exception {
        if (money < 0) {
            throw new Exception("Deposit money must be bigger than 0");
        }
        this.balance += money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNum='" + accountNum + '\'' +
                ", accountType='" + accountType + '\'' +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}