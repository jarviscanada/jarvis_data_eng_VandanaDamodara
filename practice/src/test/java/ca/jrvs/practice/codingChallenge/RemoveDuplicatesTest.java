package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class RemoveDuplicatesTest {
  private RemoveDuplicates solver = new RemoveDuplicates();

  @Test
  public void twoPointers() {
    int[] arr = {1,2,2,4,4,5,5};
    assertEquals(4,solver.twoPointers(arr));
  }
}