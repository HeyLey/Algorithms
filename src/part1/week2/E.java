package part1.week2;

/*
 «Ну не гномы, а наказание какое-то!», —
 подумала Белоснежка, в очередной раз пытаясь уложить гномов спать.
 Одного уложишь — другой уже проснулся! И так всю ночь.
 У Белоснежки n гномов, и все они очень разные.
 Она знает, что для того, чтобы уложить спать i-го гнома нужно a_i минут,
 и после этого он будет спать ровно b_i минут.
 Помогите Белоснежке узнать, может ли она получить хотя бы минутку отдыха,
 когда все гномы будут спать, и если да,
 то в каком порядке для этого нужно укладывать гномов спать.
 Например, пусть есть всего два гнома, a_1 = 1, b_1 = 10, a_2 = 10, b_2 = 20.
 Если Белоснежка сначала начнет укладывать первого гнома, то потом ей потребуется целых 10 минут,
 чтобы уложить второго, а за это время проснется первый.
 Если же она начнет со второго гнома, то затем она успеет уложить первого и получит целых 9 минут отдыха.
 Формат входных данных
 Первая строка входного файла содержит число n (1 <= n <= 10^5),
 вторая строка содержит числа a_1, a_2,..., a_n,
 третья — числа b_1, b_2,..., b_n (1 <= a_i, b_i <= 10^9).
 Формат выходных данных
 Выведите в выходной файл n чисел — порядок, в котором нужно укладывать гномов спать.
 Если Белоснежке отдохнуть не удастся, выведите число −1.
 Если решений несколько, выведите любое.
 */

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class E {
    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static String next() throws IOException {
        in.nextToken();
        return in.sval;
    }

    public static void main(String[] args) throws IOException {

        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(System.out);

        int n = nextInt();

        final int[][]time = new int[n][3];

        long sum = 0;

        for (int i = 0; i < n; i++) {
            time[i][0] = nextInt();
            sum += time[i][0];
        }

        for (int i = 0; i < n; i++) {
            time[i][1] = nextInt();
            time[i][2] = i + 1;
        }

        Arrays.sort(time, new Comparator<int[]>() {
            @Override
            public int compare(int[] g1, int[] g2) {
                int s1 = g1[0] + g1[1];
                int s2 = g2[0] + g2[1];
                return s2 - s1;
            }
        });

        boolean g = true;

        for (int i = 0; i < n; i++) {
            if (sum >= time[i][0] + time[i][1]) {
                g = false;
                break;
            }
            sum -= time[i][0];
        }

        if (g) {
            for (int i = 0; i < n; i++) {
                out.print(time[i][2] + " ");
            }
            out.println();
        } else {
            out.println(-1);
        }

        out.close();
    }

}