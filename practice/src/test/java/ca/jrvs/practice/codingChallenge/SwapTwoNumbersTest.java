package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class SwapTwoNumbersTest {
  private SwapTwoNumbers solver= new SwapTwoNumbers();


  @Test
  public void swapByBits() {
    int[] arr={5,6};
    assertEquals(6,solver.swapByBits(arr)[0]);
  }

  @Test
  public void swapByArithmetic() {
    int[] arr={7,8};
    assertEquals(7,solver.swapByArithmetic(arr)[1]);
  }
}