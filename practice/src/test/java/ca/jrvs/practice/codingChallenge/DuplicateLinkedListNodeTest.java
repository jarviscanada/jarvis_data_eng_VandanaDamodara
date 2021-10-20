package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import ca.jrvs.practice.dataStructure.LinkedJList;
import org.junit.Test;

public class DuplicateLinkedListNodeTest {

  final DuplicateLinkedListNode removedNode = new DuplicateLinkedListNode();
  @Test
  public void removeDuplicateLinkedList() {
    LinkedJList<Integer> list = new LinkedJList<>();
    removedNode.removeDuplicateLinkedList(list);
    assertEquals(0,list.size());
        list.add(1);
    list.add(2);
    list.add(1);
    list.add(2);
    removedNode.removeDuplicateLinkedList(list);
    assertEquals(2,list.size());
  }
}