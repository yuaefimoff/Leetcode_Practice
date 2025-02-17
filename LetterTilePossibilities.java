import java.util.*;

class LetterTilePossibilities {
    // Given an array of tiles, list all the possible non empty sequences
    HashMap<String, Integer> freqCnt = new HashMap<String, Integer>();

    public int numTilePossibilities(String tiles) {
        /** BLIND approach:
        If we have n letters, then we can sum all the combinations of n choose i (1 <= i <= n). This doesn't consider order.
        We also have to consider the cases where due to duplicate letters, we can have multiple of the same combination.
        Since this applies to every possible permutation/combination of letters, a formula will not work
        */

        // Create a collection counter tracking the number of possibilities for each alphabet
        freqCntCheck(tiles);
        
        return backtrack(freqCnt);
    }

    private int backtrack(HashMap<String, Integer> freq){
        int cnt = 0;
        for (String key : freqCnt.keySet()) {
            // Check if frequency of letter > 0
            if (freq.get(key) > 0) {
                cnt++; // Increment the counter by 1
                freq.replace(key, freq.get(key) - 1); // Decrease frequency of available letter possibilities
                cnt += backtrack(freq);
                freq.replace(key, freq.get(key) + 1); // Restore frequency of available letter possibilities after recursive call hits base case
            }
        }

        return cnt;
    }

    private void freqCntCheck(String tiles){
        List<Character> ch = new ArrayList<>();
        for( char c: tiles.toCharArray()){
            ch.add(c);
        }
        for (char c: ch) {
            freqCnt.putIfAbsent( Character.toString(c), Collections.frequency(ch, c));
        }

    }
}