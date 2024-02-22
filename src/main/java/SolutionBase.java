import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public abstract class SolutionBase {

    abstract protected int findHorizontalMirror(String[] pattern);

    protected Scanner getScanner(String pathname) throws FileNotFoundException {
        File file = new File(pathname);
        return new Scanner(file);
    }

    protected String[] rotatePattern(String[] pattern) {
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

    protected int findVerticalMirror(String[] pattern) {
        return findHorizontalMirror(rotatePattern(pattern));
    }

    protected int checkPattern(List<String> pattern) {
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
