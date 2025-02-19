
class getHappyString {
    public String getHappyString(int n, int k) {

        // Blind approach: The number of possible strings will always be 3*2^(n-1)
        // Instead of making a list of happy strings (HUGE waste of space), we can build one with a tree-like approach

        // Check if k is within this range before attempting to create a happy string
        if(k > (3 * Math.pow(2, n - 1))){
            return "";
        }

        // Base case: since we know for sure that the set of possibilities is divisible by 3
        // Determine what "third" k is located in
        int powTwo = (1 << (n - 1));
        StringBuilder sb = new StringBuilder();
        

        // First character identifier
        // Rip all nodes except the top 3 for (a, b, c) by dividing by 2^(n-1)
        int index = (k-1) / powTwo; // k - 1 due to 0-index array
        char prev = (char) ('a' + index);
        sb.append(prev);

        /**
        Check where specifically k is in a subrange. Similar approach to before but checking halves instead of thirds
        Alternative can look at this like a complete binary tree, and searching for which node to traverse through
         */
        for(int i = (n - 1); i > 0; i--){
            if((k - 1) % (1 << i) < (1 << (i - 1)) ){
                // In first half/left child: Choose lexicographically earliest letter
                prev = (prev == 'a')?'b':'a';
            }else {
                // In second half/right child: Choose lexicographically latest letter
                prev = (prev == 'c')?'b':'c';
                k -= (1 << (i - 1)); // Set position of k to better visualize relative position of k in the leaves of possibilities
            }
            sb.append(prev);
        }

        /**
        Alternative way of determining string using bit analysis
        We start with a range (n - 1) bits long
        MSB = 0 --> smaller lexicographic letter
        MSB = 1 --> larger lexicographic letter

        In either case, swap out the letters
        Repeat until no bits left to iterate through
        */

        return sb.toString();
    }   
}