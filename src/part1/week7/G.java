package part1.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.valueOf(in.readLine());
        char[] text = in.readLine().toCharArray();

        int[] stat = new int[10];

        for (int i = text.length - k; i >= 0; i--) {
            stat[text[i] - '0']++;
        }

        int max = argMax(stat);

        StringBuilder result = new StringBuilder();

        for (char ch : text) {
            int c = ch - '0';
            stat[c]--;
            if (c == max) {
                result.append(ch);
                int left = k - result.length();
                if (left == 0) {
                    break;
                }
                stat[text[text.length - left] - '0']++;
                max = argMax(stat);
            }
        }
        System.out.println(result);
    }

    private static int argMax(int[] stat) {
        for (int i = 9; i >= 0; i--) {
            if (stat[i] != 0) {
                return i;
            }
        }
        return 0;
    }
}