package com.leesin.sentineldemo;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Leesin Dong
 * @date: Created in 2020/5/9 0009 10:23
 * @modified By:
 */
@RestController
public class SentinelController {
    @SentinelResource(value = "sayHello")//方法级别的限流
    @GetMapping("/say")
    public String sayHello() {
        System.out.println("hello world");
        return "hello world";
    }
}
