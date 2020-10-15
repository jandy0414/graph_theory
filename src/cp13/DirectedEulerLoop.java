package cp13;




import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;


public class DirectedEulerLoop {
    private Graph G;


    public DirectedEulerLoop(Graph G){
        if(!G.isDirected()){
            throw new IllegalArgumentException("DirectedEulerLoop only works in directed graph");
        }
        this.G = G;
    }


    public boolean hasEulerLoop(){
        //后续补充处理
//        CC cc = new CC(G);
//        if(cc.getCcCount() >1){
//            return false;
//        }

        for(int v=0;v<G.V();v++){
            if(G.indegree(v)!=G.outdegree(v)){
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
            if(g.outdegree(curv)!=0){// 改为出度
                stack.push(curv);
                int w=g.adj(curv).iterator().next();
                g.removeEdge(curv,w);
                curv = w;
            } else {
                res.add(curv);
                curv = stack.pop();
            }
        }
        Collections.reverse(res);
        return res;

    }



    public static void main(String[] args) {
        Graph g = new Graph("src/cp13/ug.txt",true);
        DirectedEulerLoop eulerLoop = new DirectedEulerLoop(g);
        System.out.println(eulerLoop.hasEulerLoop());
        System.out.println(eulerLoop.result());

         g = new Graph("src/cp13/ug3.txt",true);
         eulerLoop = new DirectedEulerLoop(g);
        System.out.println(eulerLoop.hasEulerLoop());
        System.out.println(eulerLoop.result());

//                [0, 2, 4, 3, 2, 1, 0]
//                [0, 3, 4, 6, 7, 9, 10, 8, 7, 5, 4, 1, 5, 2, 1, 0]

    }
}
