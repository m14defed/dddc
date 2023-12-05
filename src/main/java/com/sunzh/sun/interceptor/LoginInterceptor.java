package com.sunzh.sun.interceptor;

import com.sunzh.sun.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("请求路径为："+requestURI);
        String jwt = request.getHeader("Authorization");
        if (jwt==null){
            log.info("令牌为空");
            response.getWriter().write("NOT_LOGIN");
            return false;
        }
        try {
            TokenUtil.verify(jwt);
        }catch (Exception e){
            log.info("解析失败");
            response.getWriter().write("NOT_LOGIN");
            throw new RuntimeException(e);
        }
        return true;
    }
}
