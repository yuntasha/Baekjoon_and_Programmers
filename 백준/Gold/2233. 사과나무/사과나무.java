/*
스택을 사용해서?
썩은 곳이 둘 다 빠져나온 그 시점에 그 위치를...!?

0이면 스택에 넣고
1이면 스택에서 뺀다

썩은 부분이면 썩은 부분이라고 만들고 빠질때 썩었는지 확인
2개일때 빠져나오

아니?

그냥 링크를 시켜주자
그리고 그 사이에 2개 다 있으면 시작점이 큰 것으로 선택
 */

import java.io.*;
import java.util.*;

public class Main {

    static int IN = 0;
    static int OUT = 1;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[] searchs = Arrays.stream(bf.readLine().split("")).mapToInt(Integer::parseInt).toArray();

        int[] shit = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.print(solution(N, searchs, shit));
    }

    public static String solution(int N, int[] searchs, int[] shit) {
        ArrayDeque<Integer> stk = new ArrayDeque<>();
        List<Node> nodes = new ArrayList<>();

        for (int i = 0; i < (N << 1); i++) {
            if (searchs[i] == IN) {
                stk.add(i + 1);
            } else {
                nodes.add(new Node(stk.removeLast(), i + 1));
            }
        }

        Node result = new Node(-1, 100);

        for (Node node : nodes) {
            if (node.in <= shit[0] && shit[0] <= node.out && node.in <= shit[1] && shit[1] <= node.out) {
                if (result.in < node.in) result = node;
            }
        }

        return result.in + " " + result.out;
    }

    static class Node {
        int in;
        int out;

        public Node(int in, int out) {
            this.in = in;
            this.out = out;
        }
    }
}