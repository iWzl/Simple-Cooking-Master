package com.quarks.cooking.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.quarks.cooking.annotation.Security;
import com.quarks.cooking.pojo.bean.Account;
import com.quarks.cooking.pojo.common.Msg;
import com.quarks.cooking.pojo.common.RoleEnum;
import com.quarks.cooking.pojo.req.AccountLoginReq;
import com.quarks.cooking.pojo.req.AccountRegisterReq;
import com.quarks.cooking.pojo.req.RefreshProfileReq;
import com.quarks.cooking.pojo.rsp.ProfileRsp;
import com.quarks.cooking.pojo.rsp.AccountLoginRsp;
import com.quarks.cooking.service.AccountService;
import com.quarks.cooking.utils.HttpUtil;
import com.quarks.cooking.utils.JWTUtil;
import com.quarks.cooking.utils.ObjectUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户账户控制器
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-12-08 23:21
 **/

@RestController
@RequestMapping(
        value = "/api/account",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(value = "login",consumes = MediaType.APPLICATION_JSON_VALUE)
    @Security
    public Msg<AccountLoginRsp> login(@Validated @RequestBody AccountLoginReq accountLoginReq){
        Account account = accountService.login(accountLoginReq.getAccount(),accountLoginReq.getPassword());
        if(null == account || account.getUid() == 0){
            return Msg.buildFailedMsg("incorrect password or account does not exist");
        }
        String token = JWTUtil.buildToken(account.getUid(),account.getPassword());
        AccountLoginRsp accountLoginRsp = new AccountLoginRsp();
        BeanUtils.copyProperties(account,accountLoginRsp);
        accountLoginRsp.setToken(token);
        return Msg.buildSuccessMsg(accountLoginRsp);
    }

    @PostMapping(value = "register",consumes = MediaType.APPLICATION_JSON_VALUE)
    @Security
    public Msg<AccountLoginRsp> register(@Validated @RequestBody AccountRegisterReq accountRegisterReq){
        Account account = accountService.getByAccountName(accountRegisterReq.getAccount());
        if(null != account){
            return Msg.buildFailedMsg("account already exists");
        }
        long system = System.currentTimeMillis();
        account = new Account();
        BeanUtils.copyProperties(accountRegisterReq,account);
        account.setCreateTime(system);
        account.setLastLoginTime(system);
        account.setUpdateTime(system);
        account.setAboutMe("");
        account.setStatus(0);
        if(accountService.save(account)){
            String token = JWTUtil.buildToken(account.getUid(),account.getPassword());
            AccountLoginRsp accountLoginRsp = new AccountLoginRsp();
            BeanUtils.copyProperties(account,accountLoginRsp);
            accountLoginRsp.setToken(token);
            return Msg.buildSuccessMsg(accountLoginRsp);
        }
        return Msg.buildFailedMsg("register Failed");
    }

    @GetMapping
    @Security(RoleEnum.LOGIN)
    public Msg<ProfileRsp> fetchAccountProfile(@Validated @RequestParam("uid")Integer uid){
        Account account = accountService.getById(uid);
        if(null == account){
            return Msg.buildFailedMsg("user does not exist");
        }
        ProfileRsp profileRsp = new ProfileRsp();
        BeanUtils.copyProperties(account,profileRsp);
        return Msg.buildSuccessMsg(profileRsp);
    }

    @PutMapping
    @Security(RoleEnum.LOGIN)
    public Msg<ProfileRsp> refreshAccountProfile(@Validated @RequestBody RefreshProfileReq refreshProfileReq){
        Integer uid = HttpUtil.getUid();
        UpdateWrapper<Account> updateWrapper = new UpdateWrapper<>();
        if(ObjectUtil.isNotEmpty(refreshProfileReq.getName())){
            updateWrapper.set("name",refreshProfileReq.getName());
        }
        if(ObjectUtil.isNotEmpty(refreshProfileReq.getAboutMe())){
            updateWrapper.set("about_me",refreshProfileReq.getAboutMe());
        }
        if(ObjectUtil.isNotEmpty(refreshProfileReq.getAvatar())){
            updateWrapper.set("avatar",refreshProfileReq.getAvatar());
        }
        if(ObjectUtil.isNotEmpty(refreshProfileReq.getGender())){
            updateWrapper.set("gender",refreshProfileReq.getGender());
        }
        updateWrapper.eq("uid",uid);
        if(accountService.update(updateWrapper)){
            return fetchAccountProfile(uid);
        }
        return Msg.buildFailedMsg("failed to update data");
    }
}
