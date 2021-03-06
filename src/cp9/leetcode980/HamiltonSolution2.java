package cp9.leetcode980;

import cp2.AdjSet;
import cp2.Graph;

import java.util.ArrayList;

public class HamiltonSolution2 {
    private int[][] grid;
    private int R,C;
    private boolean[][] visited;
    private int start,end;
    private int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}};

    public int uniquePathsIII(int[][] grid) {

        if(grid==null || grid.length==0 || grid[0].length==0){
            return 0;
        }
        this.grid=grid;
        R=grid.length;
        C=grid[0].length;
        visited=new boolean[R][C];
        int left=R*C;

        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(grid[i][j]==1){
                    start = i*C+j;
                    this.grid[i][j]=0;
                } else if(grid[i][j]==2){
                    end=i*C+j;
                    this.grid[i][j]=0;
                } else if(grid[i][j]==-1){
                    left --;
                }
            }
        }

        return dfs(start,left);

    }



    private int dfs(int v,int left){
        int x=v/C,y=v%C;
        visited[x][y]=true;
        left --;
        if(left==0 && v==end){
            visited[x][y]=false;//回溯
            return 1;
        }

        int res=0;

        for(int w: getNexts(x,y)){
            if(!visited[w/C][w%C]){
                res +=dfs(w,left);
            }
        }
        
        visited[x][y]=false;
        return res;

    }

    private ArrayList<Integer> getNexts(int x,int y){
        ArrayList<Integer> res=new ArrayList<>();
        for(int d=0;d<4;d++){
            int nextx=x+dirs[d][0];
            int nexty=y+dirs[d][1];
            if(inArea(nextx,nexty)&& grid[nextx][nexty]==0){
                res.add(nextx*C+nexty);
            }
        }
        return res;
    }

    private boolean inArea(int x,int y){
        return x>=0 && x<R && y>=0 && y<C;
    }


//    private boolean checkIsFinishVisited(){
//        for(int i=G.V()-1;i>=0;i--){
//            if(!visited[i]){
//                return false;
//            }
//        }
//        return true;
//    }








    public static void main(String[] args) {


    }
}
