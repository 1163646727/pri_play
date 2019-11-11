package com.pri.strategy.demo_1;

/**
 * className:  StrategyTest <BR>
 * description: <BR>
 * remark: <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-09 19:26 <BR>
 */
public class StrategyTest {

    public static void main(String[] args) {
        Context context;
        context = new Context(new StrategyA());
        context.algorithm();
        context = new Context(new StrategyB());
        context.algorithm();
        context = new Context(new StrategyC());
        context.algorithm();
    }
}
