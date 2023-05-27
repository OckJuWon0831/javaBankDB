package com.example.bank.repository;

import com.example.bank.model.Account;
import static com.example.bank.repository.JDBCtemplate.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class AccountDAO {
    public int accountInsert(Connection conn, Account account) {
        String query = "insert into bank (account_number, name, balance, account_type) values (?,?,?,?)";
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, account.getAccountNum());
            pstmt.setString(2, account.getName());
            pstmt.setInt(3, account.getBalance());
            pstmt.setString(4, account.getAccountType());
            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return result;
    }

    public int accountDelete(Connection conn, String account_number) {
        String query = "delete from bank where account_number = ?";
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, account_number);

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return result;
    }

    // Withdraw(출금), Deposit(입금) 될때만
    public int accountUpdate(Connection conn, Account account) {
        String query = "update bank set balance = ? where account_number = ?";
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, account.getBalance());
            pstmt.setString(2, account.getAccountNum());
            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return result;
    }

    public List<Account> accountShowAll(Connection conn) {
        String query = "select * from bank";
        Statement stmt = null;
        ResultSet rs = null;
        List<Account> accountList = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()) {
                String accountNum = rs.getString("account_number");
                String name = rs.getString("name");
                int balance = rs.getInt("balance");
                String accountType = rs.getString("account_type");
                Account account = new Account(accountNum, name, balance, accountType);

                accountList.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs);
            close(stmt);
        }
        return accountList;
    }

    public Account accountShowByAccountNum(Connection conn, String accountNum) {
        String query = "select * from bank where account_number = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Account account = null;
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, accountNum);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                account = new Account(
                        rs.getString("account_number"),
                        rs.getString("name"),
                        rs.getInt("balance"),
                        rs.getString("account_type")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs);
            close(pstmt);
        }
        return account;
    }

}
