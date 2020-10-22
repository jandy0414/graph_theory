package cp6.leetcode529;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

class Solution3 {

//    private int[][] dirs4={{-1,0},{0,1},{1,0},{0,-1}};
    private int[][] dirs8={{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
    private boolean[] visited;
    private boolean[] isEBorder;
    private int R,C;
    private int tarVer;
    private char tarValue;

    private char[][] grid;

    private HashSet<Integer>[] G; // 构建图
    private char[] countMAround;
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
        isEBorder = new boolean[R*C];
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
        needToChangeN=new LinkedList<>();
        this.setBoardByM();//标注num 值

        this.G=this.constructGraph('E');// 按E 和 N 构建图


        dfs(tarVer);



        while (!needToChangeN.isEmpty()){
            int curV=needToChangeN.remove();
            if(visited[curV]&&isEBorder[curV]){
                int x2=curV/C,y2=curV%C;
                this.grid[x2][y2]=countMAround[curV];
            }

        }
        return this.grid;

    }


    private void dfs(int v){
        visited[v]=true;
        if(!isEBorder[v]){
            grid[v/C][v%C]='B';
        }
        if(checkIsEBorder(v)){
            return;
        }

        for(int w:G[v]){
            if(!visited[w] ){
                dfs(w);
            }
        }
    }
    private boolean checkIsEBorder(int v){
        if(!needToChangeN.contains(v))//如果不是数字，不是E边框
        {
            return false;
        }
        int x,y;
        x=v/C;
        y=v%C;
        for(int d=0;d<8;d++){
            int nextX=x+dirs8[d][0];
            int nextY=y+dirs8[d][1];
            if(!inArea(nextX,nextY)){
                return true;//是边界,也是边框
            } else if (needToChangeN.contains(nextX*C+nextY)){
                return true;//下一个还是数字，是E边框了
            }
            else if( grid[nextX][nextY]=='M'){
                return true;//下一个是M
            }
        }
        return false;//不是E边框
    }

    private void setBoardByM(){
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                for(int d=0;d<8;d++){
                    int nextX=i+dirs8[d][0];
                    int nextY=j+dirs8[d][1];
                    if(inArea(nextX,nextY) && grid[nextX][nextY]=='M' && grid[i][j]!='M'){
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
            if(grid[x][y]==curI || checkIsEBorder(v)){//包含  E 和 E的边框数字
                for(int d=0;d<8;d++){
                    int nextX=x+dirs8[d][0];
                    int nextY=y+dirs8[d][1];
                    if(inArea(nextX,nextY) && (grid[nextX][nextY]==curI || checkIsEBorder(nextX*C+nextY))){//包含 E的边框数字 和 E
                        int next=nextX*C+nextY;
                        g[v].add(next);
                        g[next].add(v);
                    }
                }
            }
            if(checkIsEBorder(v)){ // 构建图的过程种，标识是否是边界
                isEBorder[v]=true;
            }
        }
        return g;
    }



    private boolean inArea(int x,int y){
        return x>=0 && x<R && y>=0 && y<C;
    }


    public static void main(String[] args) {

        Solution3 solution=new Solution3();
//        char[][] input={{'E','E','E','E','E'},
//                        {'E','E','M','E','E'},
//                        {'E','E','E','E','E'},
//                        {'E','E','E','E','E'}};
//        int[] click={3,0};

//        char[][] input={{'B','1','E','1','B'},
//                        {'B','1','M','1','B'},
//                        {'B','1','1','1','B'},
//                        {'B','B','B','B','B'}};
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
        /**
         * [["B","B","B","B","B","B","1","E"],
         * ["B","1","1","1","B","B","1","M"],
         * ["1","2","M","1","B","B","1","1"],
         * ["M","2","1","1","B","B","B","B"],
         * ["1","1","B","B","B","B","B","B"],
         * ["B","B","B","B","B","B","B","B"],
         * ["B","1","2","2","1","B","B","B"],
         * ["B","1","M","M","1","B","B","B"]]
         */

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