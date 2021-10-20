package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class ContainDuplicatesTest {
  private ContainDuplicates solver = new ContainDuplicates();


  @Test
  public void duplicatesorting() {
    int[] arr ={1,2,3,1,2};
    assertEquals(true,solver.duplicatesorting(arr));
  }

  @Test
  public void duplicateset() {
    int[] arr ={1,2,3,1,2};
    assertEquals(true,solver.duplicateset(arr));

  }
}