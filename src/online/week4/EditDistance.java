package online.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EditDistance {
    public static void main(String[] args) throws IOException {
        BufferedReader fr = new BufferedReader(new InputStreamReader(System.in));

        String s1 = fr.readLine();
        String s2 = fr.readLine();

        int[][] matrix = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 && j == 0) {
                    matrix[i][j] = 0;
                } else {
                    matrix[i][j] = Integer.MAX_VALUE;
                }
                if (i > 0) {
                    if ((matrix[i - 1][j] + 1) < matrix[i][j]) {
                        matrix[i][j] = matrix[i - 1][j] + 1;
                    }
                }
                if (j > 0) {
                    if ((matrix[i][j - 1] + 1) < matrix[i][j]) {
                        matrix[i][j] = matrix[i][j - 1] + 1;
                    }
                }
                if (i > 0 && j > 0) {
                    int w;
                    if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                        w = matrix[i - 1][j - 1];
                    } else {
                        w = matrix[i - 1][j - 1] + 1;
                    }
                    if (w < matrix[i][j]) {
                        matrix[i][j] = w;
                    }
                }
            }

        }
        System.out.println(matrix[s1.length()][s2.length()]);
    }
}