import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;

public class Main {
    private static final Logger log = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args) {
        /*
            Part 1:
                testSample = 405
                finalSample = 34918
            Part 2:
                testSample = 400
                finalSample = 33054
         */
        String pathname = "src/main/resources/finalSample";
        SolutionBase solution = new SolutionPart2();
        try {
            System.out.println(solution.solve(pathname));
        } catch (FileNotFoundException e) {
            log.error("File {} not found", pathname);
            System.exit(1);
        }
    }
}
