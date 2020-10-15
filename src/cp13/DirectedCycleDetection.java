package cp13;


public class DirectedCycleDetection {

    private Graph G;
    private boolean[] visited;
    private boolean hasCycle=false;
    private boolean[] onPath;


    public DirectedCycleDetection(Graph G){
        if(!G.isDirected()){
            throw new IllegalArgumentException("DirectedCycleDetection only works in directed graph.");
        }
        this.G=G;
        this.visited = new boolean[G.V()];//按顶点个数开空间
        this.onPath =new boolean[G.V()];
//        dfs(0); //  存在多个联调分量时会导致遍历未完成
        for(int v=0;v<this.G.V();v++){
            if(!this.visited[v]){
                hasCycle=dfs(v,v);
                if(hasCycle){
                    break;
                }
            }
        }

    }

    /**
     * 深度优先遍历主方法
     * @param v
     */
    private boolean dfs(int v,int parent){
        this.visited[v]=true;
        onPath[v]=true;
        for(int w: G.adj(v)){
            if(!visited[w]){
                if(dfs(w,v)){
                    return true;
                };
            } else if(onPath[w]){//在同一个路径
                return true;
            }
        }
        onPath[v]=false;//被退回了
        return false;
    }


    public boolean hasCycle(){

         return this.hasCycle;
    }

    public static void main(String[] args) {
        Graph g = new Graph("src/cp13/ug.txt",true);
        DirectedCycleDetection graphDFS = new DirectedCycleDetection(g);
        System.out.println(graphDFS.hasCycle);

         g = new Graph("src/cp13/ug2.txt",true);
         graphDFS = new DirectedCycleDetection(g);
        System.out.println(graphDFS.hasCycle);





    }


}
