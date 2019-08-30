package com.pri.builder;

/**
 * className:  Person <BR>
 * description: 产品实例(产品角色)<BR>
 * remark: 被建造的复杂对象，包含多个组成部分，<BR>
 *      具体建造者创建该产品的内部表示并定义它的装配过程。<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-08-30 13:26 <BR>
 */
public class Person {
    /**
     * description: 头部
     * author:  ChenQi <BR>
     * createDate:  2019-08-30 13:27  <BR>
     */
    private String head;
    /**
     * description: 身体
     * author:  ChenQi <BR>
     * createDate:  2019-08-30 13:27  <BR>
     */
    private String body;
    /**
     * description: 四肢
     * author:  ChenQi <BR>
     * createDate:  2019-08-30 13:28  <BR>
     */
    private String foot;

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getFoot() {
        return foot;
    }

    public void setFoot(String foot) {
        this.foot = foot;
    }
}
