import java.io.*;
import java.util.*;

/*
이거 그냥 옮기는거라
애매함
생각을 해보자
4까지 할 수 있고
왼쪽 사이드가 5라고 한다면 위로 5를 한번에 보내는 것과 밑에를 마저 처리하는것
3까지 제한 근데 밑에는 5
그냥 위에서부터 탐색하면서 제한이 넘어간다면 위로 옮겼다고 생각을 해보자
그러면 어떻게 되지않을까

트리의 루트는 0

잠깐 위에 저거 안됨

제일 아래까지 내려간 다음 밑에서부터 깊이가 제한에 걸리는 경우 루트가 아니라면 위로 올려버리자
2 4 4 6 = 2
2 6 = 3
오히려 나눠서 한 경우에 잘 처리가 되는 모습
2 5 = 2
따라서 그냥 위로 옮겨버리는게 더 나을듯

위로 옮겨버리고
아 잠깐만 뭐지

넘으면 그냥 다 땡겨버려?
한칸씩 위로 옮기면 값은 어떻게 나오지?
2 6 = 2
2 5 = 2
2 4 = 1
차이 / 2 + 홀수면 1 <- 이게 최소로 움직이는 방법

근데 나눠지면 하나씩 옮기는 것보다 그냥 전체가 옮겨지는게 나음
즉 부모가 먼저 움직이는게 낫다
부모가 우선적으로 움직여져야함
따라서 위에서부터 탐색
일단 각 노드의 자식노드중 가장 아래에 위치하는 녀석을 위의 값으로 가져옴
그리고 깊이가 깊다면 그냥 2칸위로 계속 옮겨주자
그렇게 끝ㅌ까지 탐색해보자
 */

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        List<List<Integer>> tree = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());

            int parent = Integer.parseInt(input.nextToken());
            int child = Integer.parseInt(input.nextToken());

            tree.get(parent).add(child);
        }

        int cutLine = Integer.parseInt(bf.readLine());

        System.out.println(solution(N, tree, cutLine));
    }

    static int solution(int N, List<List<Integer>> tree, int cutLine) {
        int[] depth = new int[N];

        getDepth(tree, 0, depth);

        return work(N, tree, cutLine, 0, 0, 0, depth);
    }

    static int work(int N, List<List<Integer>> tree, int cutLine, int now, int cut, int nowDepth, int[] depth) {
        int cost = 0;

        if (nowDepth > 1 && nowDepth + depth[now] - 1 - cut > cutLine) {
            cut++;
            cost++;
        }

        for (int i : tree.get(now)) {
            cost += work(N, tree, cutLine, i, cut, nowDepth + 1, depth);
        }
        return cost;
    }

    static int getDepth(List<List<Integer>> tree, int now, int[] depth) {
        if (tree.get(now).isEmpty()) {
            return depth[now] = 1;
        }

        for (int next : tree.get(now)) {
            depth[now] = Math.max(depth[now], getDepth(tree, next, depth) + 1);
        }

        return depth[now];
    }
}