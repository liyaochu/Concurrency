package com.lyc.concurrency;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Jhon Li
 * @Date: 2018/12/6 12:06
 * @Description:
 */
@Controller
@Slf4j
public class TestController {
    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        return "test";
    }
}
