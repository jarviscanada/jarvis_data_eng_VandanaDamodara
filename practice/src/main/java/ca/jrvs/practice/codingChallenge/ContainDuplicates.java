package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ContainDuplicates {
  public boolean duplicatesorting(int[] nums) {
    Arrays.sort(nums);
    for (int i = 0; i < nums.length; i++)
      for (int j = 1; j < nums.length; j++) {
        if (nums[i] == nums[j])
          return true;
      }

    return false;
  }
public boolean duplicateset(int[] arr)
{
  Arrays.sort(arr);
  Set<Integer> set = new HashSet<>();
  for (int num: arr)
    if(set.contains(num))
      return  true;
    else
      set.add(num);
    return false;
}

}
