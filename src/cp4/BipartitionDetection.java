package cp4;

import cp2.AdjSet;
import cp2.Graph;

public class BipartitionDetection {

    private Graph G;
    private boolean[] visited;
    private char[] color;
    private boolean isBinaryGraph=false;


    public BipartitionDetection(Graph G){
        this.G=G;
        this.visited = new boolean[G.V()];//按顶点个数开空间

        this.color = new char[G.V()];
        for(int i=0;i<color.length;i++){
            color[i]='w';
        }


        for(int v=0;v<G.V();v++){
            if(!visited[v]){
                isBinaryGraph=dfs(v,v);
                if(!isBinaryGraph){
                    break;
                }
            }
        }


    }

    /**
     * 深度优先遍历主方法
     * @param v
     */
    private boolean dfs(int v,int parentId){
        this.visited[v]=true;
        if(v==parentId){
            this.color[v]='b';
        } else {
            this.color[v]=this.color[parentId]=='b'?'g':'b';
        }

        for(int w: G.adj(v)){
            if(!visited[w] ){
                if(!dfs(w,v)){
                    return false;
                }
            } else{ // 访问过就是被染色过
                if(this.color[w]==this.color[v]){
                    return false;
                }
            }
        }
        return true;
    }


    public boolean isBinaryGraph(){
        return this.isBinaryGraph;
    }

    public void printSetColor(){
        for(int i=0;i<this.color.length;i++){
            System.out.print(i+":"+this.color[i]+"  ");
        }
        System.out.println();

    }



    public static void main(String[] args) {
        Graph g = new AdjSet("g3.txt");
        BipartitionDetection graphDFS = new BipartitionDetection(g);

        System.out.println("是否是二分图："+graphDFS.isBinaryGraph());
        graphDFS.printSetColor();

        Graph g2 = new AdjSet("g2.txt");
        BipartitionDetection graphDFS2 = new BipartitionDetection(g2);

        System.out.println("是否是二分图："+graphDFS2.isBinaryGraph());
         graphDFS2.printSetColor();

        Graph g4= new AdjSet("g4.txt");
        BipartitionDetection graphDFS4 = new BipartitionDetection(g4);

        System.out.println("是否是二分图："+graphDFS4.isBinaryGraph());
        graphDFS4.printSetColor();

    }


}
