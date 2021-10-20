package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class MissingNumberTest {
private MissingNumber test = new MissingNumber();
  @Test
  public void findBySumming() {
    int[] arr={0,1,2,4};
    assertEquals(3,test.FindBySumming(arr));
  }

  @Test
  public void findUsingSet() {
    int[] arr={0,1,2,4};
    assertEquals(3,test.FindUsingSet(arr));
  }

  @Test
  public void findWithGaussFormula() {
    int[] arr={0,1,2,4};
    assertEquals(3,test.FindWithGaussFormula(arr));
  }
}