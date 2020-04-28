import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * className: OptionalTest <BR>
 * description: <BR>
 * remark: <BR>
 * auther: ChenQi <BR>
 * date: 2020/4/22 10:40 <BR>
 * version 1.0 jdk1.8 <BR>
 */
public class OptionalTest {
    public static void main(String[] args) {
        // Optional类结合通过三元运算符设置默认值 ChenQi
        Integer value1 = null;
        Integer a = Optional.ofNullable(value1).isPresent() ? value1 :0 ;
        System.out.println("a:"+a);

        // Optional类结合lambda表达式,能够让开发出的代码更简洁和优雅 ChenQi
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list = null;
        Integer c  = Optional.ofNullable(list).map(u -> u.get(0)).orElse(0);
        System.out.println("c:"+c);
    }
}
