package cp9.leetcode980;

import cp2.AdjSet;
import cp2.Graph;

import java.util.ArrayList;
import java.util.Stack;

public class HamiltonSolution {
    private int[][] grid;
    private int R,C;
    private boolean[] visited;
    private int res=0;
    private int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}};

    public int uniquePathsIII(int[][] grid) {
//        int res=0;
        if(grid==null || grid.length==0 || grid[0].length==0){
            return res;
        }
        this.grid=grid;
        R=grid.length;
        C=grid[0].length;
        visited=new boolean[R*C];


        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(grid[i][j]==1){
                    dfs(i,j);
                }
            }
        }

        return res;
    }



    private boolean dfs(int i,int j){
        visited[i*C+j]=true;
        if(grid[i][j]==2 && checkIsFinishVisited()){
            res++;
            return true;
        }

        for(int w: getNexts(i,j)){
            if(!visited[w]){
                if(dfs(w/C,w%C)) {
                    visited[w]=false;
                }
            }

        }
//        left++;//方法里面传递了，不影响，值传递，返回回去的值还是上一层了
        visited[i*C+j]=false;
        return false;

    }

    private ArrayList<Integer> getNexts(int x,int y){
        ArrayList<Integer> res=new ArrayList<>();
        for(int d=0;d<4;d++){
            int nextx=x+dirs[d][0];
            int nexty=y+dirs[d][1];
            if(inArea(nextx,nexty)&& (grid[nextx][nexty]==0 || grid[nextx][nexty]==2)){
                res.add(nextx*C+nexty);
            }
        }
        return res;
    }

    private boolean inArea(int x,int y){
        return x>=0 && x<R && y>=0 && y<C;
    }


    private boolean checkIsFinishVisited(){
        for(int i=R*C-1;i>=0;i--){
            if(!visited[i]&&grid[i/C][i%C]!=-1){
                return false;
            }
        }
        return true;
    }






    public static void main(String[] args) {


    }
}
