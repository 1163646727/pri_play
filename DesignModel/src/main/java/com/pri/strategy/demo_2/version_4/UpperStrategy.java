package com.pri.strategy.demo_2.version_4;

import java.util.concurrent.Executors;
/**
 * className:  UpperStrategy <BR>
 * description: 攻取西川的上上计策<BR>
 * remark: 具体策略的实现<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-11-11 19:22 <BR>
 */
public class UpperStrategy implements IOccupationStrategyWestOfSiChuan{

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
        if (msg == null || msg.length() < 10) {
            // 上上策失败 ChenQi;
            System.out.println("由于计划泄露，上上计策失败！");
            int i = 100/0;
        }
        System.out.println("挑选精兵，昼夜兼行直接偷袭成都，可以一举而定,此为上计计也!");
    }
}
