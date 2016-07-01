package online.week1;

import java.util.Scanner;

class L2_Last {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        long n = s.nextInt();

        if(n == 0 || n == 1) {
            System.out.println(n);
        } else {
            long f0 = 0;
            long f1 = 1;
            long res = 0;
            for (int i = 2; i <= n; i++) {
                res = f0 + f1;
                f0 = f1 % 10;
                f1 = res % 10;
            }
            if (res < 10) {
                System.out.println(res);
            } else {
                System.out.println(res % 10);
            }
        }
    }
}