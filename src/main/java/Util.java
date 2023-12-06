package src.main.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class Util {
  public static Map<Integer, String> getDigitToStringMap() {
    return Map.of(
        1, "one", 2, "two", 3, "three", 4, "four", 5, "five", 6, "six", 7, "seven", 8, "eight", 9,
        "nine");
  }

  public static List<String> getSeparatedStrings(String string, String separator, boolean trim) {
    List<String> strings = new ArrayList<>();
    var stringArray = string.split(separator);
    if (trim) {
      for (String s : stringArray) {
        strings.add(s.trim());
      }
    } else {
      strings.addAll(Arrays.asList(stringArray));
    }
    return strings;
  }

  public static List<String> getSeparatedStrings(String string, String separator) {
    return getSeparatedStrings(string, separator, true);
  }

  public static String getStringRepresentation(List<Character> list) {
    StringBuilder builder = new StringBuilder(list.size());
    for (Character ch : list) {
      builder.append(ch);
    }
    return builder.toString();
  }
}
