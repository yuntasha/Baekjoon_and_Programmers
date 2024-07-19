import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bufferedReader.readLine());

        System.out.println(solution(N));
    }

    static int solution(int N){
        var sosu = getList(N);
        var q = new LinkedList<Integer>();
        var result = 0;
        var sum = 0;
        while (!(sosu.isEmpty() && sum<N)) {
            if (sum<N) {
                var now = sosu.remove();
                q.add(now);
                sum += now;
            } else {
                if (N == sum) {
                    result++;
                }
                sum-=q.remove();
            }
        }
        return result;
    }

    static LinkedList<Integer> getList(int N){
        var che = new boolean[N+1];
        che[1] = true;
        var result = new LinkedList<Integer>();
        for (int i=2; i<N+1; i++){
            if (!che[i]) result.add(i);
            var now = i*2;
            while (now<N+1){
                che[now] = true;
                now+=i;
            }
        }
        return result;
    }
}