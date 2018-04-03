package com.tyf.dao;

import com.tyf.pojo.Account;

import java.util.List;

public interface AccountDao {
    int add(Account account);

    int update(Account account);

    int delete(int id);

    Account findAccountById(int id);

    List<Account> findAccountList();
}
