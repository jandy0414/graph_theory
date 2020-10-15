package cp4;

import cp2.AdjSet;
import cp2.Graph;

public class CycleDetection2 {

    private Graph G;
    private boolean[] visited;
    private boolean hasCycle=false;


    public CycleDetection2(Graph G){
        this.G=G;
        this.visited = new boolean[G.V()];//按顶点个数开空间
//        dfs(0); //  存在多个联调分量时会导致遍历未完成
        for(int v=0;v<this.G.V();v++){
            if(!this.visited[v]){
                hasCycle=dfs(v,v);
                if(hasCycle){
                    break;
                }
            }
        }

    }

    /**
     * 深度优先遍历主方法
     * @param v
     */
    private boolean dfs(int v,int parent){
        this.visited[v]=true;
        for(int w: G.adj(v)){
            if(!visited[w]){
                if(dfs(w,v)){
                    return true;
                };
            } else if(w!=parent){
                return true;
            }
        }
        return false;
    }


    public boolean hasCycle(){

         return this.hasCycle;
    }

    public static void main(String[] args) {
        Graph g = new AdjSet("g.txt");
        CycleDetection2 graphDFS = new CycleDetection2(g);
        System.out.println(graphDFS.hasCycle);

        Graph g2 = new AdjSet("g2.txt");
        CycleDetection2 graphDFS2 = new CycleDetection2(g2);
        System.out.println(graphDFS2.hasCycle);



    }


}
