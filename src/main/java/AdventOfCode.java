package src.main.java;

import src.main.java.solutions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdventOfCode {
  public static void main(String[] args) throws IOException {

    int day = 5;
    boolean isExample = false;

    // getSolutions().get(day).solve1(isExample);
    System.out.println();
    getSolutions().get(day).solve2(isExample);
  }

  private static List<Solution> getSolutions() {

    List<Solution> solutions = new ArrayList<>();
    Solution exampleSolution = new ExampleSolution();
    solutions.add(exampleSolution);

    var solutionDay1 = new SolutionDay1();
    solutions.add(solutionDay1);
    var solutionDay2 = new SolutionDay2();
    solutions.add(solutionDay2);
    var solutionDay3 = new SolutionDay3();
    solutions.add(solutionDay3);
    var solutionDay4 = new SolutionDay4();
    solutions.add(solutionDay4);
    var solutionDay5 = new SolutionDay5();
    solutions.add(solutionDay5);

    return solutions;
  }
}
