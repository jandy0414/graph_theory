package cp15;


import java.util.*;

public class MaxFlow {

    private WeightedGraph network;
    private int s,t;
    private int maxFlow = 0;

    private WeightedGraph rG;//残量图

    public MaxFlow(WeightedGraph network, int s, int t){
        if(!network.isDirected()){
            throw new IllegalArgumentException("MaxFlow only works in directed graph");
        }

        if(network.V()<2){
            throw new IllegalArgumentException("The network should has at least 2 vertices.");
        }

        network.validateVertex(s);
        network.validateVertex(t);
        if(s==t){
            throw new IllegalArgumentException("s and t should be different.");
        }

        this.network = network;
        this.s=s;
        this.t=t;

        this.rG = new WeightedGraph(network.V(),true);

        for(int v=0;v<network.V();v++){ //初始的残量图
            for(int w:network.adj(v)){
                int c =network.getWeight(v,w);
                rG.addEdge(v,w,c);
                rG.addEdge(w,v,0);
            }
        }

        while(true){
            ArrayList<Integer> augPath=getAugmentingPath();
            if(augPath.size() ==0 ){
                break;
            }

            int f=Integer.MAX_VALUE;
            //todo 计算增广路径上的最小值
            for(int i=1;i < augPath.size();i++){
                int v=augPath.get(i-1);
                int w=augPath.get(i);
                f=Math.min(f,rG.getWeight(v,w));
            }

            maxFlow +=f;

            //todo: 根据增广路径更新 rG
            for(int i=1;i<augPath.size();i++){
                int v=augPath.get(i-1);
                int w=augPath.get(i);

//                if(network.hasEdge(v,w)){//正向边
//                    rG.setWeight(v,w,rG.getWeight(v,w)-f);
//                    rG.setWeight(w,v,rG.getWeight(w,v)+f);
//                } else {//反向边
//                    rG.setWeight(w,v,rG.getWeight(w,v)+f);
//                    rG.setWeight(v,w,rG.getWeight(v,w)-f);
//                }

                //同上，精简优化而得
                rG.setWeight(v,w,rG.getWeight(v,w)-f);
                rG.setWeight(w,v,rG.getWeight(w,v)+f);

            }
        }


    }

    private ArrayList<Integer> getAugmentingPath(){
        //bfs,   也相当于运行无权图的最短路径
        Queue<Integer> q =new LinkedList<>();
        int[] pre=new int[network.V()];
        Arrays.fill(pre,-1);

        q.add(s);
        pre[s]=s;
        while (!q.isEmpty()){
            int cur=q.remove();
            if(cur==t){
                break;
            }
            for(int next:rG.adj(cur)){
                if(pre[next] ==-1 && rG.getWeight(cur,next)>0){
                    pre[next]=cur;
                    q.add(next);
                }
            }
        }

        // 取出路径
        ArrayList<Integer> res=new ArrayList<>();
        if(pre[t]==-1){
            return res;
        } else {
            int cur=t;
            while(cur != s){
                res.add(cur);
                cur = pre[cur];
            }
            res.add(s);
            Collections.reverse(res);
            return res;
        }

    }

    public int result(){
        return maxFlow;
    }

    public int flow(int v,int w){
        if(!network.hasEdge(v,w)){
            throw new IllegalArgumentException(String.format("No edge %d-%d",v,w));
        }
        return rG.getWeight(w,v);//残量图找反向边能抵消的数量
    }

    public static void main(String[] args) {
        WeightedGraph g=new WeightedGraph("14_network.txt",true);

        MaxFlow maxFlow=new MaxFlow(g,0,3);
        System.out.println(maxFlow.result());

        for(int v=0;v<g.V();v++){
            for(int w:g.adj(v)){
                System.out.println(String.format("%d-%d: %d / %d",v,w,maxFlow.flow(v,w),g.getWeight(v,w)));
            }
        }

        System.out.println("=====================");
        g=new WeightedGraph("14_network2.txt",true);

        maxFlow=new MaxFlow(g,0,5);
        System.out.println(maxFlow.result());

        for(int v=0;v<g.V();v++){
            for(int w:g.adj(v)){
                System.out.println(String.format("%d-%d: %d / %d",v,w,maxFlow.flow(v,w),g.getWeight(v,w)));
            }
        }
    }


}
