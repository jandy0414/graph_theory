package cp9;

import cp2.AdjSet;
import cp2.Graph;

import java.util.ArrayList;
import java.util.Stack;

public class HamiltonDfs {
    private Graph G;
    private boolean[] visited;
    private Stack<Integer> tarVer;




    public HamiltonDfs(Graph G){

        this.G = G;
        this.visited =new boolean[G.V()];
        this.tarVer =new Stack<>();

//        this.tarVer.push(0);
        if(dfs(0)){
            this.tarVer.push(0);
        }

    }

    private boolean dfs(int v){
        visited[v]=true;
        for(int w: G.adj(v)){
            if(!visited[w]){
//                visited[w]=true;
                if(dfs(w)) {
                    tarVer.push(w);
                    return true;
                }
//                } else{
//                    visited[w]=false;
//                }

            } else if(w==0 && checkIsFinishVisited()){
                return true;
            }
        }
        visited[v]=false;
        return false;
//        return checkIsFinishVisited();

    }

    private boolean checkIsFinishVisited(){
        for(int i=G.V()-1;i>=0;i--){
            if(!visited[i]){
                return false;
            }
        }
        return true;
    }

    private ArrayList<Integer> result(){
        ArrayList<Integer> res=new ArrayList<>();
        while(!tarVer.isEmpty()){
            res.add(tarVer.pop());
        }
//        Collections.reverse(res);
        return res;
    }







    public static void main(String[] args) {
        Graph g = new AdjSet("g9.txt");
        HamiltonDfs hamiltonDfs = new HamiltonDfs(g);
        System.out.println(hamiltonDfs.result());

        g = new AdjSet("g92.txt");
        hamiltonDfs = new HamiltonDfs(g);
        System.out.println(hamiltonDfs.result());

    }
}
