package goldmine.easy;

import java.util.LinkedList;

public class Election {
    public static int whoIsElected(int n,int k){
        LinkedList<Integer> list = new LinkedList<>();
        for(int i=0;i<n;i++)
            list.add(i);

        int i=-1;
        while(list.size()>1){
            list.remove( (i+k)%list.size());
            i = (i+k)%(list.size()+1)-1;
        }
        return list.getFirst();
    }
}
