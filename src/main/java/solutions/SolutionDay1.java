package src.main.java.solutions;

import src.main.java.Util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class SolutionDay1 extends Solution {
  public SolutionDay1() throws FileNotFoundException {
    super(1);
  }

  @Override
  public void solve1(boolean isExample) throws IOException {

    var r = getReader(isExample);
    String line;
    int sum = 0;
    while ((line = r.readLine()) != null) {
      sum += getSumFirstLastDigit(line);
    }

    System.out.printf("Sum = %d!%n", sum);
  }

  public void solve2(boolean isExample) throws IOException {
    var r = getReader(isExample);
    String line;
    int sum = 0;
    while ((line = r.readLine()) != null) {
      System.out.printf("%d\n", getSumFirstLastDigitOrWrittenNumber(line));
      sum += getSumFirstLastDigitOrWrittenNumber(line);
    }

    System.out.printf("Sum = %d!%n", sum);
  }

  static int getSumFirstLastDigit(String line) {
    var charArray = line.toCharArray();
    char[] charray = new char[2];

    for (int i = 0; i < charArray.length; i++) {
      if (Character.isDigit(charArray[i])) {
        charray[0] = charArray[i];
        break;
      }
    }
    for (int i = charArray.length - 1; i >= 0; i--) {
      if (Character.isDigit(charArray[i])) {
        charray[1] = charArray[i];
        break;
      }
    }
    return Integer.parseInt(new String(charray));
  }

  static int getSumFirstLastDigitOrWrittenNumber(String line) {
    var charArray = line.toCharArray();
    char[] charray = new char[2];
    var d2sMap = Util.getDigitToStringMap();

    for (int i = 0; i < charArray.length; i++) {
      if (Character.isDigit(charArray[i])) {
        charray[0] = charArray[i];
        break;
      }
      String substring = new String(Arrays.copyOfRange(charArray, 0, i + 1));
      boolean found = false;
      for (var d2sEntry : d2sMap.entrySet()) {
        if (substring.contains(d2sEntry.getValue())) {
          charray[0] = Character.forDigit(d2sEntry.getKey(), 10);
          found = true;
          break;
        }
      }
      if (found) break;
    }
    for (int i = charArray.length - 1; i >= 0; i--) {
      if (Character.isDigit(charArray[i])) {
        charray[1] = charArray[i];
        break;
      }
      String substring = new String(Arrays.copyOfRange(charArray, i, charArray.length));
      boolean found = false;
      for (var d2sEntry : d2sMap.entrySet()) {
        if (substring.contains(d2sEntry.getValue())) {
          charray[1] = Character.forDigit(d2sEntry.getKey(), 10);
          found = true;
          break;
        }
      }
      if (found) break;
    }
    return Integer.parseInt(new String(charray));
  }
}
