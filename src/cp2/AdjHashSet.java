package cp2;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.HashSet;

/**
 * 构建表示图的邻接表
 * 使用 哈希表，优势性能更高，劣势，无序性
 */
public class AdjHashSet implements Graph {

    private int V;
    private int E;
    private HashSet<Integer>[] adj;

    public AdjHashSet(String filename) {
        File file = new File(filename);

        try(Scanner scanner = new Scanner(file)){  // 1.8新的语法
            V = scanner.nextInt();
            if(V < 0) {
                throw new IllegalArgumentException("V must be non-negative");
            }
            adj = new HashSet[V]; //数组
            for (int i=0;i<V;i++){ // 遍历数组的每个值，进行开空间
                adj[i] = new HashSet<Integer>();// Integer 可省
            }

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
                adj[b].add(a);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void validateVertex(int v) {
        if (v<0 || v>=V) {
            throw new IllegalArgumentException("vertex " + v + "is invalid");
        }
    }

    @Override
    public int V(){
        return V;
    }

    @Override
    public int E(){
        return E;
    }

    @Override
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
    @Override
    public Iterable<Integer> adj(int v){
        validateVertex(v);
        return adj[v];
    }

    // 求顶点的有几个边
    @Override
    public int degree(int v){
        return adj[v].size();
    }

    @Override
    public void removeEdge(int v,int w){
        validateVertex(v);
        validateVertex(w);

        adj[v].remove(w);
        adj[w].remove(v);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V = %d, E = %d\n",V,E));
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
        AdjHashSet adjSet = new AdjHashSet("g.txt");
        System.out.println(adjSet);

    }

}
