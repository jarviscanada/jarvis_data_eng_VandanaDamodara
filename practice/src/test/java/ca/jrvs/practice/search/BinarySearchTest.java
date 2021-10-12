package ca.jrvs.practice.search;

import static org.junit.Assert.*;

import java.util.Optional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BinarySearchTest {
  private Integer[] arr;
  private BinarySearch binarySearch;

  @Before
  public void setUp() throws Exception {
    arr = new Integer[]{5,6,7,8,9};
    binarySearch = new BinarySearch();
  }

  @Test
  public void binarySearchIteration() {
    Assert.assertEquals(Optional.of(0),binarySearch.binarySearchIteration(arr,5));
    Assert.assertEquals(Optional.of(1),binarySearch.binarySearchIteration(arr,6));
    Assert.assertEquals(Optional.of(2),binarySearch.binarySearchIteration(arr,7));
    Assert.assertEquals(Optional.of(3),binarySearch.binarySearchIteration(arr,8));
    Assert.assertEquals(Optional.of(4),binarySearch.binarySearchIteration(arr,9));

  }

  @Test
  public void binarySearchRecursion() {
    Assert.assertEquals(Optional.of(0),binarySearch.binarySearchRecursion(arr,5));
    Assert.assertEquals(Optional.of(1),binarySearch.binarySearchRecursion(arr,6));
    Assert.assertEquals(Optional.of(2),binarySearch.binarySearchRecursion(arr,7));
    Assert.assertEquals(Optional.of(3),binarySearch.binarySearchRecursion(arr,8));
    Assert.assertEquals(Optional.of(4),binarySearch.binarySearchRecursion(arr,9));
  }
}