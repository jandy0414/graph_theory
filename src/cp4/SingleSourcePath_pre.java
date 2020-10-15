package cp4;

import cp2.AdjSet;
import cp2.Graph;

import java.util.ArrayList;
import java.util.Collections;

public class SingleSourcePath_pre {
    private Graph G;
    private int s;
    private int[] pre;//两个含义，一个是否遍历，一个上一个节点的值


    public SingleSourcePath_pre(Graph G, int s){
        G.validateVertex(s);
        this.G = G;
        this.s = s;
        this.pre = new int[G.V()];
        for(int i=0;i<pre.length;i++){
            pre[i]=-1;
        }
        dfs(s,s);//从s开始 ,求解单源路径问题,   自己没有上一个节点，没有，定义为自己（行规）
    }

    private void dfs(int v,int parent){
        pre[v] = parent;
        for(int w: G.adj(v)){
            if(pre[w]==-1){
                dfs(w,v);
            }
        }
    }

    public boolean isConnectedTo(int t){
        G.validateVertex(t);
        return pre[t]!=-1;
    }


    public Iterable<Integer> path(int t){
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
        SingleSourcePath_pre sspath = new SingleSourcePath_pre(g,0);

        System.out.println("0 --> 6: "+sspath.path(6));
        System.out.println("0 --> 5: "+sspath.path(5));
    }
}
