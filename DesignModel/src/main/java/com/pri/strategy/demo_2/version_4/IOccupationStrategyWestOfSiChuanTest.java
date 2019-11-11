package com.pri.strategy.demo_2.version_4;

/**
 * className:  IOccupationStrategyWestOfSiChuanTest <BR>
 * description: 测试<BR>
 * remark: <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-11-11 19:34 <BR>
 */
public class IOccupationStrategyWestOfSiChuanTest {
    public static void main(String[] args) {
        // 创建上下文 ChenQi;
        OccupationContext context = new OccupationContext();
        context.occupationWestOfSichuan("速速拿下西川！");
        System.out.println("=========================");
        context.occupationWestOfSichuan("速速拿下西川，赏黄金万两！");
    }
}
