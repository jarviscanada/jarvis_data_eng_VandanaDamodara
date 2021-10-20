package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class LetterswithNumbersTest {
  private LetterswithNumbers solver = new LetterswithNumbers();

  @Test
  public void printLetterwithNumbers() {
    assertEquals("a1b2c3",solver.printLetterwithNumbers("abc"));

  }
}