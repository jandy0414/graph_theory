package cp6.leetcode;

import java.util.HashSet;


public class Solution695 {
    private int R, C;
    private int[][] grid;

    private HashSet<Integer>[] G; // 构建图

    private int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}};

    private boolean[] visited;

    public int maxAreaOfIsland(int[][] grid) {
        if(grid==null||grid.length==0 || grid[0].length==0){
            return 0;
        }
        R = grid.length;
        C=grid[0].length;

        this.grid=grid;

        G=constructGraph();// 构建图

        int res=0;
        // 遍历图求联调分量的个数就可以了
        visited = new boolean[G.length];
        for(int v=0; v<G.length;v++){
            int x =v/C,y=v%C;
            if(!visited[v] && grid[x][y]==1){
                res = Math.max(res,dfs(v));
            }
        }

        return res;

    }

    private int dfs(int v){
        visited[v]=true;
        int res=1;
        for(int w:G[v]){
            if(!visited[w]){
                res+=dfs(w);
//                dfs(w);
            }
        }
        return res;
    }
    /**
     * 构建图
     * @return
     */
    private HashSet<Integer>[] constructGraph(){
        HashSet<Integer>[] g = new HashSet[R*C];
        for(int i=0; i<g.length;i++){
            g[i] = new HashSet<>(); //开空间
        }

        for(int v = 0; v<g.length;v++){
            int x = v/C,y=v%C;
            if(grid[x][y]==1){
                for(int d=0;d<4;d++){
                    int nextx = x+dirs[d][0];
                    int nexty = y+dirs[d][1];
                    if(inArea(nextx,nexty) && grid[nextx][nexty]==1) { //判断4联通存在1
                        int next =nextx*C + nexty;
                        g[v].add(next);
                        g[next].add(v);

                    }
                }
            }
        }
        return g;
    }

    private boolean inArea(int x,int y){
        return x>=0 && x<R && y>=0 && y<C;
    }


}
