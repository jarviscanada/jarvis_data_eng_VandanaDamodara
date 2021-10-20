package ca.jrvs.practice.codingChallenge;

public class RemoveElement {

  public int removeElement(int[] arr,int val)
  {
    int j =0;
    for (int i =0;i<arr.length;i++)
      if(arr[i]!=val){
        if(i!=j)
          arr[j]=arr[i];
        j++;
      }
    return j;
  }

}
