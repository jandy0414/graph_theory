package cp7.leetcode1091;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Solution3 {

    private int C,R;
    private boolean[][] visisted;
    private int[] dis;

    private int[][] dirs8={{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};



    public int shortestPathBinaryMatrix(int[][] grid) {
        if(grid==null || grid.length==0 || grid[0].length==0){
            return -1;
        }

        R=grid.length;
        C=grid[0].length;
        if(grid[0][0]!=0 || grid[R-1][C-1]!=0){
            return -1;
        }
        visisted=new boolean[R][C];
        dis=new int[R*C];
        for(int i=0;i<R*C;i++){
            dis[i]=-1;
        }

        Queue<Integer> queue=new LinkedList<>();
        for(int x=0;x<R;x++){
            for(int y=0;y<C;y++){
                if(!visisted[x][y] && grid[x][y]==0){
                    queue.add(x*C+y);
                    visisted[x][y]=true;
                    dis[x*C+y]=1;
                    while (!queue.isEmpty()){
                        int cur=queue.remove();
                        for(int d=0;d<8;d++){
                            int nextX=cur/C+dirs8[d][0];
                            int nextY=cur%C+dirs8[d][1];
                            if(inArea(nextX,nextY) && !visisted[nextX][nextY]  && grid[nextX][nextY]==0){
                                queue.add(nextX*C+nextY);
                                visisted[nextX][nextY]=true;
                                dis[nextX*C+nextY]=dis[cur]+1;
                            }
                        }
                    }
                    return dis[R*C-1];
                }

            }
        }

        return -1;

    }


    private boolean inArea(int x,int y){
        return x>=0 && x<R && y>=0 && y<C;
    }

    public static void main(String[] args) {
        Solution3 solution2=new Solution3();

//        int[][] input={
//                {0,0,0},
//                {1,1,0},
//                {1,1,0}};
//        int[][] input={
//                {1,0,0},
//                {1,1,0},
//                {1,1,0}};[[0,0,1,0,0,0,0],[0,1,0,0,0,0,1],[0,0,1,0,1,0,0],[0,0,0,1,1,1,0],[1,0,0,1,1,0,0],[1,1,1,1,1,0,1],[0,0,1,0,0,0,0]]
        int[][] input={
                {0,0,1,0,0,0,0},
                {0,1,0,0,0,0,1},
                {0,0,1,0,1,0,0},
                {0,0,0,1,1,1,0},
                {1,0,0,1,1,0,0},
                {1,1,1,1,1,0,1},
                {0,0,1,0,0,0,0}};


        int out=solution2.shortestPathBinaryMatrix(input);
        System.out.println(out);
    }

}
