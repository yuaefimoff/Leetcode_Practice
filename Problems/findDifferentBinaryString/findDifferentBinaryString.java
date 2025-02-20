
import java.util.Arrays;

class findDifferentBinaryString {
    public String findDifferentBinaryString(String[] nums) {
        // 2^n possible strings from length n
        Arrays.sort(nums);
        // Value in array index needs to match index 
        // If there is a mismatch, return index in binary form

        // First approach is linear, could be improved by taking advantage of bits
        for (int i = 0; i < nums.length; i++) {
            if(binToDec(nums[i]) != i){
                return decToBin(i, nums[0].length());
            }
        }
        return decToBin(nums.length, nums[0].length());
    }

    public static int binToDec(String s){
        char[] c = s.toCharArray();
        int num = 0;
        for (int i = 0; i < c.length; i++) {
            num += (c[i] - '0')*(int)Math.pow(2, (c.length - i - 1));
        }
        return num;
    }

    public static String decToBin(int n, int length){
        StringBuilder sb = new StringBuilder();
        int nLen = (n != 0)?((int)Math.ceil(Math.log(n) / Math.log(2)) + 1):0;
        System.out.println("Binary length of " + n + ": " + nLen);
        int zeroes = length - nLen;
        System.out.println("Extra zeroes " + zeroes);

        while (zeroes != 0) { 
            sb.append("0");
            zeroes--;
        }

        while (nLen != 0) { 
            int digit = (n >> nLen - 1) & 1;
            sb.append(String.valueOf(digit));
            nLen--;
        }

        System.out.println(sb.toString());
        return sb.toString();
    }

    public static void main(String[] args) {
        //findDifferentBinaryString solution = new findDifferentBinaryString();
        //binToDec("1000");
        //decToBin(8, 4);
    }
}