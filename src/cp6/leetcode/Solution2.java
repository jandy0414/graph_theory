package cp6.leetcode;

import java.util.LinkedList;
import java.util.Stack;

public class Solution2 {

    /**
     * Definition for singly-linked list.
     * */
      public class ListNode {
          int val;
          ListNode next;
          ListNode(int x) { val = x; }
     }
    private int curUP=0;
    private ListNode curNode;


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        int cur=0;
        cur=(l1.val+l2.val)%10;
        this.curUP=(l1.val+l2.val)/10;
        ListNode res=new ListNode(cur);
        this.curNode=res;

        ListNode node1,node2,tmp;
        node1=l1.next;
        node2=l2.next;
        while(node1!=null && node2!=null)
        {
            tmp=addVal(node1.val,node2.val);
            node1=node1.next;
            node2=node2.next;
            this.curNode.next=tmp;
            this.curNode=tmp;
        }


        return res;

    }

    private ListNode addVal(int x1,int x2){
        int cur=0;
        cur=(x1+x2+this.curUP)%10;
        this.curUP=(x1+x2+this.curUP)/10;
        return new ListNode(cur);
    }

    public static void main(String[] args) {
        Solution2 solution2=new Solution2();

    }

    public Solution2(){
        ListNode l1=new ListNode(2);
        l1.next=new ListNode(4);
        l1.next.next=new ListNode(3);

        ListNode l2=new ListNode(5);
        l2.next= new ListNode(6);
        l2.next.next=new ListNode(4);

        ListNode res=addTwoNumbers(l1,l2);
        System.out.println(res);
    }


}
