import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/*
플로이드 워셜인가 벨만포드인가 뭐시기로 풀자
0 - 평시
-1  - 적
1 - 친구
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());
        int M = Integer.parseInt(bf.readLine());

        Node[] arr = new Node[N + 1];

        for (int i = 1; i <= N; i++) arr[i] = new Node(i);

        for (int i = 0; i < M; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());
            boolean isF = input.nextToken().charAt(0) == 'F';
            int a = Integer.parseInt(input.nextToken());
            int b = Integer.parseInt(input.nextToken());

            if (isF) {
                arr[a].f.add(b);
                arr[b].f.add(a);
            } else {
                arr[a].e.add(b);
                arr[b].e.add(a);
            }
        }

        System.out.println(solution(N, M, arr));
    }

    static int solution(int N, int M, Node[] nodes) {
        int[] g = IntStream.range(0, N + 1).toArray();

        for (int i = 1; i <= N; i++) {
            for (int s = 0; s < nodes[i].e.size(); s++) {
                for (int e = s + 1; e < nodes[i].e.size(); e++) {
                    int a = find(nodes[i].e.get(s), g);
                    int b = find(nodes[i].e.get(e), g);

                    if (a < b) {
                        g[b] = a;
                    } else {
                        g[a] = b;
                    }
                }
            }
        }
        
        for (int i = 1; i <= N; i++) {
            for (int s = 0; s < nodes[i].f.size(); s++) {

                int n1 = find(i, g);
                int n2 = find(nodes[i].f.get(s), g);

                if (n1 < n2) {
                    g[n2] = n1;
                } else {
                    g[n1] = n2;
                }

                for (int e = s + 1; e < nodes[i].f.size(); e++) {
                    int a = find(nodes[i].f.get(s), g);
                    int b = find(nodes[i].f.get(e), g);

                    if (a < b) {
                        g[b] = a;
                    } else {
                        g[a] = b;
                    }
                }
            }
        }

        int result = 0;
        
        for (int i = 1; i <= N; i++) {
            if (g[i] == i) result++;
        }

        return result;
    }

    static int find(int n, int[] g) {
        if (n != g[n]) g[n] = find(g[n], g);
        return g[n];
    }

    static class Node {
        int n;
        List<Integer> e;
        List<Integer> f;

        public Node(int n) {
            this.n = n;
            this.e = new ArrayList<>();
            this.f = new ArrayList<>();
        }
    }
}