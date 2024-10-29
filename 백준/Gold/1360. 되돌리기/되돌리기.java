import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bf.readLine());

        Stack<Command> stk = new Stack<>();
        
        for (int i=0; i<N; i++) {
            stk.push(new Command(bf.readLine()));
        }

        System.out.println(solution(N, stk));
    }

    static String solution(int N, Stack<Command> stk) {
        var sb = new StringBuffer();

        while (!stk.isEmpty()) {
            var now = stk.pop();
            if (now.add) {
                sb.append(now.node);
            } else {
                while (!stk.isEmpty() && stk.peek().time >= now.time-Integer.parseInt(now.node)) {
                    stk.pop();
                }
            }
        }

        sb.reverse();
        return sb.toString();
    }

    static class Command {
        boolean add;
        String node;
        int time;

        Command(String s) {
            var arr = s.split(" ");
            add = arr[0].equals("type");
            node = arr[1];
            time = Integer.parseInt(arr[2]);
        }
    }
}