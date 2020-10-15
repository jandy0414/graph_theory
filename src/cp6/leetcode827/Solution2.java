package cp6.leetcode827;

import java.util.Date;
import java.util.HashSet;

class Solution2 {

    private int[][] dirs4={{-1,0},{0,1},{1,0},{0,-1}};
    private boolean[][] visited;
    private int R,C;
    private int perMaxIsLand;


    private int[][] grid;



    public int largestIsland(int[][] grid) {
        if(grid ==null || grid.length==0 || grid[0].length==0){
            return 0;
        }
        R=grid.length;
        C=grid[0].length;


        this.grid=grid;
        visited =new boolean[R][C];
        int maxIsLand=getGsMaxIL();

        int curI=-1,curJ=-1;
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(this.grid[i][j]==0){
                    if(curI!=-1&& curJ!=-1){
                        this.grid[curI][curJ]=0;//复原
                    }
                    curI=i;
                    curJ=j;
                    this.grid[curI][curJ]=1;//探索
                    maxIsLand=Math.max(getGsMaxIL(),maxIsLand);
//                    System.out.println(maxIsLand);
                }
            }
        }


        return maxIsLand;
    }

    private int getGsMaxIL(){
        int countIsLand=0;
        reSetVisited();
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(!visited[i][j]&&grid[i][j]==1){
                    this.perMaxIsLand=0;
                    dfs(i,j);
                    countIsLand=Math.max(countIsLand,this.perMaxIsLand);
                }
            }
        }
        return countIsLand;
    }


    private void dfs(int x,int y){
        visited[x][y]=true;
        this.perMaxIsLand++;
        for(int d=0;d<4;d++){
            int nextX=x+dirs4[d][0];
            int nextY=y+dirs4[d][1];
            if(inArea(nextX,nextY) && !visited[nextX][nextY] &&  grid[nextX][nextY]==1 ){
                dfs(nextX,nextY);
            }
        }
    }

    private void reSetVisited(){
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(visited[i][j]){
                    visited[i][j]=false;
                }
            }
        }
    }






    private boolean inArea(int x,int y){
        return x>=0 && x<R && y>=0 && y<C;
    }

    public static void main(String[] args) {
        Solution2 solution=new Solution2();

//        int[][] input= {
//                {1,0},
//                {0,1}};
//        int[][] input= {
//                {1,1},
//                {1,0}};
//        int[][] input= {
//                {1,1},
//                {1,1}};

        int[][] input={
                {1,0,1},
                {0,0,0},
                {0,1,1}};

        long startTime =  System.currentTimeMillis();


       int out=solution.largestIsland(input);
        System.out.println(out);
        long endTime =  System.currentTimeMillis();
        long usedTime = (endTime-startTime);
        System.out.println(usedTime);
    }
}