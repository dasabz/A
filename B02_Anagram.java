package goldmine.easy;

import java.util.*;

public class Anagram {
    interface AnagramSolution{

    }
    public static boolean groupWordsByAnagram(){
        List<String> words= Arrays.asList("cat","dog","god","cat");
        AnagramSolution sol =(input)->{
            Map<String,Set<String>> hashIndex = new HashMap<>();
            input.stream().forEach((word)->{
                String sorted = sortCharacters(word);
                if(!hashIndex.containsKey(sorted))
                    hashIndex.put(sorted,new HashSet<>());
                else
                    hashIndex.get(sorted).add(word);
            });
            return new HashSet<>(hashIndex.values());
        };
        Set<Set<String>> grouped = sol.group(words);
    }

    private static String sortCharacters(String word){
        char[] chars=word.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
