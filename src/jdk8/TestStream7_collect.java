package jdk8;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *收集
 * 方法	描述
 * collect(Collector c)	将流转换为其他形式。接收一个 Collector接口的 实现，用于给Stream中元素做汇总的方法
 *
 */
public class TestStream7_collect {

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
    }


    /*
    想存入什么集合类型自己定义
     */
    public static void test1(){
        System.out.println(animals);
        animals.stream()
                .map((a)-> a.getaColor())
              .collect(Collectors.toList());
//                .collect(Collectors.toSet());
//                .collect(Collectors.toCollection(HashSet::new));
//                .collect(Collectors.toCollection(LinkedHashSet::new));
        System.out.println(animals);
    }
}
