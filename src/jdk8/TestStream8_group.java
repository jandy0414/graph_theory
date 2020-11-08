package jdk8;


import com.sun.org.apache.xpath.internal.objects.XString;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.w3c.dom.ls.LSOutput;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import jdk8.bean.User;

public class TestStream8_group {
    class UserDto{
        private long id;
        private String username;

        public UserDto(long id, String username) {
            this.id = id;
            this.username = username;
        }
    }

    private   List<User> userList = Arrays.asList(
            new User(1L,"zhangsan","张三",30),
            new User(2L,"lisi","张三",32),
            new User(3L,"wangwu","王五",41)
    );

    //平均数
    public void givenUser_average(){
        double age=userList.stream().collect(Collectors.averagingDouble(User::getAge));
        System.out.println((30+32+41)/3.0==age);

    }
    //求和
    public void givenUser_sum(){
        double age=userList.stream().collect(Collectors.summingInt(User::getAge));
        System.out.println((30+32+41)==age);

    }
    // 一次性取出各值
    public void givenUser_all(){
        DoubleSummaryStatistics stat=userList.stream().collect(Collectors.summarizingDouble(User::getAge));
        System.out.println(stat);//DoubleSummaryStatistics{count=3, sum=103.000000, min=30.000000, average=34.333333, max=41.000000}
    }

    //按10年纬度进行分组
    public void givenUser_groupyByAge(){
        Map<Integer,List<User>> res=userList.stream().collect(Collectors.groupingBy(user -> (int)Math.floor(user.getAge()/10)));
        System.out.println(res);
    }
    //按10年纬度进行分组，并得到DTO的新对象
    public void givenUser_groupyByAge_list(){
        Map<Integer,List<UserDto>> res=userList.stream().collect(Collectors.
                        groupingBy(
                                    user -> (int)Math.floor(user.getAge()/10),
                                    Collectors.mapping(
                                            user-> new UserDto(user.getId(),user.getUsername()),
                                            Collectors.toList()
                                    )
                        ));
        System.out.println(res);
    }


    public static void main(String[] args) {
        TestStream8_group test=new TestStream8_group();
        test.givenUser_average();
        test.givenUser_sum();
        test.givenUser_all();
        test.givenUser_groupyByAge_list();
    }


}
