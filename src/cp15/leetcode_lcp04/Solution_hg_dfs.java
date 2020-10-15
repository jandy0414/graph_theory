package cp15.leetcode_lcp04;



import java.util.*;

public class Solution_hg_dfs {
    //无权图，支持有向，无向
    public class Graph implements Cloneable {

        private int V;
        private int E;
        private TreeSet<Integer>[] adj;
        private boolean directed;
        private int[] indegree,outdegree;

        // 构建空图
        public Graph(int V,boolean directed){
            this.V = V;
            this.directed = directed;
            this.E = 0;

            adj = new TreeSet[V];
            for(int i=0;i<V;i++){
                adj[i] = new TreeSet<Integer>();
            }
        }
        public void addEdge(int a,int b){
            validateVertex(a);
            validateVertex(b);

            if(a==b){
                throw new IllegalArgumentException("Self Loop is Detected!");// 简单图，不处理自环边
            }
            if(adj[a].contains(b)){
                throw new IllegalArgumentException("Parallel Edges are Detected!");// 简单图，不处理平行边
            }
            adj[a].add(b);
            if(!directed){
                adj[b].add(a);
            }

            this.E ++;
        }
        public boolean isDirected(){
            return directed;
        }


        public void validateVertex(int v) {
            if (v<0 || v>=V) {
                throw new IllegalArgumentException("vertex " + v + " is invalid");
            }
        }


        public int V(){
            return V;
        }


        public int E(){
            return E;
        }


        public  boolean hasEdge(int v,int w){
            validateVertex(v);
            validateVertex(w);
            return adj[v].contains(w) ;
        }

        /**
         * 返回顶点的所有相邻边
         * @param v
         * @return
         */

        public Iterable<Integer> adj(int v){
            validateVertex(v);
            return adj[v];
        }

        // 求顶点的度
        public int degree(int v){
            if(directed){
                throw new RuntimeException("degree only works in undirected graph.");
            }
            validateVertex(v);
            return adj[v].size();
        }

        public int indegree(int v){
            if(!directed){
                throw new RuntimeException("indegree only works in directed graph.");
            }
            validateVertex(v);
            return indegree[v];
        }

        public int outdegree(int v){
            if(!directed){
                throw new RuntimeException("outdegree only works in directed graph.");
            }
            validateVertex(v);
            return outdegree[v];
        }



        public void removeEdge(int v,int w){
            validateVertex(v);
            validateVertex(w);
            if(adj[v].contains(w)){
                E--;
                if(directed){
                    outdegree[v]--;
                    indegree[w]--;
                }
            }

            adj[v].remove(w);
            if(!directed){
                adj[w].remove(v);
            }

        }

        @Override
        public Object clone(){
            try{
                Graph cloned = (Graph) super.clone();
                cloned.adj = new TreeSet[V];
                for(int v=0;v<V;v++){
                    cloned.adj[v]=new TreeSet<>();
                    for(int w:this.adj[v]){
                        cloned.adj[v].add(w);
                    }
                }
                return cloned;
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("V = %d, E = %d，directed=%b \n",V,E,directed));
            for(int v=0; v< V; v++){
                sb.append(String.format("%d : ",v));
                for(int w: adj[v]){
                    sb.append(String.format("%d ",w));
                }
                sb.append('\n');
            }
            return sb.toString();
        }


    }


    //二分图检测
    public class BipartitionDetection {

        private Graph G;
        private boolean[] visited;
        private char[] color;
        private boolean isBinaryGraph=false;


        public BipartitionDetection(Graph G){
            this.G=G;
            this.visited = new boolean[G.V()];//按顶点个数开空间

            this.color = new char[G.V()];
            for(int i=0;i<color.length;i++){
                color[i]='w';
            }


            for(int v=0;v<G.V();v++){
                if(!visited[v]){
                    isBinaryGraph=dfs(v,v);
                    if(!isBinaryGraph){
                        break;
                    }
                }
            }


        }

        /**
         * 深度优先遍历主方法
         * @param v
         */
        private boolean dfs(int v,int parentId){
            this.visited[v]=true;
            if(v==parentId){
                this.color[v]='b';
            } else {
                this.color[v]=this.color[parentId]=='b'?'g':'b';
            }

            for(int w: G.adj(v)){
                if(!visited[w] ){
                    if(!dfs(w,v)){
                        return false;
                    }
                } else{ // 访问过就是被染色过
                    if(this.color[w]==this.color[v]){
                        return false;
                    }
                }
            }
            return true;
        }


        public boolean isBinaryGraph(){
            return this.isBinaryGraph;
        }

        public void printSetColor(){
            for(int i=0;i<this.color.length;i++){
                System.out.print(i+":"+this.color[i]+"  ");
            }
            System.out.println();

        }

        public char[] colors(){
            return color;
        }

    }



    /**
     * 匈牙利算法实现寻找最大匹配
     */
    public class Hungarian_dfs {

        private Graph G;
        private int maxMatching=0;
        private int[] matching; //代表匹配，点2与点6匹配，则mathcing[2]=6,matching[6]=2;
        private boolean[] visisted;

        public Hungarian_dfs(Graph G){

            BipartitionDetection bd=new BipartitionDetection(G);
            if(!bd.isBinaryGraph()){
                throw new IllegalArgumentException("Hungarian only works for bipartite graph.");
            }
            this.G=G;

            char[] colors=bd.colors();

            matching = new int[G.V()];
            Arrays.fill(matching,-1);
            visisted = new boolean[G.V()];

            for(int v=0;v<G.V();v++){
                if(colors[v]=='b' && matching[v] == -1){
                    Arrays.fill(visisted,false);//每一轮都需要设为false
                    if(dfs(v)){
                        maxMatching ++;
                    }
                }
            }
        }

        /**
         * dfs 实现增广路径查找
         * @param v
         * @return
         */
        private boolean dfs(int v){
            visisted[v]=true;
            for(int u:G.adj(v)){//
                if(!visisted[u]){
                    visisted[u]=true;
                    if(matching[u]==-1|| dfs(matching[u])){
                        //匹配边 非匹配边互换
                        matching[v]=u;
                        matching[u]=v;
                        return true;
                    }
                }
            }
            return false;
        }


        public int result(){
            return maxMatching;
        }

        public boolean isPerfectMatching(){
            return maxMatching*2 == G.V();
        }

    }





    public int domino(int n,int m,int[][] broken){
        int[][] board=new int[n][m];

        // 位置值为1是坏格子
        for(int[] p:broken){
            board[p[0]][p[1]]=1;
        }

        Graph g=new Graph(n*m,false);

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                //无向图，4连同只需要看，两个方向，右边和下边
                if(j+1<m && board[i][j]==0 && board[i][j+1]==0){
                    g.addEdge(i*m+j,i*m+(j+1));
                }
                if(i+1<n && board[i][j]==0 && board[i+1][j]==0){
                    g.addEdge(i*m+j,(i+1)*m+j);
                }
            }
        }

        Hungarian_dfs bm=new Hungarian_dfs(g);
        return bm.result();

    }

}
