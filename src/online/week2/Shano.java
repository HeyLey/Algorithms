package online.week2;

/**
 * Created by leyla on 30/11/15.
 */
import java.util.*;


public class Shano {

    public HashMap compress(HashMap freq) {

        HashMap<Character, String> result = new HashMap<>();
        List<Character> charList = new ArrayList<>();

        Iterator entries = freq.entrySet().iterator();
        while( entries.hasNext() ) {
            Map.Entry<Character, Integer> entry = (Map.Entry)entries.next();
            charList.add(entry.getKey());
        }

        addBit(result, charList, true);

        return result;
    }

    private void addBit(HashMap<Character, String> result, List<Character> charList, boolean up) {
        String bit = "";
        if( !result.isEmpty() ) {
            bit = (up) ? "0" : "1";
        }

        for( Character c : charList ) {
            String s = (result.get(c) == null) ? "" : result.get(c);
            result.put(c, s + bit);
        }

        if( charList.size() >= 2 ) {
            int separator = (int)Math.floor((float)charList.size()/2.0);

            List<Character> upList = charList.subList(0, separator);
            addBit(result, upList, true);
            List<Character> downList = charList.subList(separator, charList.size());
            addBit(result, downList, false);
        }
    }

    public static void main(String[] args) {
        HashMap freq = new HashMap();
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        for (int i = 0; i < n; i++) {

        }


    }
}