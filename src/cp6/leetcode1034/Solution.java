package cp6.leetcode1034;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

class Solution {

    private int[][] dirs={{-1,0},{0,1},{1,0},{0,-1}};
    private boolean[] visited;
    private int R,C;
    private int tarV;
    private int tarColor;

    private int[][] grid;

    private HashSet<Integer>[] G; // 构建图
    private boolean hasTar=false;
    private Queue<Integer> needToChange;

    public int[][] colorBorder(int[][] grid, int r0, int c0, int color) {
        if(grid ==null || grid.length==0 || grid[0].length==0){
            return grid;
        }
        R=grid.length;
        C=grid[0].length;
        if(!inArea(r0,c0) || color<0 || color>1000){
            return grid;
        }
        this.grid=grid;
        visited =new boolean[R*C];
        this.tarColor=this.grid[r0][c0];
        tarV=r0*C+c0;

        this.G=this.constructGraph(tarColor);//构建图
        for(int v=0;v<R*C;v++){
            int x,y;
            x=v/C;
            y=v%C;
            if(!visited[v] && grid[x][y]==tarColor)
            {
                needToChange=new LinkedList<>();
                this.hasTar=false;
                dfs(v);
                if(this.hasTar){
                    while (!needToChange.isEmpty()){
                        int curV=needToChange.remove();
                        int x2=curV/C,y2=curV%C;
                        this.grid[x2][y2]=color;
                    }
                }

            }
        }

        return this.grid;

    }


    private void dfs(int v){
        visited[v]=true;
        if(v==this.tarV){
            this.hasTar=true;
        }
        if(!checkIsNotEdge(v)){
            this.needToChange.add(v);
        }

        for(int w:G[v]){
            if(!visited[w] ){
                dfs(w);
            }
        }
    }

    private boolean checkIsNotEdge(int v){
        int x,y;
        x=v/C;
        y=v%C;
        for(int d=0;d<4;d++){
            int nextX=x+dirs[d][0];
            int nextY=y+dirs[d][1];
            if(!inArea(nextX,nextY)){
                return false;//是边界,也是边框
            } else if (this.grid[nextX][nextY]!=this.tarColor){
                return false;//4个方向只有要一个非同色了，说明是边框
            }
        }
        return true;//不是边界，也不是边框
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
                   int nextX=x+dirs[d][0];
                   int nextY=y+dirs[d][1];
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
        Solution solution=new Solution();
        int[][] input={{1,1},{1,2}};
        int sr=0,sc=0,newColor=3;

//        int[][] input={{1,2,2},{2,3,2}};
//        int sr=0,sc=1,newColor=3;
//        int[][] input={{1,1,1},{1,1,1},{1,1,1}};
//        int sr=1,sc=1,newColor=2;

        int[][] out=solution.colorBorder(input,sr,sc,newColor);
        for(int i=0;i<out.length;i++){
            for(int j=0;j<out[0].length;j++){
                System.out.print(out[i][j]+" ");
            }
            System.out.println();
        }
    }
}