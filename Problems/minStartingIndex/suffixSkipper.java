public class suffixSkipper {
    // Function to compute the suffix skip array S for a pattern P
    public static int[] computeSuffixSkipArray(String pattern) {
        int m = pattern.length();
        int[] suffixSkip = new int[m]; // Suffix skip array

        // Iterate over each position in the pattern
        for (int i = m - 1; i >= 0; i--) {
            boolean found = false; // Flag to check if a valid j is found

            // Find largest j such that P[i+1..m-1] = P[j+1..j+m-1-i] and P[i] != P[j]
            for (int j = i - 1; j >= 0; j--) {
                if (pattern.charAt(i) != pattern.charAt(j) && pattern.substring(i+1, m).equals(pattern.substring(j+1, j + m - i))) {
                    suffixSkip[i] = j; // Set S[i] to the valid j
                    found = true;
                    break;
                }
            }

            // This condition allows for the assumption of negative indices
            if (!found) {
                suffixSkip[i] = i - (m - 2);
            }
        }

        return suffixSkip;
    }

    public static void main(String[] args) {
        String P = "bonobobo";
        int[] S = computeSuffixSkipArray(P);
        System.out.print("Suffix Skip Array for \"" + P + "\": ");
        for (int i = 0; i < S.length; i++) {
            System.out.print(S[i] + " ");
        }
    }
}