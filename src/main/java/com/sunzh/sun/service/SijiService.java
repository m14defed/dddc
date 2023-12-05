package com.sunzh.sun.service;

import org.fisco.bcos.sdk.transaction.model.exception.ContractException;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.List;

public interface SijiService {
    List<String> getAllId() throws ContractException;

    Boolean reception(HttpServletRequest request, String id);

    Boolean deposit(HttpServletRequest request, BigInteger qian);
}
