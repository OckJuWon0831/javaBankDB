package com.example.bank.service;

import static com.example.bank.repository.JDBCtemplate.*;
import com.example.bank.model.Account;
import com.example.bank.repository.AccountDAO;
import java.sql.Connection;
import java.util.List;
public class AccountService {
    private AccountDAO accountDAO = new AccountDAO();
    public int accountInsert(Account account) {
        Connection conn = getConnection();
        int result = accountDAO.accountInsert(conn, account);
        if(result > 0) {
            commit(conn);
        }else {
            rollback(conn);
        }
        close(conn);
        return result;
    }

    public int accountDelete(String accountNum) {
        Connection conn = getConnection();
        int result = accountDAO.accountDelete(conn, accountNum);
        if(result > 0) {
            commit(conn);
        }else {
            rollback(conn);
        }
        close(conn);
        return result;
    }

    public int accountUpdate(Account account) {
        Connection conn = getConnection();
        int result = accountDAO.accountUpdate(conn, account);
        if(result > 0) {
            commit(conn);
        }else {
            rollback(conn);
        }
        close(conn);
        return result;
    }
    public List<Account> accountShowAll() {
        Connection conn = getConnection();
        List<Account> accountShowAll = accountDAO.accountShowAll(conn);
        close(conn);
        return accountShowAll;
    }

    public Account accountShowByAccountNum(String accountNum) {
        Connection conn = getConnection();
        Account account = accountDAO.accountShowByAccountNum(conn, accountNum);
        close(conn);
        return account;
    }
}
