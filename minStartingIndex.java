import java.util.*;

class minStartingIndex {
    public int minStartingIndex(String s, String pattern) {
        // Blind approach: This is a slightly modified version of a string matching algorithm
        // Since the constraints use the alphabet, I will use a Boyer-Moore string matching approach
        // The difference is that I will implement an almostCount variable initialized to 0- if it does not stay as 1 by the end of the string, there is no match
        int[] arrL = lastOccurence(pattern);
        int[] arrS = suffixSkip(pattern);

        int pLen = pattern.length() - 1;
        int i = pLen;
        int j = pLen;

        int almostCnt = 0;

        System.out.println("" + s + "\n" + pattern);
        while (i < s.length() && j >= 0) { 
            System.out.println(i + " to " + j);
            /** 
            if (s.charAt(i) == pattern.charAt(j)) {
                i--;
                j--;
            } else{
                
                almostCnt++;
                System.out.println("Mistakes incremented to " + almostCnt + " (" + s.charAt(i) + " != " + pattern.charAt(j) + ")");
                
            }*/
            if (s.charAt(i) != pattern.charAt(j)) {
                almostCnt++;
                System.out.println("MISTAKE #" + almostCnt + " (" + s.charAt(i) + " != " + pattern.charAt(j) + ")");
            }

            if (almostCnt > 1) {
                System.out.println("Last occurence at s[i] = " + s.charAt(i) + ": " + arrL[s.charAt(i) - 'a']); 
                System.out.println("Suffix skip for pattern[j] " + arrS[j]); 
                System.out.println("i:" + i + "\tj:" + j + "\tSelected: " + Math.min(arrL[s.charAt(i) - 'a'], arrS[j])); 
                
                i = i + pLen - Math.min(arrL[s.charAt(i) - 'a'], arrS[j]);
                System.out.println("NEW INDEX: " + i + "\t FURTHEST INDEX: " + (s.length() - 1)); 
                j = pLen;
                almostCnt = 0;
                continue;
            }
            i--;
            j--;
            
        }

        return (j == -1)?(i + 1):-1;

    }

    private int[] lastOccurence(String p) {
        char[] alphabet = ("abcdefghijklmnopqrstuvwxyz").toCharArray();
        int[] lastOcc = new int[alphabet.length];

        Arrays.fill(lastOcc, -1);
        for (int i = 0; i < p.length(); i++) lastOcc[p.charAt(i) - 'a'] = i;
        System.out.println("Last occurence: \n" +Arrays.toString(lastOcc));
        return lastOcc;
    }

    // Boyer-Moore Good suffix skip pre-processing
    private int[] suffixSkip(String pattern) {
        int m = pattern.length();
        int[] suffixSkip = new int[m]; // Suffix skip array

        // Iterate over each position in the pattern
        for (int i = m - 1; i >= 0; i--) {
            boolean found = false; // Flag to check if a valid j is found

            // Find largest j such that P[i+1..m-1] = P[j+1..j+m-1-i] and P[i] != P[j]
            for (int j = i - 1; j >= 0; j--) {
                if (pattern.charAt(i) != pattern.charAt(j) 
                && pattern.substring(i+1, m).equals(pattern.substring(j+1, j + m - i))) {
                    suffixSkip[i] = j; // Set S[i] to the valid j
                    found = true;
                    break;
                }
            }

            // This condition allows for the assumption of negative indices
            if (!found) {
                //suffixSkip[i] = i - (m - 2);
                int j = 1;
                for (j = 1; i + 1 + j < m; j++) {
               
                //System.out.println("Suffix: " + pattern.substring(i + 1 + j) + "\tPrefix: " + pattern.substring(0, m - (i + 1) - j));
                if (pattern.substring(i + 1 + j).equals(pattern.substring(0, (m - (i + 1) - j)))) {
                    //System.out.println("Prefix and suffix math! Assigning value " + -j);
                    suffixSkip[i] = -j;
                    found = true;
                    break;
                }
               }
               if(!found) suffixSkip[i] = -j ;
            }
        }
        System.out.println("Suffix skip: \n" +Arrays.toString(suffixSkip));
        return suffixSkip;
    }

    public static void main(String[] args) {
        minStartingIndex solution = new minStartingIndex();

        String s1 = "ababbababa";
        String pattern1 = "bacaba";
        int result1 = solution.minStartingIndex(s1, pattern1);
        System.out.println("Test case 1: " + result1 + "\n"); // Expected output: 10

        
        String s2 = "abcdefg";
        String pattern2 = "bcdffg";
        int result2 = solution.minStartingIndex(s2, pattern2);
        System.out.println("\nTest case 2: " + result2); // Expected output: 10 

    }
}