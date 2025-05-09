/*
높이가 밑변 길이의 2배
즉 밑변^2이 넓이라는 뜻

start를 기준으로 우선순위 큐에 넣어버림

모든 범위에 닿지 않는 경우 - 그냥 삼각형임
범위가 겹치는 경우

잉 잠시만...
완전히 먹힌 경우 - 작은쪽 제거
일부분 먹힌 경우 - 작은쪽의 삼각형 일부분을 없앤다는 느낌으로 접근?
a - b, c - d인 경우
a < b, c < b, c < d, a < d인 경우
    (b - a)^2 + (d - c)^2 - (b - c)^2
그냥 다른거 신경쓸 필요 없이 이전에 있던 것의 최댓값을 처리하면 되는거 아닌가?
end가 같은 경우 무시
end가 늘어났다면
(b - a)^2 - max(end - a, 0)^2

 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        PriorityQueue<Mountain> mountains = new PriorityQueue<>(Comparator.comparingInt(Mountain::getS)
                .thenComparingInt(Mountain::getE));

        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());
            mountains.add(new Mountain(Integer.parseInt(input.nextToken()), Integer.parseInt(input.nextToken())));
        }

        System.out.println(solution(N, mountains));
    }

    static int solution(int N, PriorityQueue<Mountain> mountains) {
        int result = 0;
        int end = 0;

        while (!mountains.isEmpty()) {
            Mountain m = mountains.remove();
            if (m.e <= end) continue;
            result += (m.e - m.s) * (m.e - m.s) - Math.max(end - m.s, 0) * Math.max(end - m.s, 0);
            end = m.e;
        }

        return result;
    }

    static class Mountain {
        int s;
        int e;

        public Mountain(int s, int e) {
            this.s = s;
            this.e = e;
        }

        public int getS() {
            return s;
        }

        public int getE() {
            return -e;
        }
    }
}