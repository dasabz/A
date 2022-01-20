package goldmine.medium;

public class Atoi {
    public static int atoi(String str){
        int result=0,i=0,multiplier=1;
        if(str.length()!=0 && str.charAt(0)=='-') {
            multiplier = -1;
            i++;
        }
        for(;i<str.length();i++){
            if(str.charAt(i)< '0' || str.charAt(i) >'9')
                break;
            result= result*10+ str.charAt(i)-'0';
        }
        return result*multiplier;

    }
}
