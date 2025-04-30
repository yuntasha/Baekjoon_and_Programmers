/*
대놓고 세그먼트 트리
입력
배열 크기, 변경 횟수, 구간 합 구하는 횟수
배열
.
.
.
1(변경), 바뀔 인덱스 번호 + 1, 바꿀 번호
2(합구하기), 첫 번호, 마지막 번호
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        M += Integer.parseInt(input.nextToken());



        long[] arr = new long[N];

        for (int i = 0; i < N; i++) arr[i] = Long.parseLong(bf.readLine());

        Seg seg = new Seg(N);

        seg.init(1, N, 1, arr);

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < M; i++) {
            input = new StringTokenizer(bf.readLine());
            int type = Integer.parseInt(input.nextToken());
            int a = Integer.parseInt(input.nextToken());
            long b = Long.parseLong(input.nextToken());

            if (type == 1) {
                seg.modify(a, 1, b);
            } else {
                output.append(seg.find(a, (int) b, 1)).append("\n");
            }
        }

        System.out.print(output);
    }

    static class Seg {
        Node[] nodes;

        public Seg(int n) {
            nodes = new Node[n << 2];
        }

        public long init(int min, int max, int now, long[] arr) {
            if (min == max) {
                nodes[now] = new Node(min, max, arr[min - 1]);
                return arr[min - 1];
            }
            int mid = (min + max) >> 1;
            long nowSum = init(min, mid, now << 1, arr) + init(mid + 1, max, (now << 1) + 1, arr);

            nodes[now] = new Node(min, max, nowSum);

            return nowSum;
        }

        public long find(int min, int max, int now) {
            if (min <= nodes[now].min && nodes[now].max <= max) return nodes[now].sum;

            long result = 0;
            int mid = (nodes[now].min + nodes[now].max) >> 1;

            if (min <= mid) result += find(min, max, now << 1);
            if (mid < max) result += find(min, max, (now << 1) + 1);

            return result;
        }

        public long modify(int idx, int now, long num) {
            if (nodes[now].min == nodes[now].max) {
                long m = num - nodes[now].sum;
                nodes[now].sum = num;
                return m;
            }

            int mid = (nodes[now].min + nodes[now].max) >> 1;

            if (idx <= mid) {
                long m = modify(idx, now << 1, num);
                nodes[now].sum += m;
                return m;
            } else {
                long m = modify(idx, (now << 1) + 1, num);
                nodes[now].sum += m;
                return m;
            }
        }
    }

    static class Node {
        int min;
        int max;
        long sum;

        public Node(int min, int max, long sum) {
            this.max = max;
            this.min = min;
            this.sum = sum;
        }
    }
}