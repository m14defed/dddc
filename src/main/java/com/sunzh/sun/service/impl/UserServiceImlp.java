package com.sunzh.sun.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sunzh.sun.pojo.LoginBO;
import com.sunzh.sun.pojo.OrdorBO;
import com.sunzh.sun.pojo.UserInfoBO;
import com.sunzh.sun.raw.Dididache;
import com.sunzh.sun.service.UserService;
import com.sunzh.sun.util.AccoutUtil;
import com.sunzh.sun.util.Result;
import com.sunzh.sun.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple2;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple6;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;




@Service
@Slf4j
public class UserServiceImlp implements UserService {
    @Autowired
    AccoutUtil accoutUtil;
    @Override
    public Boolean register(LoginBO loginBO) {
        Dididache dididache = accoutUtil.getDididache();
        TransactionReceipt transactionReceipt;
        String s = AccoutUtil.saveAccountWithPem(loginBO.getUsername());
        if (loginBO.getType()==0){
            transactionReceipt = dididache.addDriver(s, BigInteger.valueOf(0));
        }else {
            transactionReceipt = dididache.addPassenger(s, BigInteger.valueOf(0));
        }

        if (transactionReceipt.isStatusOK()==true){
            System.out.println(Result.Success());
            return true;

        }
        return false;
    }

    @Override
    public  String login(LoginBO loginBO) {
        String token = TokenUtil.getJwt(loginBO.getUsername(), loginBO.getType());
        return token;
    }

    @Override
    public JSONObject getUserInfo(String username , Integer types) throws ContractException {
        if (types==0){
            // 通过client获取CryptoSuite对象
            Dididache dididache = accoutUtil.getDididache(username);
            Tuple2<BigInteger, List<String>> people = dididache.getPeople(BigInteger.valueOf(0));
            BigInteger value1 = people.getValue1();
            List<String> value2 = people.getValue2();
            UserInfoBO people1 = new UserInfoBO();

            people1.setTokens(value1);
            people1.setIds(value2);
            JSONObject outjson = new JSONObject();
            outjson.put("username",username);
            outjson.put("siji",people1);
            return outjson;

        }else {
            Dididache  dididache = accoutUtil.getDididache(username);
            Tuple2<BigInteger, List<String>> people = dididache.getPeople(BigInteger.valueOf(1));
            BigInteger value1 = people.getValue1();
            List<String> value2 = people.getValue2();
            UserInfoBO people1 = new UserInfoBO();
            people1.setTokens(value1);
            people1.setIds(value2);
            JSONObject outjson = new JSONObject();
            outjson.put("username",username);
            outjson.put("chengke",people1);
            return outjson;
        }
    }

    @Override
    public JSONObject getOrder(String id) throws ContractException {
        Dididache dididache = accoutUtil.getDididache();
        Tuple6<String, String, String, String, BigInteger, BigInteger> order = dididache.getOrder(id);
        OrdorBO ordorBO = new OrdorBO();
        ordorBO.setId(id);
        ordorBO.setOrigin(order.getValue1());
        ordorBO.setEnd(order.getValue2());
        ordorBO.setAddress1(order.getValue3());
        ordorBO.setAddress2(order.getValue4());
        ordorBO.setStatus(order.getValue5());
        ordorBO.setPrice(order.getValue6());
        JSONObject outjson = new JSONObject();
        outjson.put("dingdanxinxi",ordorBO);
        log.info(outjson.toString());
        return outjson;
    }
}
