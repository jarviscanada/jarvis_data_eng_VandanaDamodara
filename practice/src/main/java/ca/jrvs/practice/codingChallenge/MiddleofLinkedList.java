package ca.jrvs.practice.codingChallenge;

public class MiddleofLinkedList {
  /*
   * Complexity: O(n)
   * Justification: Only pass through all nodes once
   */
  public static class ListNode {
     int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }



    public ListNode middleNode(ListNode head)
    {
    int middleIndex =0;
    int numNodes =0 ;
    ListNode current = head;
    ListNode middle = head;
    while(current != null)
    {
      numNodes++;
      if ((int)(numNodes/2)>middleIndex)
      {
        middleIndex++;
        middle= middle.next;
      }
      current=current.next;
    }
    return middle;
    }
  }


}
