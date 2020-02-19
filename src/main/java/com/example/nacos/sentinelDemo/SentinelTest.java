package com.example.nacos.sentinelDemo;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：zhengqh
 * @date 2020/1/30 10:32
 **/
public class SentinelTest {

    // 定义规则
    public static  void initFlowRules(){
        List<FlowRule> list = new ArrayList();
        FlowRule  rule = new FlowRule();
        rule.setResource("helloword");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setCount(10);
        list.add(rule);
        FlowRuleManager.loadRules(list);
    }

     // 定义资源
    public static void main(String[] args) {
        initFlowRules();
        while (true){
            Entry entry = null;
            try {
                entry = SphU.entry("helloword");
                System.out.println("zqh");
            } catch (BlockException e) {
                System.out.println("block");
            }finally {
                if(entry!=null){
                    entry.exit();//释放
                }
            }
        }

    }
}
