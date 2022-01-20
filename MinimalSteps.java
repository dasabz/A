package goldmine.easy;

public class MinimalSteps {
    private Integer minimalSteps(String ingredients){
        int n = ingredients.length();
        if(n==0)
            return 0;
        Integer dp[]= new Integer[n];
        for(int i=0;i<n;i++){
            dp[i]=Integer.MAX_VALUE;
        }
        dp[0]=1;
        for(int i=1;i<n;i++){
            dp[i]=Math.min(dp[i],dp[i-1]+1);
            //IF strig can be replicated, update 2*i +1
            if(2*i+1<n &&
                    ingredients.substring(0,i+1).equals(ingredients.substring(i+1,2*i+2))){
                dp[2*i+1]=dp[i]+1;

            }
        }
        return dp[n-1];
    }
}
