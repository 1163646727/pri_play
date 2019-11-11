package com.pri.strategy.demo_2.version_3;

import java.math.BigDecimal;

/**
 * className:  NewCustomerQuoteStrategy <BR>
 * description: 新客户报价策略 <BR>
 * remark: 具体策略的实现<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-11-11 14:18 <BR>
 */
public class NewCustomerQuoteStrategy implements IQuoteStrategy{

    /**
     * methodName: getPrice <BR>
     * description:获取折后价的价格 <BR>
     * remark: 具体算法的实现<BR>
     * param: originalPrice <BR>
     * return: java.math.BigDecimal <BR>
     * author: ChenQi <BR>
     * createDate: 2019-11-11 14:19 <BR>
     */
    @Override
    public BigDecimal getPrice(BigDecimal originalPrice) {
        System.out.println("抱歉！新客户没有折扣！");
        return originalPrice;
    }
}
