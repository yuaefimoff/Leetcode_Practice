
import java.util.*;

class minStartingIndex_BoyerMooreHorspool {

    public int minStartingIndex_BoyerMooreHorspool(String s, String pattern) {
        // Blind approach: This is a slightly modified version of a string matching algorithm
        // Since the constraints use the alphabet, I will use a Boyer-Moore string matching approach
        // The difference is that I will implement an almostCount variable initialized to 0- if it does not stay as 1 by the end of the string, there is no match
        int[] arrL = badMatch(pattern);

        int pLen = pattern.length() - 1;
        int i = pLen;
        int j = pLen;

        int almostCnt = 0;

        int m1 = s.charAt(i) - 'a'; // Checkpoint 1
        int m2 = -1; // Checkpoint 2

        System.out.println("" + s + "\n" + pattern);
        while (i < s.length() && j >= 0) {
            // Adjusted Boyer-Moore-Horspool Algorithm to accomodate for 1 mismatch
            System.out.println("i " + i + "\tj " + j);
            if (s.charAt(i) == pattern.charAt(j)) {
                i--;
                j--;
            } else {
                almostCnt++;
                System.out.println("Mistakes: " + almostCnt);
                if(almostCnt == 1){
                    m2 = s.charAt(i) - 'a';
                } 
                if (almostCnt > 1) {
                    System.out.println("Shift by: " + arrL[m1] + " vs " + arrL[m2]);
                    i = i + Math.max(1, arrL[m1]);
                    j = pLen;
                    almostCnt = 0;
                    m1 = s.charAt(i) - 'a'; // Checkpoint 1
                } else{
                    i--;
                    j--;
                }
                
            }
        }

        return (j == -1) ? (i + 1) : -1;
    }

    public static int[] badMatch(String p) {
        char[] alphabet = ("abcdefghijklmnopqrstuvwxyz").toCharArray();
        int[] lastOcc = new int[alphabet.length];

        Arrays.fill(lastOcc, -1);
        for (int i = 0; i < p.length(); i++) {
            lastOcc[p.charAt(i) - 'a'] = i;
        }
        System.out.println(Arrays.toString(lastOcc));
        return lastOcc;
    }

    public static void main(String[] args) {
        minStartingIndex_BoyerMooreHorspool solution = new minStartingIndex_BoyerMooreHorspool();
        
        String s1 = "ababbababa"; 
        String pattern1 = "bacaba"; 
        int result1 = solution.minStartingIndex_BoyerMooreHorspool(s1, pattern1); 
        System.out.println("Testcase 1: " + result1 + "\n");
    
        String s2 = "abcdefg";
        String pattern2 = "bcdffg";
        int result2 = solution.minStartingIndex_BoyerMooreHorspool(s2, pattern2);
        System.out.println("\nTest case 2: " + result2);

    }
}
