package cp14;


import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * 构建表示无向带权图的邻接表
 * 使用 红黑树 映射 treeMap
 */
//有权图，（支持有向，无向）
public class WeightedGraph implements Cloneable {

    private int V;
    private int E;
    private TreeMap<Integer,Integer>[] adj;
    private boolean directed;

    public WeightedGraph(String filename){
        this(filename,false);//默认无向图
    }

    public WeightedGraph(int V,boolean directed){
        this.V = V;
        this.directed = directed;
        this.E = 0;

        adj = new TreeMap[V];
        for(int i=0;i<V;i++){
            adj[i]=new TreeMap<>();
        }
    }


    public WeightedGraph(String filename, boolean directed) {
        this.directed=directed;
        File file = new File(filename);

        try(Scanner scanner = new Scanner(file)){  // 1.8新的语法
            V = scanner.nextInt();
            if(V < 0) {
                throw new IllegalArgumentException("V must be non-negative");
            }
            adj = new TreeMap[V]; //数组
            for (int i=0;i<V;i++){ // 遍历数组的每个值，进行开空间
                adj[i] = new TreeMap<>();// Integer 可省
            }

            E=0;
            int e = scanner.nextInt();
            if(e < 0) {
                throw new IllegalArgumentException("E must be non-negative");
            }
            for (int i = 0; i< e; i++) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                int v = scanner.nextInt();

               addEdge(a,b,v);

            }
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addEdge(int a,int b,int v){
        validateVertex(a);
        validateVertex(b);

        if(a==b){
            throw new IllegalArgumentException("Self Loop is Detected!");// 简单图，不处理自环边
        }
        if(adj[a].containsKey(b)){
            throw new IllegalArgumentException("Parallel Edges are Detected!");// 简单图，不处理平行边
        }
        adj[a].put(b,v);
        if(!directed){
            adj[b].put(a,v);
        }

        this.E ++;
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
        return adj[v].containsKey(w) ;
    }

    /**
     * 返回顶点的所有相邻边
     * @param v
     * @return
     */
    public Iterable<Integer> adj(int v){
        validateVertex(v);
        return adj[v].keySet();
    }

    public int getWeight(int v,int w){
        if(hasEdge(v,w)){
            return adj[v].get(w);
        }
        throw new IllegalArgumentException(String.format("No edge %d-%d",v,w));
    }

    public void setWeight(int v,int w,int newWeight){
        if(!hasEdge(v,w)){
            throw new IllegalArgumentException(String.format("No edge %d-%d",v,w));
        }

        adj[v].put(w,newWeight);
        if(!directed){
            adj[w].put(v,newWeight);
        }

    }


    // 求顶点的有几个边
//    public int degree(int v){
//        return adj[v].size();
//    }

    public void removeEdge(int v,int w){
        validateVertex(v);
        validateVertex(w);
        if(adj[v].containsKey(w)){
            E--;
        }

        adj[v].remove(w);
        if(!directed){
            adj[w].remove(v);
        }

    }

    public boolean isDirected(){
        return this.directed;
    }


    @Override
    public Object clone(){
        try{
            WeightedGraph cloned = (WeightedGraph) super.clone();
            cloned.adj = new TreeMap[V];
            for(int v=0;v<V;v++){
                cloned.adj[v]=new TreeMap<>();
                for(Map.Entry<Integer,Integer> entry:this.adj[v].entrySet()){
                    cloned.adj[v].put(entry.getKey(),entry.getValue());
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
        sb.append(String.format("V = %d, E = %d directed=%b \n",V,E,directed));
        for(int v=0; v< V; v++){
            sb.append(String.format("%d : ",v));
            for(Map.Entry<Integer,Integer> entry:this.adj[v].entrySet()){
                sb.append(String.format("(%d:%d) ",entry.getKey(),entry.getValue()));
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void main(String[] args){
        WeightedGraph wg = new WeightedGraph("src/cp13/wg.txt",true);
        System.out.println(wg);

    }

}
