package cp7;

import java.util.*;

public class NonFu7_5 {
    private String[] pre;//1代表出现在目的地，0代表不出现在目的地；第1位代表农夫，第2位代表狼，第3位代表羊，第4位代表菜。
    private String start="0000";//目标地初始为0000,代表还未开始要过河；
    private String end="1111";//目标地为1111依次代表农夫，狼，羊，菜过河了。

    public NonFu7_5(){
        Queue<String> q=new LinkedList<>();
        boolean[] visied=new boolean[16]; //开一个够大的空间
        pre=new String[16];

        q.add(start);
        visied[0]=true;
        while (!q.isEmpty()){
            String cur=q.remove();

            //可以达到的状态
            ArrayList<String> nexts=getNexts(cur);
            for(String next:nexts){
                if(!visied[Str2Int(next)]){
                    q.add(next);
                    visied[Str2Int(next)]=true;
                    pre[Str2Int(next)]=cur;

                    if(next.equals(end)){
                        return;
                    }
                }
            }
        }
    }

    //将字符串转二进制，在转换成int
    private int Str2Int(String str){
        char[] chars=str.toCharArray();
        int out=0;
        for(int i=0;i<chars.length;i++){
            out +=(chars[chars.length-1-i]-'0')<<i;
        }
        return out;
    }

    //取原始地，状态
    private String getOppStr(String str){
        char[] chars=str.toCharArray();
        StringBuffer out=new StringBuffer();
        for(int i=0;i<chars.length;i++){
            out.append(chars[i]=='1'?'0':'1');
        }
        return out.toString();
    }



    //获取下一轮个可能的状态
    // 农夫每次运一个种类过河，或者自己过河
    // 农夫确保目的地，及原始地，不能让狼和羊，或者羊和菜单独留着，也就不能存在 0110（狼吃羊），0011（羊吃菜）,0111(狼吃羊,或者羊吃菜）
    private ArrayList<String> getNexts(String curs){
        ArrayList<String> nexts=new ArrayList<>();
        char[] chars=curs.toCharArray();
        StringBuffer next=new StringBuffer();

        //自己过河
        for(int i=0;i<chars.length;i++){
            if(i==0){
                next.append(chars[i]=='1'?'0':'1');
            } else{
                next.append(chars[i]);
            }

        }
        if(checkTarget(next.toString()) && checkOrig(next.toString())){
            nexts.add(next.toString());
        }

        //自己过河+运其中一个过河
        for(int j=1;j<chars.length;j++){
            next=new StringBuffer();
            for(int i=0;i<chars.length;i++){
                if(i==0){
                    next.append(chars[i]=='1'?'0':'1');
                } else{
                    if(i==j){
                        next.append(chars[i]=='1'?'0':'1');
                    } else{
                        next.append(chars[i]);
                    }

                }

            }
            if(checkTarget(next.toString()) && checkOrig(next.toString())){
                nexts.add(next.toString());
            }
        }

        return nexts;

    }
    private boolean checkTarget(String str){
       if("0110".equals(str)){
           return false; //0110（狼吃羊）
       } else if("0011".equals(str)){
           return false;//0011（羊吃菜）
       } else if("0111".equals(str)){
           return false;//0111(狼吃羊,或者羊吃菜）
       } else{
           return true;
       }
    }

    private boolean checkOrig(String str){
        String ss=getOppStr(str);
        return checkTarget(ss);
    }


    public Iterable<String> result(){
        ArrayList<String> res=new ArrayList<>();

        String curs=end;
        while (!curs.equals(start)){
            res.add(curs);
            curs=pre[Str2Int(curs)];
        }
        res.add(start);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        System.out.println((new NonFu7_5()).result());
        // 输出：[0000, 1010, 0010, 1110, 0100, 1101, 0101, 1111]
    }

}
