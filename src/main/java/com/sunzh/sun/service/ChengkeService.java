package com.sunzh.sun.service;

import com.sunzh.sun.pojo.OrdorBchuanru;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;

public interface ChengkeService {
    Boolean complete(HttpServletRequest request, String id);

    Boolean addOrder(HttpServletRequest request, OrdorBchuanru ordorBO);

    Boolean recharge(HttpServletRequest request,BigInteger qian);
}
