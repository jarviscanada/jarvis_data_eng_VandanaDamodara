package ca.jrvs.practice.codingChallenge;

import java.util.Stack;

public class validParenthesis {

  /**
   * https://www.notion.so/jarvisdev/Valid-Parentheses-2fc4f0bada664aa497fdf26498b861fa#26022e44f97f4301bfc5d5cc407d797f
   *  string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
   * @param s
   * @return
   */

  public static boolean isValid(String s) {
    int length = s.length();
    char[] array = s.toCharArray();
    if (length == 0)
      return true;
    Stack<Character> stack = new Stack<>();
    for (int i = 0; i < length; i++) {
      if (array[i] == '(' || array[i] == '{' || array[i] == '[') {
        stack.push(array[i]);
      }
      if (array[i] == ')' || array[i] == '}' || array[i] == ']') {
        if (stack.empty())
          return false;
        char temp = stack.pop();
        if ((temp == '(' && array[i] == ')') || (temp == '{' && array[i] == '}') || (temp == '['
            && array[i] == ']')) {
          continue;
        } else {
          return false;
        }
      }
    }
    if (!stack.empty())
      return false;
    return true;

  }

  public static void main(String[] args) {
    System.out.println(isValid("()"));
  }
}
