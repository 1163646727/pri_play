package com.pri.strategy.demo_2.version_3;

import java.math.BigDecimal;

/**
 * className:  VIPCustomerQuoteStrategy <BR>
 * description: VIP客户报价策略<BR>
 * remark: 具体策略的实现<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-11-11 14:22 <BR>
 */
public class VIPCustomerQuoteStrategy implements IQuoteStrategy {

    /**
     * methodName: getPrice <BR>
     * description: 获取折后价的价 <BR>
     * remark: 具体算法的实 <BR>
     * param: originalPrice <BR>
     * return: java.math.BigDecimal <BR>
     * author: ChenQi <BR>
     * createDate: 2019-11-11 14:22 <BR>
     */
    @Override
    public BigDecimal getPrice(BigDecimal originalPrice) {
        System.out.println("恭喜！VIP客户享有8折优惠！");
        originalPrice = originalPrice.multiply(new BigDecimal(0.8)).setScale(2,BigDecimal.ROUND_HALF_UP);
        return originalPrice;
    }
}
