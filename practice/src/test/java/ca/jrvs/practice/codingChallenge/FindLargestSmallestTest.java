package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class FindLargestSmallestTest {

  private FindLargestSmallest solver = new FindLargestSmallest();

  @Test
  public void finglargesmallloop() {
    int[] arr= {2,3,5,7};
   assertEquals(7,solver.Finglargesmallloop(arr,true));
  }

  @Test
  public void streamAPI() {
    int[] arr= {2,3,5,7};
    assertEquals(2,solver.streamAPI(arr,false));
  }

  @Test
  public void javaApi() {
  }
}