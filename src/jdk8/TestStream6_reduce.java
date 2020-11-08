package jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 规约
 * 方法	描述
 * reduce(T iden, BinaryOperator b)	可以将流中元素反复结合起来，得到一个值。返回 T
 * reduce(BinaryOperator b)	可以将流中元素反复结合起来，得到一个值。 返回 Optional<T>
 * 备注：map 和reduce 的连接通常称为map-reduce 模式，因Google 用它 来进行网络搜索而出名。
 *
 *
 */
public class TestStream6_reduce {

    static List<Animal> animals= Arrays.asList(
            new Animal("黑色","马"),
            new Animal("白色","鸽子"),
            new Animal("红色","朱雀"),
            new Animal("灰色","狼"),
            new Animal("粉色","兔子"),
            new Animal("蓝色","鹦鹉"),
            new Animal("绿色","野鸡"),
            new Animal("红色","朱雀")
    );

    public static void main(String[] args) {
        test1();
        test2();
    }

    // 累计汇总
    public static void test1() {
        List<Integer> integerList = Arrays.asList(1,2,3,4,5,6);

        Integer reduce = integerList.stream()
                .reduce(0, (x, y) -> x + y);

        System.out.println("reduce:"+reduce);
    }

    // 字符串拼接
    public static void test2() {
        //可能为空的时候才会用Optional包裹
        Optional<String> reduce = animals.stream()
                .map((a)-> a.getTypeName())
                .reduce(String::concat);

        System.out.println("reduce:"+reduce.get());
    }

}
