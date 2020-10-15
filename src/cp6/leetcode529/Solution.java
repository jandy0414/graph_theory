package cp6.leetcode529;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

class Solution {

    private int[][] dirs4={{-1,0},{0,1},{1,0},{0,-1}};
    private int[][] dirs8={{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
    private boolean[] visited;
    private int R,C;
    private int tarVer;
    private char tarValue;

    private char[][] grid;

    private HashSet<Integer>[] G; // 构建图
    private char[] countMAround;
    private Queue<Integer> needToChangeB;
    private Queue<Integer> needToChangeN;

    public char[][] updateBoard(char[][] board,int[] click) {
        if(board ==null || board.length==0 || board[0].length==0){
            return grid;
        }
        R=board.length;
        C=board[0].length;
        if(click==null || click.length!=2 || !inArea(click[0],click[1]) ){
            return grid;
        }

        visited =new boolean[R*C];
        countMAround =new char[R*C];
        for(int i=0;i<R*C;i++){
            countMAround[i]='E';
        }
        tarValue=board[click[0]][click[1]];
        if(tarValue!='E' && tarValue!='M'){
            return grid;
        }
        grid=board;
        if(tarValue=='M'){
            grid[click[0]][click[1]]='X';
            return grid;
        }

        tarVer=click[0]*C+click[1];

        this.G=this.constructGraph('E');// 按E 构建图
        needToChangeN=new LinkedList<>();
        this.setBoardByM();


        needToChangeB=new LinkedList<>();
        dfs(tarVer);
//        for(int v=0;v<C*R;v++){
//            int x=v/C,y=v%C;
//            if(!visited[v] && this.grid[x][y]=='E' && !checkEIsEdge(x,y)){
//                dfs(v);
//            }
//        }


        while (!needToChangeB.isEmpty()){
            int curV=needToChangeB.remove();
            int x2=curV/C,y2=curV%C;
            this.grid[x2][y2]='B';
        }
        while (!needToChangeN.isEmpty()){
            int curV=needToChangeN.remove();
            if(visited[curV]){
                int x2=curV/C,y2=curV%C;
                this.grid[x2][y2]=countMAround[curV];
            }

        }
        return this.grid;

    }


    private void dfs(int v){
        visited[v]=true;

        if(!this.needToChangeN.contains(v)){//不是雷的边界设B
            this.needToChangeB.add(v);
        } else{ //雷的边界,不遍历临边了
            for(int w:G[v]){
                int x=w/C,y=w%C;
                if(!visited[w] && !checkEIsEdge(x,y)){
                    visited[w]=true;
                }
            }
            return;
        }

        for(int w:G[v]){
            if(!visited[w] ){
                dfs(w);
            }
        }
    }

    private void setBoardByM(){
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                for(int d=0;d<8;d++){
                    int nextX=i+dirs8[d][0];
                    int nextY=j+dirs8[d][1];
                    if(inArea(nextX,nextY) && grid[nextX][nextY]=='M'){
                        setBValue(i*C+j);
                    }
                }
            }
        }
    }

    private void setBValue(int ver){
        if(countMAround[ver]=='E'){
            needToChangeN.add(ver);
            countMAround[ver]='1';
        } else{
            char tmp=countMAround[ver];
            int tmp2=(int)tmp;
            tmp2++;
            countMAround[ver]=(char)tmp2;

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

    private boolean checkEIsEdge(int x,int y){
        for(int d=0;d<4;d++){
            int nextX=x+dirs4[d][0];
            int nextY=y+dirs4[d][1];
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
//        char[][] input={{'E','E','E','E','E'},{'E','E','M','E','E'},{'E','E','E','E','E'},{'E','E','E','E','E'}};
//        int[] click={3,0};

//        char[][] input={{'B','1','E','1','B'},{'B','1','M','1','B'},{'B','1','1','1','B'},{'B','B','B','B','B'}};
//        int[] click={1,2};

        char[][] input= {
                {'E','E','E','E','E','E','E','E'},
                {'E','E','E','E','E','E','E','M'},
                {'E','E','M','E','E','E','E','E'},
                {'M','E','E','E','E','E','E','E'},
                {'E','E','E','E','E','E','E','E'},
                {'E','E','E','E','E','E','E','E'},
                {'E','E','E','E','E','E','E','E'},
                {'E','E','M','M','E','E','E','E'}};
        int[] click={0,0};

//        char[][] input= {
//                {'E','M','M','E','E','E','E','E'},
//                {'E','E','M','E','E','E','E','E'},
//                {'E','E','E','E','E','E','E','E'},
//                {'E','M','E','E','E','E','E','E'},
//                {'E','E','E','E','E','E','E','E'},
//                {'E','E','M','E','E','E','E','E'},
//                {'E','E','E','E','E','E','E','E'},
//                {'E','E','E','E','E','E','E','E'}};
//        int[] click={5,5};

        char[][] out=solution.updateBoard(input,click);
        for(int i=0;i<out.length;i++){
            for(int j=0;j<out[0].length;j++){
                System.out.print(out[i][j]+" ");
            }
            System.out.println();
        }
    }
}