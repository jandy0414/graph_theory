
import java.util.*;
public class Solution {


        public class TreeNode {
            int val = 0;
            TreeNode left = null;
            TreeNode right = null;

            public TreeNode(int val) {
                this.val = val;

            }

        }

    TreeNode KthNode(TreeNode pRoot, int k)
    {
        Queue pq=new PriorityQueue<Integer>();
        if(pRoot==null){
            return null;
        }

        Queue p=new LinkedList<TreeNode>();
        p.add(pRoot);

        while(!p.isEmpty()){
            TreeNode tmp= (TreeNode)p.remove();
            pq.add(tmp.val);
            if(tmp.left!=null){
                p.add(tmp.left);
            }
            if(tmp.right!=null){
                p.add(tmp.right);
            }
        }
        int i=1;
        while(i<k){
            pq.remove();
        }
        int out=(int)pq.remove();
        TreeNode outNode=new TreeNode(out);
        return outNode;

    }


}
