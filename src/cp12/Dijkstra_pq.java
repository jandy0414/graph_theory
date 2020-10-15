package src.cp12;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;


public class Dijkstra_pq {

    private WeightedGraph G;
    private int s;  // 源点s
    private int[] dis;
    private boolean[] visited;
    private int[] pre;

    private class Node implements Comparable<Node>{
        public int v,dis;
        public Node(int v,int dis){
            this.v=v;
            this.dis=dis;
        }

        @Override
        public int compareTo(Node another){
            return dis-another.dis;
        }

    }


    public Dijkstra_pq(WeightedGraph G, int s){

        this.G=G;

        G.validateVertex(s);
        this.s=s;



        dis=new int[G.V()];
        Arrays.fill(dis,Integer.MAX_VALUE);
        dis[s]=0;//从自己到自己距离是0

        pre=new int[G.V()];
        Arrays.fill(pre,-1);
        pre[s]=s;


        visited=new boolean[G.V()];// 有来表示那些顶点是确认了

        PriorityQueue<Node> pq =new PriorityQueue<Node>();// 优先队列 priopityQueue默认是最小堆
        pq.add(new Node(s,0));
        while (!pq.isEmpty()){
            //第一件事：找到当前没有访问的最短路节点
            int curv=pq.remove().v;
            if(visited[curv]){//因为有可能存在多份，所以要判断一下，去重
                continue;
            }

            //第二件事：确认这个节点的最短路就是当前大小
            visited[curv]=true;

            //第三件事：根据这个节点的最短路大小，更新其他节点的路径长度
            for(int w:G.adj(curv)){
                if(!visited[w]){
                    if (dis[curv] + G.getWeight(curv, w) < dis[w]) {
                        dis[w]=dis[curv]+G.getWeight(curv,w);
                        pq.add(new Node(w,dis[w]));//差强人意的是，同一个节点有可能存多份
                        pre[w]=curv;
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
        WeightedGraph g=new WeightedGraph("src/cp12/g.txt");
        Dijkstra_pq dijkstra=new Dijkstra_pq(g,0);
        for(int v=0;v<g.V();v++){
            System.out.print(dijkstra.disTo(v)+" ");
        }
        System.out.println();

        System.out.println(dijkstra.path(3));
        System.out.println(dijkstra.path(4));


    }

}
