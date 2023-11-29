package com.sunzh.sun.pojo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

@Data
public class UserInfoBO implements Serializable {
    private static final long serialVersionUID = 10086L;
    private String account;
    private BigInteger tokens;
    private List<String> ids;
}
