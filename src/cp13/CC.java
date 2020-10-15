package cp13;



import java.util.ArrayList;

public class CC {

    private Graph G;
    private int[] visited;
    private int ccCount = 0;

    public CC(Graph G){
        this.G=G;
        this.visited = new int[G.V()];//按顶点个数开空间
        for(int i=0; i<visited.length;i++){
            visited[i] = -1;
        }

//        dfs(0); //  存在多个联调分量时会导致遍历未完成
        for(int v=0;v<this.G.V();v++){
            if(this.visited[v] == -1){
                dfs(v,this.ccCount);
                this.ccCount++;
            }
        }

    }

    /**
     * 深度优先遍历主方法
     * @param v
     */
    private void dfs(int v,int ccid){
        this.visited[v]=ccid;
        for(int w: G.adj(v)){
            if(this.visited[w] == -1){
                dfs(w,ccid);
            }
        }
    }
    public int getCcCount(){
        return this.ccCount;
    }

    public ArrayList<Integer>[] components(){
        ArrayList<Integer>[] res = new ArrayList[this.ccCount];
        for(int i=0; i<ccCount; i++){
            res[i] =new ArrayList<>();
        }

        for(int v=0;v<G.V();v++){
            res[visited[v]].add(v);
        }
        return res;
    }

    public boolean isConnected(int v,int w){
        this.G.validateVertex(v);
        this.G.validateVertex(w);
        return visited[v] == visited[w];
    }



}
