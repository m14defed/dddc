package com.sunzh.sun.service.impl;

import com.sunzh.sun.pojo.OrdorBchuanru;
import com.sunzh.sun.raw.Dididache;
import com.sunzh.sun.service.ChengkeService;
import com.sunzh.sun.util.AccoutUtil;
import com.sunzh.sun.util.Result;
import com.sunzh.sun.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.UUID;

@Service
@Slf4j
public class ChengkeServiceImpl implements ChengkeService {
    @Autowired
    AccoutUtil accoutUtil;
    @Override
    public Boolean complete(HttpServletRequest request, String id) {
        String authorization = request.getHeader("Authorization");
        String username = TokenUtil.getUsername(authorization);
        Dididache dididache = accoutUtil.getDididache(username);
        TransactionReceipt complete = dididache.complete(id);
        if (complete.isStatusOK()==true){
            return  true;

        }
        return false;
    }

    @Override
    public Boolean addOrder(HttpServletRequest request, OrdorBchuanru ordorBO) {
        String authorization = request.getHeader("Authorization");
        String username = TokenUtil.getUsername(authorization);
        log.info(username);
        Dididache dididache = accoutUtil.getDididache(username);
        //本来想用UUID后来发现是整数崩溃了
        String uuid = UUID.randomUUID().toString();
        log.info(uuid);

        TransactionReceipt transactionReceipt = dididache.addOrder(uuid, ordorBO.getOrigin(), ordorBO.getEnd());


        if (transactionReceipt.isStatusOK()==true){
            System.out.println(Result.Success());
            return true;

        }
        return true;
    }

    @Override
    public Boolean recharge(HttpServletRequest request, BigInteger qian) {
        // 通过client获取CryptoSuite对象
        String authorization = request.getHeader("Authorization");
        String username = TokenUtil.getUsername(authorization);
        Dididache dididache = accoutUtil.getDididache(username);
        TransactionReceipt recharge = dididache.recharge(qian);

        if (recharge.isStatusOK()==true){
            System.out.println(Result.Success());
            return  true;

        }
        return  false;
    }
}

