package cp6.leetcode_130;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

class Solution {

    private int[][] dirs={{-1,0},{0,1},{1,0},{0,-1}};
    private boolean[] visited;
    private int R,C;
    private Queue<Integer> needToChange;

    private char[][] grid;

    private HashSet<Integer>[] G; // 构建图
    private boolean hasEdge=false;

    public void solve(char[][] board) {
        if(board ==null || board.length==0 || board[0].length==0){
            return ;
        }
        R=board.length;
        C=board[0].length;
        this.grid=board;
        visited =new boolean[R*C];

        this.G=this.constructGraph();//构建图

        for(int v=0;v<R*C;v++){
            int x,y;
            x=v/C;
            y=v%C;
            if(!visited[v] && grid[x][y]=='O')
            {

                this.hasEdge=false;
                needToChange=new LinkedList<>();
                dfs(v);
                if(!this.hasEdge){//如果没有边
                    while (!needToChange.isEmpty()){
                        int curV=needToChange.remove();
                        int x2=curV/C,y2=curV%C;
                        board[x2][y2]='X';
                    }
                }

            }
        }

    }

    private void dfs(int v){
        visited[v]=true;
        if(checkOneIsEdge(v)){
            this.hasEdge=true;
        }
        this.needToChange.add(v);
        for(int w:G[v]){
            if(!visited[w] ){
                dfs(w);
            }
        }
    }

    private HashSet<Integer>[] constructGraph(){
        HashSet<Integer>[] g=new HashSet[C*R];

        for(int i=0;i<g.length;i++){
            g[i]=new HashSet<>();
        }

        for(int v=0;v<g.length;v++){
            int x=v/C,y=v%C;
            if(grid[x][y]=='O'){
                for(int d=0;d<4;d++){
                    int nextX=x+dirs[d][0];
                    int nextY=y+dirs[d][1];
                    if(inArea(nextX,nextY) && grid[nextX][nextY]=='O'){
                        int next=nextX*C+nextY;
                        g[v].add(next);
                        g[next].add(v);
                    }
                }
            }
        }
        return g;
    }

    private boolean checkOneIsEdge(int v){
        int x,y;
        x=v/C;
        y=v%C;
        for(int d=0;d<4;d++){
            int nextX=x+dirs[d][0];
            int nextY=y+dirs[d][1];
            if(!inArea(nextX,nextY)){
                return true;//是边界
            }
        }
        return false;//不是边界
    }

    private boolean inArea(int x,int y){
        return x>=0 && x<R && y>=0 && y<C;
    }

    public static void main(String[] args) {
        Solution solution=new Solution();
        char[][] input={{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
//        char[][] input={{'O','O','O'},{'O','O','O'},{'O','O','O'}};

        solution.solve(input);
        for(int i=0;i<input.length;i++){
            for(int j=0;j<input[0].length;j++){
                System.out.print(input[i][j]+" ");
            }
            System.out.println();
        }

    }
}