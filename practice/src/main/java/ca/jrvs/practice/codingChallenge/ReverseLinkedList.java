package ca.jrvs.practice.codingChallenge;

import ca.jrvs.practice.dataStructure.LinkedJList;
import java.util.LinkedList;

public class ReverseLinkedList {

  public class ListNode {

    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }

  public ListNode reverseListRecursion(ListNode head) {
    if (head == null)
      return null;
    if (head.next == null)
      return head;
    ListNode result = reverseListRecursion(head.next);
    head.next.next = head;
    head.next = null;
    return result;
  }
  public ListNode reverseListiteration(ListNode head){
  if (head == null || head.next == null) {
    return head;
  }

  ListNode listToDo = head.next;
  ListNode reversedList = head;

  reversedList.next = null;

    while (listToDo != null) {
    ListNode temp = listToDo;
    listToDo = listToDo.next;

    temp.next = reversedList;
    reversedList = temp;
  }

    return reversedList;
}

}