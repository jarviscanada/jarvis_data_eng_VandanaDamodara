package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListCycleTest {
  private LinkedListCycle solver = new LinkedListCycle();

  @Test
  public void hasCycleSetApproach() {

    LinkedListCycle.ListNode head = null;
    assertFalse(solver.hasCycleSetApproach(head));
     head = new LinkedListCycle.ListNode(0);
    assertFalse(solver.hasCycleSetApproach(head));
    LinkedListCycle.ListNode current = head;
    LinkedListCycle.ListNode cycle =null;
    for (int i = 0;i<10;i++)
    {
      current.next = new LinkedListCycle.ListNode(i);
      current=current.next;
          if(i==6)
          {
            cycle=current;

          }
          current.next =cycle;
          assertTrue(solver.hasCycleSetApproach(head));
    }
  }

  @Test
  public void hasCyclePointerApproach() {
    LinkedListCycle.ListNode head = null;
    LinkedListCycle.ListNode current = head;
    LinkedListCycle.ListNode cycle =null;
    for (int i = 0;i<10;i++)
    {
      current.next = new LinkedListCycle.ListNode(i);
      current=current.next;
      if(i==6)
      {
        cycle=current;

      }
      current.next =cycle;
      assertTrue(solver.hasCyclePointerApproach(head));
    }
  }
}