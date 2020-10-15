package cp5;

import cp2.AdjSet;
import cp2.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class UnwSsSpath {

    private Graph G;
    private int s;
    private boolean[] visited;
    private int[] dis;
    private int[] pre;

    public UnwSsSpath(Graph G, int s){
        this.G=G;
        this.s=s;
        this.visited = new boolean[G.V()];//按顶点个数开空间
        pre=new int[G.V()];
        dis=new int[G.V()];
        for(int i=0;i<this.G.V();i++){
            pre[i]=-1;
            dis[i]=-1;
        }

        bfs(s);

    }

    /**
     * 广度优先遍历主方法
     */
    private void bfs(int s) {
        Queue<Integer> queue =new LinkedList<>();
        queue.add(s);
        this.visited[s]=true;
        pre[s]=s;
        dis[s]=0;
        while(!queue.isEmpty()){
            int v = queue.poll();
            for(int w: G.adj(v)){
                if(!visited[w]){
                    queue.add(w);
                    visited[w]=true;
                    pre[w]=v;
                    dis[w]=dis[v]+1;
                }
            }
        }
    }


    public boolean isConnectedTo(int t){
        G.validateVertex(t);
        return visited[t];
    }

    public Iterable<Integer> path(int t){
        ArrayList<Integer> res = new ArrayList<>();
        if(!isConnectedTo(t)) {
            return res;
        }

        int cur =t;
        while(cur != s){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);
        Collections.reverse(res);
        return res;
    }

    public int dis(int t){
        G.validateVertex(t);
        return dis[t];
    }


    public void getPre(){
        for(int w:this.pre){
            System.out.print(w+", ");
        }

    }


    public static void main(String[] args) {
        Graph g = new AdjSet("g.txt");
        UnwSsSpath graphBFS = new UnwSsSpath(g,0);

        System.out.print("前序：");
        graphBFS.getPre();
        System.out.println();

        System.out.println("0到4:"+graphBFS.path(4)+",距离"+graphBFS.dis(4));
        System.out.println("0到6:"+graphBFS.path(6)+",距离"+graphBFS.dis(6));
        System.out.println("0到5:"+graphBFS.path(5)+",距离"+graphBFS.dis(5));



    }


}
