package cp7.leetcode1091;

import cp2.Graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Solution2 {

    private int C,R;
    private boolean[] visited;
    private int[] dis;
    private int[][] dirs8={{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};

    private int[][] grid;

    private HashSet<Integer>[] G; // 构建图

    public int shortestPathBinaryMatrix(int[][] grid) {
        if(grid==null || grid.length==0 || grid[0].length==0){
            return -1;
        }

        this.grid=grid;
        R=grid.length;
        C=grid[0].length;
        if(grid[0][0]!=0 || grid[R-1][C-1]!=0){
            return -1;
        }


        this.G=this.constructGraph(0);// 按E 构建图

        visited=new boolean[R*C];
        dis =new int[R*C];
        for(int i=0;i<R*C;i++){
            dis[i]=-1;
        }
        bfs(0);


        return dis[R*C-1];

    }

    private void bfs(int v){
        Queue<Integer> queue=new LinkedList<>();
        visited[v]=true;
        queue.add(v);
        dis[v]=1;
        while (!queue.isEmpty()){
            int cur=queue.remove();
            for(int w:G[cur]){
                if(!visited[w]){
                    visited[w]=true;
                    queue.add(w);
                    dis[w]=dis[cur]+1;
                }
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
                for(int d=0;d<8;d++){
                    int nextX=x+dirs8[d][0];
                    int nextY=y+dirs8[d][1];
                    if(inArea(nextX,nextY) && grid[nextX][nextY]==curI){
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
        Solution2 solution2=new Solution2();

//        int[][] input={
//                {0,0,0},
//                {1,1,0},
//                {1,1,0}};
        int[][] input={
                {1,0,0},
                {1,1,0},
                {1,1,0}};


        int out=solution2.shortestPathBinaryMatrix(input);
        System.out.println(out);
    }

}
