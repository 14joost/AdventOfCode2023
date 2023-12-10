package src.main.java.solutions;

import src.main.java.Util;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class SolutionDay4 extends Solution {

  public SolutionDay4() {
    super(4);
  }

  @Override
  public void solve1(boolean isExample) throws IOException {
    var r = getReader(isExample);
    String line;
    long score = 0;
    while ((line = r.readLine()) != null) {
      var values = Util.getSeparatedStrings(line, ":").get(1);
      var winningsVsOurs =
          Util.getSeparatedStrings(values, "\\|").stream()
              .filter(number -> !number.isBlank())
              .toList();
      var winningNumbers =
          Util.getSeparatedStrings(winningsVsOurs.get(0), " ").stream()
              .filter(number -> !number.isBlank())
              .toList();
      var ourNumbers = Util.getSeparatedStrings(winningsVsOurs.get(1), " ");

      score += getRowScore(ourNumbers, winningNumbers);
    }
    System.out.printf("We scored %d points!", score);
  }

  @Override
  public void solve2(boolean isExample) throws IOException {
    var r = getReader(isExample);
    String line;
    int numLines = 0;
    while (r.readLine() != null) {
      numLines++;
    }

    int[] cards = new int[numLines];
    Arrays.fill(cards, 1);
    r = getReader(isExample);

    while ((line = r.readLine()) != null) {
      var splitString = Util.getSeparatedStrings(line, ":");
      var cardStrings = Util.getSeparatedStrings(splitString.get(0), " ");
      var game = Integer.parseInt(cardStrings.get(cardStrings.size() - 1)) - 1;
      var values = splitString.get(1);
      var winningsVsOurs =
          Util.getSeparatedStrings(values, "\\|").stream()
              .filter(number -> !number.isBlank())
              .toList();
      var winningNumbers =
          Util.getSeparatedStrings(winningsVsOurs.get(0), " ").stream()
              .filter(number -> !number.isBlank())
              .toList();
      var ourNumbers = Util.getSeparatedStrings(winningsVsOurs.get(1), " ");
      int matches = getNumMatches(ourNumbers, winningNumbers);

      for (int cardNum = 0; cardNum < cards[game]; cardNum++) {
        // System.out.printf("Card %d has %d matches\n", game + 1, matches);
        for (int i = 0; i < matches; i++) {
          cards[game + 1 + i]++;
        }
      }
      System.out.printf("We have in total %d copies of card %d!\n\n", cards[game], game + 1);
    }
    var finalTotalCardCount = Arrays.stream(cards).sum();
    System.out.printf("In total, we ended up with %d cards!", finalTotalCardCount);
  }

  private int getRowScore(List<String> ourNumbers, List<String> winningNumbers) {
    long matches = ourNumbers.stream().filter(winningNumbers::contains).count();
    if (matches == 0) return 0;
    return (int) Math.pow(2, matches - 1);
  }

  private int getNumMatches(List<String> ourNumbers, List<String> winningNumbers) {
    return (int) ourNumbers.stream().filter(winningNumbers::contains).count();
  }
}
