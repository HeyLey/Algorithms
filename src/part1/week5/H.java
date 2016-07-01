package part1.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class H {

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

        int n = nextInt();
        int[] sequence = new int[n];
        int[] subSequence = new int[n];

        for (int i = 0; i < n; i++) {
            sequence[i] = nextInt();
        }
        for (int i = 0; i < n; i++) {
            subSequence[i] = 1;
            for (int j = 0; j < i; j++) {
                if (sequence[i] % sequence[j] == 0) {
                    if (subSequence[i] < subSequence[j] + 1) {
                        subSequence[i] = subSequence[j] + 1;
                    }
                }
            }
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            if (max < subSequence[i]) {
                max = subSequence[i];
            }
        }
        System.out.println(max);
    }
}
