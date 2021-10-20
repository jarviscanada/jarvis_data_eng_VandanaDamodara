package ca.jrvs.practice.codingChallenge;

import java.io.IOException;
import java.util.Scanner;

public class StringToInt {

  /**
   * ticket url https://www.notion.so/jarvisdev/String-to-Integer-atoi-f8671f1036494b1f93b94088c843a59c
   */

  public static void main(String[] args) {
    Scanner reader = new Scanner(System.in);
    System.out.print("Enter a string: ");
    String s = reader.next();
   try {
      //Converting String into int using Integer.parseInt()
      int i = Integer.parseInt(s);
      //int i=Integer.valueOf(s);
     System.out.print(i);
      int l = 0;
      int num = 0;
      boolean isNeg = false;

      if (s.charAt(0) == '-') {
        isNeg = true;
        l = 1;

      }
    for(; l<s.length(); l++) {
        num = num * 10;
        num += s.charAt(l)-48;

      }
      if (isNeg) {
        num = -num;
      }
      System.out.println(num);
   } catch (NumberFormatException e) {

      //System.out.println("Enter a valid number");
   }


  }
}
