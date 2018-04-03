package com.tyf.service;

import com.tyf.pojo.Account;

import java.util.List;

public interface IAccount {
    int add(Account account);

    int update(Account account);

    int delete(int id);

    Account findAccountById(int id);

    List<Account> findAccountList();
}
