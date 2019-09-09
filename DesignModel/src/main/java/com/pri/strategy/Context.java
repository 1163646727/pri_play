package com.pri.strategy;

/**
 * className:  Context <BR>
 * description: 策略执行<BR>
 * remark: <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-09 18:55 <BR>
 */
public class Context {
    Strategy strategy;

    public Context(Strategy strategy){
        this.strategy = strategy;
    }
    /**
     * methodName: algorithm <BR>
     * description: 算法方法<BR>
     * remark: <BR>
     * param:  <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-09 18:59 <BR>
     */
    public void algorithm(){
        strategy.algorithm();
    }
}
