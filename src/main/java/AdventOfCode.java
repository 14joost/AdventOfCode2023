package src.main.java;

import src.main.java.solutions.ExampleSolution;
import src.main.java.solutions.Solution;
import src.main.java.solutions.SolutionDay1;
import src.main.java.solutions.SolutionDay2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdventOfCode {
  public static void main(String[] args) throws IOException {

    int day = 2;
    boolean isExample = false;

    // getSolutions().get(day).solve1(isExample);
    getSolutions().get(day).solve2(isExample);
  }

  private static List<Solution> getSolutions() throws FileNotFoundException {

    List<Solution> solutions = new ArrayList<>();
    Solution exampleSolution = new ExampleSolution();
    solutions.add(exampleSolution);

    Solution solutionDay1 = new SolutionDay1();
    solutions.add(solutionDay1);
    Solution solutionDay2 = new SolutionDay2();
    solutions.add(solutionDay2);

    return solutions;
  }
}
