package goldmine.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
//Input: letters=öet", dictionary={"to","toe","toes"}
//Output :{"toe"}
public class LongestWord {
    class Dictionary{
        private String[] entries;
        public Dictionary(String []entries){
            this.entries=entries;
        }
        public boolean contains(String word){
            return Arrays.asList(entries).contains(word);
        }
    }
    public static Set<String> longestWord(String letters,Dictionary dict){
        Set<String> result = new HashSet<>();
        if(dict.contains(letters)){
            result.add(letters);
        }
        return result;
    }
}
