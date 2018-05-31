package com.tyf.service.impl;

import com.tyf.dao.AccountDao;
import com.tyf.pojo.Account;
import com.tyf.service.IAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.util.List;
@Service
public class AccountImpl implements IAccount {
    @Autowired
    AccountDao accountDAO ;
    @Resource
    private TransactionTemplate transactionTemplate;
    @Override
    public int add(Account account) {
        return accountDAO.add(account);
    }

    @Override
    @Transactional   //编程式事物
    public int update(Account account) {
        System.out.println("==================start");
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                try{
                    accountDAO.delete(1);
                    accountDAO.update(account);
                    Object obj  = null;   //制造异常使数据库事务生效：
                    obj.toString();
                }catch (Exception e){
                    transactionStatus.setRollbackOnly();
                    e.printStackTrace();
                }





            }
        });
        System.out.println("==================stop");
        return 1;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public int delete(int id) {
        accountDAO.delete(id);
        Object obj  = null;   //制造异常使数据库事务生效：
        obj.toString();
        return 1;
    }

    @Override
    public Account findAccountById(int id) {
        return accountDAO.findAccountById(id);
    }

    @Override
    public List<Account> findAccountList() {
        return accountDAO.findAccountList();
    }
}
