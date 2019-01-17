package com.lyc.concurrency;

import com.lyc.concurrency.example.threadlocal.RequestHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Handler;

/**
 * @Auther: Jhon Li
 * @Date: 2018/12/12 10:11
 * @Description:
 */
@Slf4j
public class HttpInterceptor  extends HandlerInterceptorAdapter {
    /**
     * 
     * @param request
     * @param response
     * @param handler
     * @return   接口调用之前
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle");
        return true;
    }
    /** 
    * @Description: 接口调用之后
    * @Author: Mr.Jhon Li 
    * @Date: 2018/12/12 
    */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        RequestHolder.remove();
        log.info("afterCompletion");
       return;
    }
}
