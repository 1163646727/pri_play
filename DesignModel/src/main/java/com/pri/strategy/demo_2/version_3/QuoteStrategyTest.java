package com.pri.strategy.demo_2.version_3;

import java.math.BigDecimal;

/**
 * className:  QuoteStrategyTest <BR>
 * description: 报价策略测试<BR>
 * remark: <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-11-11 14:27 <BR>
 */
public class QuoteStrategyTest {

    public static void main(String[] args) {
        IQuoteStrategy quoteStrategy = new NewCustomerQuoteStrategy();
        QuoteContext quoteContext = new QuoteContext(quoteStrategy);
        /** 调用报价上下文的方法 ChenQi */
        BigDecimal price = quoteContext.getPrice(new BigDecimal(100));
        System.out.println("折扣价为：" +price);

        quoteStrategy = new OldCustomerQuoteStrategy();
        quoteContext = new QuoteContext(quoteStrategy);
        price = quoteContext.getPrice(new BigDecimal(100));
        System.out.println("折扣价为：" +price);

    }
}
