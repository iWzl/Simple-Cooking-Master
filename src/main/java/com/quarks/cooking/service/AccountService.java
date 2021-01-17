package com.quarks.cooking.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.quarks.cooking.pojo.bean.Account;

/**
 * Account
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-12-12 16:56
 **/
public interface AccountService extends IService<Account> {
    /**
     * 用户登录
     *
     * @param account 账户
     * @param password 密码
     * @return 查询结果
     */
    Account login(String account, String password);


    /**
     * 根据用户账户获取用户信息
     *
     * @param account 用户账户
     * @return 用户账户信息
     */
    Account getByAccountName(String account);

    /**
     * 更新用户的最后在线时间
     *
     * @param uid 用户uid
     */
    void refreshAccountLastLoginTime(Integer uid);
}
