package jdk8;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestStream1 {

    List<User> arrayOfUsers;

    public static void main(String[] args) {
        test1();
        test3();
        test4();
    }

    //1.通过Collection 系列集合提供的stream() 或者 parallelStream
    public static void test1() {
        List<String> list = new ArrayList<>();
        Stream<String> stringStream = list.stream();
    }

//    //2.通过Arrays中静态方法stream()获取数组流
//    public static void test2() {
//        Animal[] animals = new Animal[5];
//        Stream<Animal> animalStream = Arrays.stream(animals);
//    }

    //3.通过stream类中的静态方法of()
    public static void test3() {
        Stream<String> stream = Stream.of("a","b","c");
        boolean existed = stream.anyMatch(s->s.startsWith("a"));
        Optional<String> first= stream.findFirst();

    }

    //4.无限流
    public static void test4() {
        //迭代
        Stream<Integer> integerStream = Stream.iterate(0, (x) -> x+2);
        //生成
        Stream.generate(() -> Math.random()*100);
    }

    public User getGivenUsers() throws Exception{

        return findByUserName("zsan").orElseThrow(()->new Exception("未知用户"));

    }



    private Optional<User> findByUserName(String name){

        return arrayOfUsers.stream().filter(user-> !"zhangsan".equals(name) && user.getUsername().equals(name)).findAny();

    }
}


 class User{
    private String username;

     public String getUsername() {
         return username;
     }

     public void setUsername(String username) {
         this.username = username;
     }
 }