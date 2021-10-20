package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FindDuplicateNumber {

  public int findDuplicatesorting(int[] arr)

  {
    Arrays.sort(arr);

    int prev =0;
    for(int num:arr)

      if(num== prev)
        return  num;
      else
        prev=num;


  return 0;
  }
public int findDuplicateSet(int[] nums)
{
  Set<Integer> set = new HashSet<>();
  for(int num:nums)
    if(set.contains(num))
      return num;
    else
      set.add(num);

    return  0;
}
}
