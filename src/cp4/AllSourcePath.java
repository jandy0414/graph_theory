package cp4;

import cp2.AdjSet;
import cp2.Graph;

import java.util.ArrayList;
import java.util.Collections;

public class AllSourcePath {
    private Graph G;
    private int s;
    private boolean[] visited;
    private int[] pre;


    public AllSourcePath(Graph G){

        this.G = G;

        this.visited = new boolean[G.V()];//按顶点个数开空间
        this.pre = new int[G.V()];
        for(int i=0;i<pre.length;i++){
            pre[i]=-1;
        }

    }

    private void setStartSoucrce(int s){
        G.validateVertex(s);
        this.s = s;
        dfs(s,s);//从s开始 ,求解单源路径问题,   自己没有上一个节点，没有，定义为自己（行规）
    }


    private void dfs(int v,int parent){
        this.visited[v]=true;
        pre[v] = parent;
        for(int w: G.adj(v)){
            if(!visited[w]){
                dfs(w,v);
            }
        }
    }

    public boolean isConnectedTo(int t){
        G.validateVertex(t);
        return visited[t];
    }


    public Iterable<Integer> path(int s,int t){
        this.setStartSoucrce(s);

        ArrayList<Integer> res = new ArrayList<>();
        if(!isConnectedTo(t)){
            return res;
        }
        int cur = t;
        while (cur !=s){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);
        Collections.reverse(res);
        return res;
    }



    public static void main(String[] args) {
        Graph g = new AdjSet("g.txt");
        AllSourcePath sspath = new AllSourcePath(g);

        System.out.println("0 --> 6: "+sspath.path(0,6));
        System.out.println("0 --> 5: "+sspath.path(0,5));
        System.out.println("8 --> 5: "+sspath.path(8,5));
    }
}
