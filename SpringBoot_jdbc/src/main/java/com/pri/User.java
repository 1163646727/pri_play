package com.pri;

import java.util.Date;

/**
 * className: User <BR> description: <BR> remark: <BR> auther: ChenQi <BR> date: 2019/10/24 15:19
 * <BR> version 1.0 jdk1.8 <BR>
 */
public class User {
    /**ChenQi 2019/4/28; 小程序用户主键*/
    private Integer id;

    /**ChenQi 2019/4/28; 小程序openid*/
    private String openid;

    /**ChenQi 2019/4/28; 平台id*/
    private String nid;

    /**ChenQi 2019/4/28; 昵称*/
    private String nickname;

    /**ChenQi 2019/4/28; 性别(1男，2女)*/
    private Integer sex;

    /**ChenQi 2019/4/28; 头像*/
    private String headimgurl;

    /**ChenQi 2019/4/28; 状态0.禁用1.正常*/
    private Integer state;

    /**ChenQi 2019/4/28; 创建时间*/
    private Date createdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
