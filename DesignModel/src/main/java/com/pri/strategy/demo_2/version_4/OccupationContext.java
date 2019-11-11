package com.pri.strategy.demo_2.version_4;

/**
 * className:  OccupationContext <BR>
 * description: 上下文<BR>
 * remark: 攻取西川参谋部，就是上下文啦，由上下文来选择具体的策略<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-11-11 19:29 <BR>
 */
public class OccupationContext {
    /**
     * methodName: occupationWestOfSichuan <BR>
     * description: <BR>
     * remark: 回调具体的策略方法<BR>
     * param: msg <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-11-11 19:42 <BR>
     */
    public void occupationWestOfSichuan(String msg){
        // 创建策略接口，先使用上上策 ChenQi;
        IOccupationStrategyWestOfSiChuan strategy = new UpperStrategy();
        try {
            // 取西川 ChenQi;
            strategy.occupationWestOfSiChuan(msg);
        } catch (Exception e) {
            //  上上策失败了，使用中策ChenQi;
            strategy = new MiddleStrategy();
            // 取西川 ChenQi;
            strategy.occupationWestOfSiChuan(msg);
        }
    }
}
