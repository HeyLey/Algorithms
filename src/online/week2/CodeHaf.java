package online.week2;

/*
По данной непустой строке s длины не более 10^4, состоящей из строчных букв латинского алфавита,
постройте оптимальный беспрефиксный код. В первой строке выведите количество различных букв k,
встречающихся в строке, и размер получившейся закодированной строки.
В следующих k строках запишите коды букв в формате "letter: code".
В последней строке выведите закодированную строку.

Sample Input 1:
a

Sample Output 1:
1 1
a: 0
0

Sample Input 2:
abacabad

Sample Output 2:
4 14
a: 0
b: 10
c: 110
d: 111
01001100100111
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CodeHaf {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        String[] codes = new String[26];

        int[] stat = new int[28];

        for (char ch : s.toCharArray()) {
            stat[ch - 'a']++;
        }



        for (char ch = 'a'; ch <= 'z'; ch++) {
            codes[ch - 'a'] = "";
        }

        ArrayList<Block> blocks = new ArrayList<Block>();

        for (char ch = 'a'; ch <= 'z'; ch++) {
            final int num = stat[ch - 'a'];
            if (num > 0) {
                Block block = new Block();
                block.number = num;
                block.chars = Collections.singletonList(ch);
                blocks.add(block);
            }
        }

        int charsNumber = blocks.size();

        if (blocks.size() == 1) {
            final Block block = blocks.get(0);
            final Character ch = block.chars.get(0);
            codes[ch - 'a'] = "0";
        } else {
            while (blocks.size() > 1) {
                Collections.sort(blocks);
                final int size = blocks.size();

                final Block b1 = blocks.remove(size - 1);
                final Block b2 = blocks.remove(size - 2);
                for (char ch : b1.chars) {
                    codes[ch - 'a'] = "1" + codes[ch - 'a'];
                }
                for (char ch : b2.chars) {
                    codes[ch - 'a'] = "0" + codes[ch - 'a'];
                }
                Block b = new Block();
                b.number = b1.number + b2.number;
                b.chars = new ArrayList<Character>();
                b.chars.addAll(b1.chars);
                b.chars.addAll(b2.chars);
                blocks.add(b);
            }
        }

        StringBuilder result = new StringBuilder();
        for (char ch : s.toCharArray()) {
            result.append(codes[ch - 'a']);
        }
        System.out.println(charsNumber + " " + result.length());


        for (char ch = 'a'; ch <= 'z'; ch++) {
            if (codes[ch - 'a'].length() > 0) {
                System.out.println(ch + ": " + codes[ch - 'a']);
            }
        }
        System.out.println(result);
    }

    static class Block implements Comparable<Block> {
        int number;
        List<Character> chars = new ArrayList<Character>();


        @Override
        public int compareTo(Block o) {
            if (number > o.number) {
                return -1;
            } else if (number < o.number) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}