/*
그냥 위치로 정렬
대충 누적합해주고
중간이란말이지?
1 1 1 1
이면
중간 이상값 처음 나오면 거기
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        List<Town> towns = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());
            towns.add(new Town(Long.parseLong(input.nextToken()), Long.parseLong(input.nextToken())));
        }

        System.out.println(solution(N, towns));
    }

    static long solution(int N, List<Town> towns) {
        long sum = 0;

        towns.sort(Comparator.comparingLong(Town::getX));

        for (int i = 0; i < N; i++) {
            sum += towns.get(i).v;
        }

        long now = 0;
        long half = (sum >> 1) + (sum & 1);

        for (int i = 0; i < N; i++) {
            now += towns.get(i).v;
            if (half <= now) return towns.get(i).x;
        }

        return towns.get(0).x;
    }

    static class Town {
        long x;
        long v;

        public Town(long x, long v) {
            this.x = x;
            this.v = v;
        }

        public long getX() {
            return x;
        }
    }
}