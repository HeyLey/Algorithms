package part1.week6;

import java.io.*;
import java.util.*;

public class C {

    public static void main(String[] args) throws IOException {
        Scanner scr = new Scanner(new InputStreamReader(System.in));

        long n = scr.nextLong();
        long k = scr.nextLong();
        Map<Long, Long> map = new HashMap<Long, Long>();

        System.out.println(get(n, k, map));
    }

    private static long get(long n, long k, Map<Long, Long> map) {
        if (n <= k) {
            return 1;
        }
        if (map.containsKey(n)) {
            return map.get(n);
        }
        long v1 = n / 2;
        long v2 = n - v1;
        long result = get(v1, k, map) + get(v2, k, map);
        map.put(n, result);
        return result;
    }
}