package com.pri.api;

import java.sql.ResultSet;

/**
 * interfaceName: ResultSetHandler <BR>
 * description: 结果集处理器接口<BR> remark: <BR> auther: ChenQi <BR> date:
 * 2019/10/24 14:46 <BR> version 1.0 jdk1.8 <BR>
 */
public interface ResultSetHandler {
    public Object handler(ResultSet resultSet);
}
