/*
그냥 전부 문자열로 구해두자
포인터로 그냥 전부 해버려
그리고 전체를 싹 돌면서 B나 W로 압축가능한지 확인하고 가능하면 더 내려가지 않는 방법으로 조회하면서 기본 값 구하기
다시 전체를 싹 돌면서 이미 방문한 적 있는 문자열이면 더 내려가지 않는 방법으로 조회하면서 압축 값 구하기

1111이 1로 합쳐져서 1이 되고
0111 1 1111 1111이랑
0 1111 1111 1111이랑 다르게 봐야한다
저거 띄우자
 */

import java.io.*;
import java.util.*;

public class Main {

    static HashSet<String> set = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(input.nextToken());
        int m = Integer.parseInt(input.nextToken());

        int N = 1;

        while (n > N || m > N) {
            N = N << 1;
        }

        int[][] map = new int[N][N];

        for (int i = 0; i < n; i++) {
            String[] in = bf.readLine().split("");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(in[j]);
            }
        }

        System.out.println(solution(N, n, m, map));
    }

    static String solution(int N, int n, int m, int[][] map) {
        Node root = makeTree(0, 0, N >> 1, N, map);

        int result1 = findResult1(root);
        int result2 = findResult2(root);

        return result1 + " " + result2;
    }

    static int findResult2(Node now) {
        if (now.s.length() <= 2) return 1;
        if (set.contains(now.s)) return 0;
        set.add(now.s);

        int result = 1;

        for (int i = 0; i < 4; i++) {
            result += findResult2(now.child[i]);
        }

        return result;
    }

    static int findResult1(Node now) {
        if (now.s.length() <= 2) return 1;
        int result = 1;

        for (int i = 0; i < 4; i++) {
            result += findResult1(now.child[i]);
        }

        return result;
    }

    static Node makeTree(int r, int c, int l, int N, int[][] map) {
        if (l == 0) {
            return new Node(null, String.valueOf(map[r][c]));
        }

        Node[] child = new Node[4];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                child[i * 2 + j] = makeTree(r + i * l, c + j * l, l >> 1, N, map);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append(child[i].s).append(" ");
        }

        if (sb.toString().equals("0 0 0 0 ")) {
            return new Node(child, "0");
        }
        if (sb.toString().equals("1 1 1 1 ")) {
            return new Node(child, "1");
        }

        return new Node(child, sb.toString());
    }

    static class Node {
        Node[] child;
        String s;

        public Node(Node[] child, String s) {
            this.child = child;
            this.s = s;
        }
    }
}

/*
4 4
1010
0101
1010
0101

21 6

4 4
1100
1100
0011
0011

5 5

4 4
1111
1111
1111
1111
 */