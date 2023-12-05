package com.sunzh.sun.service.impl;

import com.sunzh.sun.raw.Dididache;
import com.sunzh.sun.service.SijiService;
import com.sunzh.sun.util.AccoutUtil;
import com.sunzh.sun.util.Result;
import com.sunzh.sun.util.TokenUtil;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.List;

@Service
public class SijiServiceImpl implements SijiService {
    @Autowired
    AccoutUtil accoutUtil;
    @Override
    public List<String> getAllId() throws ContractException {
        Dididache dididache = accoutUtil.getDididache();
        List allId = dididache.getAllId();
        return allId;
    }

    @Override
    public Boolean reception(HttpServletRequest request, String id) {
        String authorization = request.getHeader("Authorization");
        String username = TokenUtil.getUsername(authorization);
        Dididache dididache = accoutUtil.getDididache(username);
        TransactionReceipt reception = dididache.reception(id);
        if (reception.isStatusOK()==true){
            return  true;

        }
        return false;
    }

    @Override
    public Boolean deposit(HttpServletRequest request, BigInteger qian) {
        // 通过client获取CryptoSuite对象
        String authorization = request.getHeader("Authorization");
        String username = TokenUtil.getUsername(authorization);
        Dididache dididache = accoutUtil.getDididache(username);
        TransactionReceipt deposit = dididache.deposit(qian);
        if (deposit.isStatusOK()==true){
            System.out.println(deposit);
            System.out.println(Result.Success());
            return true;

        }
        return false;
    }

}
