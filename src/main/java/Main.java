import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;

public class Main {
    private static final Logger log = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args) {
        /*
            testSample = 405
            finalSample = 34918
         */
        String pathname = "src/main/resources/finalSample";
        Solution solution = new Solution();
        try {
            System.out.println(solution.solve(pathname));
        } catch (FileNotFoundException e) {
            log.error("File {} not found", pathname);
            System.exit(1);
        }
    }
}
