package cp5;

import cp2.AdjSet;
import cp2.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GraphBFS_CC {

    private Graph G;
    private int[] visited;
    private int ccCount=0;

    public GraphBFS_CC(Graph G){
        this.G=G;
        this.visited = new int[G.V()];//按顶点个数开空间
        for(int v=0;v<this.G.V();v++){
            this.visited[v]=-1;
        }

        for(int v=0;v<this.G.V();v++){
            if(this.visited[v]==-1){
                 bfs(v,this.ccCount);
                 this.ccCount++;
            }
        }

    }

    /**
     * 广度优先遍历主方法
     */
    private void bfs(int s,int ccid) {
        Queue<Integer> queue =new LinkedList<>();
        queue.add(s);
        this.visited[s]=ccid;
        while(!queue.isEmpty()){
            int v = queue.poll();
            for(int w: G.adj(v)){
                if(visited[w]==-1){
                    queue.add(w);
                    visited[w]=ccid;
                }
            }
        }
    }


    public int getCcCount(){
        return this.ccCount;
    }
    public ArrayList<Integer>[] components(){
        ArrayList<Integer>[] res = new ArrayList[this.ccCount];
        for(int i=0; i<ccCount; i++){
            res[i] =new ArrayList<>();
        }

        for(int v=0;v<G.V();v++){
            res[visited[v]].add(v);
        }
        return res;
    }

    public boolean isConnected(int v,int w){
        this.G.validateVertex(v);
        this.G.validateVertex(w);
        return visited[v] == visited[w];
    }



    public static void main(String[] args) {
        Graph g = new AdjSet("g.txt");
        GraphBFS_CC graphBFS = new GraphBFS_CC(g);

        System.out.println("联通分量数："+graphBFS.getCcCount());
        System.out.println(graphBFS.isConnected(0,6));
        System.out.println(graphBFS.isConnected(0,5));
        ArrayList<Integer>[] comp=graphBFS.components();
        for(int ccid=0;ccid<comp.length;ccid++){
            System.out.print(ccid+" : ");
            for(int w: comp[ccid]){
                System.out.print(w + " ");
            }
            System.out.println();
        }


    }


}
