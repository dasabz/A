package goldmine.easy;

public class SquareRoot {
    public static double squareRoot(double x){
        double threshold = 0.001;
        double guess = x/2.0;
        for(int iterations=500;iterations>0 &&
                Math.abs(guess*guess-x)>threshold;iterations--){
            guess=guess-((guess*guess-x)/(2.0*guess));
        }
        return guess;
    }
}
