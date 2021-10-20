package ca.jrvs.practice.codingChallenge;

public class ValidAnagram {
  /**
   *
   *
   *https://www.notion.so/jarvisdev/Valid-Anagram-6f57a5acf5434cf4bac694c5cca27e30#a826aa15f2364b3db918c5a51d1a4a92
   *
   */

  public static boolean isAnagram(String s, String t)
  {


    if(s==null || t==null)
      return false;
    if(s.length()!= t.length())
      return false;
    int[] arr = new int[26];
    for (int i=0;i<s.length();i++) {
      arr[s.charAt(i)-'a']++;
      System.out.println(arr);
      arr[t.charAt(i)-'a']--;
      System.out.println(arr);
    }
    for (int i : arr)
    {
      if(i!=0)
        return false;
    }
          return true;
  }

  public static void main(String[] args) {
 System.out.println(isAnagram("love","vol"));
  }
}
