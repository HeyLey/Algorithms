package online.week1;

import java.util.Scanner;

class L2_Fib {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        long a = 1;
        long b = 1;
        long res = 1;

        for(int i = 2; i < n; i++) {
            res = a + b;
            a = b;
            b = res;
        }
         System.out.println(res);
    }
}