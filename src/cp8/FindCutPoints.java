package cp8;

import cp2.AdjSet;
import cp2.Graph;

import java.util.ArrayList;
import java.util.HashSet;

public class FindCutPoints {
    private Graph G;
    private boolean[] visited;

    private int[] ord;
    private int[] low;
    private int cnt;
    private HashSet<Integer> res;

    public FindCutPoints(Graph G){
        this.G=G;
        visited=new boolean[G.V()];
        ord=new int[G.V()];
        low=new int[G.V()];
        res=new HashSet<>();
        cnt=0;

        for(int v=0;v<G.V();v++){
            if(!visited[v]){
                dfs(v,v);
            }
        }
    }
    private void dfs(int v,int parent){
        visited[v]=true;
        ord[v]=cnt;
        low[v]=ord[v];
        cnt++;

        int firstCnt=0;
        for(int w:G.adj(v)){
            if(!visited[w]){
                firstCnt++;
                dfs(w,v);
                low[v]=Math.min(low[v],low[w]);
                if(low[w] >= ord[v]){ // 等于也是
                    if(v!=parent){//不是根节点
                        res.add(v);
                    }
                }
            } else if(w !=parent){
                low[v]=Math.min(low[v],low[w]);
            }
        }

        if(v==parent && firstCnt>1){
            res.add(v);
        }
    }

    public HashSet<Integer> result(){
        return  res;
    }

    public static void main(String[] args) {
        Graph g=new AdjSet("g8.txt");
        FindCutPoints findBridges=new FindCutPoints(g);
        System.out.println(findBridges.result());

        g=new AdjSet("g82.txt");
        findBridges=new FindCutPoints(g);
        System.out.println(findBridges.result());

        g=new AdjSet("g83.txt");
        findBridges=new FindCutPoints(g);
        System.out.println(findBridges.result());

    }


}
