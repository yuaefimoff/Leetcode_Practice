class cantorStringGeneration {
    public String findDifferentBinaryString(String[] nums) {
        // Implementation considering Cantor's diagonalization principle for counting
        // Works with the given constraints of string and array length
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            sb.append((nums[i].charAt(i)-'0') ^ 1);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        findDifferentBinaryString solution = new findDifferentBinaryString();
        //binToDec("1000");
        //decToBin(8, 4);
    }
}