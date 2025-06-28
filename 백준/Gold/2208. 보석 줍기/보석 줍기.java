/*
보석의 최대 개수는 2억개까지 먹을 수 있다
10만 * 10만이니 누적합은 100억이다
이거 dp로 확인할 수 있을 느낌이다
앞에서부터 왔을때 그냥 이 값을 사용하는게 낫나 아니면 사용하지 않는게 낫나
문제는 10만 * 10만이 될 수 있다
누적합에 이분탐색?
앞은 최소값 찾아야하고 뒤는 최대값이 되어야하니
3개의 배열을 만들자
누적합 배열
누적합 왼쪽부터 최소값 배열
누적합 오른쪽부터 최대값 배열
그럼 대충 40만 나올듯
 */

import java.io.*;
import java.util.*;

public class Main {

    static int MIN_POINT = -100_000_000;

    public static void main(String[] args) throws IOException {

//        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = readI();
        int M = readI();

        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = readUI();
        }

        System.out.println(solution(N, M, arr));
    }

    static int solution(int N, int M, int[] arr) {
        int[] sum = new int[N];

        sum[0] = arr[0];

        for (int i = 1; i < N; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }

        int[] min = new int[N];

        min[0] = sum[0];

        for (int i = 1; i < N; i++) {
            min[i] = Math.min(min[i - 1], sum[i]);
        }

        int[] max = new int[N];

        max[N - 1] = sum[N - 1];

        for (int i = N - 2; i >= 0; i--) {
            max[i] = Math.max(max[i + 1], sum[i]);
        }

        int result = Math.max(0, max[M - 1]);
        // max[i + M - 1] - min[i - 1];

        for (int i = 1; i <= N - M; i++) {
            result = Math.max(result, max[i + M - 1] - min[i - 1]);
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

