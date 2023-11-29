package com.sunzh.sun.pojo;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

@Data
public class LoginBO {
    private String username;
    private Integer type;
}
