package ca.jrvs.practice.codingChallenge;

public class CheckdigitStrings {

  public boolean isDigitAscii(String s)
  {
    for (int i=0;i<s.length();i++)
    {
      if(s.codePointAt(i)<48|| s.codePointAt(i)>57) {
        return false;
      }

    }
    return true;
  }
  public boolean isDigitJava(String s)
  {
    boolean isInteger = true;
    try {
      Integer.parseInt(s);
    } catch (NumberFormatException e) {
      isInteger = false;
    }
    return isInteger;
  }

  public boolean checkStringRegex(String s) {
    return s.matches("[0-9]+");
  }
}
