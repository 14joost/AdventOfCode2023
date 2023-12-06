package src.main.java;

import src.main.java.solutions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdventOfCode {
  public static void main(String[] args) throws IOException {

    int day = 3;
    boolean isExample = false;

    // getSolutions().get(day).solve1(isExample);
    getSolutions().get(day).solve2(isExample);
  }

  private static List<Solution> getSolutions() throws FileNotFoundException {

    List<Solution> solutions = new ArrayList<>();
    Solution exampleSolution = new ExampleSolution();
    solutions.add(exampleSolution);

    var solutionDay1 = new SolutionDay1();
    solutions.add(solutionDay1);
    var solutionDay2 = new SolutionDay2();
    solutions.add(solutionDay2);
    var solutionDay3 = new SolutionDay3();
    solutions.add(solutionDay3);

    return solutions;
  }
}
