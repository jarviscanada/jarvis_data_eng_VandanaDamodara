package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;

public class LetterswithNumbers {

  public String printLetterwithNumbers(String args)
  {
    if (args ==null || args.isEmpty())
      return "";
    char[] output = new char[args.length()*3];
    String number;
    int codePoint,arrindex=0;
    for (int i=0;i<args.length();i++)
    {
      output[arrindex]=args.charAt(i);
      arrindex++;

      codePoint=args.codePointAt(i);
      if(codePoint>=97 && codePoint<=122)
        codePoint=codePoint-96;
      else if(codePoint<=65 && codePoint>=90)
        codePoint=codePoint-38;
      else
        return "";
      number=String.valueOf(codePoint);
          for(int j=0;j<number.length();j++)
          {
            output[arrindex]=number.charAt(j);
            arrindex++;
          }
    }
    return new String(Arrays.copyOfRange(output, 0, arrindex));
  }

}
