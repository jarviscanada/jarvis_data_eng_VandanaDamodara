package ca.jrvs.practice.codingChallenge;

import java.util.Scanner;

public class isPalindrome {

  /**
   * https://www.notion.so/jarvisdev/Valid-Palindrome-f8fb4dcb4aa04889a71f1d20ae08f6e0#149b63f2ca7d4450853202521c8954c6
   *
   *  Given a string s, determine if it is a palindrome, considering only alphanumeric characters and
   *  * ignoring cases.
   *  *
   *  * <p>Example 1:
   *  *
   *  * <p>Input: s = "race a car" Output: false Explanation: "raceacar" is not a palindrome.@param str
   * @return
   */
  public static boolean isPalindromefunct(String str)

{
  int i=0;
  int j = str.length()-1;
  while (i<j)
  {
    if(str.charAt(i)!= str.charAt(j))
      return false;
  i++;
  j--;
  }
  return true;
}
  public static void main(String[] args) {
    Scanner reader = new Scanner(System.in);
    System.out.print("Enter strings to check: ");
    String s = reader.next();
    System.out.println("the palindrome is"+ isPalindromefunct(s));
  }

}
