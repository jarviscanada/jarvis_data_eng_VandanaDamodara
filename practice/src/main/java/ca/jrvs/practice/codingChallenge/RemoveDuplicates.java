package ca.jrvs.practice.codingChallenge;

public class RemoveDuplicates {

  public int twoPointers(int[] arr) {
    if (arr.length == 0)
      return 0;
    else if (arr.length == 1)
      return 1;
    int previous = arr[0];
    int i = 1;
    int j = 1;
    for (i = 1; i < arr.length; i++) {
      if (arr[i] != previous) {
        arr[j] = arr[i];
        j++;
      }
      previous = arr[i];


    }
    return j;
  }
}
