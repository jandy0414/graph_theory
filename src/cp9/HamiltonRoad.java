package cp9;

import cp2.AdjSet;
import cp2.Graph;

import java.util.ArrayList;
import java.util.Stack;

public class HamiltonRoad {
    private Graph G;
    private boolean[] visited;
    private Stack<Integer> tarVer;




    public HamiltonRoad(Graph G,int s){

        this.G = G;
        this.visited =new boolean[G.V()];
        this.tarVer =new Stack<>();

//        this.tarVer.push(0);
        if(dfs(s,G.V())){
            this.tarVer.push(s);
        }

    }

    private boolean dfs(int v,int left){
        visited[v]=true;
        left --;
        if(left==0 ){
            return true;
        }

        for(int w: G.adj(v)){
            if(!visited[w]){
                if(dfs(w,left)) {
                    tarVer.push(w);
                    return true;
                }
            }
//            else if(w==0 && left==0){
//                return true;
//            }
        }
//        left++;//方法里面传递了，不影响，值传递，返回回去的值还是上一层了
        visited[v]=false;
        return false;

    }

//    private boolean checkIsFinishVisited(){
//        for(int i=G.V()-1;i>=0;i--){
//            if(!visited[i]){
//                return false;
//            }
//        }
//        return true;
//    }

    private ArrayList<Integer> result(){
        ArrayList<Integer> res=new ArrayList<>();
        while(!tarVer.isEmpty()){
            res.add(tarVer.pop());
        }
//        Collections.reverse(res);
        return res;
    }







    public static void main(String[] args) {
        Graph g = new AdjSet("g93.txt");
        HamiltonRoad hamiltonDfs = new HamiltonRoad(g,1);
        System.out.println(hamiltonDfs.result());

        g = new AdjSet("g92.txt");
        hamiltonDfs = new HamiltonRoad(g,0);
        System.out.println(hamiltonDfs.result());

    }
}
