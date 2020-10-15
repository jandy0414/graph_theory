package cp11;

import java.util.ArrayList;
import java.util.Collections;

public class Kruskal {

    private WeightedGraph G;
    private ArrayList<WeightedEdge> mst;

    public Kruskal(WeightedGraph G){
        this.G=G;
        mst = new ArrayList<WeightedEdge>();

        CC cc =new CC(G);
        if(cc.getCcCount()>1){//不联通，没有最小生成树
            return;
        }

        // Kruskal
        ArrayList<WeightedEdge> edges=new ArrayList<>();
        for(int v=0;v<G.V();v++){
            for(int w:G.adj(v)){
                if(v<w){
                    edges.add(new WeightedEdge(v,w,G.getWeight(v,w)));//获取图中的所有的边
                }
            }
        }

        Collections.sort(edges);//排序

        //判断是否有环
        UF uf=new UF(G.V());
        for(WeightedEdge edge:edges){
            int v = edge.getV();
            int w = edge.getW();

            if(!uf.isConnected(v,w)){
                mst.add(edge);
                uf.unionElemnts(v,w);
            }

        }



    }

    public ArrayList<WeightedEdge> reult(){
        return mst;
    }

    public static void main(String[] args) {
        WeightedGraph g=new WeightedGraph("src/cp11/g.txt");
        Kruskal kruskal=new Kruskal(g);

        System.out.println(kruskal.reult()); //[0--1: 2, 1--2: 1, 0--5: 2, 1--4: 3, 4--3: 1, 3--6: 5]

    }


}
