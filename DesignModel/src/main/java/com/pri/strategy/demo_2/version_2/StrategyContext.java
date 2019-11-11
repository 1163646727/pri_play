package com.pri.strategy.demo_2.version_2;

/**
 * className:  StrategyContext <BR>
 * description: 策略上下文<BR>
 * remark: <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-11-11 14:00 <BR>
 */
public class StrategyContext {
    private IStrategy iStrategy;
    /**
     * methodName: StrategyContext <BR>
     * description: 构造函数<BR>
     * remark: 注入策略 <BR>
     * param: iStrategy <BR>
     * return:  <BR>
     * author: ChenQi <BR>
     * createDate: 2019-11-11 14:03 <BR>
     */
    public StrategyContext(IStrategy iStrategy){
        this.iStrategy = iStrategy;
    }

    public void contextMethod(){
        // 调用策略实现的算法 ChenQi;
        iStrategy.algorithmMethod();
    }
}
