package com.sunzh.sun.controller;

import com.sunzh.sun.service.SijiService;
import com.sunzh.sun.util.Result;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.List;

@RestController
public class SijiController {
    @Autowired
    SijiService sijiService;
    @GetMapping("/getAllId")
    public Result getOrder() throws ContractException {
        List<String> allId =sijiService.getAllId();
        return Result.Success(allId);
    }
    @GetMapping("/reception")
    public Result reception(HttpServletRequest request,String id) {
        Boolean reception = sijiService.reception(request,id);
        if (reception==false){
            return Result.Eorr("你不是司机");
        }
        return Result.Success("成功");
    }
    @GetMapping("/deposit")
    public Result deposit(HttpServletRequest request,BigInteger qian){
        Boolean deposit = sijiService.deposit(request,qian);
        if (deposit==false){
            return Result.Eorr("提款失败");
        }
        return Result.Success("提款成功");
    }
}
