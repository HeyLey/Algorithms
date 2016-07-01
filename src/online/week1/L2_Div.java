package online.week1;

import java.util.Scanner;

class L2_Div {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        long n = s.nextLong();
        long m = s.nextLong();

        int t = 0;
        long f0 = 0;
        long f1 = 1;
        long res;

        if((n == 0) || (n == 1)) {
            System.out.println(n);
            return;
        }

        for (int i = 2; i <= n; i++) {
            res = f0 + f1;
            f0 = f1 % m;
            f1 = res % m;

            if ((f0 == 0) && (f1 == 1) && i <= n) {
                t = i - 1;
                break;
            }
            if(i == n) {
                System.out.println(res % m);
                return;
            }
        }

        long k = n%t;

        if(k == 0) {
            k = t;
        }

        if((k == 0) || (k == 1)) {
            System.out.println(k);
            return;
        }

        f0 = 0;
        f1 = 1;
        res = 0;

        for (int i = 2; i <= k; i++) {
            res = f0 + f1;
            f0 = f1 % m;
            f1 = res % m;
        }

        System.out.println(res % m);
    }
}