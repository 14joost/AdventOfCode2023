package solutions;

import lombok.Getter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static java.lang.String.format;

@Getter
public abstract class Solution {
  private static final String PATH_PREFIX =
      System.getProperty("user.dir") + "\\com.joost.public\\src\\main\\resources\\input\\";
  private static final String INPUT_PREFIX = "input_";
  private static final String EXAMPLE_INPUT_PREFIX = "example_input_";
  private static final String FILE_EXTENSION = ".txt";
  private final int day;
  private final BufferedReader inputReader;
  private final BufferedReader exampleInputReader;

  public Solution(int day) throws FileNotFoundException {
    this.day = day;
    this.inputReader =
        new BufferedReader(
            new FileReader(format("%s%s%s%s", PATH_PREFIX, INPUT_PREFIX, day, FILE_EXTENSION)));
    this.exampleInputReader =
        new BufferedReader(
            new FileReader(
                format("%s%s%s%s", PATH_PREFIX, EXAMPLE_INPUT_PREFIX, day, FILE_EXTENSION)));
  }

  public abstract void solve(boolean isExample) throws IOException;

  protected BufferedReader getReader(boolean isExample) {
    // Creating an object of BufferedReader class
    return isExample ? inputReader : exampleInputReader;
  }
}
