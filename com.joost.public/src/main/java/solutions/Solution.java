package solutions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static java.lang.String.format;

public abstract class Solution {
  private static final String INPUT_PREFIX = "input_";
  private static final String EXAMPLE_INPUT_PREFIX = "example_input_";
  private final int day;
  private final String inputFile;
  private final String exampleInputFile;

  public Solution(int day) {
    this.day = day;
    this.inputFile = format("%s%s", INPUT_PREFIX, day);
    this.exampleInputFile = format("%s%s", EXAMPLE_INPUT_PREFIX, day);
  }

  public abstract void solve(boolean isExample) throws FileNotFoundException;

  protected BufferedReader getReader(boolean isExample) throws FileNotFoundException {
    // Creating an object of BufferedReader class
    return isExample
        ? new BufferedReader(new FileReader(exampleInputFile))
        : new BufferedReader(new FileReader(inputFile));
  }
}
