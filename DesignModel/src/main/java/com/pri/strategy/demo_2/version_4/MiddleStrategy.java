package com.pri.strategy.demo_2.version_4;
/**
 * className:  MiddleStrategy <BR>
 * description: 攻取西川的中计策<BR>
 * remark: 具体策略的实现<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-11-11 19:54 <BR>
 */
public class MiddleStrategy implements IOccupationStrategyWestOfSiChuan {

    /**
     * methodName: occupationWestOfSiChuan <BR>
     * description: 取西川<BR>
     * remark: 具体算法的实现<BR>
     * param: msg <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-11-11 19:27 <BR>
     */
    @Override
    public void occupationWestOfSiChuan(String msg) {
        System.out.println("杨怀、高沛是蜀中名将，手下有精锐部队，"
            + "而且据守关头，我们可以装作要回荆州，引他们轻骑来见，"
            + "可就此将其擒杀，而后进兵成都，此为中计。");
    }
}
