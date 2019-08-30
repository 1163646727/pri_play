 /**
  * className:  Res <BR>
  * description: ThreadLocal测试<BR>
  * remark: <BR>
  * author:  ChenQi <BR>
  * createDate:  2019-08-23 14:39 <BR>
  */
public class Res {
    public static Integer count = 0;
    public static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
        protected Integer initialValue(){
            return 0;
        }
    };
    public Integer getNum(){
        Integer count = threadLocal.get()+1;
        threadLocal.set(count);
        return count;
    };
}
