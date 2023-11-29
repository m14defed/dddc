package com.sunzh.sun.controller;


import com.alibaba.fastjson.JSONObject;
import com.sunzh.sun.pojo.LoginBO;

import com.sunzh.sun.service.UserService;
import com.sunzh.sun.util.Result;
import com.sunzh.sun.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/register")
    public Result register(@RequestBody LoginBO loginBO){
        if (loginBO.getUsername()==null){
            log.info("username is null");
            return Result.Eorr("username is null");
        }
        if (loginBO.getType()==null){
            log.info("type is null");
            return Result.Eorr("type is null");
        }
        Boolean register = userService.register(loginBO);
        if (register==true){
            return Result.Success();
        }else {
            return Result.Eorr("register fail");
        }

    }
    @PostMapping("/login")
    public Result login(@RequestBody LoginBO loginBO/* @RequestParam("password") String password*/){
        if (loginBO.getUsername()==null){
            log.info("username is null");
            return Result.Eorr("username is null");
        }
        if (loginBO.getType()==null){
            log.info("type is null");
            return Result.Eorr("type is null");
        }
        String token = userService.login(loginBO);
        log.info(token);
        return Result.Success(token);
    }
    @GetMapping("/getUserInfo")
    public Result getUserInfo(HttpServletRequest request) throws ContractException {
        String Jwt = request.getHeader("Authorization");
        String username = TokenUtil.getUsername(Jwt);
        Integer type = TokenUtil.getType(Jwt);
        JSONObject userInfo = userService.getUserInfo(username, type);
        return Result.Success(userInfo);
    }
}
