package jdk8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 排序
 * 方法	描述
 * sorted()	产生一个新流，其中按自然顺序排序
 * sorted(Comparator comp)	产生一个新流，其中按比较器顺序排序
 */
public class TestStream4_sorted {

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

    // sorted():自然排序
    public static void test1(){
        List<String> list =Arrays.asList("ab","bv","ed","cd","xc","fg");
        list.stream().sorted().forEach(System.out::println);

    }

    //sorted(Comparator com)---定制排序（Comparator)
    public static void test2(){
        animals.stream().sorted((e1,e2)->{
            if(e1.getTypeName().equals(e2.getTypeName())){
                return e1.getaColor().compareTo(e2.getaColor());
            } else{
                return e1.getaColor().compareTo(e2.getaColor());
            }
        }).forEach(System.out::println);

    }


    public static void main(String[] args) {
//        test1();

        test2();

    }


}
