package cp15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 匈牙利算法实现寻找最大匹配
 */
public class Hungarian_dfs {

    private Graph G;
    private int maxMatching=0;
    private int[] matching; //代表匹配，点2与点6匹配，则mathcing[2]=6,matching[6]=2;
    private boolean[] visisted;

    public Hungarian_dfs(Graph G){

        BipartitionDetection bd=new BipartitionDetection(G);
        if(!bd.isBinaryGraph()){
            throw new IllegalArgumentException("Hungarian only works for bipartite graph.");
        }
        this.G=G;

        char[] colors=bd.colors();

        matching = new int[G.V()];
        Arrays.fill(matching,-1);
        visisted = new boolean[G.V()];

        for(int v=0;v<G.V();v++){
            if(colors[v]=='b' && matching[v] == -1){
                Arrays.fill(visisted,false);//每一轮都需要设为false
                if(dfs(v)){
                    maxMatching ++;
                }
            }
        }
    }

    /**
     * dfs 实现增广路径查找
     * @param v
     * @return
     */
    private boolean dfs(int v){
        visisted[v]=true;
        for(int u:G.adj(v)){//
            if(!visisted[u]){
                visisted[u]=true;
                if(matching[u]==-1|| dfs(matching[u])){
                    //匹配边 非匹配边互换
                    matching[v]=u;
                    matching[u]=v;
                    return true;
                }
            }
        }
        return false;
    }


    public int result(){
        return maxMatching;
    }

    public boolean isPerfectMatching(){
        return maxMatching*2 == G.V();
    }

    public static void main(String[] args) {
        Graph g = new Graph("15_g.txt");
        Hungarian_dfs hungarian=new Hungarian_dfs(g);

        System.out.println(hungarian.maxMatching);
        System.out.println(hungarian.isPerfectMatching());

         g = new Graph("15_g2.txt");
         hungarian=new Hungarian_dfs(g);

        System.out.println(hungarian.maxMatching);
        System.out.println(hungarian.isPerfectMatching());
    }

}
