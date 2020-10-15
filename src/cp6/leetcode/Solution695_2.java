package cp6.leetcode;

import java.util.HashSet;


public class Solution695_2 {
    private int R, C;
    private int[][] grid;

    private int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}};

    private boolean[][] visited;

    public int maxAreaOfIsland(int[][] grid) {
        if(grid==null||grid.length==0 || grid[0].length==0){
            return 0;
        }
        R = grid.length;
        C=grid[0].length;

        this.grid=grid;


        int res=0;
        // 遍历图求联调分量的个数就可以了
        visited = new boolean[R][C];
        for(int i=0; i<R;i++){
            for(int j=0;j<C;j++){
                if(!visited[i][j] && grid[i][j]==1){
                    res = Math.max(res,dfs(i,j));
                }
            }
        }

        return res;

    }

    private int dfs(int x,int y){
        visited[x][y]=true;
        int res=1;
        for(int d=0;d<4;d++){
            int nextx = x+dirs[d][0];
            int nexty = y+dirs[d][1];
            if(inArea(nextx,nexty) && !visited[nextx][nexty] && grid[nextx][nexty]==1){
                res+=dfs(nextx,nexty);
            }
        }
        return res;
    }

    private boolean inArea(int x,int y){
        return x>=0 && x<R && y>=0 && y<C;
    }

    public static void main(String[] args) {
        int[][] grid={{1,1,0,0,0},{1,1,0,0,0},{0,0,0,1,1},{0,0,0,1,1}};
        Solution695_2 solution695_2=new Solution695_2();
        int max= solution695_2.maxAreaOfIsland(grid);
        System.out.println("max:"+max);
    }


}
