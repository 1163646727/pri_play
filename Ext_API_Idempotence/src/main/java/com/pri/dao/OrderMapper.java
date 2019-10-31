package com.pri.dao;

import com.pri.entity.OrderEntity;
import org.apache.ibatis.annotations.Insert;

/**
 * className:  OrderMapper <BR>
 * description: <BR>
 * remark: 参考文献：每特教育 <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-10-31 23:58 <BR>
 */
public interface OrderMapper {
	@Insert("insert order_info values (null,#{orderName},#{orderDes})")
	public int addOrder(OrderEntity OrderEntity);
}
