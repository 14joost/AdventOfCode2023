package src.main.java.solutions;

import src.main.java.Util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SolutionDay2 extends Solution {
  public SolutionDay2() throws FileNotFoundException {
    super(2);
  }

  @Override
  public void solve1(boolean isExample) throws IOException {
    var r = getReader(isExample);
    String line;
    int game;
    int possibleGameIdSum = 0;
    var blocks = Map.of("red", 12, "green", 13, "blue", 14);
    while ((line = r.readLine()) != null) {
      var gameStrings = Util.getSeparatedStrings(line, ":");
      game = Integer.parseInt(Util.getSeparatedStrings(gameStrings.get(0), " ").get(1));
      var reveals = Util.getSeparatedStrings(gameStrings.get(1), ";");
      System.out.printf("Game %d ", game);
      if (!isGameInpossible(reveals, blocks)) {
        possibleGameIdSum += game;
      }
    }
    System.out.printf("\nThe sum of the id's of the possible games is thus: %d", possibleGameIdSum);
  }

  @Override
  public void solve2(boolean isExample) throws IOException {
    var r = getReader(isExample);
    String line;
    int game;
    int powerSum = 0;
    while ((line = r.readLine()) != null) {
      var gameStrings = Util.getSeparatedStrings(line, ":");
      game = Integer.parseInt(Util.getSeparatedStrings(gameStrings.get(0), " ").get(1));
      var reveals = Util.getSeparatedStrings(gameStrings.get(1), ";");
      var map = getMinimumBlocksForGame(reveals);
      int red = map.get("red");
      int green = map.get("green");
      int blue = map.get("blue");
      int power = red * green * blue;
      System.out.printf(
          "Game %d minimum blocks: red=%d, green=%d, blue=%d!\n", game, red, green, blue);
      System.out.printf(
          "This means that the power of the set = %d*%d*%d = %d!\n\n", red, green, blue, power);
      powerSum += power;
    }
    System.out.printf("\nThe sum of the powers is thus: %d", powerSum);
  }

  private boolean isGameInpossible(List<String> reveals, Map<String, Integer> blocks) {
    for (String reveal : reveals) {
      var pull = Util.getSeparatedStrings(reveal, ",");
      for (String blockCount : pull) {
        var blockCountSep = Util.getSeparatedStrings(blockCount, " ");
        var block = blockCountSep.get(1);
        int count = Integer.parseInt(blockCountSep.get(0));
        if (count > blocks.get(block)) {
          System.out.printf("is impossible! %d %s blocks were pulled\n", count, block);
          return true;
        }
      }
    }
    System.out.println("is very possible!");
    return false;
  }

  private Map<String, Integer> getMinimumBlocksForGame(List<String> reveals) {
    var minBlocksMap = new HashMap<>(Map.of("red", 0, "green", 0, "blue", 0));
    for (String reveal : reveals) {
      var pull = Util.getSeparatedStrings(reveal, ",");
      for (String blockCount : pull) {
        var blockCountSep = Util.getSeparatedStrings(blockCount, " ");
        var block = blockCountSep.get(1);
        int count = Integer.parseInt(blockCountSep.get(0));
        minBlocksMap.put(block, Math.max(count, minBlocksMap.get(block)));
      }
    }
    return minBlocksMap;
  }
}
