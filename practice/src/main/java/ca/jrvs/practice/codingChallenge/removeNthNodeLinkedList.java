package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.LinkedList;
import jdk.internal.org.objectweb.asm.util.CheckAnnotationAdapter;

public class removeNthNodeLinkedList {

  public static class ListNode {

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

  public static ListNode removeNthNode(ListNode head, int n) {
    ListNode fast = head, slow = head;
    for (int i = 0; i < n; i++)
      fast = fast.next;

    if (fast == null)
      return head.next;
    while (fast.next != null) {
      fast = fast.next;
      slow = slow.next;

    }
    slow = slow.next;
    return head;


  }

  public static void main(String[] args) {
    ListNode head = new ListNode(0);
    removeNthNode(head, 0);

    ListNode temp = head;
    for (int i = 1; i < 10; i++) {
      temp.next = new ListNode(i);
      temp = temp.next;
      head = removeNthNode(head, 10);
      System.out.println(head);


    }
  }
}
