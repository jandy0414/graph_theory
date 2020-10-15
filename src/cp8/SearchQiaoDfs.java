package cp8;

import cp2.AdjSet;
import cp2.Graph;

import java.util.ArrayList;
import java.util.Collections;

public class SearchQiaoDfs {
    private Graph G;
    private boolean[] visited;
    private int[] order;
    private int[] low;



    public SearchQiaoDfs(Graph G){

        this.G = G;
        this.visited =new boolean[G.V()];
        this.order = new int[G.V()];
        this.low = new int[G.V()];
        for(int i=0;i<G.V();i++){
            order[i]=-1;
            low[i]=-1;
        }
        dfs(0,0,0);//从0节点开始
        System.out.print("order:   ");
        for(int i=0;i<G.V();i++){
            System.out.print(order[i]+" ");
        }
        System.out.println();
        System.out.print("low:     ");
        for(int i=0;i<G.V();i++){
            System.out.print(low[i]+" ");
        }
    }

    private void dfs(int v,int parent,int ord){
        order[v] = ord;
        low[v]=ord;
        visited[v]=true;
        for(int w: G.adj(v)){
            if(!visited[w]){
                ord++;
                dfs(w,v,ord);
                if(low[w]>order[v]){
                    System.out.println(v+"--->>"+w+" is qiao! in for");
                }
            } else if( w!=parent && low[w]<low[v]){
                low[v]=low[w];
            }
        }
        if( low[v]<low[parent]){
            low[parent]=low[v];
        }
        if(low[v]>order[parent]){
            System.out.println(parent+"--->>"+v+" is qiao! dfs");
        }

    }






    public static void main(String[] args) {
        Graph g = new AdjSet("g8.txt");
        SearchQiaoDfs sspath = new SearchQiaoDfs(g);

    }
}
