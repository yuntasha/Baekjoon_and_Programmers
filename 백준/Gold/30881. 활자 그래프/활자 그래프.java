import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
그러니까 각 추종자 집에서 특정 위치까지의 거리가 1번에서 출발한 거리보다 짧은 애들을 모두 찾는 것
일단 1번에서 전체를 구하고
각 위치를 1로 두고 다익스트라로 뻗어나가면 되겠네
이거 역방향 가능한지 알아보고 그 부분도 넣어줘야겠네...?
 */

public class Main {

    static long MAX = 1_000_000_000_000_000_000L;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(bf.readLine());

        List<Long> results = new ArrayList<>();
        results.add(0L);
        List<Long> reverse = new ArrayList<>();
        reverse.add(0L);

        for (int t = 0; t < T; t++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());
            int N = Integer.parseInt(input.nextToken());
            int M = Integer.parseInt(input.nextToken());

            List<List<Line>> lines = new ArrayList<>();

            for (int i = 0; i <= N; i++) lines.add(new ArrayList<>());

            for (int i = 0; i < M; i++) {
                input = new StringTokenizer(bf.readLine());
                int v = Integer.parseInt(input.nextToken());
                int w = Integer.parseInt(input.nextToken());
                long x = Long.parseLong(input.nextToken());

                if (x < 0) {
                    if (results.get((int) -x) >= 0) {
                        lines.get(v).add(new Line(w, results.get((int) -x)));
                    }
                    if (reverse.get((int) -x) >= 0) {
                        lines.get(w).add(new Line(v, reverse.get((int) -x)));
                    }
                } else {
                    lines.get(v).add(new Line(w, x));
                }
            }

            results.add(getDist(N, M, lines, 1, 2));
            reverse.add(getDist(N, M, lines, 2, 1));
        }

        System.out.println(results.get(T));
    }

    public static long getDist(int N, int M, List<List<Line>> lines, int start, int end) {
        long[] dist = new long[N + 1];

        Arrays.fill(dist, MAX + 1);

        PriorityQueue<Line> pq = new PriorityQueue<>(Comparator.comparingLong(Line::getV));
        pq.add(new Line(start, 0));

        while (!pq.isEmpty()) {
            Line now = pq.remove();

            if (dist[now.d] <= now.v) continue;
            dist[now.d] = now.v;

            for (Line l : lines.get(now.d)) {
                if (l.v + now.v > MAX) continue;
                if (dist[l.d] <= l.v + now.v) continue;
                pq.add(new Line(l.d, now.v + l.v));
            }
        }

        return dist[end] == MAX + 1 ? -1 : dist[end];
    }

    static class Line {
        int d;
        long v;

        public Line(int d, long v) {
            this.d = d;
            this.v = v;
        }

        public long getV() {
            return v;
        }
    }
}