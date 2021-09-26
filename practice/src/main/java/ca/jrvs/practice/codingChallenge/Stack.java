package ca.jrvs.practice.codingChallenge;

import java.util.LinkedList;
import java.util.Queue;

public class Stack {

  /**
   * Implementation of  a last-in-first-out (LIFO) stack using  two queues. The implemented stack  supports all the functions of a normal stack (push, top, pop, and empty).
   * https://www.notion.so/jarvisdev/Implement-Stack-using-Queue-bcc4ebe35ef24ba18a13126eadec5c9c#d827e0e409744cfcb4e34563dba2456e
   *
   * /

  static Queue<Integer> q1 = new LinkedList<>();
  static Queue<Integer> q2 = new LinkedList<>();
  int size = q1.size();
  int size1 = q2.size();

  public void push(int x)
      {
        q2.add(x);
        size1++;
        while (!q1.isEmpty())
        {
          q2.add(q1.peek());
          q1.remove();
          size-- ;
        }
        Queue<Integer> q= q1;
        q1 = q2;
        q2 = q;

      }
  public void pop()
  {
    if (q1.isEmpty())
      return;
    q1.remove();
  }
  public boolean empty() {
        if(q1.isEmpty()) {
          return true;

        }
    return false;
      }

  public int top()
  {
    return q1.peek();
  }

  public static void main(String[] args) {
    Stack s = new Stack();
    s.push(1);
    s.push(2);
    s.push(3);
    System.out.println(s.empty());
    System.out.println(s.top());
    s.pop();
    System.out.println(s.top());
    s.pop();
    System.out.println(s.top());
    s.pop();
    s.empty();
    System.out.println(s.empty());
  }

}
