package jdk8.interview;


import com.sun.deploy.util.ArrayUtil;

import java.util.*;
import java.util.stream.Collectors;

public class Paper20201027 {

    //给定一系列数据求解，求解数据中第一个大于等于6的数
    // 数据异常时，需要抛出异常
    public Integer getFristDigit6(Integer... nums)throws Exception{
        List<Integer> alist= Arrays.asList(nums);
//        Optional<Integer> res=alist.stream().filter(num->num>=6).findFirst();
        return alist.stream().filter(num->num>=6).findFirst().orElseThrow(()->new Exception("can not find the tar numQ"));
//        return res.get();

    }





    //求解一个字符串中重复次数最多的那个字母
    // 假设给定的字符串不会为空，null
    public char getOftenCharacte(String str){
        List<Character> characterList=new ArrayList<>();
        for(Character c:str.toCharArray()){
            characterList.add(c);
        }
        Map<Character,Long> res=characterList.stream().collect(Collectors.groupingBy(Character::charValue,Collectors.counting()));
        System.out.println(res);
        char out=res.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
        return out;
    }

    public static void main(String[] args) throws Exception{
        Paper20201027 paper=new Paper20201027();
        Integer o=paper.getFristDigit6(3,2,2,8,23,6,7,5,4);
        System.out.println(o);
        char s=paper.getOftenCharacte("faaaaaaadafdakjwerwe");
        System.out.println(s);
    }
}
