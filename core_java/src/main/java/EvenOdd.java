import java.util.Scanner;

public class EvenOdd {

  /**
   *
   *ticket url
   *https://www.notion.so/jarvisdev/Sample-Check-if-a-number-is-even-or-odd-098b3c6ad04e455cb783d21b41d2dfb4
   *
   */
    public static void main(String[] args) {

      Scanner reader = new Scanner(System.in);

      System.out.print("Enter a number: ");
      int num = reader.nextInt();

      if(num % 2 == 0)
        System.out.println(num + " is even");
      else
        System.out.println(num + " is odd");
    }

}
