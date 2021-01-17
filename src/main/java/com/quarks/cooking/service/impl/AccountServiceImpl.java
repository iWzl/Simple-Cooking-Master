package com.quarks.cooking.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quarks.cooking.mapper.AccountDao;
import com.quarks.cooking.pojo.bean.Account;
import com.quarks.cooking.service.AccountService;
import org.springframework.stereotype.Service;

/**
 * Account
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-12-12 16:59
 **/
@Service
public class AccountServiceImpl extends ServiceImpl<AccountDao, Account> implements AccountService {
    @Override
    public Account login(String account, String password) {
        QueryWrapper<Account> accountWrapper = new QueryWrapper<>();
        accountWrapper.eq("account",account).eq("password",password);
        return getOne(accountWrapper);
    }

    @Override
    public Account getByAccountName(String account) {
        QueryWrapper<Account> accountWrapper = new QueryWrapper<>();
        accountWrapper.eq("account",account);
        return getOne(accountWrapper);
    }

    @Override
    public void refreshAccountLastLoginTime(Integer uid) {
        UpdateWrapper<Account> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("last_login_time",System.currentTimeMillis())
        .eq("uid",uid);
        update(updateWrapper);
    }
}
