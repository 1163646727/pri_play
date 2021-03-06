package com.pri.strategy.demo_2.version_1;

import java.math.BigDecimal;

/**
 * className:  QuoteManager <BR>
 * description: 报价管理<BR>
 * remark: 现实生活中我们到商场买东西的时候，卖场往往根据不同的客户制定不同的报价策略，<BR>
 * 比如针对新客户不打折扣，针对老客户打9折，针对VIP客户打8折...<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-11-11 13:17 <BR>
 */
public class QuoteManager {
    /**
     * methodName: quote <BR>
     * description: 报价方法<BR>
     * remark: <BR>
     * param: originalPrice 原始价格<BR>
     * param: customType 客户类型<BR>
     * return: java.math.BigDecimal <BR>
     * author: ChenQi <BR>
     * createDate: 2019-11-11 13:20 <BR>
     */
    public BigDecimal quote(BigDecimal originalPrice,String customType){
        if ("新客户".equals(customType)) {
            System.out.println("抱歉！新客户没有折扣！");
            return originalPrice;
        } else if("老客户".equals(customType)){
            System.out.println("恭喜你！老客户打9折！");
            originalPrice = originalPrice.multiply(new BigDecimal(0.9)).setScale(2,BigDecimal.ROUND_HALF_UP);
            return originalPrice;
        }else if("VIP客户".equals(customType)){
            System.out.println("恭喜你！VIP客户打8折！");
            originalPrice = originalPrice.multiply(new BigDecimal(0.8)).setScale(2,BigDecimal.ROUND_HALF_UP);
            return originalPrice;
        }
        return originalPrice;
    }

    /** 测试 ChenQi */
    public static void main(String[] args) {
        QuoteManager quoteManager = new QuoteManager();
        quoteManager.quote(new BigDecimal(100),"VIP客户");
    }
}
