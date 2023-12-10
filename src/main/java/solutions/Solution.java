package src.main.java.solutions;

import lombok.Getter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static java.lang.String.format;

@Getter
public abstract class Solution {
  private static final String PATH_PREFIX =
      System.getProperty("user.dir") + "\\src\\main\\resources\\input\\";
  private static final String INPUT_PREFIX = "input_";
  private static final String EXAMPLE_INPUT_PREFIX = "example_input_";
  private static final String FILE_EXTENSION = ".txt";
  private final int day;

  public Solution(int day) {
    this.day = day;
  }

  public abstract void solve1(boolean isExample) throws IOException;

  public abstract void solve2(boolean isExample) throws IOException;

  protected BufferedReader getReader(boolean isExample) throws FileNotFoundException {
    // Creating an object of BufferedReader class
    return isExample
        ? new BufferedReader(
            new FileReader(
                format("%s%s%s%s", PATH_PREFIX, EXAMPLE_INPUT_PREFIX, day, FILE_EXTENSION)))
        : new BufferedReader(
            new FileReader(format("%s%s%s%s", PATH_PREFIX, INPUT_PREFIX, day, FILE_EXTENSION)));
  }
}
