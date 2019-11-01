/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.pri.controller;


import com.pri.annotation.ExtApiIdempotent;
import com.pri.annotation.ExtApiToken;
import com.pri.dao.OrderMapper;
import com.pri.entity.OrderEntity;
import com.pri.utils.ConstantUtils;
import com.pri.utils.RedisToken;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * className:  OrderPageController <BR>
 * description: <BR>
 * remark:参考：每特教育 <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-11-01 10:54 <BR>
 */
@Controller
public class OrderPageController {
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private RedisToken redisToken;

	@RequestMapping("/indexPage")
	@ExtApiToken
	public String indexPage(HttpServletRequest req) {
		return "indexPage";
	}

	@RequestMapping("/addOrderPage")
	@ExtApiIdempotent(type = ConstantUtils.EXTAPIFROM)
	public String addOrder(OrderEntity orderEntity) {
		int addOrder = orderMapper.addOrder(orderEntity);
		return addOrder > 0 ? "success" : "fail";
	}

}
