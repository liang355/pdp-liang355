package Assignment1;

import java.util.Scanner;

public class ReverseNumber {
//    public ReverseNumber() {
//        while(true) {
//            Scanner sc = new Scanner(System.in);
//            System.out.println(getReverseNumber(sc.nextInt()));
//        }
//    }

    /**
     * Calculates the reversed number of the number user entered in console.
     *
     * @param num the number user entered in console
     * @return reversed number of <var>num<var/>
     */
    public int getReverseNumber(int num) {
        char[] digits = Integer.toString(num).toCharArray();
        int lo = 0;
        int hi = digits.length - 1;
        while (lo < hi) {
            if(digits[lo] == '-') {
                lo++;
                continue;
            }
            swap(lo, hi, digits);
            lo++;
            hi--;
        }
        return Integer.valueOf(String.valueOf(digits));
    }

    private void swap(int lo, int hi, char[] digits) {
        char temp = digits[lo];
        digits[lo] = digits[hi];
        digits[hi] = temp;
    }
}
