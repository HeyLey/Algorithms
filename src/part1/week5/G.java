package part1.week5;

/**
 * Created by leyla on 17/10/15.
 */
import java.io.*;

public class G {

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
        int a1[] = new int[n];

        for (int i = 0; i < n; i++) {
            a1[i] = nextInt();
        }

        int m = nextInt();
        int a2[] = new int[m];

        for (int i = 0; i < m; i++) {
            a2[i] = nextInt();
        }

        int[][] size = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                size[i][j] = Math.max(size[i - 1][j], size[i][j - 1]);
                if (a1[i - 1] == a2[j - 1]) {
                    size[i][j] = Math.max(size[i][j], size[i - 1][j - 1] + 1);
                }
            }
        }

        System.out.println(size[n][m]);
    }
}