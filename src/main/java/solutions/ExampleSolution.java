package src.main.java.solutions;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ExampleSolution extends Solution {
  public ExampleSolution() throws FileNotFoundException {
    super(0);
  }

  @Override
  public void solve1(boolean isExample) throws IOException {
    var reader = this.getReader(isExample);

    String line;
    while ((line = reader.readLine()) != null) {
      System.out.println(line);
    }
  }

  @Override
  public void solve2(boolean isExample) {
    System.out.println("Solution 2");
  }
}
