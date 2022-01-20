package dynamicProgramming;

public class A05_MaxPathSumMatrix {

    int findMaxPath(int mat[][]) {
        // To find max val in first row
        int res = -1;
        for (int i = 0; i < mat[0].length; i++)
            res = Math.max(res, mat[0][i]);

        for (int i = 1; i < mat.length; i++) {
            res = -1;
            for (int j = 0; j < mat[0].length; j++) {
                // When all paths are possible
                if (j > 0 && j < mat[0].length - 1)
                    mat[i][j] += Math.max(mat[i - 1][j], Math.max(mat[i - 1][j - 1], mat[i - 1][j + 1]));
                    // When diagonal right is not possible
                else if (j > 0)
                    mat[i][j] += Math.max(mat[i - 1][j], mat[i - 1][j - 1]);
                    // When diagonal left is not possible
                else if (j < mat[0].length - 1)
                    mat[i][j] += Math.max(mat[i - 1][j], mat[i - 1][j + 1]);

                // Store Math.max path sum
                res = Math.max(mat[i][j], res);
            }
        }
        return res;
    }

}
