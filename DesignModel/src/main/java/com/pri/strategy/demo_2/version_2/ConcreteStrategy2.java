package com.pri.strategy.demo_2.version_2;

/**
 * className:  ConcreteStrategy2 <BR>
 * description: 具体的策略实现<BR>
 * remark: <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-11-11 13:59 <BR>
 */
public class ConcreteStrategy2 implements IStrategy {

    /**
     * methodName: algorithmMethod <BR>
     * description: 具体算法实现<BR>
     * remark: <BR>
     * param:  <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-11-11 13:59 <BR>
     */
    @Override
    public void algorithmMethod() {
        System.out.println("第二种具体算法实现....");
    }
}
