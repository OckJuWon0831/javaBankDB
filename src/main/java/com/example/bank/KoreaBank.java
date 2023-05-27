package com.example.bank;

import com.example.bank.controller.AccountController;
import com.example.bank.model.Account;
import com.example.bank.model.AccountType;

import java.util.List;
import java.util.Scanner;

public class KoreaBank {
    Scanner sc = new Scanner(System.in);
    boolean isExit = false;
    AccountController accountController = new AccountController();

    private void initUI() {
        System.out.println("========================");
        System.out.println("한국은행");
        System.out.println("========================");
        System.out.println("1. 계좌개설");
        System.out.println("2. 입금");
        System.out.println("3. 출금");
        System.out.println("4. 계좌조회");
        System.out.println("5. 계좌삭제");
        System.out.println("6. 종료");
        System.out.println("항목을 선택하세요");
        System.out.println("========================");
    }
    private void accountOpenUI() {
        System.out.println("계좌개설 칸이 선택되었습니다.");
        System.out.println("계좌를 선택해주세요.");
        System.out.println("========================");
        System.out.println("1. 일반 계좌");
        System.out.println("2. 학생 계좌");
        System.out.println("3. 적금");
        System.out.println("========================");
    }
    private void accountSelectUI(String accountType) {
        Account account;
        switch (accountType) {
            // 일반계좌 & 학생계좌 & 적금
            case AccountType.NORMAL:
                System.out.print("생성할 일반 계좌의 계좌번호, 이름, 최초 입금액을 입력하세요.");
                String accountNum = sc.nextLine();
                String name = sc.nextLine();
                int balance = Integer.parseInt(sc.nextLine());
                account = new Account(accountNum, name, balance, accountType);
                accountController.accountInsert(account);
                break;
            case AccountType.STUDENT:
                System.out.print("생성할 학생 계좌의 계좌번호, 이름, 최초 입금액을 입력하세요.");
                String accountNum1 = sc.nextLine();
                String name1 = sc.nextLine();
                int balance1 = Integer.parseInt(sc.nextLine());
                account = new Account(accountNum1, name1, balance1, accountType);
                accountController.accountInsert(account);
                break;
            case AccountType.SAVINGS:
                System.out.print("생성할 적금 계좌의 계좌번호, 이름, 최초 입금액을 입력하세요.");
                String accountNum2 = sc.nextLine();
                String name2 = sc.nextLine();
                int balance2 = Integer.parseInt(sc.nextLine());
                account = new Account(accountNum2, name2, balance2, accountType);
                accountController.accountInsert(account);
                break;
        }
    }
    private void accountUpdateUI(int indicateUpdate) {
        switch (indicateUpdate) {
            case 2: // deposit(입금)
                System.out.println("계좌번호를 입력해주세요.");
                String accountNumberToDeposit = sc.nextLine();
                System.out.println("입금할 금액을 입력해주세요.");
                int moneyToDeposit = Integer.parseInt(sc.nextLine());
                accountController.accountDeposit(accountNumberToDeposit, moneyToDeposit);
                break;
            case 3: // withdraw(출금)
                System.out.println("계좌번호를 입력해주세요.");
                String accountNumberToWithdraw = sc.nextLine();
                System.out.println("출금할 금액을 입력해주세요.");
                int moneyToWithdraw = Integer.parseInt(sc.nextLine());
                accountController.accountWithdraw(accountNumberToWithdraw, moneyToWithdraw);
                break;
        }
    }
    public void KoreaBankStart() {
        do
        {
            initUI();
            int selector = Integer.parseInt(sc.nextLine());
            switch(selector) {
                case 1:
                    accountOpenUI();
                    int accountOpenSelector = Integer.parseInt(sc.nextLine());
                    switch(accountOpenSelector) {
                        case 1:
                            System.out.println("========================");
                            System.out.println("1. 일반 계좌가 선택되었습니다.");
                            accountSelectUI(AccountType.NORMAL);
                            break;
                        case 2:
                            System.out.println("========================");
                            System.out.println("2. 학생 계좌가 선택되었습니다.");
                            accountSelectUI(AccountType.STUDENT);
                            break;
                        case 3:
                            System.out.println("========================");
                            System.out.println("3. 적금 계좌가 선택되었습니다.");
                            accountSelectUI(AccountType.SAVINGS);
                            break;
                    }
                    break;
                case 2:
                    System.out.println("입금 칸이 선택되었습니다.");
                    accountUpdateUI(2);
                    break;
                case 3:
                    System.out.println("출금 칸이 선택되었습니다.");
                    accountUpdateUI(3);
                    break;
                case 4:
                    System.out.println("계좌조회 칸이 선택되었습니다");
                    accountController.accountShowAll();
                    break;
                case 5:
                    System.out.println("계좌 삭제 칸이 선택되었습니다");
                    System.out.println("삭제하고싶은 계좌 번호를 입력하십시오");
                    String accountNumberToDelete = sc.nextLine();
                    accountController.accountDelete(accountNumberToDelete);
                    break;
                case 6:
                    System.out.println("프로그램이 종료됩니다");
                    isExit = true;
            }
        } while(!isExit);
    }
}

