package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;

public class compareMaps {

  /**
   * https://www.notion.so/jarvisdev/How-to-compare-two-maps-bee4742667794df0a54c59cdce0d82c6#bb88133dc2064471a95628b177401794
   * @param args
   */

  public static void main(String[] args) {

    HashMap<Integer, String> map1 = new HashMap<>();

    map1.put(1, "A");
    map1.put(2, "B");
    map1.put(3, "C");

    HashMap<Integer, String> map2 = new HashMap<>();

    map2.put(3, "C");
    map2.put(1, "A");
    map2.put(2, "B");

    HashMap<Integer, String> map3 = new HashMap<>();

    map3.put(1, "A");
    map3.put(2, "B");
    map3.put(3, "C");
    map3.put(3, "D");

    System.out.println(compareMaps(map1,map2));
    System.out.println(compareMaps(map2,map3));
  }

  public static boolean compareMaps(HashMap m, HashMap n)
  {
    return m.equals(n);
  }
}
