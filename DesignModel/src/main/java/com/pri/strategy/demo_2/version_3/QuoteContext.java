package com.pri.strategy.demo_2.version_3;

import java.math.BigDecimal;

/**
 * className:  QuoteContext <BR>
 * description: 报价策略上下文BR>
 * remark: <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-11-11 14:23 <BR>
 */
public class QuoteContext {
    private IQuoteStrategy iQuoteStrategy;

    /**
     * methodName: QuoteContext <BR>
     * description: 构造函数<BR>
     * remark: 注入报价策略<BR>
     * param: iQuoteStrategy <BR>
     * return:  <BR>
     * author: ChenQi <BR>
     * createDate: 2019-11-11 14:26 <BR>
     */
    public QuoteContext(IQuoteStrategy iQuoteStrategy){
        this.iQuoteStrategy = iQuoteStrategy;
    }

    /**
     * methodName: getPrice <BR>
     * description: 回调具体报价策略的方法 <BR>
     * remark: <BR>
     * param: originalPrice <BR>
     * return: java.math.BigDecimal <BR>
     * author: ChenQi <BR>
     * createDate: 2019-11-11 14:27 <BR>
     */
    public BigDecimal getPrice(BigDecimal originalPrice){
        return iQuoteStrategy.getPrice(originalPrice);
    }
}
