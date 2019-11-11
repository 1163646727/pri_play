package com.pri.strategy.demo_2.version_4;

/**
 * className:  LowerStrategy <BR>
 * description: 攻取西川的下下计策<BR>
 * remark: 具体策略的实现<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-11-11 19:53 <BR>
 */
public class LowerStrategy implements IOccupationStrategyWestOfSiChuan {

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
        System.out.println("退还白帝，连引荆州，慢慢进图益州，此为下计。");
    }
}
