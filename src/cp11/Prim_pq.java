package cp11;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Prim_pq {

    private WeightedGraph G;
    private ArrayList<WeightedEdge> mst;

    public Prim_pq(WeightedGraph G){

        this.G = G;
        mst =new ArrayList<>();

        CC cc = new CC(G);
        if(cc.getCcCount()>1)
        {
            return;
        }

        //prim
        boolean[] visited = new boolean[G.V()];
        visited[0] = true;
        Queue pq =new PriorityQueue<WeightedEdge>();// priopityQueue默认是最小堆

        for(int w:G.adj(0)){
            pq.add(new WeightedEdge(0,w,G.getWeight(0,w)));
        }
        while (!pq.isEmpty()){
            WeightedEdge minEdge = (WeightedEdge) pq.remove();
            if(visited[minEdge.getV()] && visited[minEdge.getW()]){
                continue;
            }

            mst.add(minEdge);

            int newv=visited[minEdge.getV()] ?minEdge.getW():minEdge.getV();//不是visited
            visited[newv]=true;
            for(int w:G.adj(newv)){
                if(!visited[w]){
                    pq.add(new WeightedEdge(newv,w,G.getWeight(newv,w)));
                }
            }

        }



    }

    public ArrayList<WeightedEdge> result(){
        return mst;
    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        WeightedGraph g=new WeightedGraph("src/cp11/g.txt");
        Prim_pq prim=new Prim_pq(g);
        System.out.println(prim.result());//prim: [(0--1: 2), (1--2: 1), (0--5: 2), (1--4: 3), (4--3: 1), (3--6: 5)]
        //kruskal: [(1--2: 1), (3--4: 1), (0--1: 2), (0--5: 2), (1--4: 3), (3--6: 5)]
    }

}
