package com.pri.test.singleness;

/**
 * className:  LazyDemno <BR>
 * description: 懒汉式实例<BR>
 * remark: 类初始化时，不会创建该对象，在正在需要使用的时候，<BR>
 *     判断如果对象为空，就创建<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-08-28 15:58 <BR>
 */
public class LazyDemno {
    // 类初始化时，不会创建ChenQi;
    private static LazyDemno lazyDemno;
    /**
     * methodName: LazyDemno <BR>
     * description: 构造函数<BR>
     * remark: 私有化<BR>
     * param:  <BR>
     * return:  <BR>
     * author: ChenQi <BR>
     * createDate: 2019-08-28 16:02 <BR>
     */
    private LazyDemno(){
        System.out.println("LazyDemno初始化");
    }
    /**
     * methodName: getInstance <BR>
     * description: 获取实例 <BR>
     * remark: 判断，如果对象为空，就实例化 <BR>
     *     否则直接返回对象
     * param:  <BR>
     * return: com.pri.test.singleness.LazyDemno <BR>
     * author: ChenQi <BR>
     * createDate: 2019-08-28 16:04 <BR>
     */
    public synchronized static LazyDemno getInstance(){
        // 判断，如果对象为空，就实例化，ChenQi;
        if (lazyDemno == null) {
            lazyDemno = new LazyDemno();
        }
        return lazyDemno;
    }

    public static void main(String[] args) {
        LazyDemno lazyDemno1 = LazyDemno.getInstance();
        LazyDemno lazyDemno2 = LazyDemno.getInstance();
        System.out.println(lazyDemno1 == lazyDemno2);
    }
}
