package com.sunzh.sun.pojo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;

@Data
public class OrdorBO implements  Serializable {
    private static final long serialVersionUID = 10086L;
    private String id;
    private String origin;
    private String end;
    private String address1;
    private String address2;
    private BigInteger status;
    private  BigInteger price;
}
