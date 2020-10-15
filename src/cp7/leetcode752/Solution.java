package cp7.leetcode752;

import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    private int[] visited;

//    private String[] deadends;
    private HashMap<String,String> deadMap;

    private HashSet<Integer>[] G; // 构建图
    public int openLock(String[] deadends, String target) {

        if(target.isEmpty()){
            return -1;
        }
        deadMap=new HashMap<>();
        for(String dd:deadends){
            deadMap.put(dd,dd);
        }
//        this.deadends=deadends;
        if(deadMap.get(target)!=null){
            return -1;
        }


        this.G=this.constructGraph();// 按E 构建图
        visited=new int[10000];
        for(int i=0;i<10000;i++){
            visited[i]=-1;
        }

        int tar=Integer.parseInt(target);
        bfs(0,tar);


        return visited[tar];

    }


    private void bfs(int v,int tar){
        Queue<Integer> queue=new LinkedList<>();
        visited[v]=0;
        queue.add(v);
        while (!queue.isEmpty()){
            int cur=queue.remove();
            for(int w:G[cur]){
                if(visited[w]==-1){
                    visited[w]=visited[cur]+1;
                    queue.add(w);
                    if(w==tar){
                        return;
                    }
                }
            }
        }
    }



    private HashSet<Integer>[] constructGraph(){
        HashSet<Integer>[] g=new HashSet[10000];

        for(int i=0;i<g.length;i++){
            g[i]=new HashSet<>();
        }

        String curS,next;
        String[] nextStr8;
        for(int v=0;v<10000;v++){
            curS=this.intToStr(v);
            if(deadMap.get(curS)==null){
                nextStr8=getNext8Str(curS);

                for(int d=0;d<8;d++){
                    next=nextStr8[d];
                    if(deadMap.get(next)==null){
                        g[Integer.parseInt(curS)].add(Integer.parseInt(next));
                        g[Integer.parseInt(next)].add(Integer.parseInt(curS));
                    }
                }
            }

        }
        return g;
    }
    private String intToStr(Integer s){
        if(s<10){
            return "000"+s.toString();
        } else if(s<100){
            return "00"+s.toString();
        } else if(s<1000){
            return "0"+s.toString();
        } else {
            return s.toString();
        }
    }
    private String[] getNext8Str(String s){
        String[] out=new String[8];

        out[0]=getNextAdd(s.charAt(0))+s.substring(1);
        out[1]=getNextSub(s.charAt(0))+s.substring(1);
        out[2]=s.substring(0,1)+getNextAdd(s.charAt(1))+s.substring(2);
        out[3]=s.substring(0,1)+getNextSub(s.charAt(1))+s.substring(2);
        out[4]=s.substring(0,2)+getNextAdd(s.charAt(2))+s.substring(3);
        out[5]=s.substring(0,2)+getNextSub(s.charAt(2))+s.substring(3);
        out[6]=s.substring(0,3)+getNextAdd(s.charAt(3));
        out[7]=s.substring(0,3)+getNextSub(s.charAt(3));

        return out;
    }
    private String getNextAdd(char c){
        Integer i=c-'0';
        i++;
        if(i==10){
            return "0";
        }else {
            return i.toString();
        }
    }

    private String getNextSub(char c){
        Integer i=c-'0';
        i--;
        if(i==-1){
            return "9";
        }else {
            return i.toString();
        }
    }

//    private boolean strInDeadends(String str){
//        for(String inStr:this.deadends){
//            if(str.equals(inStr)){
//                return true;
//            }
//        }
//        return false;
//    }



    public static void main(String[] args) {
            String s="0000";
            Solution solution=new Solution();

//        String[] input={"0201","0101","0102","1212","2002"};
//        String target="0202";

            String[] input={"4162","4112","8950","5450","2201","6607",
                    "4995","8541","3724","0475","1357","2433","7028",
                    "3271","9666","5861","9028","5562","9637","6900",
                    "7727","5386","3235","3096","9721","9236","1612",
                    "2572","3447","4644","0774","8564","7304","5612",
                    "4593","0926","0132","4162","7982","3872","5738",
                    "8375","2899","9357","6871","7731","5679","8323",
                    "5761","9752","6311","5917","9293","8764","2486",
                    "9156","4879","3016","0327","2940","2951","8787",
                    "5114","0295","8598","5781","7901","4096","8165",
                    "5547","1131","8371","7192","1570","7729","7319",
                    "4348","9924","6183","3592","6539","8650","9974",
                    "2110","1512","5780","6369","0794","4475","5157",
                    "7859","2891","8510","4564","1270","4684","7773",
                    "6543","1657","1468","6273","4873","5113","7760",
                    "5664","9749","1320","6048","7995","0681","0873",
                    "6832","9674","5162","8996","0896","6131","9479",
                    "3467","2327","7239","9197","4467","8564","6972",
                    "1911","9490","5011","6335","2719","7314","4039",
                    "8273","3059","9257","6289","5122","7028","9299",
                    "8047","6845","2052","2898","1903","9301","0284",
                    "1778","2138","4876","8753","0296","8863","1306",
                    "9961","4643","5635","8817","5460","9472","8524",
                    "3511","8767","6017","1029","9442","4938","5802",
                    "1099","2914","5036","5632","1943","3784","1202",
                    "4522","8494","4373","0322","6605","1391","9720",
                    "0086","9489","8644","0898","8316","0028","6081",
                    "6517","4085","3484","6169","4212","4821","4078",
                    "3839","7243","9713","7878","8976","0920","4287","0389",
                    "6741","1934","3010","8333","4805","1009","7029","7468","5319","3557","4071","2689",
                    "7547","0809","8537","3487","7029","8799","3387","7076","1234","3942","4324","8127",
                    "0159","0813","5333","3585","7652","9587","9939","2259","4190","9520","3635","4785",
                    "6520","1393","6769","2912","8860","0284","5437","4202","1619","9054","2389","2247",
                    "6949","8195","1533","9215","1487","0451","1453","2970","8592","5261","8157","9357",
                    "0913","7526","8861","9942","5945","7733","1268","2398","8511","0819","9253","3842",
                    "0979","8448","8235","4156","5261","4270","9997","2596","7500","0066","2026","1125",
                    "5049","2049","2066","4790","5505","6552","2658","1876","0236","4177","9330","0953",
                    "9737","5141","9916","1589","1414","3845","8556","2595","7854","8834","0561","8109",
                    "2973","1528","1217","0588","3951","9702","6137","7013","9594","6481","1565","6266",
                    "8636","7489","3685","0524","8231","1402","1875","4691","7425","9816","3746","6486",
                    "5236","6992","9154","6154","3700","0038","5738","4629","9020","9509","0097","4286",
                    "5674","8566","5726","6846","3160","4917","4935","6200","9078","8307","7544","3494",
                    "3837","9214","6713","1920","6194","9353","9828","8759","9984","6545","8335","6358",
                    "4430","1236","9496","6941","2730","9220","6520","2560","5950","5613","8637","7056",
                    "5658","9097","4443","3100","6648","2059","6595","1145","8867","2525","8664","3074",
                    "2046","1742","6490","8806","5879","8207","8967","4810","2164","1214","0702","3514",
                    "5838","6660","9804","5678","8626","7785","6499","0572","6199","2649","4869","1700",
                    "2884","6156","1336","1255","7734","0027","5131","0591","0485","9950","1505","8162",
                    "8001","6075","1268","3483","8082","3337","0734","5930","6184","3184","7944","8076",
                    "4102","3664","9646","0807","5431","0986","0931","1877","2793","3628","5160","8856",
                    "3692","3569","7698","3564","1023","3987","6237","2615","0008","7838","3850","9047",
                    "9158","5023","4519","3822","2526","8071","8134","2807","0493","5638","9894","5959",
                    "7028","5011","9026","3892","5770","3025","8106","5823","9274","2635","8648","9660",
                    "4834","6390","1401","8160","4471","1832","2219","1495","2512"};
            String target="9003";
            long start=System.currentTimeMillis();
            int out=solution.openLock(input,target);
            System.out.println(out);
            long end=System.currentTimeMillis();
            long use=(end-start);
        System.out.println("用时："+use);
//        String[] out=solution.getNext8Str(s);
//        System.out.println(out);
//        System.out.println(solution.intToStr(0));
//        System.out.println(solution.intToStr(1));
//        System.out.println(solution.intToStr(11));
//        System.out.println(solution.intToStr(666));
//        System.out.println(solution.intToStr(100));


//        Solution solution2=new Solution();
//
////        int[][] input={
////                {0,0,0},
////                {1,1,0},
////                {1,1,0}};
//        int[][] input={
//                {1,0,0},
//                {1,1,0},
//                {1,1,0}};
//
//
//        int out=solution2.shortestPathBinaryMatrix(input);
//        System.out.println(out);
    }

}
