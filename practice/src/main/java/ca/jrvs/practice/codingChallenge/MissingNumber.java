package ca.jrvs.practice.codingChallenge;

import java.util.HashSet;
import java.util.Set;

public class MissingNumber {
   public int FindBySumming(int[] arr)
   {
     int expectedSum= arr.length;
     int actualsum=0;
     for(int i=0;i<arr.length;i++)
     {
       expectedSum = expectedSum+i;
       actualsum=actualsum+arr[i];

     }
     return expectedSum-actualsum;
   }

   public int FindUsingSet(int[] arr)
   {
     Set<Integer> set = new HashSet<>();
     for(int num:arr)
       set.add(num);
     for(int i=0;i<arr.length;i++)

       if(!set.contains(i))
         return i;
     return arr.length+1;
   }

   public int FindWithGaussFormula(int[] arr)
   {
     int expectedSum= (arr.length*arr.length+arr.length)/2;
     int actualSum=0;
     for (int num:arr)
       actualSum=actualSum+num;
     return expectedSum-actualSum;
   }
}
