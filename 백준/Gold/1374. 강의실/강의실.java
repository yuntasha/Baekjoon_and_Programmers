import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bf.readLine());

        var pq = new PriorityQueue<Lecture>(Comparator.comparingInt(Lecture::getStart));

        for (int i=0; i<N; i++) {
            pq.add(new Lecture(Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray()));
        }

        System.out.println(solution(N, pq));
    }

    static int solution(int N, PriorityQueue<Lecture> nextLecture) {
        var result = 0;
        var t = 0;

        var nowLecture = new PriorityQueue<Lecture>(Comparator.comparingInt(Lecture::getEnd));

        while (!nextLecture.isEmpty()) {
            var lecture = nextLecture.remove();
            nowLecture.add(lecture);

            while (!nextLecture.isEmpty() && nextLecture.peek().getStart() == lecture.getStart()) {
                nowLecture.add(nextLecture.remove());
            }

            while (!nowLecture.isEmpty() && nowLecture.peek().getEnd() <= lecture.getStart()) {
                nowLecture.remove();
            }

            result = Math.max(result, nowLecture.size());
        }

        return result;
    }

    static class Lecture {
        int n;
        int start;
        int end;

        Lecture(int[] input) {
            this.n = input[0];
            this.start = input[1];
            this.end = input[2];
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }
    }
}