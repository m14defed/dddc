package com.sunzh.sun.service;

import com.alibaba.fastjson.JSONObject;
import com.sunzh.sun.pojo.LoginBO;
import com.sunzh.sun.util.Result;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;

public interface UserService {
    Boolean register(LoginBO loginBO);
    String login(LoginBO loginBO);
    JSONObject getUserInfo(String username, Integer types) throws ContractException;
}
