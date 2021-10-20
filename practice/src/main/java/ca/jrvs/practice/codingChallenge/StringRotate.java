package ca.jrvs.practice.codingChallenge;


import java.util.Scanner;

public class StringRotate {

  /**
   * https://www.notion.so/jarvisdev/Rotate-String-c01dca1d12a34b62b1dbeb02505e3658#9e991609e13442bb91fc0dc0fded9d51
   * String rotation
   * @param args
   */
  public static void main(String[] args) {


    Scanner reader = new Scanner(System.in);
    String s = reader.next();
    int len = s.length();
    char[] outstring = s.toCharArray();

    for (int i = len-1; i >= 0 ; i--) {
      System.out.print(outstring[i]);

    }

  }

}