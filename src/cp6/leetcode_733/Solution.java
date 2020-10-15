package cp6.leetcode_733;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

class Solution {

    private int[][] dirs={{-1,0},{0,1},{1,0},{0,-1}};
    private boolean[] visited;
    private int R,C;
    private int tarV;

    private int[][] grid;

    private HashSet<Integer>[] G; // 构建图
    private boolean hasTar=false;
    private Queue<Integer> needToChange;

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if(image ==null || image.length==0 || image[0].length==0){
            return image;
        }
        R=image.length;
        C=image[0].length;
        if(!inArea(sr,sc) || newColor<0 || newColor>65535){
            return image;
        }
        this.grid=image;
        visited =new boolean[R*C];
        int curI=this.grid[sr][sc];
        tarV=sr*C+sc;

        this.G=this.constructGraph(curI);//构建图

        for(int v=0;v<R*C;v++){
            int x,y;
            x=v/C;
            y=v%C;
            if(!visited[v] && grid[x][y]==curI)
            {
                needToChange=new LinkedList<>();
                this.hasTar=false;
                dfs(v);
                if(this.hasTar){
                    while (!needToChange.isEmpty()){
                        int curV=needToChange.remove();
                        int x2=curV/C,y2=curV%C;
                        this.grid[x2][y2]=newColor;
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
        this.needToChange.add(v);
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
        int[][] input={{1,1,1},{1,1,0},{1,0,1}};
        int sr=1,sc=1,newColor=2;
//        int[][] input={{0,0,0},{0,0,0}};
//        int sr=0,sc=0,newColor=2;

        int[][] out=solution.floodFill(input,sr,sc,newColor);
        for(int i=0;i<out.length;i++){
            for(int j=0;j<out[0].length;j++){
                System.out.print(out[i][j]+" ");
            }
            System.out.println();
        }
    }
}