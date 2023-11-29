package com.sunzh.sun.pojo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigInteger;

@Data
public class OrdorBchuanru implements  Serializable {
    private static final long serialVersionUID = 10086L;
    @NotBlank(message = "起始地点不能为空")
    private String origin;
    @NotBlank(message = "终点地点不能为空")
    private String end;
    @NotBlank(message = "账户名不能为空")
    private String account;

}
