package cp3;

import cp2.AdjList;
import cp2.Graph;

import java.util.ArrayList;

public class GraphDFS_unDG {

    private Graph G;
    private boolean[] visited;
    private ArrayList<Integer> preOrder = new ArrayList<>();
    private ArrayList<Integer> postOrder = new ArrayList<>();

    public GraphDFS_unDG(Graph G){
        this.G=G;
        this.visited = new boolean[G.V()];//按顶点个数开空间
//        dfs(0); //  存在多个联调分量时会导致遍历未完成
        Iterable<Integer>[] adj=new Iterable[this.G.V()];
        for(int v=0;v<this.G.V();v++){
            adj[v]=this.G.adj(v);
        }
        Iterable<Integer> tmpAdj;
        int cccout=0;
        for(int v=0;v<this.G.V();v++){
            if(!this.visited[v]){
                this.visited[v]=true;
                this.preOrder.add(v);
                cccout++;
            }
            tmpAdj=adj[v];
            while (true){
                for(int w:tmpAdj){
                    if(!this.visited[w]){
                        this.visited[w]=true;
                        this.preOrder.add(w);
                        System.out.println(this.preOrder);
//                        tmpAdj=adj[w];
                        break;
                    }
                }
                break;
            }


        }

        System.out.println("cccout:"+cccout);
    }

    /**
     * 深度优先遍历主方法
     * @param v
     */
    private void dfs(int v){
        this.visited[v]=true;
        this.preOrder.add(v);
        for(int w1: G.adj(v)){
            if(!this.visited[w1]){
                this.visited[w1]=true;
                this.preOrder.add(w1);
                for(int w2:G.adj(w1)){
                    this.visited[w2]=true;
                    this.preOrder.add(w2);
                    //...
                    for(int wi:G.adj(w2)){
                        this.visited[wi]=true;
                        this.preOrder.add(wi);
                    }
                }
            }
        }

    }


    public Iterable<Integer> getPreOrder() {
        return this.preOrder;
    }

    public Iterable<Integer> getPostOrder() {return this.postOrder;}

    public static void main(String[] args) {
        Graph g = new AdjList("g.txt");//链表实现
        GraphDFS_unDG graphDFS = new GraphDFS_unDG(g);

        System.out.println("前序："+graphDFS.getPreOrder());
        System.out.println("后序："+graphDFS.getPostOrder());

    }


}
