/*
왼 높 우 x 좌표
10억까지면 int 가능

우큐 2개 사용
더 작은 쪽에 있는 것을 사용

현재 높이
새로운 애 시작
더 큰 애 나오면 높여줌
작은 애 무시

이미 있던 곳에서 빼기
현재 값 빠지면 빼고 그 다음 큰 것을 가져오기 TreeSet?
작은 값 빠지면 그냥 빼고 무시

트리맵으로 높이 -> 개수로 만들어둠

우큐에 다 넣어버리고 시작값 작은애부터
그리고 둘 중에 작은 위치 값 찾기
찾은 좌표인 애들 모두 처리

현재 위치값 변하면 결과에 넣어버리기
*/

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = read();

        PriorityQueue<Building> buildings = new PriorityQueue<>(Comparator.comparingInt(Building::getS));

        for (int i = 0; i < N; i++) {
            buildings.add(new Building(read(), read(), read()));
        }

        System.out.println(solution(N, buildings));
    }

    public static String solution(int N, PriorityQueue<Building> starts) {
        StringBuilder result = new StringBuilder();
        PriorityQueue<Building> ends = new PriorityQueue<>(Comparator.comparingInt(Building::getE));
        TreeMap<Integer, Integer> hCount = new TreeMap<>();

        int h = 0;

        while (!starts.isEmpty() || !ends.isEmpty()) {
            int x = Integer.MAX_VALUE;

            if (!starts.isEmpty()) {
                x = Math.min(x, starts.peek().s);
            }
            if (!ends.isEmpty()) {
                x = Math.min(x, ends.peek().e);
            }

            while (!starts.isEmpty() && starts.peek().s == x) {
                Building now = starts.remove();
                hCount.put(now.h, hCount.getOrDefault(now.h, 0) + 1);
                ends.add(now);
            }

            while (!ends.isEmpty() && ends.peek().e == x) {
                Building now = ends.remove();
                hCount.put(now.h, hCount.getOrDefault(now.h, 0) - 1);
                if (hCount.get(now.h) == 0) {
                    hCount.remove(now.h);
                }
            }

            if (hCount.isEmpty()) {
                if (h == 0) continue;
                h = 0;
                result.append(x).append(" ").append(h).append(" ");
                continue;
            }

            if (h != hCount.lastKey()) {
                h = hCount.lastKey();
                result.append(x).append(" ").append(h).append(" ");
            }
        }

        return result.substring(0, result.length() - 1);
    }

    static class Building {
        int s;
        int e;
        int h;

        public Building(int s, int h, int e) {
            this.s = s;
            this.e = e;
            this.h = h;
        }

        public int getS() {
            return s;
        }

        public int getE() {
            return e;
        }
    }

    static int read() throws IOException {
        int n = 0;
        int c;

        while ((c = System.in.read()) >= '0') {
            n = (n << 3) + (n << 1) + (c & 15);
        }

        return n;
    }
}