package cp7;

import java.util.*;

public class WaterPuzzle7_4 {
    private int[] pre;
    private int end=-1;

    public WaterPuzzle7_4(){
        Queue<Integer> q=new LinkedList<>();
        boolean[] visied=new boolean[100]; //大概开一个够大的空间
        pre=new int[100];

        q.add(0); //初始两个桶是0
        while (!q.isEmpty()){
            int cur=q.remove();
            int a=cur/10;
            int b=cur%10;
            // max a==5,  max b==3

            //可以达到的状态
            ArrayList<Integer> nexts=getNexts(a,b);

//            ArrayList<Integer> nexts=new ArrayList<>();
//            //todo:nexts
//            nexts.add(5*10 + b);// 有可能重合状态，但visisted会处理掉
//            nexts.add(a*10 + 3);
//            nexts.add(0*10 + b);// add(b)
//            nexts.add(a*10 + 0);// add(a)
//
//            int x=Math.min(a,3-b);
//            nexts.add((a-x)*10+ (b+x)); //把A桶的水倒到B桶
//
//            int y=Math.min(5-a,b);
//            nexts.add((a+y)*10 + (b-y));//把B桶的水倒到A桶



            for(int next:nexts){
                if(!visied[next]){
                    q.add(next);
                    visied[next]=true;
                    pre[next]=cur;

                    if(next/10==4 || next%10==4){
                        end=next;
                        return;
                    }
                }
            }
        }
    }

    private ArrayList<Integer> getNexts(int a,int b){
        ArrayList<Integer> nexts=new ArrayList<>();
        ArrayList<Integer> next1=getPerA(a,b);
        ArrayList<Integer> next2=getPerB(a,b);
        nexts.addAll(next1);
        nexts.addAll(next2);
        return nexts;

    }
    private ArrayList<Integer> getPerA(int a,int b){
        ArrayList<Integer> resA=new ArrayList<>();
        resA.add(5*10+b);
        resA.add(0*10+b);
        int x=Math.min(a,3-b);
        resA.add((a-x)*10+b+x);
        return resA;
    }

    private ArrayList<Integer> getPerB(int a,int b){
        ArrayList<Integer> resB=new ArrayList<>();
        resB.add(a*10+3);
        resB.add(a*10+0);
        int x=Math.min(5-a,b);
        resB.add((a+x)*10+b-x);
        return resB;
    }



    public Iterable<Integer> result(){
        ArrayList<Integer> res=new ArrayList<>();
        if(end==-1){
            return res;
        }

        int cur=end;
        while (cur !=0){
            res.add(cur);
            cur=pre[cur];
        }
        res.add(0);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        System.out.println((new WaterPuzzle7_4()).result());
    }

}
