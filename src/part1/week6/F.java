package part1.week6;

import java.io.*;

public class F {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String text = in.readLine();
        String[][] conv = new String[text.length()][text.length()];

        for (int sz = 1; sz <= text.length(); sz++) {
            for (int i = 0; i < text.length() - sz + 1; i++) {
                conv[sz - 1][i] = text.substring(i, i + sz);
                if (sz < 3) {
                    continue;
                }
                for (int d = 1; d <= sz / 2; d++) {
                    if (sz % d != 0) {
                        continue;
                    }
                    boolean good = true;
                    for (int j = d; j < sz; j++) {
                        if (text.charAt(i + j) != text.charAt(i + (j % d))) {
                            good = false;
                            break;
                        }
                    }
                    if (!good) {
                        continue;
                    }
                    String tmp = conv[d - 1][i];
                    String newText = (sz / d) + "(" + tmp + ")";
                    if (conv[sz - 1][i].length() > newText.length()) {
                        conv[sz - 1][i] = newText;
                    }
                    break;
                }
                for (int j = 1; j < sz; j++) {
                    if (conv[j - 1][i].length() + conv[sz - j - 1][i + j].length() < conv[sz - 1][i].length()) {
                        conv[sz - 1][i] = conv[j - 1][i] + conv[sz - j - 1][i + j];
                    }
                }
            }
        }
        System.out.println(conv[text.length() - 1][0]);
    }
}