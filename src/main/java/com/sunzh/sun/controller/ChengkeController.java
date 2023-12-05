package com.sunzh.sun.controller;

import com.sunzh.sun.pojo.OrdorBchuanru;
import com.sunzh.sun.service.ChengkeService;
import com.sunzh.sun.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;

@RestController
public class ChengkeController {
    @Autowired
    ChengkeService chengkeService;
    @GetMapping("/complete")
    public Result complete(HttpServletRequest request, String id) {
        Boolean complete = chengkeService.complete(request,id);
        if (complete==false){
            return Result.Eorr("你不是乘客本人");
        }
        return Result.Success("成功了");
    }
    @PostMapping("/addOrder")
    public Result addOrder(HttpServletRequest request,@RequestBody OrdorBchuanru ordorBO) {
        Boolean addOrder = chengkeService.addOrder(request,ordorBO);
        if (addOrder==false){
            return Result.Eorr("你不是乘客");
        }
        return Result.Success("成功了");
    }
    @GetMapping("/recharge")
    public Result recharge( HttpServletRequest request,BigInteger qian){
        Boolean recharge = chengkeService.recharge(request,qian);
        if (recharge==false){
            return Result.Eorr("充值失败");
        }
        return Result.Success("充值成功");
    }

}
