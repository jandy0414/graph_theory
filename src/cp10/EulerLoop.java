package src.cp10;



import src.cp10.AdjSet;

import java.util.ArrayList;
import java.util.Stack;


public class EulerLoop {
    private AdjSet G;


    public EulerLoop(AdjSet G){
        this.G = G;
    }


    public boolean hasEulerLoop(){
        cp10.CC cc = new cp10.CC(G);
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
        AdjSet g =(AdjSet)G.clone();

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
        AdjSet g = new AdjSet("g10.txt");
        EulerLoop eulerLoop = new EulerLoop(g);
        System.out.println(eulerLoop.hasEulerLoop());
        System.out.println(eulerLoop.result());

         g = new AdjSet("g11.txt");
         eulerLoop = new EulerLoop(g);
        System.out.println(eulerLoop.hasEulerLoop());
        System.out.println(eulerLoop.result());

//                [0, 2, 4, 3, 2, 1, 0]
//                [0, 3, 4, 6, 7, 9, 10, 8, 7, 5, 4, 1, 5, 2, 1, 0]

    }
}
