package sunday17th;

import java.util.*;

public class HashTables {

    // Leetcode 47
    // Group Anagrams
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String word : strs) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);

            List<String> group = map.getOrDefault(key, new ArrayList<>());
            group.add(word);
            map.put(key, group);
        }

        return new ArrayList<>(map.values());
    }

    //568666
    public static String reOrganizeString(String str) {
        int[] hash = new int[26];
        for (int i = 0; i < str.length(); i++) {
            hash[str.charAt(i)]++;
        }

        int max = 0, letter = 0;
        for (int i = 0; i < str.length(); i++) {
            if(hash[i] > max) {
                max = hash[i];
                letter = i;
            }
        }

        if (max > str.length() + 1 / 2 ) {
            return "";
        }



        return

    }

}


// Leetcode 535
// Encode and Decode TinyUrl
class Codec {

    private Map<String, String > map = new HashMap<>();
    private static String BASE_URL = "http://tinyurl.com/";
    private int counter = 1;

    public String encode(String longUrl) {
        String key = String.valueOf(counter++);
        map.put(key, longUrl);
        return BASE_URL+key;
    }

    public String decode(String shortUrl) {
        String key = shortUrl.replace(BASE_URL, "");
        return map.get(key);
    }
}

