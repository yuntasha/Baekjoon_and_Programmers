/*
A B C
C만 가득 차있음
최대 200
1000씩 만들어서 해시값으로 치환하면 방문처리가 더 간단할수도?
그럼 이렇게 일단하고
방법
A C
A B
B A
B C
C A
C B
6가지 그냥 깡으로 하면 될듯?
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int A = Integer.parseInt(input.nextToken());
        int B = Integer.parseInt(input.nextToken());
        int C = Integer.parseInt(input.nextToken());

        System.out.print(solution(A, B, C));
    }

    public static String solution(int A, int B, int C) {
        Set<Integer> result = new TreeSet<>();
        find(0, 0, C, A, B, C, result, new HashSet<>());

        StringBuilder output = new StringBuilder();

        for (int n : result) output.append(" ").append(n);

        return output.substring(1);
    }

    public static void find(int a, int b, int c, int A, int B, int C, Set<Integer> result, Set<Integer> visited) {
        if (visited.contains(hash(a, b, c))) return;
        visited.add(hash(a, b, c));

        if (a == 0) result.add(c);

        int m = move(a, b, A, B);
        find(a - m, b + m, c, A, B, C, result, visited);

        m = move(a, c, A, C);
        find(a - m, b, c + m, A, B, C, result, visited);

        m = move(b, a, B, A);
        find(a + m, b - m, c, A, B, C, result, visited);

        m = move(b, c, B, C);
        find(a, b - m, c + m, A, B, C, result, visited);

        m = move(c, a, C, A);
        find(a + m, b, c - m, A, B, C, result, visited);

        m = move(c, b, C, B);
        find(a, b + m, c - m, A, B, C, result, visited);
    }

    // a->b일때 옮겨지는 물의 양
    public static int move(int a, int b, int A, int B) {
        return Math.min(a, B - b);
    }

    public static int hash(int A, int B, int C) {
        return A * 1000 * 1000 + B * 1000 + C;
    }
}