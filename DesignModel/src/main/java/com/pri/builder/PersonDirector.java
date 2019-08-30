package com.pri.builder;

/**
 * className:  PersonDirector <BR>
 * description: 创建对象的各个部分<BR>
 * remark: 调用具体创建者来创建复杂对象的各个部分，<BR>
 *     不涉及具体产品信息，只负责保证对象各部分完整创建<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-08-30 13:53 <BR>
 */
public class PersonDirector {
    /**
     * methodName: constructPerson <BR>
     * description: 创建对象的各个部分<BR>
     * remark: 调用具体创建者来创建复杂对象的各个部分<BR>
     * param: personBuilder <BR>
     * return: com.pri.builder.Person <BR>
     * author: ChenQi <BR>
     * createDate: 2019-08-30 13:58 <BR>
     */
    public Person constructPerson(PersonBuilder personBuilder){
        personBuilder.builderHead();
        personBuilder.buliderBody();
        personBuilder.builderFoot();
        return personBuilder.BuilderPerson();
    }

    public static void main(String[] args) {
        PersonDirector personDirector = new PersonDirector();
        Person person = personDirector.constructPerson(new ConcreteBuilder());
        System.out.println(person.getHead());
        System.out.println(person.getBody());
        System.out.println(person.getFoot());
    }
}
