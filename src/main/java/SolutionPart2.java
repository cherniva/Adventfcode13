public final class SolutionPart2 extends SolutionBase {

    /*  return value:
        0 - equal
        1 - almost equal
        -1 - more than 1 symbol differs
     */
    private int almostEqual(String s1, String s2) {
        // assume that length of both string is the same value
        boolean symbolDiffers = false;
        for(int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (!symbolDiffers)
                    symbolDiffers = true;
                else
                    return -1;
            }
        }
        return symbolDiffers ? 1 : 0;
    }

    private boolean validHorizontalMirror(String[] pattern, int objectIndex, int smudgeFlag) {
        int reflectionIndex = objectIndex + 3;
        while (objectIndex >= 0 && reflectionIndex < pattern.length) {
            // if smudge was detected, the rest of lines must be identical
            if(smudgeFlag > 0 && !pattern[objectIndex--].equals(pattern[reflectionIndex++])) {
                return false;
            }
            else if(smudgeFlag == 0) {
                smudgeFlag = almostEqual(pattern[objectIndex--], pattern[reflectionIndex++]);
                if(smudgeFlag < 0)
                    return false;
            }
        }
        // if we were able to reach edge of pattern then mirror was valid -> return true
        return smudgeFlag == 1; // "...exactly 1 smudge..." so if we found 0 keep searching"
    }

    @Override
    protected int findHorizontalMirror(String[] pattern) {
        //looks for two identical lines next to each other
        for (int i = 0; i < pattern.length - 1; i++) {
            int smudgeFlag = almostEqual(pattern[i], pattern[i+1]);
            if(smudgeFlag >= 0) { // 1 or 0
                //when found so checks if mirror on this position works for the whole pattern
                if (validHorizontalMirror(pattern, i-1, smudgeFlag)) //start with item behind
                    return i + 1; //indexes start with 1
                //otherwise keep searching...
            }
        }
        return -1;
    }
}
