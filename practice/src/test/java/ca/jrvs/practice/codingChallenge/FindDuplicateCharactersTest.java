package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class FindDuplicateCharactersTest {
private FindDuplicateCharacters solver = new FindDuplicateCharacters();
  @Test
  public void findDuplicate() {
    String arr = "abc";
    assertEquals(arr,solver.findDuplicate("abcabcd"));
  }
}