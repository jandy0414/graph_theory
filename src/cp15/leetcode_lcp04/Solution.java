package cp15.leetcode_lcp04;




import java.io.File;
import java.io.IOException;
import java.util.*;

public class Solution {
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

    //有权图，（支持有向，无向）
    public class WeightedGraph implements Cloneable {

        private int V;
        private int E;
        private TreeMap<Integer,Integer>[] adj;
        private boolean directed;


        public WeightedGraph(int V, boolean directed){
            this.V = V;
            this.directed = directed;
            this.E = 0;

            adj = new TreeMap[V];
            for(int i=0;i<V;i++){
                adj[i]=new TreeMap<>();
            }
        }


        public void addEdge(int a,int b,int v){
            validateVertex(a);
            validateVertex(b);

            if(a==b){
                throw new IllegalArgumentException("Self Loop is Detected!");// 简单图，不处理自环边
            }
            if(adj[a].containsKey(b)){
                throw new IllegalArgumentException("Parallel Edges are Detected!");// 简单图，不处理平行边
            }
            adj[a].put(b,v);
            if(!directed){
                adj[b].put(a,v);
            }

            this.E ++;
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
            return adj[v].containsKey(w) ;
        }

        /**
         * 返回顶点的所有相邻边
         * @param v
         * @return
         */
        public Iterable<Integer> adj(int v){
            validateVertex(v);
            return adj[v].keySet();
        }

        public int getWeight(int v,int w){
            if(hasEdge(v,w)){
                return adj[v].get(w);
            }
            throw new IllegalArgumentException(String.format("No edge %d-%d",v,w));
        }

        public void setWeight(int v,int w,int newWeight){
            if(!hasEdge(v,w)){
                throw new IllegalArgumentException(String.format("No edge %d-%d",v,w));
            }

            adj[v].put(w,newWeight);
            if(!directed){
                adj[w].put(v,newWeight);
            }

        }

        public void removeEdge(int v,int w){
            validateVertex(v);
            validateVertex(w);
            if(adj[v].containsKey(w)){
                E--;
            }

            adj[v].remove(w);
            if(!directed){
                adj[w].remove(v);
            }

        }

        public boolean isDirected(){
            return this.directed;
        }


        @Override
        public Object clone(){
            try{
                WeightedGraph cloned = (WeightedGraph) super.clone();
                cloned.adj = new TreeMap[V];
                for(int v=0;v<V;v++){
                    cloned.adj[v]=new TreeMap<>();
                    for(Map.Entry<Integer,Integer> entry:this.adj[v].entrySet()){
                        cloned.adj[v].put(entry.getKey(),entry.getValue());
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
            sb.append(String.format("V = %d, E = %d directed=%b \n",V,E,directed));
            for(int v=0; v< V; v++){
                sb.append(String.format("%d : ",v));
                for(Map.Entry<Integer,Integer> entry:this.adj[v].entrySet()){
                    sb.append(String.format("(%d:%d) ",entry.getKey(),entry.getValue()));
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

    //求解最大流架类
    public class MaxFlow {

        private WeightedGraph network;
        private int s,t;
        private int maxFlow = 0;

        private WeightedGraph rG;//残量图

        public MaxFlow(WeightedGraph network, int s, int t){
            if(!network.isDirected()){
                throw new IllegalArgumentException("MaxFlow only works in directed graph");
            }

            if(network.V()<2){
                throw new IllegalArgumentException("The network should has at least 2 vertices.");
            }

            network.validateVertex(s);
            network.validateVertex(t);
            if(s==t){
                throw new IllegalArgumentException("s and t should be different.");
            }

            this.network = network;
            this.s=s;
            this.t=t;

            this.rG = new WeightedGraph(network.V(),true);

            for(int v=0;v<network.V();v++){ //初始的残量图
                for(int w:network.adj(v)){
                    int c =network.getWeight(v,w);
                    rG.addEdge(v,w,c);
                    rG.addEdge(w,v,0);
                }
            }

            while(true){
                ArrayList<Integer> augPath=getAugmentingPath();
                if(augPath.size() ==0 ){
                    break;
                }

                int f=Integer.MAX_VALUE;
                //todo 计算增广路径上的最小值
                for(int i=1;i < augPath.size();i++){
                    int v=augPath.get(i-1);
                    int w=augPath.get(i);
                    f=Math.min(f,rG.getWeight(v,w));
                }

                maxFlow +=f;

                //todo: 根据增广路径更新 rG
                for(int i=1;i<augPath.size();i++){
                    int v=augPath.get(i-1);
                    int w=augPath.get(i);

                    //同上，精简优化而得
                    rG.setWeight(v,w,rG.getWeight(v,w)-f);
                    rG.setWeight(w,v,rG.getWeight(w,v)+f);

                }
            }


        }

        private ArrayList<Integer> getAugmentingPath(){
            //bfs,   也相当于运行无权图的最短路径
            Queue<Integer> q =new LinkedList<>();
            int[] pre=new int[network.V()];
            Arrays.fill(pre,-1);

            q.add(s);
            pre[s]=s;
            while (!q.isEmpty()){
                int cur=q.remove();
                if(cur==t){
                    break;
                }
                for(int next:rG.adj(cur)){
                    if(pre[next] ==-1 && rG.getWeight(cur,next)>0){
                        pre[next]=cur;
                        q.add(next);
                    }
                }
            }

            // 取出路径
            ArrayList<Integer> res=new ArrayList<>();
            if(pre[t]==-1){
                return res;
            } else {
                int cur=t;
                while(cur != s){
                    res.add(cur);
                    cur = pre[cur];
                }
                res.add(s);
                Collections.reverse(res);
                return res;
            }

        }

        public int result(){
            return maxFlow;
        }

        public int flow(int v,int w){
            if(!network.hasEdge(v,w)){
                throw new IllegalArgumentException(String.format("No edge %d-%d",v,w));
            }
            return rG.getWeight(w,v);//残量图找反向边能抵消的数量
        }

    }

    //求解匹配问题
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

        BipartiteMatching bm=new BipartiteMatching(g);
        return bm.getMaxMatching();

    }

}
