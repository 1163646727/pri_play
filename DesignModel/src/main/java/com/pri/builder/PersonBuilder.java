package com.pri.builder;

/**
 * interfaceName:  PersonBuilder <BR>
 * description: 接口规范<BR>
 * remark: 给出一个抽象接口，以规范产品对象的各个组成部分的创建，<BR>
 *     这个接口规范需要实现对对象的创建的部分，但不涉及具体的创建<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-08-30 13:30 <BR>
 */
public interface PersonBuilder {
    /**
     * description: 创建头部
     * author:  ChenQi <BR>
     * createDate:  2019-08-30 13:35  <BR>
     */
    void builderHead();
    /**
     * description: 创建身体
     * author:  ChenQi <BR>
     * createDate:  2019-08-30 13:35  <BR>
     */
    void buliderBody();
    /**
     * description: 创建脚部
     * author:  ChenQi <BR>
     * createDate:  2019-08-30 13:36  <BR>
     */
    void builderFoot();
    /**
     * description: 组装产品
     * author:  ChenQi <BR>
     * createDate:  2019-08-30 13:37  <BR>
     */
    Person BuilderPerson();
}
