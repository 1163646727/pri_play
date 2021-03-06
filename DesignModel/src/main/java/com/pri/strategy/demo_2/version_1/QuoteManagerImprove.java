package com.pri.strategy.demo_2.version_1;

import java.math.BigDecimal;

/**
 * className:  QuoteManagerImprove <BR>
 * description: 报价管理改善版<BR>
 * remark: <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-11-11 13:35 <BR>
 */
public class QuoteManagerImprove {
    /**
     * methodName: quote <BR>
     * description: 报价方法 <BR>
     * remark: <BR>
     * param: originalPrice <BR>
     * param: customType <BR>
     * return: java.math.BigDecimal <BR>
     * author: ChenQi <BR>
     * createDate: 2019-11-11 13:37 <BR>
     */
    public BigDecimal quote(BigDecimal originalPrice, String customType){
        if ("新客户".equals(customType)) {
            return this.quoteNewCustomer(originalPrice);
        }else if ("老客户".equals(customType)) {
            return this.quoteOldCustomer(originalPrice);
        }else if("VIP客户".equals(customType)){
            return this.quoteVIPCustomer(originalPrice);
        }
        //其他人员都是原价
        return originalPrice;
    }

    /**
     * methodName: quoteVIPCustomer <BR>
     * description: 对VIP客户的报价算法<BR>
     * remark: <BR>
     * param: originalPrice 原价<BR>
     * return: java.math.BigDecimal 折后<BR>
     * author: ChenQi <BR>
     * createDate: 2019-11-11 13:37 <BR>
     */
    private BigDecimal quoteVIPCustomer(BigDecimal originalPrice) {
        System.out.println("恭喜！VIP客户打8折");
        originalPrice = originalPrice.multiply(new BigDecimal(0.8)).setScale(2,BigDecimal.ROUND_HALF_UP);
        return originalPrice;
    }

    /**
     * methodName: quoteOldCustomer <BR>
     * description: 对老客户的报价算法<BR>
     * remark: <BR>
     * param: originalPrice 原价<BR>
     * return: java.math.BigDecimal 折后<BR>
     * author: ChenQi <BR>
     * createDate: 2019-11-11 13:37 <BR>
     */
    private BigDecimal quoteOldCustomer(BigDecimal originalPrice) {
        System.out.println("恭喜！老客户打9折");
        originalPrice = originalPrice.multiply(new BigDecimal(0.9)).setScale(2,BigDecimal.ROUND_HALF_UP);
        return originalPrice;
    }

    /**
     * methodName: quoteNewCustomer <BR>
     * description: 对新客户的报价算法<BR>
     * remark: <BR>
     * param: originalPrice 原价<BR>
     * return: java.math.BigDecimal 折后<BR>
     * author: ChenQi <BR>
     * createDate: 2019-11-11 13:38 <BR>
     */
    private BigDecimal quoteNewCustomer(BigDecimal originalPrice) {
        System.out.println("抱歉！新客户没有折扣！");
        return originalPrice;
    }
}
