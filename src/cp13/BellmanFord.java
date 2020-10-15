package cp13;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BellmanFord {

    private WeightedGraph G;
    private int s;
    private int[] dis;
    private int V;
    private boolean hasNegativeCycle=false;
    private int[] pre;

    public BellmanFord(WeightedGraph G, int s){
        this.G=G;
        G.validateVertex(s);
        this.V=G.V();

        this.s=s;

        dis=new int[G.V()];
        Arrays.fill(dis,Integer.MAX_VALUE);
        dis[s]=0;
        pre = new int[this.V];
        Arrays.fill(pre,-1);

        for(int pass = 1;pass<V;pass++){

            for(int v=0;v<V;v++){
                for(int w:G.adj(v)){
                    if(dis[v] != Integer.MAX_VALUE && dis[v]+G.getWeight(v,w) < dis[w]){
                        dis[w]=dis[v]+G.getWeight(v,w);
                        pre[w]=v;
                    }
                }
            }
        }

        //验证是否有负权环
        for(int v=0;v<V;v++){
            for(int w:G.adj(v)){
                if(dis[v] != Integer.MAX_VALUE && dis[v]+G.getWeight(v,w) < dis[w]){
                    hasNegativeCycle=true;
                }
            }
        }

    }

    public boolean isHasNegativeCycle(){
        return hasNegativeCycle;
    }

    public boolean isConnectedTo(int v){
        G.validateVertex(v);
        return dis[v] != Integer.MAX_VALUE;
    }

    public int distTo(int v){
        G.validateVertex(v);
        if(hasNegativeCycle){
            throw new RuntimeException("exist Negative cycle.");
        }
        return dis[v];
    }

    public Iterable<Integer> path(int t){
        ArrayList<Integer> res=new ArrayList<>();
        if(!isConnectedTo(t)){
            return res;
        }

        int cur=t;
        while (cur !=s){
            res.add(cur);
            cur=pre[cur];
        }
        res.add(s);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        WeightedGraph g=new WeightedGraph("src/cp13/wg2.txt",true);
        BellmanFord bellmanFord=new BellmanFord(g,0);
        if(!bellmanFord.isHasNegativeCycle()){
            for(int v=0;v<g.V();v++){
                System.out.print(bellmanFord.distTo(v)+" ");
            }
            System.out.println();
            System.out.println(bellmanFord.path(1));
        } else{
            System.out.println("exist negative cycle.");
        }

         g=new WeightedGraph("src/cp13/wg3.txt",true);
         bellmanFord=new BellmanFord(g,0);
        if(!bellmanFord.isHasNegativeCycle()){
            for(int v=0;v<g.V();v++){
                System.out.print(bellmanFord.distTo(v)+" ");
            }
            System.out.println();
        } else{
            System.out.println("exist negative cycle.");
        }
    }


}
