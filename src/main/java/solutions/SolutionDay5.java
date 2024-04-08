package src.main.java.solutions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import src.main.java.Util;

import java.io.IOException;
import java.util.*;
import java.util.stream.LongStream;

public class SolutionDay5 extends Solution {
    public SolutionDay5() {
        super(5);
    }

    @Override
    public void solve1(boolean isExample) throws IOException {
        var r = getReader(isExample);
        String firstLine, line;
        firstLine = r.readLine();
        var seeds = Util.getLongArray(Util.getSeparatedStrings(firstLine, ":").get(1));
        List<List<RangeMap>> rangeMappers = new ArrayList<>(new ArrayList<>());
        List<long[]> rangeList = new ArrayList<>();

        while ((line = r.readLine()) != null) {
            if (line.isBlank()) {
                List<RangeMap> rangeMapList = new ArrayList<>();
                for (var range : rangeList) {
                    rangeMapList.add(new RangeMap(range));
                }

                rangeList.clear();
                rangeMappers.add(rangeMapList);
                r.readLine();
                continue;
            }
            rangeList.add(Util.getLongArray(line));
        }
        List<RangeMap> rangeMapList = new ArrayList<>();
        for (var range : rangeList) {
            rangeMapList.add(new RangeMap(range));
        }
        rangeList.clear();
        rangeMappers.add(rangeMapList);
        rangeMappers.remove(0);

        var locations = Arrays.stream(seeds).map(seed -> seedToLocation(seed, rangeMappers)).toArray();

        System.out.printf(
                "The locations are %s, so the smallest one = %d",
                Util.getLongArrayAsString(locations), Arrays.stream(locations).min().getAsLong());
    }

    @Override
    public void solve2(boolean isExample) throws IOException {
        var r = getReader(isExample);
        String firstLine, line;
        firstLine = r.readLine();
        var seedRanges = Util.getLongArray(Util.getSeparatedStrings(firstLine, ":").get(1));
        List<List<RangeMap>> rangeMappers = new ArrayList<>(new ArrayList<>());
        List<long[]> rangeList = new ArrayList<>();

        while ((line = r.readLine()) != null) {
            if (line.isBlank()) {
                List<RangeMap> rangeMapList = new ArrayList<>();
                for (var range : rangeList) {
                    rangeMapList.add(new RangeMap(range));
                }

                rangeList.clear();
                rangeMappers.add(rangeMapList);
                r.readLine();
                continue;
            }
            rangeList.add(Util.getLongArray(line));
        }
        List<RangeMap> rangeMapList = new ArrayList<>();
        for (var range : rangeList) {
            rangeMapList.add(new RangeMap(range));
        }
        rangeList.clear();
        rangeMappers.add(rangeMapList);
        rangeMappers.remove(0);

        var seeds = getSeedsFromRange(seedRanges);

        var locations =
                seeds.stream().parallel().map(seed -> seedToLocation(seed, rangeMappers)).toList();

        System.out.printf("The smallest one = %d!\n", Collections.min(locations));
    }

    @AllArgsConstructor
    @Getter
    public class RangeMap {
        long lowerBound, upperBound, offset;

        public RangeMap(long[] range) {
            this(range[1], range[1] + (range[2] - 1), range[0] - range[1]);
        }

        boolean inRange(long num) {
            return num >= lowerBound && num <= upperBound;
        }
    }

    private long seedToLocation(long seed, List<List<RangeMap>> rangeMappers) {
        long currentNum = seed;
        for (var rangeMapper : rangeMappers) {
            currentNum = mapToNext(currentNum, rangeMapper);
        }
        return currentNum;
    }

    private long mapToNext(long num, List<RangeMap> ranges) {
        return ranges.stream()
                .filter(rm -> rm.inRange(num))
                .map(range -> num + range.getOffset())
                .findFirst()
                .orElse(num);
    }

    private List<Long> getSeedsFromRange(long[] seedRanges) {
        OffsetLength[] offsetLengths = new OffsetLength[seedRanges.length / 2];
        for (int i = 0; i < seedRanges.length; i += 2) {
            offsetLengths[i / 2] = new OffsetLength(seedRanges[i], seedRanges[i + 1]);
        }

        long totalLength = Arrays.stream(offsetLengths).mapToLong(OffsetLength::getLength).sum();
        System.out.printf("Total num of seed= %d !\n", totalLength);

        List<Long> seeds = new ArrayList<>();

        for (var offsetLength : offsetLengths) {
            long localOffset = offsetLength.getOffset();
            long length = offsetLength.getLength();

            for (long i = 0; i < length; i++) {
                seeds.add(i + localOffset);
            }
        }

        return seeds;
    }

    @AllArgsConstructor
    @Getter
    public class OffsetLength {
        long offset, length;
    }
}
