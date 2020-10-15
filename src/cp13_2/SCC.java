package cp13_2;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 求解有向图的 强连通分量！！
 */
public class SCC {

    private Graph G;
    private int[] visited;
    private int sccCount = 0;

    public SCC(Graph G){
        if(!G.isDirected()){
            throw new IllegalArgumentException("SCC only works in directed graph!");
        }


        this.G=G;
        this.visited = new int[G.V()];//按顶点个数开空间
        Arrays.fill(visited,-1);

        GraphDFS dfs=new GraphDFS(G.reverseGraph());
        ArrayList<Integer> order=new ArrayList<>();

        for(int v:dfs.getPostOrder()){
           order.add(v);
        }
        Collections.reverse(order);// 求反图后，的逆

        for(int v:order){
            if(visited[v] == -1){
                dfs(v,sccCount);
                sccCount++;
            }
        }

    }

    private void dfs(int v,int sccid){
        visited[v]=sccid;
        for(int w:G.adj(v)){
            if(visited[w] == -1)
            {
                dfs(w,sccid);
            }
        }
    }

    public int getCcCount(){
        return this.sccCount;
    }

    public ArrayList<Integer>[] components(){
        ArrayList<Integer>[] res = new ArrayList[this.sccCount];
        for(int i=0; i<sccCount; i++){
            res[i] =new ArrayList<>();
        }

        for(int v=0;v<G.V();v++){
            res[visited[v]].add(v);
        }
        return res;
    }

    public boolean isStronglyConnected(int v,int w){
        this.G.validateVertex(v);
        this.G.validateVertex(w);
        return visited[v] == visited[w];
    }


    public static void main(String[] args) {
        Graph g=new Graph("13_2_ug3.txt",true);
        SCC scc=new SCC(g);
        System.out.println(scc.sccCount);

        /**
         * 求解出所有的连通分量
         */
        ArrayList<Integer>[] comp=scc.components();
        for(int sccid=0;sccid< comp.length;sccid++){
            System.out.print(sccid+" : ");
            for(int w:comp[sccid]){
                System.out.print(w+ " ");
            }
            System.out.println();
        }

    }



}
