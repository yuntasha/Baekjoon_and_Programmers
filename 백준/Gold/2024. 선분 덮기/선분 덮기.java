/*
채워야할 길이 M임
[0, M]을 채워야한다
선분이 여러개가 주어졌을 때
그리디하게 풀면 될 것 같은데
예외가 없어...
시작 지점으로 pq를 만들어
그다음에 현재 위치로 갈 수 있는 애들 모두를 가져와
그 다음에 도착지 이상이 됐을때 몇개인지 구해
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int M = Integer.parseInt(bf.readLine());

        String input;

        PriorityQueue<Line> pq = new PriorityQueue<>(Comparator.comparingInt(Line::getS));

        while (!(input = bf.readLine()).equals("0 0")) {
            int[] line = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
            pq.add(new Line(line));
        }

        System.out.println(solution(M, pq));
    }

    static int solution(int M, PriorityQueue<Line> pq) {
        int now = 0;
        int count = 0;

        while (now < M) {
            int next = now;
            count++;
            while (!pq.isEmpty() && pq.peek().s <= now) {
                next = Math.max(next, pq.remove().e);
            }

            if (now == next) return 0;
            now = next;
        }

        return count;
    }

    static class Line {
        int s;
        int e;

        public Line(int[] input) {
            this.s = Math.min(input[0], input[1]);
            this.e = Math.max(input[0], input[1]);
        }

        public int getS() {
            return s;
        }
    }
}