package cp7.leetcode773;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Solution2 {
    private int[][] dirs={{-1,0},{0,1},{1,0},{0,-1}};

    public int slidingPuzzle(int[][] board) {
        Queue<String> queue=new LinkedList<>();
        HashMap<String ,Integer> visited= new HashMap<>();

        String initalState=boardToString(board);
        if(initalState.equals("123450")){
            return 0;
        }

        //bfs
        queue.add(initalState);
        visited.put(initalState,0);
        while (!queue.isEmpty()){
            String cur=queue.remove();
            ArrayList<String> nexts=getNexts(cur);

            for(String next:nexts){
                if(!visited.containsKey(next)){
                    queue.add(next);
                    visited.put(next,visited.get(cur)+1);
                    if(next.equals("123450")){
                        return visited.get(next);
                    }
                }
            }
        }

        return -1;
    }

    private ArrayList<String> getNexts(String s){
        ArrayList<String> res=new ArrayList<>();
        int [][] cur=string2Board(s);
        int zero;
        for(zero=0;zero<6; zero++){
            if(s.charAt(zero)=='0'){
                break;
            }
        }
        int zx=zero/3,zy=zero%3;
        for(int d=0;d<4;d++){
            int nextx= zx+dirs[d][0];
            int nexty= zy+dirs[d][1];
            if(inArea(nextx,nexty)){
                swap(cur,zx,zy,nextx,nexty);
                res.add(boardToString(cur));
                swap(cur,zx,zy,nextx,nexty);//恢复
            }
        }


        return res;
    }

    private void swap(int[][] board,int x1,int y1,int x2,int y2){
        int t=board[x1][y1];
        board[x1][y1]=board[x2][y2];
        board[x2][y2]=t;
    }

    private String boardToString(int[][] board){
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<2;i++){
            for(int j=0;j<3;j++){
                sb.append(board[i][j]);
            }
        }
        return sb.toString();
    }

    private int[][] string2Board(String s){

        int[][] board=new int[2][3];
        for(int i=0;i<6;i++){
            board[i/3][i%3]=s.charAt(i)-'0';
        }
        return board;
    }
    private boolean inArea(int x,int y){
        return x>=0 && x<2 && y>=0 && y<3;
    }





    public static void main(String[] args) {
        int[][]  input={{0,1,3},{4,5,2}};
        Solution2 solution=new Solution2();



    }
}
