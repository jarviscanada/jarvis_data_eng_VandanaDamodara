package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class RemoveElementTest {
  private RemoveElement solver = new RemoveElement();

  @Test
  public void removeElement() {
    int[] arr = {1,2,4,5,7,4,4};
    assertEquals(4,solver.removeElement(arr,4));

  }
}