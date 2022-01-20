package goldmine.medium;

public class DistanceBetweenStrings {
    //shortestDistance(document,"we", "just") ==4
    public static double shortestDistance(String document, String word1,String word2){
        String [] words = document.split(" ");
        int index=0;
        double shortest = document.length();
        double word1Loc=0;
        double word2Loc=0;
        for (String word: words){
            if(word.equals(word1)){
                word1Loc = index +(word.length()/2);
            }else if(word.equals(word2)){
                word2Loc=index+(word.length()/2);
            }
            if(word1Loc>0 && word2Loc>0){
                double current = word2Loc - word1Loc;
                if(current<shortest){
                    shortest=current;
                }
            }
            index+=word.length();
        }
        if(word1Loc==0 || word2Loc==0){
            return -1;
        }
        return shortest;
    }
}
