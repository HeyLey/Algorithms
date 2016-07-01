package online.week2;

import java.io.*;
import java.util.Arrays;

/*
Первая строка содержит количество предметов 1 ≤ n ≤ 10^3 и вместимость рюкзака 0 ≤ W ≤ 2⋅10^6.
Каждая из следующих n строк задаёт стоимость 0 ≤ c_i ≤ 2⋅10^6 и вес 0 < w_i ≤ 2⋅10^6 предмета
(n, W, c_i, w_i — целые числа).
Выведите оптимальную стоимость частей предметов
(от каждого предмета можно отделить любую часть, стоимость и вес при этом пропорционально уменьшатся),
помещающихся в данный рюкзак, с точностью не менее трёх знаков после запятой.

Sample Input:
3 50
60 20
100 50
120 30

Sample Output:
180.000
 */


class Rykzak {
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

        double w = nextInt();

        Thing[] things = new Thing[n];

        for (int i = 0; i < n; i++) {
            things[i] = new Thing();
            things[i].c = nextInt();
            things[i].w = nextInt();
        }


        Arrays.sort(things);


        double c = 0;

        for (int i = 0; i < n; i++) {
            if (w > things[i].w) {
                w -= things[i].w;
                c += things[i].c;
            } else {
                c += things[i].c * w / things[i].w ;
                break;
            }

        }

        out.println(c);
        out.close();
    }

    static class Thing implements Comparable<Thing> {
        double c;
        double w;


        @Override
        public int compareTo(Thing thing) {
            return Double.compare(thing.c / thing.w, c / w);
        }
    }
}