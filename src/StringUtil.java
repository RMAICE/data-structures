import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StringUtil {
    public static int countVowels(String str) {
        int count = 0;
        
        if (str == null)
            return count;

        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'o', 'u', 'i'));

        for (char ch : str.toLowerCase().toCharArray())
            if (vowels.contains(ch))
                count++;

        return count;
    }

    public static String reverse(String str) {
        if (str == null)
            return "";

        StringBuilder result = new StringBuilder();

        for (int i = str.length() - 1; i >= 0; i--)
            result.append(str.charAt(i));

        return result.toString();
    }

    public static String reverseSentence(String inc) {
        if (inc == null)
            return "";

        String[] words = inc.trim().split(" ");

        Collections.reverse(Arrays.asList(words));

        return String.join(" ", words);
    }

    public static boolean isRotation(String ref, String target) {
        if (ref == null || target == null)
            return false;
        
        if (ref.length() != target.length())
            return false;
        
        int j = target.indexOf(ref.charAt(0));
        for (int i = j; i < ref.length() - j; i++)
            if (ref.charAt(i) != target.charAt(++j))
                return false;

        j = 0;
        for (int i = 0; i < j; i++)
            if (ref.charAt(i) != target.charAt(++j))
                return false;

        return true;
    }

    public static String removeDuplicates(String str) {
        if (str == null)
            return "";

        StringBuilder output = new StringBuilder();
        Set<Character> seen = new HashSet<>();

        for (char ch : str.toCharArray()) {
            if (!seen.contains(ch)) {
                output.append(ch);
                seen.add(ch);
            }
        }

        return output.toString();
    }

    public static char mostRepeatedCharachter(String str) {
        if (str == null || str.isEmpty())
            throw new IllegalArgumentException();

        final int ASCII_SIZE = 256;
        int[] counts = new int[ASCII_SIZE];

        for (char ch : str.toCharArray())
            counts[ch]++;

        int biggest = 0;
        char ch = ' ';
        for (int i = 0; i < counts.length; i++)
            if (counts[i] > biggest) {
                biggest = counts[i];
                ch = (char) i;
            }

        return ch;
    }

    public static String capitalizeWords(String str) {
        if (str == null)
            return "";

        String[] words = str.toLowerCase().trim().split(" ");
        List<String> result = new ArrayList<>();
        
        for (int i = 0; i < words.length; i++) {
            String word = words[i].trim();
            if (word.length() < 1)
                continue;

            char[] wordChars = word.toCharArray();
            wordChars[0] = Character.toUpperCase(wordChars[0]);

            result.add(String.valueOf(wordChars));
        }

        return String.join(" ", result);
    }

    public static boolean areAnagram(String ref, String target) {
        if (ref == null || target == null)
            return false;

        if (ref.length() != target.length())
            return false;

        final int ASCII_SIZE = 26;
        int[] frequencies = new int[ASCII_SIZE];

        for (char ch : ref.toLowerCase().toCharArray())
            frequencies[ch - 'a']++;

        for (char ch : target.toLowerCase().toCharArray()) {
            int index = ch - 'a';
            if (frequencies[index] == 0)
                return false;
            frequencies[index]--;
        }
        
        return true;
    }

    public static boolean isPalindrome(String str) {
        if (str == null)
            return false;
        
        int left = 0;
        int right = str.length() - 1;

        while (left < right)
            if (str.charAt(left++) != str.charAt(right++))
                return false;
        
        return true;
    }
}
