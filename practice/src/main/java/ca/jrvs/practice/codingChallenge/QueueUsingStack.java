package ca.jrvs.practice.codingChallenge;

import java.util.ArrayDeque;
import java.util.Deque;


public class QueueUsingStack {
  /**
   * https://www.notion.so/jarvisdev/Implement-Queue-using-Stacks-ae828e3b35c74b798dfa3622007ad1b1#b0eedaeb9cc14042986b83f51a681ad0
   *Implementation of  a first in first out (FIFO) queue using only two stacks. The implemented queue supports all the functions of a normal queue (push, peek, pop, and empty).
   *
   */

  public int front;
  Deque<Integer> s1 = new ArrayDeque<>();
  Deque<Integer> s2 = new ArrayDeque<>();

  public void pushenqueue(int x)
  {
    if (s1.isEmpty())
      front = x;
    while (!s1.isEmpty())
    {
      s2.push(s1.pop());
    }
  s2.push(x);
    while (!s2.isEmpty())
    {
      s1.push(s2.pop());
    }
  }
  public int popdequeue()
  {

    int front= s1.peek();
    if (!s1.isEmpty())
      front = s1.peek();
    s1.pop();
    return front;

  }
  public boolean empty() {
    return s1.isEmpty();
  }

  public static void main(String[] args) {
    QueueUsingStack q = new QueueUsingStack();
    q.pushenqueue(3);
    q.pushenqueue(2);
    q.pushenqueue(1);

    System.out.print(q.popdequeue());
    System.out.print(q.popdequeue());
    System.out.println((q.empty()));
  }

}
