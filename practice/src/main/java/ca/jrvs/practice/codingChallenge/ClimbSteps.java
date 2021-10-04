package ca.jrvs.practice.codingChallenge;

import java.util.Scanner;

public class ClimbSteps {

  /**
   * https://www.notion.so/jarvisdev/Fibonacci-Number-Climbing-Stairs-345d63b5e5164a70b9e3105154827036#4b3c3aafcb0c4a329d9b38bf63438f31
   * Calculate numnber of steps taken to reach the top.
   *
   * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the to
   * @param args
   */

  public static void main(String[] args) {
    Scanner reader = new Scanner(System.in);
    System.out.print("Enter number of stairs: ");
    String s = reader.next();
    int i = Integer.parseInt(s);
    System.out.println("Number of distinct ways:" + countSteps(i));

  }
public static int countSteps(int n)
{
  if (n==1||n==2)
  {
    return n;

  }
  return countSteps(n-1)+countSteps(n-2);
}
}
