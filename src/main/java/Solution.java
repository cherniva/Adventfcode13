import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Solution {

    private Scanner getScanner(String pathname) throws FileNotFoundException {
        File file = new File(pathname);
        return new Scanner(file);
    }

    private String[] rotatePattern(String[] pattern) {
        StringBuilder[] rotatedPatternBuilder = new StringBuilder[pattern[0].length()];
        for (int i = 0; i < rotatedPatternBuilder.length; i++) {
            rotatedPatternBuilder[i] = new StringBuilder();
        }
        Arrays.stream(pattern)
                .forEach(str -> {
                    for (int i = 0; i < str.length(); i++)
                        rotatedPatternBuilder[i].append(str.charAt(i));
                });
        return Arrays.stream(rotatedPatternBuilder)
                .map(StringBuilder::reverse)
                .map(StringBuilder::toString)
                .toArray(String[]::new);
    }

    private boolean validHorizontalMirror(String[] pattern, int objectIndex) {
        int reflectionIndex = objectIndex + 3;
        while (objectIndex >= 0 && reflectionIndex < pattern.length) {
            if (!pattern[objectIndex--].equals(pattern[reflectionIndex++]))
                return false;
        }
        // if we were able to reach edge of pattern then mirror was valid -> return true
        return objectIndex < 0 || reflectionIndex >= pattern.length;
    }

    private int findHorizontalMirror(String[] pattern) {
        //looks for two identical lines next to each other
        for (int i = 0; i < pattern.length - 1; i++) {
            if (pattern[i].equals(pattern[i + 1])) {
                //when found so checks if mirror on this position works for the whole pattern
                if (validHorizontalMirror(pattern, i-1)) //start with item behind
                    return i + 1; //indexes start with 1
                //otherwise keep searching...
            }
        }
        return -1;
    }

    private int findVerticalMirror(String[] pattern) {
        return findHorizontalMirror(rotatePattern(pattern));
    }

    private int checkPattern(List<String> pattern) {
        String[] patternArr = pattern.toArray(String[]::new);
        int retVal = findHorizontalMirror(patternArr);
        return retVal > 0 ? retVal*100 : findVerticalMirror(patternArr);
    }

    public int solve(String pathname) throws FileNotFoundException {
        Scanner scanner = getScanner(pathname);

        int sum = 0;
        List<String> pattern = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.matches("[.#]+")) {
                pattern.add(line);
            } else {
                sum += checkPattern(pattern);
                pattern.clear();
            }
        }
        sum += checkPattern(pattern);

        return sum;
    }
}
