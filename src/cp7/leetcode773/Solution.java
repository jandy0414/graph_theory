package cp7.leetcode773;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    private int target=123450;  //滑动的最终目标
    private int R,C;
    private int[][] dirs={{-1,0},{0,1},{1,0},{0,-1}};

    public int slidingPuzzle(int[][] board) {
        if(board==null || board.length!=2 || board[0].length!=3){
            return -1;
        }
        R=board.length;
        C=board[0].length;
        HashMap<Integer,Integer> visited=new HashMap<>();
        int start=ints2int(board);
        if(start==target){
            return 0;
        }


        //bfs
        Queue<Integer> queue=new LinkedList();
        queue.add(start);
        visited.put(start,0);
        while (!queue.isEmpty()){
            int cur=queue.remove();

            ArrayList<Integer> nexts=getNexts(cur);

            for(int next:nexts){
                if(!visited.containsKey(next)){
                    queue.add(next);
                    visited.put(next,visited.get(cur)+1);

                    if(next==target){
                        return visited.get(next);
                    }
                }
            }

        }

        return -1;
    }

    private int ints2int(int[][] s){
        int res=0;
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                res += s[i][j]* Math.pow(10,(R*C-i*C-j-1));
            }
        }
        return res;
    }
    private ArrayList<Integer> getNexts(int s){
        ArrayList<Integer> nexts=new ArrayList();
        int[][] is =  int2ints(s);
        int tmp;
        for(int i=0;i<R;i++){
            for (int j=0;j<C;j++){
                if(is[i][j]==0){
                    for(int d=0;d<4;d++) {
                        int nextX = i + dirs[d][0];
                        int nextY = j + dirs[d][1];
                        if (inArea(nextX, nextY)) {
                            tmp=is[i][j];
                            is[i][j]=is[nextX][nextY];
                            is[nextX][nextY]=tmp;
                            nexts.add(ints2int(is));

                            //复原回去
                            tmp=is[i][j];
                            is[i][j]=is[nextX][nextY];
                            is[nextX][nextY]=tmp;
                        }
                    }
                    return nexts;
                }
            }
        }
        return nexts;

    }
    private int[][] int2ints(int s){
        String str = String.valueOf(s);//num为需要转化的整数
        if(str.length()<6){
           str="0"+str;
        }
        int[][] res = new int[R][C];
        for(int i=0;i<R;i++){
            for (int j=0;j<C;j++){
                res[i][j]=Integer.parseInt(String.valueOf(str.charAt(i*C+j)));
            }
        }
        return res;

    }
    private boolean inArea(int x,int y){
        return x>=0 && x<R && y>=0 && y<C;
    }



    public static void main(String[] args) {
        int[][]  input={{0,1,3},{4,5,2}};
        Solution solution=new Solution();
        System.out.println(solution.ints2int(input));
        solution.R=2;
        solution.C=3;

        int[][] output;
        output=solution.int2ints(13452);
        output=solution.int2ints(543210);
        System.out.println(output);

        ArrayList<Integer> out2;
        out2=solution.getNexts(13452);
        out2=solution.getNexts(543210);
        System.out.println(out2);

    }
}
