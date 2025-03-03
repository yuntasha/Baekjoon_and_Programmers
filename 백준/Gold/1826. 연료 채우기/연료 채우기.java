import java.io.*;
import java.util.*;

/*
앞에서부터 가능한거 PQ에 싹다 넣자
그러고 가장 좋은거부터 계속 가져오면 굿
 */

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        PriorityQueue<Station> stations = new PriorityQueue<>(Comparator.comparingInt(Station::getD));

        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());

            int d = Integer.parseInt(input.nextToken());
            int v = Integer.parseInt(input.nextToken());

            stations.add(new Station(d, v));
        }

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int D = Integer.parseInt(input.nextToken());
        int p = Integer.parseInt(input.nextToken());

        System.out.println(solution(N, stations, D, p));
    }

    static int solution(int N, PriorityQueue<Station> stations, int D, int p) {

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        int now = p;
        int result = 0;

        while (now < D) {
            while (!stations.isEmpty() && stations.peek().d <= now) {
                pq.add(stations.remove().v);
            }

            if (pq.isEmpty()) break;
            result++;
            now += pq.remove();
        }
        
        if (now < D) return -1;
        return result;
    }

    static class Station {
        int d;
        int v;

        public Station(int d, int v) {
            this.d = d;
            this.v = v;
        }

        public int getD() {
            return d;
        }

        public int getV() {
            return v;
        }
    }
}