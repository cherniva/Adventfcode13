public final class SolutionPart1 extends SolutionBase {

    private boolean validHorizontalMirror(String[] pattern, int objectIndex) {
        int reflectionIndex = objectIndex + 3;
        while (objectIndex >= 0 && reflectionIndex < pattern.length) {
            if (!pattern[objectIndex--].equals(pattern[reflectionIndex++]))
                return false;
        }
        // if we were able to reach edge of pattern then mirror was valid -> return true
        return true;
    }

    @Override
    protected int findHorizontalMirror(String[] pattern) {
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
}
