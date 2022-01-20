package goldmine.easy;

public class SecondSmallest {
    public static int secondSmallest(int []x){
        if(x.length<2){
            return (0);
        }
        int Smallest = Integer.MAX_VALUE;
        int secSmallest = Integer.MAX_VALUE;
        int elem;
        for (int i=0;i<x.length;i++){
            elem = x[i];
            if(elem<Smallest){
                secSmallest=Smallest;
                Smallest=elem;
            }else if(elem<secSmallest) {
                secSmallest = elem;
            }
        }
        return secSmallest;
    }
}
