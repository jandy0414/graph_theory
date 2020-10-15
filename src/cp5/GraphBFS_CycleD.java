package cp5;

import cp2.AdjSet;
import cp2.Graph;
import cp4.CycleDetection2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GraphBFS_CycleD {

    private Graph G;
    private boolean[] visited;
    private boolean hasCycle=false;
    private int[] parent;

    public GraphBFS_CycleD(Graph G){
        this.G=G;
        this.visited = new boolean[G.V()];//按顶点个数开空间
        this.parent = new int[G.V()];
        for(int v=0;v<this.G.V();v++){
            this.parent[v]=-1;
        }

        for(int v=0;v<this.G.V();v++){
            if(!this.visited[v]){
                 hasCycle=bfs(v);
                 if(hasCycle){
                     break;
                 }
            }
        }

    }

    /**
     * 广度优先遍历主方法
     */
    private boolean bfs(int s) {
        Queue<Integer> queue =new LinkedList<>();
        queue.add(s);
        parent[s]=s;
        this.visited[s]=true;
        while(!queue.isEmpty()){
            int v = queue.poll();
            for(int w: G.adj(v)){
                if(!visited[w]){
                    queue.add(w);
                    parent[w]=v;
                    visited[w]=true;
                } else if(w!=parent[v]){
                    return true;
                }
            }
        }
        return false;
    }




    public static void main(String[] args) {
        Graph g = new AdjSet("g.txt");
        GraphBFS_CycleD graphDFS = new GraphBFS_CycleD(g);
        System.out.println(graphDFS.hasCycle);

        Graph g2 = new AdjSet("g2.txt");
        GraphBFS_CycleD graphDFS2 = new GraphBFS_CycleD(g2);
        System.out.println(graphDFS2.hasCycle);

    }


}
