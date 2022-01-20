package goldmine.medium;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class DecimalConversion {
    public static String vulgarToDecimal(Long numerator,Long denominator){
        boolean isNegative = numerator<0;
        numerator=Math.abs(numerator);

        Long integer = numerator/denominator;
        numerator = numerator%denominator;

        Set<Long> reminders = new LinkedHashSet<>();
        Long cycleBeginsAtReminder = -1L;
        while(numerator!=0){
            numerator*=10;
            Long reminder = numerator%denominator;
            if(reminders.contains(numerator)){
                cycleBeginsAtReminder = numerator;
                break;
            }
            reminders.add(numerator);
            numerator= reminder;
        }
        StringBuilder builder= new StringBuilder();
        if(isNegative) builder.append('-');
        builder.append(integer);
        if(!reminders.isEmpty()){
            builder.append('.');
            for(Long reminder:reminders){
                if(Objects.equals(reminder,cycleBeginsAtReminder)){
                    builder.append('(');
                }
                builder.append(reminder/denominator);
            }
            if(cycleBeginsAtReminder!=-1l){
                builder.append(')');
            }
        }
        return builder.toString();
    }
}
