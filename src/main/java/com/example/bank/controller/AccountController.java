package com.example.bank.controller;

import com.example.bank.model.Account;
import com.example.bank.service.AccountService;

import java.util.List;

public class AccountController {
    private AccountService accountService = new AccountService();

    private void catchError(int result, String msg) {
        if(result > 0) {
            System.out.println("계좌 " +msg+"이(가) 성공적으로 됐습니다");
        }else {
            try {
                throw new Exception("ERROR : 계좌 "+msg+"이(가) 실패했습니다!!");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void accountInsert(Account account) {
        int result = accountService.accountInsert(account);
        catchError(result, "생성");
    }
    public void accountWithdraw(String accountNum, int money) {
        Account account = accountService.accountShowByAccountNum(accountNum);
        try {
            account.setWithdrawBalance(money);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        int result = accountService.accountUpdate(account);
        catchError(result, "출금");
    }

    public void accountDeposit(String accountNum, int money) {
        Account account = accountService.accountShowByAccountNum(accountNum);
        try {
            account.setDepositBalance(money);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        int result = accountService.accountUpdate(account);
        catchError(result, "입금");
    }

    public void accountDelete(String accountNum) {
        int result = accountService.accountDelete(accountNum);
        catchError(result, "제거");
    }

    public void accountShowAll() {
        List<Account> accountList = accountService.accountShowAll();
        System.out.println("총 "+accountList.size()+"개의 계좌가 개설되어 있습니다");
        System.out.println("========================");
        for(Account account : accountList) {
            System.out.println(account);
        }
        if(accountList.isEmpty()) {
            System.out.println("조회 정보가 없습니다.");
        }
    }

}
