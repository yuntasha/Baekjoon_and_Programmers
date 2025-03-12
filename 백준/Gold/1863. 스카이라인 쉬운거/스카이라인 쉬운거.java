/*
이거 스택일 느낌이 강하게 든다

X축으로 정렬
그 y축으로 탐색
일단 만약 스택이 빈 경우 그냥 넣어줘
만약 다음 것이 더 큰 것이라면 스택에 채워줌
만약 다음 것이 더 작은 것이라면 스택에 있는 것 빼고 카운트 + 1 ---- 다시 반복
만약 같다면 그냥 지나가
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        List<Node> lines = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());

            int x = Integer.parseInt(input.nextToken());
            int y = Integer.parseInt(input.nextToken());

            lines.add(new Node(x, y));
        }

        System.out.println(solution(N, lines));
    }

    static int solution(int N, List<Node> lines) {
        lines.sort(Comparator.comparingInt(Node::getX));

        ArrayDeque<Integer> stk = new ArrayDeque<Integer>();
        int result = 0;

        for (Node line : lines) {
            while (!stk.isEmpty() && (stk.peekLast() > line.y)) {
                stk.removeLast();
                result++;
            }

            if (line.y == 0) continue;

            if (stk.isEmpty() || stk.peekLast() < line.y) {
                stk.add(line.y);
            }
        }

        return result + stk.size();
    }

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }
    }
}