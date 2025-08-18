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

}

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

