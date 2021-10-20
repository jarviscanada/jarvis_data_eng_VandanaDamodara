package ca.jrvs.practice.codingChallenge;

public class CountPrimes {

  public int countPrime(int n) {
    int p = n - 1;

    int numPrimes = p-1;
    boolean[] bool = new boolean[n + 1];
    for (int i = 0; i < bool.length; i++) {
      bool[i] = true;
    }
    for (int i = 2; i < Math.sqrt(n); i++) {
      if (bool[i] == true) {
        for (int j = (i * i); j < n; j = j + i) {
          bool[j] = false;
          numPrimes--;
        }
      }

    }
    return numPrimes;
  }
}