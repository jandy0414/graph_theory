package cp13;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GraphBFS {

    private Graph G;
    private boolean[] visited;
//    private Queue<Integer> queue = new LinkedList<>();
    private ArrayList<Integer> order=new ArrayList<>();

    public GraphBFS(Graph G){
        this.G=G;
        this.visited = new boolean[G.V()];//按顶点个数开空间

        for(int v=0;v<this.G.V();v++){
            if(!this.visited[v]){
                 bfs(v);
            }
        }

    }

    /**
     * 广度优先遍历主方法
     */
    private void bfs(int s) {
        Queue<Integer> queue =new LinkedList<>();
        queue.add(s);
        this.visited[s]=true;
        while(!queue.isEmpty()){
            int v = queue.poll();
            order.add(v);
            for(int w: G.adj(v)){
                if(!visited[w]){
                    queue.add(w);
                    visited[w]=true;
                }
            }
        }
    }


    public Iterable<Integer> getOrder() {
        return this.order;
    }


    public static void main(String[] args) {
        Graph g = new Graph("src/cp13/ug.txt",true);
        GraphBFS graphBFS = new GraphBFS(g);

        System.out.println("顺序："+graphBFS.getOrder());


    }


}
