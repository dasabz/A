package goldmine.easy;

import java.util.SortedSet;
import java.util.TreeSet;

public class PanagramDetector {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    //Find letters of alphabet not included
    public String findMissingLetters(String sentence){
        SortedSet<Character> missing  = new TreeSet<>();
        for(int i=0;i<ALPHABET.length();i++){
            missing.add(new Character(ALPHABET.charAt(i)));
        }
        String s = sentence.toLowerCase();
        for(int i=0;i<s.length();i++)
            missing.remove(new Character(s.charAt(i)));

        StringBuilder sb= new StringBuilder();
        for(Character c:missing){
            sb.append(c.charValue());
        }
        return sb.toString();
    }
}
