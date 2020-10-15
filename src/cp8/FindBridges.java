package cp8;

import cp2.AdjSet;
import cp2.Graph;

import java.util.ArrayList;

public class FindBridges {
    private Graph G;
    private boolean[] visited;

    private int[] ord;
    private int[] low;
    private int cnt;
    private ArrayList<Edge> res;

    public FindBridges(Graph G){
        this.G=G;
        visited=new boolean[G.V()];
        ord=new int[G.V()];
        low=new int[G.V()];
        res=new ArrayList<>();
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

        for(int w:G.adj(v)){
            if(!visited[w]){
                dfs(w,v);
                low[v]=Math.min(low[v],low[w]);
                if(low[w] > ord[v]){
                    //v --> w 是桥
                    res.add(new Edge(v,w));

                }
            } else if(w !=parent){
                low[v]=Math.min(low[v],low[w]);
            }
        }
    }

    public ArrayList<Edge> result(){
        return  res;
    }

    public static void main(String[] args) {
        Graph g=new AdjSet("g8.txt");
        FindBridges findBridges=new FindBridges(g);
        System.out.println(findBridges.result());

        g=new AdjSet("g82.txt");
        findBridges=new FindBridges(g);
        System.out.println(findBridges.result());

        g=new AdjSet("g83.txt");
        findBridges=new FindBridges(g);
        System.out.println(findBridges.result());

    }


}
