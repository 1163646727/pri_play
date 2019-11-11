package com.pri.strategy.demo_2.version_3;

import java.math.BigDecimal;

/**
 * className:  IQuoteStrategy <BR>
 * description: 报价策略接口<BR>
 * remark: <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-11-11 14:16 <BR>
 */
public interface IQuoteStrategy {
    /** 获取折后价的价格 ChenQi */
    BigDecimal getPrice(BigDecimal originalPrice);
}
