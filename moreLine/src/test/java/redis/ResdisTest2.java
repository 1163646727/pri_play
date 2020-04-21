package redis;

import redis.clients.jedis.Jedis;

/**
 * className: ResdisTest2 <BR>
 * description: <BR>
 * remark: <BR>
 * auther: ChenQi <BR>
 * date: 2020/4/21 18:41 <BR>
 * version 1.0 jdk1.8 <BR>
 */
public class ResdisTest2 {
    public static void main(String[] args) {
        Jedis jedis =new Jedis("127.0.0.1",6379);
        // 生成订单号 ChenQi
        for (int i=0;i<100;i++) {
        }
    }
}
