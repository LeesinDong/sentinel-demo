package com.leesin.sentineldemo;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SentinelDemoApplication {

    public static void main(String[] args) {
        initFlowRules();
        SpringApplication.run(SentinelDemoApplication.class, args);
    }


    //初始化规则
    private static void initFlowRules() {
        List<FlowRule> rules = new ArrayList<>();//限流规则的集合
        FlowRule flowRule = new FlowRule();
        //这里指向的是controller中定义的方法
        flowRule.setResource("sayHello");//资源（方法名称、接口）
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);//限流的阈值的类型，还有其他的类型
        flowRule.setCount(10);//qps多少合适？ 设置为10个
        rules.add(flowRule);
        FlowRuleManager.loadRules(rules);
    }
}
