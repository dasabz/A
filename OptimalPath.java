package goldmine.medium;

/*
        {{0,0,0,0,5}, finish     N
         {0,1,1,1,0},           W  E
   start {2,0,0,0,0}}            S

   //Can only travel north or east
 */
public class OptimalPath {
    public static Integer optimalPath(Integer[][] grid){
        if(grid.length==0 || grid[0].length ==0 )
            return 0;
        for(int row = grid.length-1;row>=0;row--){
            for(int col=0;col<grid[0].length;col--){
                if(row<grid.length-1 && col>0){
                    grid[row][col]+=Math.max(grid[row+1][col],grid[row][col-1]);
                }else if(row<grid.length-1)
                    grid[row][col]+=grid[row+1][col];
                else if(col>0)
                    grid[row][col]+=grid[row][col-1];
            }
        }
        int result = grid[0][grid[0].length-1];
        return result;
    }
}
