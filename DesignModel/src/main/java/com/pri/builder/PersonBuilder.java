package com.pri.builder;

/**
 * interfaceName:  PersonBuilder <BR>
 * description: 抽象建造者<BR>
 * remark: 为创建一个产品的各个部件指定了标准，<BR>
 *      规定了要创建复杂对象需要创建哪些部分，<BR>
 *      但不涉及具体的创建<BR>
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
