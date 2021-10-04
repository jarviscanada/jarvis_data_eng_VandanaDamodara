package ca.jrvs.practice.codingChallenge;

import java.util.Scanner;

public class twoSum {

  /**
   * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target
   * @param args
   */
  public static void main(String[] args) {
    int[] num = {5, 6, 7, 8, 9, 3};
    Scanner reader = new Scanner(System.in);
    System.out.print("Enter target number");
    String s = reader.next();
    int i = Integer.parseInt(s);
    int[] out = twoSumindex(num , i);
    System.out.println(out[0] + " " +out[1]);
  }

  public static int[] twoSumindex(int[] nums, int k) {
    for (int l = 0; l <= nums.length; l++) {
      for (int j = 0 ; j <= nums.length; j++) {
        if (nums[l] + nums[j] == k) {
          return new int[]{l, j};

          }
      }

    }
return null;
  }
  //return null;

}
