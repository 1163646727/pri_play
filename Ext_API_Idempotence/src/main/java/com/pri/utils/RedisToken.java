/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.pri.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * className:  RedisToken <BR>
 * description: 令牌工具类 <BR>
 * remark: <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-11-01 00:22 <BR>
 */
@Component
public class RedisToken {
	@Autowired
	private BaseRedisService baseRedisService;
	private static final long TOKENTIMEOUT = 60 * 60;

	/**
	 * methodName: getToken <BR>
	 * description: 生成令牌<BR>
	 * remark: <BR>
	 * param:  <BR>
	 * return: java.lang.String <BR>
	 * author: ChenQi <BR>
	 * createDate: 2019-11-01 00:23 <BR>
	 */
	public String getToken() {
		// 生成token 规则保证 临时且唯一 不支持分布式场景 分布式全局ID生成规则
		String token = "token" + UUID.randomUUID();
		// 如何保证token临时 （缓存）使用redis 实现缓存
		baseRedisService.setString(token, token, TOKENTIMEOUT);
		return token;
	}

	/**
	 * methodName: findToken <BR>
	 * description: 查询令牌 <BR>
	 * remark: 1.在调用接口之前生成对应的令牌(Token), 存放在Redis<BR>
	 *     2.调用接口的时候，将该令牌放入的请求头中<BR>
	 *     3.接口获取对应的令牌,如果能够获取该令牌(将当前令牌删除掉) 就直接执行该访问的业务逻辑<BR>
	 *     4.接口获取对应的令牌,如果获取不到该令牌 直接返回请勿重复提交<BR>
	 * param: tokenKey <BR>
	 * return: boolean <BR>
	 * author: ChenQi <BR>
	 * createDate: 2019-11-01 00:24 <BR>
	 */
	public synchronized boolean findToken(String tokenKey) {
		// 3.接口获取对应的令牌,如果能够获取该(从redis获取令牌)令牌(将当前令牌删除掉) 就直接执行该访问的业务逻辑
		String tokenValue = (String) baseRedisService.getString(tokenKey);
		if (StringUtils.isEmpty(tokenValue)) {
			return false;
		}
		// 保证每个接口对应的token 只能访问一次，保证接口幂等性问题
		baseRedisService.delKey(tokenValue);
		return true;
	}
}
