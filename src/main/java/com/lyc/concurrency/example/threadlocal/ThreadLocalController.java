package com.lyc.concurrency.example.threadlocal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: Jhon Li
 * @Date: 2018/12/12 15:10
 * @Description:
 */
@Controller
@RequestMapping("/threadLocal")
public class ThreadLocalController {
   @RequestMapping("/test")
   @ResponseBody
    public Long test(){
    return  RequestHolder.getId();
    }
}
