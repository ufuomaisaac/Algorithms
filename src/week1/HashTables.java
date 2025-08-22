package week1;

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
    // Leetcode 767
    // Reorganizing String
    public static String reOrganizeString(String str) {
        int[] hash = new int[26];
        for (int i = 0; i < str.length(); i++) {
            hash[str.charAt(i)]++;
        }

        int max = 0, letterIdx = 0;
        for (int i = 0; i < str.length(); i++) {
            if(hash[i] > max) {
                max = hash[i];
                letterIdx = i;
            }
        }

        if (max > str.length() + 1 / 2 ) {
            return "";
        }

        char[] res = new char[str.length()];
        int idx = 0;

        while (hash[letterIdx] > 0) {
            res[idx] = (char) (letterIdx + 'a');
            idx += 2;
            hash[letterIdx]--;
        }

        for (int i = 0; i < hash.length; i++) {
            while (hash[i] > 0) {
                if (idx >= res.length) {
                    idx = 1;
                }
                res[idx] = (char) (i + 'a');
                idx += 2;
                hash[i]--;
            }
        }

        return String.valueOf(res);
    }


    // Longest consecutive number
    // Leetcode 128
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int longest = 0;

        for (int num : set) {
            // Only start counting if 'num' is the beginning of a sequence
            if (!set.contains(num - 1)) {
                int currNum = num;
                int count = 1;

                // Count consecutive numbers
                while (set.contains(currNum + 1)) {
                    currNum++;
                    count++;
                }

                // Update the maximum length
                longest = Math.max(longest, count);
            }
        }
        return longest;
    }


    //I do not understand the process, come back to it
    // Split Array into consecutive Subsequences
    // Leetcode 659
    class Solution {
        public boolean isPossible(int[] nums) {
            return false;
        }
    }



    // Number of Matching Subsequences
    // Leetcode 792
    public int numMatchingSubseq(String s, String[] words) {

        Map<String,Integer> map = new HashMap<>();
        for(String word:words){
            map.put(word,map.getOrDefault(word,0)+1);
        }

        int ans = 0;
        char ch[] = s.toCharArray();

        for(String word:map.keySet()){

            char temp[] = word.toCharArray();
            int i = 0;
            int j = 0;

            while(i<ch.length && j<temp.length){
                if(ch[i]==temp[j]){
                    i++;
                    j++;
                }else{
                    i++;
                }
            }

            if(j==temp.length){
                ans+=map.get(word);
            }

        }

        return ans;

    }



    //Number of Good ways to split a string
    // Leetcode 1525
    public int numSplits(String s) {
        int n = s.length();
        int[] prefix = new int[n];
        int[] suffix = new int[n];
        Set<Character> pSet = new HashSet<>();
        Set<Character> qSet = new HashSet<>();

        for (int i = 0; i < n; i++) {
            pSet.add(s.charAt(i));
            qSet.add(s.charAt(n - 1 - i));
            prefix[i] = pSet.size();
            suffix[n - 1 - i] = qSet.size();
        }

        int count  = 0;
        for (int i = 1; i < n; i++) {
            if (prefix[i - 1] == suffix[i]) {
                count++;
            }
        }
        return count;
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

