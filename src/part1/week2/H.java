package part1.week2;

/**
 Друзья Тома Сойера по очереди красят забор разными красками. Каждый из них кра-
 сит несколько идущих подряд секций забора в определенный цвет, при этом используемые
 цвета могут повторяться. Новая краска ложится поверх старой. Для каждой краски вычис-
 лите количество секций, которые будут покрашены этой краской после того, как все друзья
 закончат работу.

 Формат входных данных
 В первой строке входного файла содержатся два целых числа: N (1 <= N <= 10^9) и
 K (1 <= K <= 50 000) — количество секций в заборе и количество различных красок соответственно.
 Во второй строке содержится единственное число M (0 <= M <= 50 000) —
 количество друзей Тома Сойера.
 Далее следуют M строк: в i-ой строке содержится информация о работе друга,
 который красил забор  i-ым по счету, а именно 3 целых числа c_i, l_i, r_i
(1 <= c_i <= K, 1<= l_i <= r_i <= N) —
 номер краски, которую использовал i-й друг, номер первой и номер последней покрашенной секции соответственно.
 Формат выходных данных
 Выведите в единственную строку выходного файла K целых чисел:
 i-ое число должно быть равно количеству секций, покрашенных i-й краской.
 */

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

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
        PrintWriter out = new PrintWriter(System.out);

        int n = nextInt();
        int k = nextInt();
        int m = nextInt();


        Friend[] friends = new Friend[m];


        for (int i = 0; i < m; i++) {
            friends[i] = new Friend();
            friends[i].n = i;
            friends[i].color = nextInt() - 1;
            friends[i].l = nextInt();
            friends[i].r = nextInt();
        }

        Arrays.sort(friends);


        int[] painted = new int[k];

        Friend currentFriend = null;
        int currentPosition = -1;
        TreeSet<Friend> paintingFriends = new TreeSet<Friend>(new Comparator<Friend>() {
            @Override
            public int compare(Friend f1, Friend f2) {
                return f2.n - f1.n;
            }
        });

        for (int i = 0; i < m; i++) {
            while (currentFriend != null && currentFriend.r < friends[i].l) {
                if (currentFriend.r >= currentPosition) {
                    painted[currentFriend.color] += currentFriend.r - currentPosition + 1;
                    currentPosition = currentFriend.r + 1;
                }
                paintingFriends.pollFirst();
                if (paintingFriends.size() > 0) {
                    currentFriend = paintingFriends.first();
                } else {
                    currentFriend = null;
                }            }
            if (currentFriend == null) {
                paintingFriends.add(friends[i]);
                currentFriend = friends[i];
                currentPosition = friends[i].l;
            } else {
                painted[currentFriend.color] += friends[i].l - currentPosition;
                currentPosition = friends[i].l;

                paintingFriends.add(friends[i]);
                currentFriend = paintingFriends.first();
            }
        }
        while (currentFriend != null) {
            if (currentFriend.r >= currentPosition) {
                painted[currentFriend.color] += currentFriend.r - currentPosition + 1;
                currentPosition = currentFriend.r + 1;
            }
            paintingFriends.pollFirst();
            if (paintingFriends.size() > 0) {
                currentFriend = paintingFriends.first();
            } else {
                currentFriend = null;
            }
        }
        for (int p : painted) {
            out.print(p + " ");
        }
        out.println();
        out.close();
    }

    static class Friend implements Comparable<Friend> {
        int n;
        int color;
        int l;
        int r;


        @Override
        public int compareTo(Friend friend) {
            return l - friend.l;
        }

        @Override
        public String toString() {
            return "Friend{" +
                    "n=" + n +
                    ", color=" + color +
                    ", l=" + l +
                    ", r=" + r +
                    '}';
        }
    }
}