package ca.jrvs.practice.codingChallenge;

import ca.jrvs.practice.dataStructure.LinkedJList;
import java.util.HashSet;
import java.util.Set;

public class DuplicateLinkedListNode {

  /**
   *
   *  Ticket link: https://www.notion.so/jarvisdev/Duplicate-LinkedList-Node-062094aea1dd4e25969afb91d06c3d5e
   * Complexity: O(n)
   * Justification: Only traverse all linked list nodes once
   */
  public void removeDuplicateLinkedList(LinkedJList<Integer> List)
  {
    Set<Integer> set = new HashSet<>();
    int i =0;
    while (i<List.size())
    {
      if(set.contains(List.get(i)))
        List.remove(i);
      else
      {
        set.add(List.get(i));
        i++;
      }
    }
  }

}
