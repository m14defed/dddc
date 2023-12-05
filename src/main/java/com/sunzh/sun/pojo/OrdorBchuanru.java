package com.sunzh.sun.pojo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigInteger;

@Data
public class OrdorBchuanru implements  Serializable {
    private static final long serialVersionUID = 10086L;
    private String origin;

    private String end;

    private String account;

}
