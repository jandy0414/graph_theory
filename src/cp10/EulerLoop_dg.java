package cp10;


import src.cp10.AdjSet;

import java.util.ArrayList;
import java.util.Stack;


public class EulerLoop_dg {
    private AdjSet G;
    private ArrayList<Integer> res;
    private Stack<Integer> stack;


    public EulerLoop_dg(AdjSet G){
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

        res = new ArrayList<>();
        return true;

    }

    public ArrayList<Integer> result(){

        if(!hasEulerLoop()){
            return res;
        }
        AdjSet g =(AdjSet)G.clone();

        stack = new Stack<>();
        int curv = 0;
        res.add(curv);
        dg(g,curv);
        return res;

    }

    private void dg(AdjSet g,int v){

        if(g.degree(v)!=0){
            stack.push(v);
            int w=g.adj(v).iterator().next();
            g.removeEdge(v,w);
            dg(g,w);

        }else {
            if(!stack.isEmpty()){
                int curv=stack.pop();
                res.add(curv);
                dg(g,curv);
            }

        }

    }

//    private int dg2(AdjSet g,int v,int parent){
//
//        if(g.degree(v)!=0){
//            int w=g.adj(v).iterator().next();
//            g.removeEdge(v,w);
//            dg2(g,w,v);
//            return v;
//
//        } else {
//             res.add(parent);
//             dg2(g,parent,parent);
//
//
//        }
//    }


    public static void main(String[] args) {
        AdjSet g = new AdjSet("g10.txt");
        EulerLoop_dg eulerLoop = new EulerLoop_dg(g);
        System.out.println(eulerLoop.hasEulerLoop());
        System.out.println(eulerLoop.result());

         g = new AdjSet("g11.txt");
         eulerLoop = new EulerLoop_dg(g);
        System.out.println(eulerLoop.hasEulerLoop());
        System.out.println(eulerLoop.result());

//                [0, 2, 4, 3, 2, 1, 0]
//                [0, 3, 4, 6, 7, 9, 10, 8, 7, 5, 4, 1, 5, 2, 1, 0]

    }
}
