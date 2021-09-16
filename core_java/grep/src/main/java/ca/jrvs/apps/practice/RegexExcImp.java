package ca.jrvs.apps.practice;
import java.io.File;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class RegexExcImp implements RegexExc{
  public boolean matchIp(String ip)
  {
   return ip.matches(
       "([0-9][0-9][0-9]\\.[0-9][0-9][0-9]\\.[0-9][0-9][0-9]\\.[0-9][0-9][0-9])|([0-9][0-9]\\.[0-9][0-9]\\.[0-9][0-9]\\.[0-9][0-9])|([0-9]\\.[0-9]\\.[0-9]\\.[0-9])");
  }
  public boolean matchJpeg(String filename)

  {
    return filename.matches("([a-z]|[A-Z])+\\.+[j]+[p]+(g|eg)");
  }

  public boolean isEmptyLine(String line)

  {
    return line.matches("^\\s*$");
  }


  public static void main(String[] args) {
    String ipTest = "60.30.40.50";
    String jpgTest = "fold.jpeg";
    String emptyTest = "  \n";
    RegexExcImp test = new RegexExcImp();
    test.matchIp(ipTest);
    test.matchJpeg(jpgTest);
    test.isEmptyLine(emptyTest);

  }
}