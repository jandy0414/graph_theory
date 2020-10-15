package cp6.leetcode_1020;

import java.util.HashSet;

class Solution {

    private int[][] dirs={{-1,0},{0,1},{1,0},{0,-1}};
    private boolean[] visited;
    private int R,C;
    private int CcCount=0;

    private int[][] grid;

    private HashSet<Integer>[] G; // 构建图
    private boolean hasEdge=false;
    
    public int numEnclaves(int[][] A) {
        if(A ==null || A.length==0 || A[0].length==0){
            return 0;
        }
        R=A.length;
        C=A[0].length;
        this.grid=A;
        visited =new boolean[R*C];

        this.G=this.constructGraph();//构建图

        
        
        int ccid=0;
        for(int v=0;v<R*C;v++){
            int x,y;
            x=v/C;
            y=v%C;
            if(!visited[v] && grid[x][y]==1)
            {
                this.CcCount=0;
                this.hasEdge=false;
                dfs(v);
                if(!this.hasEdge){
                    ccid+=this.CcCount;
                }
    
            }
        }

        return ccid;


    }

    private void dfs(int v){
        visited[v]=true;
        if(checkOneIsEdge(v)){
            this.hasEdge=true;
        }
        this.CcCount++;
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
            if(grid[x][y]==1){
                for(int d=0;d<4;d++){
                   int nextX=x+dirs[d][0];
                   int nextY=y+dirs[d][1];
                    if(inArea(nextX,nextY) && grid[nextX][nextY]==1){
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
        int[][] input={{0,0,0,0},{1,0,1,0},{0,1,1,0},{0,0,0,0}};
//        int[][] input={{0,1,1,0},{0,0,1,0},{0,0,1,0},{0,0,0,0}};
        int out=solution.numEnclaves(input);
        System.out.println(out);
    }
}