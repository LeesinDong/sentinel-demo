package com.leesin.sentineldemo;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Leesin Dong
 * @date: Created in 2020/5/8 0008 21:41
 * @modified By:
 */
public class SentinelDemo {
    //初始化规则
    private static void initFlowRules() {
        List<FlowRule> rules = new ArrayList<>();//限流规则的集合
        FlowRule flowRule = new FlowRule();
        flowRule.setResource("doTest");//资源（方法名称、接口）
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);//限流的阈值的类型，还有其他的类型
        flowRule.setCount(10);//qps多少合适？ 设置为10个
        rules.add(flowRule);
        FlowRuleManager.loadRules(rules);
    }

    public static void main(String[] args) {
        initFlowRules();//初始化规则
        while (true) {
            Entry entry = null;//entry表示一个资源
            try {
                entry = SphU.entry("doTest");  //匹配上面定义的规则，应用规则
                System.out.printf("Hello Word");//拿到entry了，会执行后面的代码
            } catch (BlockException e) {//如果被限流了，那么会抛出异常
                e.printStackTrace();
            }finally {
                if (entry != null) {
                    entry.exit();//释放掉
                }
            }
        }
    }

}


