package com.pri.builder;

/**
 * className:  ConcreteBuilder <BR>
 * description: 具体创建者<BR>
 * remark: <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-08-30 13:44 <BR>
 */
public class ConcreteBuilder implements PersonBuilder {
    private Person person;

    /**
     * methodName: ConcreteBuilder <BR>
     * description: 构造函数<BR>
     * remark: 创建一个Persion，用于调用set方法<BR>
     * param:  <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-08-30 16:50 <BR>
     */
    public ConcreteBuilder(){
        person = new Person();
    }
    @Override
    public void builderHead() {
        person.setHead("创建者头部分");
    }

    @Override
    public void buliderBody() {
        person.setBody("创建者身体部分");
    }

    @Override
    public void builderFoot() {
        person.setFoot("创建者四肢部分");
    }

    @Override
    public Person BuilderPerson() {
        return person;
    }
}
