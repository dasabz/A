package goldmine.medium;

import java.util.HashMap;

import java.util.ArrayList;

public class BestAverageGrade {
    public static Integer bestAverageGrade(String [][] scores){
        if(scores.length==0)
            return 0;

        HashMap<String,ArrayList<Integer>> studentsToScores = new HashMap<String,ArrayList<Integer>>();
        for(String[] scoreRow :scores) {
            if (scoreRow.length != 2)
                return 0;
            String student = scoreRow[0];
            Integer score = Integer.parseInt(scoreRow[1]);
            ArrayList<Integer> currentScores = studentsToScores.get(student);
            if (currentScores == null) {
                currentScores = new ArrayList<>();
                currentScores.add(score);
                studentsToScores.put(student, currentScores);
            } else {
                currentScores.add(score);
            }
        }
        double max= -Double.MAX_VALUE;
        for(ArrayList<Integer> studentScores : studentsToScores.values()){
            Integer sum =0;
            for(Integer i : studentScores){
                sum+=i;
            }
            double average = sum/(double)studentScores.size();
            max = Math.max(max,average);
        }
        return (int)Math.floor(max);
    }
}
