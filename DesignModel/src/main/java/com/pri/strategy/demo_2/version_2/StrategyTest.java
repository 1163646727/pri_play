package com.pri.strategy.demo_2.version_2;

/**
 * className:  StrategyTest <BR>
 * description: 策略模式测试<BR>
 * remark: 外部调用<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-11-11 14:06 <BR>
 */
public class StrategyTest {
    public static void main(String[] args) {
        /** 创建具体策略 ChenQi */
        IStrategy iStrategy = new ConcreteStrategy();
        // 创建略上下文，注入具体策略 ChenQi;
        StrategyContext strategyContext = new StrategyContext(iStrategy);
        //  调用上下文对象的方法来完成对具体策略实现的回调 ChenQi;
        strategyContext.contextMethod();

        iStrategy = new ConcreteStrategy2();
        // 创建略上下文，注入具体策略 ChenQi;
        strategyContext = new StrategyContext(iStrategy);
        //  调用上下文对象的方法来完成对具体策略实现的回调 ChenQi;
        strategyContext.contextMethod();
    }
}
