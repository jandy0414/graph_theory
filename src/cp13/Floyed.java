package cp13;

import java.util.Arrays;

public class Floyed {
    private WeightedGraph G;
    private int[][] dis;
    private int V;
    private boolean hasNegativeCycle=false;
//    private int[] pre;

    public Floyed(WeightedGraph G){
        this.G=G;
        V=G.V();
//
//        pre=new int[V];
//        Arrays.fill(pre,-1);

        dis=new int[V][V];
        for(int i=0;i<V;i++){
            Arrays.fill(dis[i],Integer.MAX_VALUE);
        }

        for(int v=0;v<V;v++){
            dis[v][v]=0;//自己到自己为0
            for(int w:G.adj(v)){
                dis[v][w]=G.getWeight(v,w);
            }
        }

        //floyed
        for(int t=0;t<V;t++){
            for(int v=0;v<V;v++){
                for(int w=0;w<V;w++){
                    if(dis[v][t] != Integer.MAX_VALUE && //防止Interger最大值溢出
                            dis[t][w] !=Integer.MAX_VALUE &&
                            dis[v][t]+dis[t][w] <dis[v][w]){
                        dis[v][w]=dis[v][t]+dis[t][w];
//                        pre[w]=v;
                    }
                }
            }
        }

        //判断是否有负权环
        for(int v=0;v<V;v++){
            if(dis[v][v]<0){
                hasNegativeCycle=true;
                break;
            }
        }


    }

    public boolean isHasNegativeCycle(){
        return hasNegativeCycle;
    }

    public boolean isConnectedTo(int v,int w){
        G.validateVertex(v);
        G.validateVertex(w);
        return dis[v][w] != Integer.MAX_VALUE;
    }

    public int distTo(int v,int w){
        G.validateVertex(v);
        G.validateVertex(w);
        if(hasNegativeCycle){
            throw new RuntimeException("exist Negative cycle.");
        }
        return dis[v][w];
    }

//    public Iterable<Integer> path(int v,int t){
//        ArrayList<Integer> res=new ArrayList<>();
//        if(!isConnectedTo(v,t)){
//            return res;
//        }
//
//        int cur=t;
//        while (cur !=v){
//            res.add(cur);
//            cur=pre[cur];
//        }
//        res.add(v);
//        Collections.reverse(res);
//        return res;
//    }
    public static void main(String[] args) {
        WeightedGraph g = new WeightedGraph("src/cp13/wg2.txt",true);
        Floyed floyed = new Floyed(g);
        if (!floyed.isHasNegativeCycle()) {
            for (int v = 0; v < g.V(); v++) {
                for(int w=0;w<g.V();w++){
                    System.out.print(floyed.distTo(v,w) + " ");
                }
                System.out.println();
            }

//            System.out.println(floyed.path(0,3));
        } else {
            System.out.println("exist negative cycle.");
        }

         g = new WeightedGraph("src/cp13/wg3.txt",true);
         floyed = new Floyed(g);
        if (!floyed.isHasNegativeCycle()) {
            for (int v = 0; v < g.V(); v++) {
                for(int w=0;w<g.V();w++){
                    System.out.print(floyed.distTo(v,w) + " ");
                }
                System.out.println();
            }

//            System.out.println(floyed.path(0,3));
        } else {
            System.out.println("exist negative cycle.");
        }
    }

}
