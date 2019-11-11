package com.pri.strategy.demo_2.version_3;

import java.math.BigDecimal;

/**
 * className:  OldCustomerQuoteStrategy <BR>
 * description: 老客户报价策略<BR>
 * remark: 具体策略的实现<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-11-11 14:20 <BR>
 */
public class OldCustomerQuoteStrategy implements IQuoteStrategy{

    /**
     * methodName: getPrice <BR>
     * description: 获取折后价的价 <BR>
     * remark: 具体算法的实现 <BR>
     * param: originalPrice <BR>
     * return: java.math.BigDecimal <BR>
     * author: ChenQi <BR>
     * createDate: 2019-11-11 14:21 <BR>
     */
    @Override
    public BigDecimal getPrice(BigDecimal originalPrice) {
        System.out.println("恭喜！老客户享有9折优惠！");
        originalPrice = originalPrice.multiply(new BigDecimal(0.9)).setScale(2,BigDecimal.ROUND_HALF_UP);
        return originalPrice;
    }
}
