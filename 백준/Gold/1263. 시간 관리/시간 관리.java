import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bf.readLine());

        var tasks = new ArrayList<Task>();

        for (int i=0; i<N; i++) tasks.add(new Task(Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray()));

        System.out.println(solution(N, tasks));
    }

    static int solution(int N, List<Task> tasks) {
        tasks.sort(Comparator.comparingInt(Task::getE).thenComparingInt(Task::getT));

        var time = 0;
        var minTime = Integer.MAX_VALUE;

        for (Task t: tasks) {
            time += t.getT();
            minTime = Math.min(minTime, t.getE() - time);
        }

        return minTime<0?-1:minTime;
    }

    static class Task {
        int e;
        int t;

        Task(int[] input) {
            t = input[0];
            e = input[1];
        }

        public int getE() {
            return e;
        }

        public int getT() {
            return t;
        }
    }
}