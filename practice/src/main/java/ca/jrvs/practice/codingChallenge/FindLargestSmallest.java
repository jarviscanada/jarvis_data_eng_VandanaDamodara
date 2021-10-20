package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FindLargestSmallest {

  public int Finglargesmallloop(int[] arr, boolean largest)
  {
    if(arr.length == 0)
    throw new IllegalArgumentException("array cannot be empty");
    int maxmin = arr[0];
    for(int num:arr)
    if(largest && maxmin<num)
      maxmin= num;
    else if(!largest && maxmin>num)
      maxmin =num;

    return maxmin;

  }

  public int streamAPI(int[] arr,boolean largest)
  {
  if (arr.length==0)
  throw new IllegalArgumentException("array cannot be empty");
    if (largest)
      return Arrays.stream(arr).max().getAsInt();
    else
      return Arrays.stream(arr).min().getAsInt();
  }

  public  Integer JavaApi(List<Integer> arr,boolean largest)
  {
    if (arr.toArray().length==0)
      throw new IllegalArgumentException("array cannot be empty");
    if (largest)
      return Collections.max(arr);
    else
      return Collections.min(arr);
  }
}
