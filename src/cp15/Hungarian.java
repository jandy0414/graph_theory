package cp15;

import java.util.*;

/**
 * 匈牙利算法实现寻找最大匹配
 */
public class Hungarian {

    private Graph G;
    private int maxMatching=0;
    private int[] matching; //代表匹配，点2与点6匹配，则mathcing[2]=6,matching[6]=2;

    public Hungarian(Graph G){

        BipartitionDetection bd=new BipartitionDetection(G);
        if(!bd.isBinaryGraph()){
            throw new IllegalArgumentException("Hungarian only works for bipartite graph.");
        }
        this.G=G;

        char[] colors=bd.colors();

        matching = new int[G.V()];
        Arrays.fill(matching,-1);

        for(int v=0;v<G.V();v++){
            if(colors[v]=='b' && matching[v] == -1){
                if(bfs(v)){
                    maxMatching ++;
                }
            }
        }
    }

    /**
     * 改进过后的BFS，，终止做了特别处理。
     * @param v
     * @return
     */
    private boolean bfs(int v){
        Queue<Integer> q = new LinkedList<>();
        int[] pre = new int[G.V()];
        Arrays.fill(pre,-1);

        q.add(v);
        pre[v]=v;
        while (!q.isEmpty()){
            int cur = q.remove();
            for(int next:G.adj(cur)){
                if(pre[next] == -1){
                    if(matching[next] !=-1){//是一个之前匹配的点
                        pre[next] = cur;
                        pre[matching[next]]=next;// 由来到二分图左侧
                        q.add(matching[next]);
                    }else{
                        pre[next] = cur;
                        ArrayList<Integer> augPath = getAugPath(pre,v,next);
                        for(int i=0;i<augPath.size();i+=2){
                            /**
                             * 匹配边变非匹配边，非匹配边变匹配边
                             */
                            matching[augPath.get(i)] = augPath.get(i+1);
                            matching[augPath.get(i+1)]=augPath.get(i);
                        }
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private ArrayList<Integer> getAugPath(int[] pre,int start,int end){
        ArrayList<Integer> res=new ArrayList<>();
        int cur=end;
        while(cur != start){
            res.add(cur);
            cur=pre[cur];
        }
        res.add(start);
//        Collections.reverse(res); 可以不用倒过来
        return res;
    }

    public int result(){
        return maxMatching;
    }

    public boolean isPerfectMatching(){
        return maxMatching*2 == G.V();
    }

    public static void main(String[] args) {
        Graph g = new Graph("15_g.txt");
        Hungarian hungarian=new Hungarian(g);

        System.out.println(hungarian.maxMatching);
        System.out.println(hungarian.isPerfectMatching());

         g = new Graph("15_g2.txt");
         hungarian=new Hungarian(g);

        System.out.println(hungarian.maxMatching);
        System.out.println(hungarian.isPerfectMatching());
    }

}
