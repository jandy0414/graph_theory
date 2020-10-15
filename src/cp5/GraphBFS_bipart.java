package cp5;

import cp2.AdjSet;
import cp2.Graph;
import cp4.BipartitionDetection;

import java.util.LinkedList;
import java.util.Queue;

public class GraphBFS_bipart {

    private Graph G;
    private boolean[] visited;
    private boolean isBipart=false;
    private int[] parent;
    private char[] color;

    public GraphBFS_bipart(Graph G){
        this.G=G;
        this.visited = new boolean[G.V()];//按顶点个数开空间
        this.parent = new int[G.V()];
        this.color = new char[G.V()];

        for(int v=0;v<this.G.V();v++){
            this.parent[v]=-1;
            color[v]='w';
        }

        for(int v=0;v<this.G.V();v++){
            if(!this.visited[v]){
                isBipart=bfs(v);
                 if(!isBipart){
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
        this.color[s]='b';
        this.visited[s]=true;
        while(!queue.isEmpty()){
            int v = queue.poll();
            for(int w: G.adj(v)){
                if(!visited[w]){
                    queue.add(w);
                    visited[w]=true;
                    this.color[w]=this.color[v]=='b'?'g':'b';
                } else if(this.color[w]==this.color[v]){
                    return false;
                }
            }
        }
        return true;
    }


    public boolean isBinaryGraph(){
        return this.isBipart;
    }

    public void printSetColor(){
        for(int i=0;i<this.color.length;i++){
            System.out.print(i+":"+this.color[i]+"  ");
        }
        System.out.println();

    }

    public static void main(String[] args) {
        Graph g = new AdjSet("g3.txt");
        GraphBFS_bipart graphDFS = new GraphBFS_bipart(g);

        System.out.println("是否是二分图："+graphDFS.isBinaryGraph());
        graphDFS.printSetColor();

        Graph g2 = new AdjSet("g2.txt");
        GraphBFS_bipart graphDFS2 = new GraphBFS_bipart(g2);

        System.out.println("是否是二分图："+graphDFS2.isBinaryGraph());
        graphDFS2.printSetColor();

        Graph g4= new AdjSet("g4.txt");
        GraphBFS_bipart graphDFS4 = new GraphBFS_bipart(g4);

        System.out.println("是否是二分图："+graphDFS4.isBinaryGraph());
        graphDFS4.printSetColor();


    }


}
