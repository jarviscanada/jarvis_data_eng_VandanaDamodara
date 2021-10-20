package ca.jrvs.practice.codingChallenge;

import java.util.ArrayList;
import java.util.LinkedList;
import jdk.internal.org.objectweb.asm.util.CheckAnnotationAdapter;

class ListNode {

  int val;
  ListNode next;

  ListNode(int x) {
    val = x;
    next = null;
  }
}
//public class NodeCycle {
  /**
   * Definition for singly-linked list.
   * class ListNode {
   *     int val;
   *     ListNode next;
   *     ListNode(int x) {
   *         val = x;
   *         next = null;
   *     }
   * }
   */
 /** public ListNode()
    public boolean hasCycle(ListNode head) {
      ArrayList<ListNode> arr = new ArrayList<>();
      ListNode node = head;
      while (node != null && node.next != null) {
        if (arr.contains(node)) {
          return true;
        }
        arr.add(node);
        node = node.next;
      }
      return false;
    }
  }

  public static void main(String[] args) {
    ListNode head = null;

    head.add(head, 20);
    head = push(head, 4);
    head = push(head, 15);
    head = push(head, 10);

    // Create a loop for testing
    head.next.next.next.next = head;

    if (detectLoop(head))
      System.out.print("Loop found");
    else
      System.out.print("No Loop");
  }
}

  }

}
**/