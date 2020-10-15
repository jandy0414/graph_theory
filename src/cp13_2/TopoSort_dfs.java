package cp13_2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class TopoSort_dfs {

    private Graph G;
    private ArrayList<Integer> res;
    private boolean[] visited;
    private boolean hasCycle = false;

    public TopoSort_dfs(Graph G){

        if(!G.isDirected()){
            throw new IllegalArgumentException("TopoSort only works in directed graph");
        }
        this.G = G;

        res=new ArrayList<>();
        visited=new boolean[G.V()];
        // 先进行环检测
        hasCycle=(new DirectedCycleDetection(G)).hasCycle();
        if(hasCycle){
            return;
        }

        dfs(0);

    }
    private void dfs(int v) {
        visited[v] = true;
        for (int w : G.adj(v)) {
            if (!visited[w]) {
                dfs(w);
            }
        }
        res.add(v);
    }


    public boolean hasCycle(){
        return hasCycle;
    }

    public ArrayList<Integer> result(){
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        Graph g=new Graph("13_2_ug.txt",true);
        TopoSort_dfs topoSort=new TopoSort_dfs(g);
        System.out.println(topoSort.hasCycle);
        System.out.println(topoSort.result());

        g=new Graph("13_2_ug2.txt",true);
         topoSort=new TopoSort_dfs(g);
        System.out.println(topoSort.hasCycle);
        System.out.println(topoSort.result());
    }


}
