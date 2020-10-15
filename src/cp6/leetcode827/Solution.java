package cp6.leetcode827;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

class Solution {

    private int[][] dirs4={{-1,0},{0,1},{1,0},{0,-1}};
    private boolean[] visited;
    private int R,C;
    private int perMaxIsLand;


    private int[][] grid;

    private HashSet<Integer>[] G; // 构建图


    public int largestIsland(int[][] grid) {
        if(grid ==null || grid.length==0 || grid[0].length==0){
            return 0;
        }
        R=grid.length;
        C=grid[0].length;


        this.grid=grid;
        this.G=this.constructGraph(1);// 按E 构建图
        int maxIsLand=0;
        maxIsLand=Math.max(getGsMaxIL(),maxIsLand);
        int curI=-1,curJ=-1;
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(this.grid[i][j]==0){
                    if(curI!=-1&& curJ!=-1){
                        this.grid[curI][curJ]=0;//复原
                    }
                    curI=i;
                    curJ=j;
                    this.grid[curI][curJ]=1;
                    this.G=this.constructGraph(1);// 按E 构建图
                    maxIsLand=Math.max(getGsMaxIL(),maxIsLand);
//                    System.out.println(maxIsLand);
                }
            }
        }


        return maxIsLand;
    }

    private int getGsMaxIL(){
        int countIsLand=0;
        visited =new boolean[R*C];
        for(int v=0;v<R*C;v++){
            int x=v/C, y=v%C;
            if(!visited[v] && grid[x][y]==1){
                this.perMaxIsLand=0;
                dfs(v);
                countIsLand=Math.max(countIsLand,this.perMaxIsLand);
            }
        }
        return countIsLand;
    }


    private void dfs(int v){
        visited[v]=true;
        this.perMaxIsLand++;
        for(int w:G[v]){
            if(!visited[w] ){
                dfs(w);
            }
        }
    }



    private HashSet<Integer>[] constructGraph(int curI){
        HashSet<Integer>[] g=new HashSet[C*R];

        for(int i=0;i<g.length;i++){
            g[i]=new HashSet<>();
        }

        for(int v=0;v<g.length;v++){
            int x=v/C,y=v%C;
            if(grid[x][y]==curI){
                for(int d=0;d<4;d++){
                   int nextX=x+dirs4[d][0];
                   int nextY=y+dirs4[d][1];
                    if(inArea(nextX,nextY) && grid[nextX][nextY]==curI ){
                        int next=nextX*C+nextY;
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

    public static void main(String[] args) {
        Solution solution=new Solution();

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