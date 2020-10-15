package cp13;




import java.util.ArrayList;
import java.util.Stack;


public class EulerLoop {
    private Graph G;


    public EulerLoop(Graph G){
        if(G.isDirected()){
            throw new IllegalArgumentException("EulerLoop only works in undirected graph");
        }
        this.G = G;
    }


    public boolean hasEulerLoop(){
        CC cc = new CC(G);
        if(cc.getCcCount() >1){
            return false;
        }

        for(int v=0;v<G.V();v++){
            if(G.degree(v)%2==1){
                return false;
            }
        }
        return true;

    }

    public ArrayList<Integer> result(){
        ArrayList<Integer> res = new ArrayList<>();
        if(!hasEulerLoop()){
            return res;
        }
        Graph g =(Graph)G.clone();

        Stack<Integer> stack = new Stack<>();
        int curv = 0;
        stack.push(curv);
        while (!stack.empty()){
            if(g.degree(curv)!=0){
                stack.push(curv);
                int w=g.adj(curv).iterator().next();
                g.removeEdge(curv,w);
                curv = w;
            } else {
                res.add(curv);
                curv = stack.pop();
            }
        }
        return res;

    }



    public static void main(String[] args) {
        Graph g = new Graph("g10.txt");
        EulerLoop eulerLoop = new EulerLoop(g);
        System.out.println(eulerLoop.hasEulerLoop());
        System.out.println(eulerLoop.result());

         g = new Graph("g11.txt");
         eulerLoop = new EulerLoop(g);
        System.out.println(eulerLoop.hasEulerLoop());
        System.out.println(eulerLoop.result());

//                [0, 2, 4, 3, 2, 1, 0]
//                [0, 3, 4, 6, 7, 9, 10, 8, 7, 5, 4, 1, 5, 2, 1, 0]

    }
}
