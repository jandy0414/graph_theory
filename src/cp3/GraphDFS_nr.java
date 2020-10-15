package cp3;

import cp2.AdjSet;
import cp2.Graph;

import java.util.ArrayList;
import java.util.Stack;

public class GraphDFS_nr {

    private Graph G;
    private boolean[] visited;
    private ArrayList<Integer> preOrder = new ArrayList<>();
    private ArrayList<Integer> postOrder = new ArrayList<>();
    private Iterable<Integer>[] allAdj;

    public GraphDFS_nr(Graph G){
        this.G=G;
        this.visited = new boolean[G.V()];//按顶点个数开空间
//        dfs(0); //  存在多个联调分量时会导致遍历未完成

        allAdj=new Iterable[this.G.V()];
        for(int v=0;v<this.G.V();v++){
            allAdj[v]=this.G.adj(v);
        }
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
        Stack<Integer> stack=new Stack<>();
        stack.push(v);
        this.visited[v]=true;
        while(!stack.empty()){
            int cur= stack.pop();

            this.preOrder.add(cur);

            for(int w: G.adj(cur)){
                if(!visited[w]){
                    visited[w]=true;
                    stack.push(w);
                }
            }
        }


    }
//    private void dfs(int v){
//        this.visited[v]=true;
////        this.preOrder.add(v);
//        Iterable<Integer> tmpAdj=allAdj[v];
//        while(true){
//            for(int w: tmpAdj)
//            {
//                if(!visited[w])
//                {
//                    visited[w]=true;
//                    this.preOrder.add(w);
////                    tmpAdj=allAdj[w];
//                    break;
//                }
//            }
//            break;
//        }
//    }


    public Iterable<Integer> getPreOrder() {
        return this.preOrder;
    }

    public Iterable<Integer> getPostOrder() {return this.postOrder;}

    public static void main(String[] args) {
        Graph g = new AdjSet("g.txt");
        GraphDFS_nr graphDFS = new GraphDFS_nr(g);

        System.out.println("前序："+graphDFS.getPreOrder());
        System.out.println("后序："+graphDFS.getPostOrder());

    }


}
