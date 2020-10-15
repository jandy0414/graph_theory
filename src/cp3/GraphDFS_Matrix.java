package cp3;

import cp2.AdjMatrix;
import cp2.Graph;

import java.util.ArrayList;

public class GraphDFS_Matrix {

    private Graph G;
    private boolean[] visited;
    private ArrayList<Integer> preOrder = new ArrayList<>();
    private ArrayList<Integer> postOrder = new ArrayList<>();

    public GraphDFS_Matrix(Graph G){
        this.G=G;
        this.visited = new boolean[G.V()];//按顶点个数开空间
//        dfs(0); //  存在多个联调分量时会导致遍历未完成
        for(int v=0;v<this.G.V();v++){
            if(!this.visited[v]){
                dfs(v);
            }
        }

    }

    /**
     * 深度优先遍历主方法
     * @param v
     */
    private void dfs(int v){
        this.visited[v]=true;
        this.preOrder.add(v);
        for(int w: G.adj(v)){
            if(!visited[w]){
                dfs(w);
            }
        }
        this.postOrder.add(v);
    }


    public Iterable<Integer> getPreOrder() {
        return this.preOrder;
    }

    public Iterable<Integer> getPostOrder() {return this.postOrder;}

    public static void main(String[] args) {
        Graph g = new AdjMatrix("g.txt");
        GraphDFS_Matrix graphDFS = new GraphDFS_Matrix(g);

        System.out.println("前序："+graphDFS.getPreOrder());
        System.out.println("后序："+graphDFS.getPostOrder());

    }


}
