package jdk8;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * 筛选与切片
 * 方法	描述
 * filter(Predicate p)	接收 Lambda ， 从流中排除某些元素。
 * distinct()	筛选，通过流所生成元素的 hashCode() 和 equals() 去 除重复元素
 * limit(long maxSize)	截断流，使其元素不超过给定数量。
 * skip(long n)	跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素 不足 n 个，则返回一个空流。与 limit(n) 互补
 *
 */

public class TestStream2_filter {

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

    // filter 内部迭代Stream完成
    public static void test1(){
        Stream<Animal> animalStream=animals.stream().filter((b) ->b.getTypeName().length()>1);
        animalStream.forEach(System.out::println);
    }

    //传统迭代
    public static void test2() {
        Iterator<Animal> animalIterable=animals.iterator();
        while (animalIterable.hasNext()){
            System.out.println("小动物："+animalIterable.next());
        }
    }

    // limit用法
    public static void test3(){
        animals.stream().filter(a->a.getTypeName().length()>1)
                .limit(2).forEach(System.out::println);
    }

    //skip
    public static void test4(){
        animals.stream()
                .filter(a->a.getTypeName().length()>1)
                .skip(2)
                .forEach(System.out::println);
    }

    //distinct
    public static void test5(){
        animals.stream().distinct().forEach(System.out::println);
    }

    public static void main(String[] args) {
        test1();
//        System.out.println("==============");
////        test2();
//        test3();
//        System.out.println("==============");
//        test4();
        System.out.println("====ff==========");
        test5();

    }


}
