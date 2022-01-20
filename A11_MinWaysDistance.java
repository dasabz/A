package dynamicProgramming;

public class A11_MinWaysDistance {
    int printCountRec(int dist) {
        if (dist < 0)
            return 0;
        if (dist == 0)
            return 1;
        return printCountRec(dist - 1) + printCountRec(dist - 2) + printCountRec(dist - 3);
    }

    int printCountDP(int dist) {
        int count[] = new int[dist + 1];
        count[0] = 1;
        count[1] = 1;
        count[2] = 2;
        for (int i = 3; i <= dist; i++)
            count[i] = count[i - 1] + count[i - 2] + count[i - 3];
        return count[dist];
    }
}
