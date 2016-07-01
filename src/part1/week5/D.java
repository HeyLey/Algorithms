package part1.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 *
 * Вычислите функцию:
 *        1                                          n <= 2
 * f(n) = f(floor(6 * n/7) + f(floor(2 * n/3)))      n mod 2 = 1
          f(n - 1) + f(n - 3)                        n mod 2 = 0
 * Формат входных данных
 * Входные данные содержат натуральное число n (1 <= n <= 10^12).
 * Формат выходных данных
 * Выведите значение функции по модулю 2^32
 */
public class D {

    public static long findVal(long n, Map<Long, Long> map) {
        if (n <= 2) {
            return 1;
        }
        long res = 0;
        if (map.containsKey(n)) {
            return map.get(n);
        }
        if (n % 2 == 1) {
            long ind1 = 6 * n/7;
            long ind2 = 2 * n/3;
            res = findVal(ind1, map) + findVal(ind2, map);
            res = res % 4294967296L;

        } else {
            res = findVal(n - 1, map) + findVal(n - 3, map);
            res = res % 4294967296L;
        }
        map.put(n, res);
        return res;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        long n = Long.parseLong(in.readLine());
        Map<Long, Long> map = new HashMap<Long, Long>();
        long res = findVal(n, map);
        System.out.println(res);
        out.close();
    }
}
