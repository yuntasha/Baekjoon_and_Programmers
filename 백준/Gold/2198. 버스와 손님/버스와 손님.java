/*
1 - N
음음
1 -> N으로 가는 애 하나 구하고
N -> 1로 가는 애 구하고
2개로 나뉘어서 저장

도착지점 기준 정렬하고 출발지점으로 역정렬
누적합으로 일단 구간합 구해주고가 안되는구나

 */

import java.io.*;
import java.util.*;

public class Main {

    static int MIN_POINT = -100_000_000;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int K = readI();
        int N = readI();
        int C = readI();

        List<Bus> up = new ArrayList<>();
        List<Bus> down = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            int s = readI();
            int e = readI();
            int m = readI();

            if (s < e) {
                up.add(new Bus(s, e, m));
            } else {
                down.add(new Bus(s, e, m));
            }
        }

        System.out.println(solution(K, N, C, up, down));
    }

    static int solution(int K, int N, int C, List<Bus> up, List<Bus> down) {
        up.sort(Comparator.comparingInt(Bus::getE));
        down.sort(Comparator.comparingInt(Bus::getME));

        int result = 0;
        int[] counts = new int[N + 1];

        Arrays.fill(counts, C);

        for (Bus bus : up) {
            int min = bus.m;
            for (int i = bus.s; i < bus.e; i++) {
                min = Math.min(min, counts[i]);
            }

            for (int i = bus.s; i < bus.e; i++) {
                counts[i] -= min;
            }

//            System.out.println("bus.s = " + bus.s);
//            System.out.println("bus.e = " + bus.e);
//            System.out.println("bus.m = " + bus.m);
//            System.out.println("min = " + min);

            result += min;
        }

        Arrays.fill(counts, C);

        for (Bus bus : down) {
            int min = bus.m;
            for (int i = bus.s; i > bus.e; i--) {
                min = Math.min(min, counts[i]);
            }

            for (int i = bus.s; i > bus.e; i--) {
                counts[i] -= min;
            }
//            System.out.println("bus.s = " + bus.s);
//            System.out.println("bus.e = " + bus.e);
//            System.out.println("bus.m = " + bus.m);
//            System.out.println("min = " + min);

            result += min;
        }

        return result;
    }

    static long readUL() throws IOException {
        int n = System.in.read();
        return n == '-' ? -readL() : readL(n & 15);
    }

    static int readUI() throws IOException {
        int n = System.in.read();
        return n == '-' ? -readI() : readI(n & 15);
    }

    static int readI() throws IOException {
        int n = 0;
        int c;

        while ((c = System.in.read()) >= '0') {
            n = (n << 3) + (n << 1) + (c & 15);
        }

        return n;
    }

    static int readI(int n) throws IOException {
        int c;

        while ((c = System.in.read()) >= '0') {
            n = (n << 3) + (n << 1) + (c & 15);
        }

        return n;
    }

    static long readL() throws IOException {
        long n = 0;
        int c;

        while ((c = System.in.read()) >= '0') {
            n = (n << 3) + (n << 1) + (c & 15);
        }

        return n;
    }

    static long readL(long n) throws IOException {
        int c;

        while ((c = System.in.read()) >= '0') {
            n = (n << 3) + (n << 1) + (c & 15);
        }

        return n;
    }

    static class Bus {
        int s;
        int e;
        int m;

        public Bus(int s, int e, int m) {
            this.s = s;
            this.e = e;
            this.m = m;
        }

        public int getE() {
            return e;
        }

        public int getME() {
            return -e;
        }
    }
}