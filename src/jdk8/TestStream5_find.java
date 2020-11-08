package jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 查找与匹配
 * 方法	描述
 * allMatch(Predicate p)	检查是否匹配所有元素
 * anyMatch(Predicate p)	检查是否至少匹配一个元素
 * noneMatch(Predicate p)	检查是否没有匹配所有元素
 * findFirst()	返回第一个元素
 * findAny()	返回当前流中的任意元素
 * count()	返回流中元素总数
 * max(Comparator c)	返回流中最大值
 * min(Comparator c)	返回流中最小值
 * forEach(Consumer c)	内部迭代(使用 Collection 接口需要用户去做迭 代，称为外部迭代。相反，Stream API 使用内部 迭代——它帮你把迭代做了)
 *
 */
public class TestStream5_find {

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

    // allMatch
    public static void test1(){
        boolean match=animals.stream().allMatch(a->a.getaColor().equals("白色"));
        System.out.println("是否都是白色的："+match);

    }

    //anyMatch
    public static void test2(){
        boolean match=animals.stream().anyMatch(a->a.getaColor().equals("白色"));
        System.out.println("是否有白色的："+match);
    }

    //noneMatch
    public static void test3(){
        boolean match=animals.stream().noneMatch(a->a.getaColor().equals("白色"));
        System.out.println("是否 没有 白色的："+match);
    }

    //find first
    public static void test4(){
        Optional<Animal> first = animals.stream().findFirst();
        System.out.println("第一只动物："+first.get());
    }

    //find any
    public static void test5(){
        Optional<Animal> any = animals.stream().findAny();
        System.out.println("任意一只动物："+any.get());
    }

    // count ,max,min
    public static void test6(){
        long count=animals.stream().count();

        System.out.println("count:"+count);

        Optional<Animal> max=animals.stream().max((x1,x2) ->x1.getTypeName().compareTo(x2.getTypeName()));
        System.out.println("max:"+max.get());

        Optional<Animal> min=animals.stream().min((x1,x2) ->x1.getTypeName().compareTo(x2.getTypeName()));
        System.out.println("min:"+min.get());
    }



    public static void main(String[] args) {
//        test1();
//
//        test2();
//        test3();

        test4();
        test5();
        test6();



    }


}
