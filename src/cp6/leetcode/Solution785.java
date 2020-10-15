package cp6.leetcode;

class Solution785 {
    private boolean[] visited;
    private int[] colors;
    private int[][] graph;

    public boolean isBipartite(int[][] graph) {

        this.graph = graph;
        int V=graph.length;
        visited = new boolean[V];
        colors = new int[V];


        for(int v=0;v<V;v++){
            if(!visited[v]){
                if(!dfs(v,0)){
                    return false;
                }
            }
        }
        return true;

    }

    private boolean dfs(int s,int color){
        visited[s]=true;
        colors[s]=color;

        for(int w:graph[s]){
            if(!visited[w]){
                if(!dfs(w,1-color)){
                    return false;
                }
            } else if(colors[s]==colors[w]){
                return false;
            }
        }
        return true;

    }
}