package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class CountPrimesTest {
private CountPrimes solver = new CountPrimes();

  @Test
  public void countPrime() {

  assertEquals(5,solver.countPrime(12));
  }
}