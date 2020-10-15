package cp13_2;


import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * 构建表示图的邻接表
 * 使用 红黑树 treeset
 */
//无权图，支持有向，无向
public class Graph implements Cloneable {

    private int V;
    private int E;
    private TreeSet<Integer>[] adj;
    private boolean directed;
    private int[] indegree,outdegree;

    public Graph(String filename, boolean directed) {
        this.directed=directed;
        File file = new File(filename);

        try(Scanner scanner = new Scanner(file)){  // 1.8新的语法
            V = scanner.nextInt();
            if(V < 0) {
                throw new IllegalArgumentException("V must be non-negative");
            }
            adj = new TreeSet[V]; //数组
            for (int i=0;i<V;i++){ // 遍历数组的每个值，进行开空间
                adj[i] = new TreeSet<Integer>();// Integer 可省
            }
            indegree=new int[V];
            outdegree=new int[V];

            E = scanner.nextInt();
            if(E < 0) {
                throw new IllegalArgumentException("E must be non-negative");
            }
            for (int i = 0; i< E; i++) {
                int a = scanner.nextInt();
                validateVertex(a);
                int b = scanner.nextInt();
                validateVertex(b);

                if (a == b ) {
                    throw new IllegalArgumentException("Self Loop is Detected!");// 简单图，不处理自环边
                }
                if (adj[a].contains(b)) {
                    throw new IllegalArgumentException("Parallel Edges are Detected!");// 简单图，不处理平行边
                }
                adj[a].add(b);
                if(directed){
                    outdegree[a] ++;
                    indegree[b]++;

                }
                if(!directed){ //无向图时
                    adj[b].add(a);
                }

            }
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Graph(String filename){
        this(filename,false);//默认，让其创建的是无向图
    }

    public Graph(TreeSet<Integer>[] adj,boolean directed){
        this.adj=adj;
        this.directed=directed;
        this.V=adj.length;
        this.E=0;
        indegree = new int[V];
        outdegree =new int[V];
        for(int v=0;v<V;v++){
            for(int w:adj[v]){
                outdegree[v]++;
                indegree[w]++;
                this.E ++;
            }
        }

        if(!directed){
            this.E /= 2;
        }

    }

    public Graph reverseGraph(){
        TreeSet<Integer>[] rAdj = new TreeSet[V];
        for(int i=0;i<V; i++){
            rAdj[i] = new TreeSet<>();
        }
        for(int v=0;v<V;v++){
            for(int w:adj(v)){
                rAdj[w].add(v);
            }
        }

        return new Graph(rAdj,directed);

    }

    public boolean isDirected(){
        return directed;
    }


    public void validateVertex(int v) {
        if (v<0 || v>=V) {
            throw new IllegalArgumentException("vertex " + v + " is invalid");
        }
    }


    public int V(){
        return V;
    }


    public int E(){
        return E;
    }


    public  boolean hasEdge(int v,int w){
        validateVertex(v);
        validateVertex(w);
        return adj[v].contains(w) ;
    }

    /**
     * 返回顶点的所有相邻边
     * @param v
     * @return
     */

    public Iterable<Integer> adj(int v){
        validateVertex(v);
        return adj[v];
    }

    // 求顶点的度
    public int degree(int v){
        if(directed){
            throw new RuntimeException("degree only works in undirected graph.");
        }
        validateVertex(v);
        return adj[v].size();
    }

    public int indegree(int v){
        if(!directed){
            throw new RuntimeException("indegree only works in directed graph.");
        }
        validateVertex(v);
        return indegree[v];
    }

    public int outdegree(int v){
        if(!directed){
            throw new RuntimeException("outdegree only works in directed graph.");
        }
        validateVertex(v);
        return outdegree[v];
    }



    public void removeEdge(int v,int w){
        validateVertex(v);
        validateVertex(w);
        if(adj[v].contains(w)){
            E--;
            if(directed){
                outdegree[v]--;
                indegree[w]--;
            }
        }

        adj[v].remove(w);
        if(!directed){
            adj[w].remove(v);
        }

    }

    @Override
    public Object clone(){
        try{
            Graph cloned = (Graph) super.clone();
            cloned.adj = new TreeSet[V];
            for(int v=0;v<V;v++){
                cloned.adj[v]=new TreeSet<>();
                for(int w:this.adj[v]){
                    cloned.adj[v].add(w);
                }
            }
            return cloned;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V = %d, E = %d，directed=%b \n",V,E,directed));
        for(int v=0; v< V; v++){
            sb.append(String.format("%d : ",v));
            for(int w: adj[v]){
                sb.append(String.format("%d ",w));
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void main(String[] args){
        Graph g = new Graph("src/cp13/ug.txt",true);
        System.out.println(g);

        for(int v=0;v<g.V();v++){
            System.out.println(g.indegree(v)+" "+g.outdegree(v));
        }

    }

}
