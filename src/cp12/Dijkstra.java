package src.cp12;

import java.util.Arrays;

public class Dijkstra {

    private WeightedGraph G;
    private int s;  // 源点s
    private int[] dis;
    private boolean[] visited;

    public Dijkstra(WeightedGraph G,int s){

        this.G=G;

        G.validateVertex(s);
        this.s=s;

        dis=new int[G.V()];
        Arrays.fill(dis,Integer.MAX_VALUE);
        dis[s]=0;//从自己到自己距离是0


        visited=new boolean[G.V()];// 有来表示那些顶点是确认了

        while (true){
            //第一件事：找到当前没有访问的最短路节点
            int curdis=Integer.MAX_VALUE;
            int curv=-1;
            for(int v=0;v<G.V();v++){
                if(!visited[v] && dis[v]<curdis){
                    curdis=dis[v];
                    curv=v;
                }
            }

            if(curv ==-1){
                break;
            }

            //第二件事：确认这个节点的最短路就是当前大小
            visited[curv]=true;

            //第三件事：根据这个节点的最短路大小，更新其他节点的路径长度
            for(int w:G.adj(curv)){
                if(!visited[w]){
                    if (dis[curv] + G.getWeight(curv, w) < dis[w]) {
                        dis[w]=dis[curv]+G.getWeight(curv,w);
                    }
                }
            }

        }

    }

    public boolean isConnectedTo(int v){
        G.validateVertex(v);
        return visited[v];
    }

    public int disTo(int v){
        G.validateVertex(v);
        return dis[v];
    }

    public static void main(String[] args) {
        WeightedGraph g=new WeightedGraph("src/cp12/g.txt");
        Dijkstra dijkstra=new Dijkstra(g,0);
        for(int v=0;v<g.V();v++){
            System.out.print(dijkstra.disTo(v)+" ");
        }
        System.out.println();


    }

}
