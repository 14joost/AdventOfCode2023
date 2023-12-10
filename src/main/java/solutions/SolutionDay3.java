package src.main.java.solutions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import src.main.java.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SolutionDay3 extends Solution {
  int x = 0;
  int y = 0;
  List<Coord> coordinates;

  public SolutionDay3() {
    super(3);
  }

  private List<Number> getNumbers(boolean isExample) throws IOException {
    var r = getReader(isExample);
    String line;
    coordinates = new ArrayList<>();
    y = 0;
    while ((line = r.readLine()) != null) {
      x = 0;
      for (char c : line.toCharArray()) {
        coordinates.add(new Coord(x, y, c));
        x++;
      }
      y++;
    }

    List<Number> numbers = new ArrayList<>();
    boolean firstDigit = true;

    for (Coord coord : coordinates) {
      if (firstDigit && coord.hasDigit()) {
        List<Character> charList = new ArrayList<>();
        var number = new Number();
        number.setX1(coord.getX());
        number.setYNum(coord.getY());
        charList.add(coord.getValue());
        int nextX = coord.getX() + 1;
        while (nextX < x && coordinates.get(index(nextX, coord.getY())).hasDigit()) {
          charList.add(coordinates.get(index(nextX, coord.getY())).getValue());
          nextX++;
        }
        number.setValue(Integer.parseInt(Util.getStringRepresentation(charList)));
        number.setX2(nextX - 1);
        numbers.add(number);

        firstDigit = false;
      }
      if (coord.getX() >= x - 1 || !coord.hasDigit()) {
        firstDigit = true;
      }
    }
    return numbers;
  }

  @Override
  public void solve1(boolean isExample) throws IOException {

    var numbers = getNumbers(isExample);
    int sum = numbers.stream().filter(Number::hasSymbolNeighbour).mapToInt(Number::getValue).sum();
    System.out.printf("The sum of the part numbers = %d!", sum);
  }

  @Override
  public void solve2(boolean isExample) throws IOException {

    var numbers = getNumbers(isExample);

    var possibleGearNumberMap = new HashMap<Coord, List<Number>>();
    for (var possibleGear : coordinates.stream().filter(Coord::isPossibleGear).toList()) {
      possibleGearNumberMap.put(possibleGear, new ArrayList<>());
    }

    for (var number : numbers) {
      for (var possibleGearNeighbour : number.getPossibleGearNeighbours()) {
        possibleGearNumberMap.get(possibleGearNeighbour).add(number);
      }
    }

    int powerSum =
        possibleGearNumberMap.values().stream()
            .filter(numberList -> numberList.size() == 2)
            .mapToInt(numberList -> numberList.get(0).getValue() * numberList.get(1).getValue())
            .sum();

    System.out.printf("The sum of the all gear ratios = %d!", powerSum);
  }

  @AllArgsConstructor
  @Getter
  @Setter
  public static class Coord {
    int x, y;
    Character value;

    public boolean hasSymbol() {
      return !(value.equals('.') || Character.isDigit(value));
    }

    public boolean hasDigit() {
      return Character.isDigit(value);
    }

    public boolean isPossibleGear() {
      return value.equals('*');
    }
  }

  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  @Setter
  public class Number {
    int x1, x2, yNum;
    int value;

    public boolean hasSymbolNeighbour() {
      return getNeighbours().stream().anyMatch(Coord::hasSymbol);
    }

    public List<Coord> getPossibleGearNeighbours() {
      return getNeighbours().stream().filter(Coord::isPossibleGear).toList();
    }

    private List<Coord> getNeighbours() {
      int startX = Math.max(0, x1 - 1);
      int endX = Math.min(x - 1, x2 + 1);
      int startY = Math.max(0, yNum - 1);
      int endY = Math.min(y - 1, yNum + 1);
      List<Coord> neighbours = new ArrayList<>();

      for (int i = startX; i <= endX; i++) {
        for (int j = startY; j <= endY; j++) {
          if (j == yNum && (i >= x1 && i <= x2)) continue;
          neighbours.add(coordinates.get(index(i, j)));
        }
      }
      return neighbours;
    }
  }

  private int index(int i, int j) {
    return j * y + i;
  }
}
