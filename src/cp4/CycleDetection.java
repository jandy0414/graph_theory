package cp4;

import cp2.AdjSet;
import cp2.Graph;

public class CycleDetection {

    private Graph G;
    private boolean[] visited;
    private boolean hasCycle=false;


    public CycleDetection(Graph G){
        this.G=G;
        this.visited = new boolean[G.V()];//按顶点个数开空间
//        dfs(0); //  存在多个联调分量时会导致遍历未完成
        for(int v=0;v<this.G.V();v++){
            if(!this.visited[v]){
                dfs(v,v);
            }
        }

    }

    /**
     * 深度优先遍历主方法
     * @param v
     */
    private void dfs(int v,int parent){
        this.visited[v]=true;

        for(int w: G.adj(v)){
            if(!visited[w]){
                dfs(w,v);
            } else if(w!=parent){
                this.hasCycle=true;
            }
        }

    }


    public boolean hasCycle(){

         return this.hasCycle;
    }

    public static void main(String[] args) {
        Graph g = new AdjSet("g.txt");
        CycleDetection graphDFS = new CycleDetection(g);
        System.out.println(graphDFS.hasCycle);

        Graph g2 = new AdjSet("g2.txt");
        CycleDetection graphDFS2 = new CycleDetection(g2);
        System.out.println(graphDFS2.hasCycle);



    }


}
