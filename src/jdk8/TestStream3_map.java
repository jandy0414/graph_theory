package jdk8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 映射
 * 方法	描述
 * map(Function f)	接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
 * mapToDouble(ToDoubleFunction f)	接收一个函数作为参数，该函数会被应用到每个元 素上，产生一个新的 DoubleStream。
 * mapToInt(ToIntFunction f)	接收一个函数作为参数，该函数会被应用到每个元 素上，产生一个新的 IntStream。
 * mapToInt(ToIntFunction f)	接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的 LongStream。
 * flatMap(Function f)	接收一个函数作为参数，将流中的每个值都换成另 一个流，然后把所有流连接成一个流
 *
 */

public class TestStream3_map {

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

    // map
    public static void test1(){
        animals.stream().map(Animal::getTypeName).forEach(System.out::println);
    }

    //flatMap
    public static void test2(){
        List<String> stringList=Arrays.asList(
                "abc",
                "hhh",
                "wc",
                "UFO"
        );
        //嵌套两层的流
//        Stream<Stream<Character>> result=stringList.stream().map(TestStream3::filterCharacter);
//        //1.遍历操作也要两层，很麻烦
//        result.forEach((s)->s.forEach(System.out::println));

        //2. 使用flatmap
        stringList.stream().flatMap(TestStream3_map::filterCharacter).forEach(System.out::println);

    }

    public static Stream<Character> filterCharacter(String str){
        List<Character> characterList=new ArrayList<>();
        for(Character c:str.toCharArray()){
            characterList.add(c);
        }
        return characterList.stream();
    }

    public static void main(String[] args) {
//        test1();

        test2();

    }


}
