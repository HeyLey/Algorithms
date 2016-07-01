package part1.week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class E {
    public static void main(String[] args) throws IOException {
        BufferedReader fr = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(fr.readLine().trim());

        String[] names = new String[n];
        boolean[][] matrix = new boolean[n][n];
        int[] depsNumber = new int[n];
        List<Integer> indexes = new ArrayList<Integer>();

        for (int i = 0; i < n; i++) {
            indexes.add(i);
            String[] strs = fr.readLine().split(" ");
            names[i] = strs[0];
            for (int j = 1; j < strs.length; j++) {
                int v = Integer.valueOf(strs[j]);
                if (! matrix[v][i]) {
                    matrix[v][i] = true;
                    depsNumber[i]++;
                }
            }

        }

        final Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                if (depsNumber[i2] > depsNumber[i1]) {
                    return 1;
                }
                if (depsNumber[i2] < depsNumber[i1]) {
                    return -1;
                }
                return names[i2].compareTo(names[i1]);
            }
        };

        while (indexes.size() > 0) {
            Collections.sort(indexes, comparator);
            final int last = indexes.size() - 1;
            final int index = indexes.get(last);
            for (int i = 0; i < n; i++) {
                if (matrix[index][i]) {
                    depsNumber[i]--;
                }
            }
            System.out.println(names[index]);
            indexes.remove(last);
        }
    }
}