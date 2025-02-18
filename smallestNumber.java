
class smallestNumber {
    public String smallestNumber(String pattern) {
        // Blind approach
        int[] ans = new int[pattern.length() + 1];

        // Set default to all incrementing values
        for (int i = 0; i < ans.length; i++) {
            ans[i] = i+1;
        }

        // Swap approach when encountering a 'D'
        char[] patternCh = pattern.toCharArray();
        for (int i = 0; i < pattern.length(); i++) {
            // Upon encountering a D at index i, find how many Ds in a row there are (Distance cntD)
            //  Reverse the order of cntD + 1 digits, from index i
            if (patternCh[i] == 'D') {
                int start = i;
                while (i + 1 < pattern.length() && pattern.charAt(i + 1) == 'D') {
                    i++;
                }

                int end = i + 1;
                while (start < end) {
                    int temp = ans[start];
                    ans[start] = ans[end];
                    ans[end] = temp;
                    start++;
                    end--;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ans.length; i++) {
            sb.append(ans[i]);
        }

        return sb.toString();
    }

}