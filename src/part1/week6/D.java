package part1.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.*;

/*

3 0
3
-2
1

 */

public class D {
    private static final int BUFF_SIZE = 200000;
    static StreamTokenizer in;
    static int a[];
    static int bestValue;
    static int lastFound;
    static int lastValue;

    static Set<Integer> setBuff = new HashSet<>();
    static int[] bigBuffer = new int[BUFF_SIZE];
    static int firstElement = 0;
    static int firstFree = 0;

    static int[] dinStart = new int[0];
    static int[] dinEnd = new int[0];

    static String next() throws IOException {
        in.nextToken();
        return in.sval;
    }

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

        int n = nextInt();
        int m = nextInt();

        a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = nextInt();
        }

        char[] result = new char[n];

        dinStart = new int[n];
        dinEnd = new int[n];


        firstElement = 0;
        firstFree = 0;
        fillResult(n, m, result);
        int best = bestValue;


        while (lastFound > 1) {
            firstElement = 0;
            firstFree = 0;
            fillResult(lastFound, lastValue, result);
        }

        System.out.println(best);

        System.out.print(a[0]);
        for (int i = 1; i < n; i++) {
            if (result[i] == 0) {
                throw new RuntimeException();
            }
            System.out.print(result[i]);
            System.out.print(a[i]);
        }

        System.out.println();

    }

    private static void fillResult(int n, int m, char[] result) {
        dinStart[0] = firstFree;

        bigBuffer[addElement()] = a[0];

        dinEnd[0] = firstFree;

        int starFree = 0;

        for (int i = 1; i < n; i++) {
            int start = dinStart[i - 1];
            int end = dinEnd[i - 1];


            setBuff.clear();

            for (int j = start; j != end; j = (j + 1) % BUFF_SIZE) {
                int value = bigBuffer[j];
                setBuff.add(value + a[i]);
                setBuff.add(value - a[i]);
            }
            dinStart[i] = firstFree;

            while (getSize() < setBuff.size() + 10) {
                firstElement = dinEnd[starFree];
                dinStart[starFree] = -1;
                dinEnd[starFree] = -1;
                starFree++;
            }

            for (int v : setBuff) {
                bigBuffer[addElement()] = v;
            }

            dinEnd[i] = firstFree;


        }

        bestValue = 0;
        int bestDistance = Integer.MAX_VALUE;

        int start = dinStart[n - 1];
        int end = dinEnd[n - 1];

        for (int j = start; j != end; j = (j + 1) % BUFF_SIZE) {
            int value = bigBuffer[j];
            if (Math.abs(value - m) < bestDistance) {
                bestDistance = Math.abs(value - m);
                bestValue = value;
            }
        }


        int p = bestValue;

        for (int i = n - 1; i > 0; i--) {
            start = dinStart[i - 1];
            end = dinEnd[i - 1];
            if (start == -1) {
                break;
            }
            for (int j = start; j != end; j = (j + 1) % BUFF_SIZE) {
                int value = bigBuffer[j];
                if (value + a[i] == p) {
                    result[i] = '+';
                    p = value;
                    break;
                }
                if (value - a[i] == p) {
                    p = value;
                    result[i] = '-';
                    break;

                }
            }
            lastFound = i;
            lastValue = p;
        }
    }

    private static int addElement() {
        int result = firstFree;
        firstFree = (firstFree + 1) % BUFF_SIZE;
        return result;
    }

    public static int getSize() {
        int size = firstElement - firstFree;
        if (size <= 0) {
            return size + BUFF_SIZE;
        }
        return size;
    }
}