import java.util.Stack;

public class BalancedParanthesis {
    public static String checkBalance(String str) {
        Stack<Character> stack = new Stack();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '[' || ch == '(' || ch == '{') {
                stack.push(ch);
            } else if ((ch == ']' || ch == '}' || ch == ')') && (!stack.isEmpty())) {
                if ((stack.peek() == '(' && ch == ')') || (stack.peek() == '{' && ch == '}') || (stack.peek() == '[' && ch == ']')) {
                    stack.pop();
                } else {
                    return "Not Balanced";
                }
            } else {
                if ((ch == ']' || ch == '}' || ch == ')')) {
                    return "Not Balanced";
                }
            }
        }
        if (stack.isEmpty())
            return "Balanced Parenthisis";
        else
            return "Not Balanced";
    }

    public static void main(String[] args) {
        String str = "([{}])";
        if (str.isEmpty()) {
            System.out.println("Empty String");
            return;
        }
        System.out.println(BalancedParanthesis.checkBalance(str));
    }
}

