import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var s1 = bufferedReader.readLine().strip().toCharArray();
        var s2 = bufferedReader.readLine().strip().toCharArray();

        System.out.println(solution(s1, s2));
    }


    static String solution(char[] s1, char[] s2) {
        var stk = new LinkedList<Integer>();

        for (int i=0; i<s1.length; i++){
            if (s1[i] == s2[s2.length-1]) {
                var l = new ArrayList<Integer>();
                var flag = false;
                l.add(i);
                for (int j=s2.length-2; j>=0; j--){
                    if (stk.isEmpty()) {
                        flag = true;
                        break;
                    }
                    var now = stk.removeLast();
                    l.add(now);
                    if (s1[now] != s2[j]) {
                        flag = true;
                        break;
                    }
                }
                while (!l.isEmpty() && flag) stk.addLast(l.remove(l.size()-1));
            } else {
                stk.addLast(i);
            }
        }

        var sb = new StringBuilder();
        if (stk.isEmpty()) return "FRULA";
        while (!stk.isEmpty()) {
            var now = stk.remove();
            sb.append(s1[now]);
        } // <- 이거 char로 형변형
        return sb.toString();
    }
}