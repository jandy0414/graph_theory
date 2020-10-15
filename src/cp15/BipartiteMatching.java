package cp15;

public class BipartiteMatching {

    private Graph G;
    private int maxMatching;

    public BipartiteMatching(Graph G){

        BipartitionDetection bd=new BipartitionDetection(G);
        if(!bd.isBinaryGraph()){
            throw new IllegalArgumentException("BipartiteMaching only works for bipartite graph.");
        }
        this.G=G;

        char[] colors=bd.colors();

        // 源点为 V，汇点为 V+1
        WeightedGraph network=new WeightedGraph(G.V()+2,true);
        for(int v=0; v<G.V();v++){
            if(colors[v]=='b'){
                network.addEdge(G.V(),v,1);
            } else {
                network.addEdge(v,G.V()+1,1);
            }

//            for(int w: G.adj(v)){
//                if(v<w){//无向图两方向的边，控制只遍历一次
//                    if(colors[v] == 'b'){
//                        network.addEdge(v,w,1);
//                    } else{
//                        network.addEdge(w,v,1);// 这一步，不理解
//                    }
//                }
//            }

            for(int w: G.adj(v)){
                if(colors[v] == 'b'){
                    network.addEdge(v,w,1);
                }
            }
        }

        MaxFlow maxFlow=new MaxFlow(network,G.V(),G.V()+1);

        maxMatching=maxFlow.result();

    }

    public int getMaxMatching(){
        return maxMatching;
    }

    public boolean isPerfectMatching(){
        return maxMatching*2 == G.V();
    }


    public static void main(String[] args) {
        Graph g=new Graph("15_g.txt");

        BipartiteMatching bm=new BipartiteMatching(g);
        System.out.println(bm.getMaxMatching());
        System.out.println(bm.isPerfectMatching());

        g=new Graph("15_g2.txt");
        bm=new BipartiteMatching(g);
        System.out.println(bm.getMaxMatching());
        System.out.println(bm.isPerfectMatching());

    }


}
