package cp11;

import java.util.ArrayList;

public class Prim {

    private WeightedGraph G;
    private ArrayList<WeightedEdge> mst;

    public Prim(WeightedGraph G){

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

        for(int i=1;i<G.V();i++){//V-1次，找这边多条横切边
           WeightedEdge minEdge=new WeightedEdge(-1,-1,Integer.MAX_VALUE);
            for(int v=0;v<G.V();v++){
                if(visited[v]){
                    for(int w:G.adj(v)){
                        if(!visited[w] && G.getWeight(v,w)<minEdge.getWeight()){
                            minEdge = new WeightedEdge(v,w,G.getWeight(v,w));
                        }
                    }
                }
            }
            mst.add(minEdge);
            //需要扩充切分
//            visited[minEdge.getV()]=true;
            visited[minEdge.getW()]=true;
        }


    }

    public ArrayList<WeightedEdge> result(){
        return mst;
    }

    public static void main(String[] args) {
        WeightedGraph g=new WeightedGraph("src/cp11/g.txt");
        Prim prim=new Prim(g);
        System.out.println(prim.result());//prim: [(0--1: 2), (1--2: 1), (0--5: 2), (1--4: 3), (4--3: 1), (3--6: 5)]
        //kruskal: [(1--2: 1), (3--4: 1), (0--1: 2), (0--5: 2), (1--4: 3), (3--6: 5)]
    }

}
