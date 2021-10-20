package ca.jrvs.practice.codingChallenge;

public class SwapTwoNumbers {
  public int[] swapByBits(int[] nums)
  {
    nums[0] =nums[0]^nums[1];
    nums[1]=nums[0]^nums[1];
    nums[0]=nums[0]^nums[1];
    return nums;
  }
  public int[] swapByArithmetic(int[] nums)
  {
    nums[0]=nums[0]+nums[1];
    nums[1]=nums[0]-nums[1];
    nums[0]=nums[0]-nums[1];
    return nums;
  }
}
