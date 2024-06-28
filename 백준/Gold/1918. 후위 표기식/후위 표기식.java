import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var inOrder = bufferedReader.readLine().strip().toCharArray();

        System.out.println(solution(inOrder));
    }

    static String solution(char[] inOrder) {
        Stack<String> s = new Stack<>();
        StringBuilder result = new StringBuilder();
        for (char c: inOrder){
            if ('A' <= c && c <= 'Z') {
                result.append(c);
            } else if (c=='(') {
                s.push(c+"");
            } else if (c==')') {
                while (!s.isEmpty() && !s.peek().equals("(")) result.append(s.pop());
                if (!s.isEmpty()) s.pop();
            } else {
                while (!s.isEmpty() && getValue(s.peek().charAt(0)) >= getValue(c)) {
                    result.append(s.pop());
                }
                s.push(c+"");
            }
        }
        while (!s.isEmpty()) {
            var now = s.pop();
            if (!now.equals("(")) result.append(now);
        }
        return result.toString();
    }

    static int getValue(char c){
        return c=='('?0:c=='*' || c=='/' ? 10 : 5;
    }
}