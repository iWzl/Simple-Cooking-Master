package com.quarks.cooking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.quarks.cooking.pojo.bean.Account;
import org.springframework.stereotype.Repository;

/**
 * Account
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-12-12 16:52
 **/

@Repository
public interface AccountDao extends BaseMapper<Account> {
}
