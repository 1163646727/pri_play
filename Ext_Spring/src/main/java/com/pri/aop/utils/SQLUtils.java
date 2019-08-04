package com.pri.aop.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:  SQLUtils
 * @Description: SQL拼接工具类
 * @Remark: 用于手写mybatis框架，<BR>
 *注：参考每特教育
 * @Author:  ChenQi
 * @CreateDate:  2019/8/3 0003 下午 2:22
 */
public class SQLUtils {
	/**
	 * 
	 * 获取Insert语句后面values 参数信息<br>
	 * 作者: 每特教育-余胜军<br>
	 * 联系方式:QQ644064779|WWW.itmayiedu.com<br>
	 * 
	 * @param sql
	 * @return
	 */
	public static List<String> sqlInsertParameter(String sql) {
		int startIndex = sql.indexOf("values");
		int endIndex = sql.length();
		String substring = sql.substring(startIndex + 6, endIndex).replace("(", "").replace(")", "").replace("#{", "")
				.replace("}", "");
		String[] split = substring.split(",");
		List<String> listArr = new ArrayList<> ();
		for (String string : split) {
			listArr.add(string);
		}
		return listArr;
	}

	/**
	 * 
	 * 获取select 后面where语句 作者: 每特教育-余胜军<br>
	 * 联系方式:QQ644064779|WWW.itmayiedu.com<br>
	 * 
	 * @param sql
	 * @return
	 */
	public static List<String> sqlSelectParameter(String sql) {
		int startIndex = sql.indexOf("where");
		int endIndex = sql.length();
		String substring = sql.substring(startIndex + 5, endIndex);
		String[] split = substring.split("and");
		List<String> listArr = new ArrayList<>();
		for (String string : split) {
			String[] sp2 = string.split("=");
			listArr.add(sp2[0].trim());
		}
		return listArr;
	}

	/**
	 * 将SQL语句的参数替换变为?<br>
	 * 作者: 每特教育-余胜军<br>
	 * 联系方式:QQ644064779|WWW.itmayiedu.com<br>
	 * 
	 * @param sql
	 * @return
	 */
	public static String parameQuestion(String sql, String[] parameterName) {
		for (int i = 0; i < parameterName.length; i++) {
			String string = parameterName[i];
			sql = sql.replace("#{" + string + "}", "?");
		}
		return sql;
	}

	public static String parameQuestion(String sql, List<String> parameterName) {
		for (int i = 0; i < parameterName.size(); i++) {
			String string = parameterName.get(i);
			sql = sql.replace("#{" + string + "}", "?");
		}
		return sql;
	}

	public static void main(String[] args) {

		// String[] sqlParameter = sqlInsertParameter(sql);
		// for (String string : sqlParameter) {
		// System.out.println(string);
		// }

	}
}
