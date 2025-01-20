import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
각 시작 위치는 +1로 저장
각 끝 위치는 -1로 저장
모든 것들이 다 적용 후 최신화하면 된다
*/

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        List<Node> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            list.add(new Node(Integer.parseInt(st.nextToken()), 1));
            list.add(new Node(Integer.parseInt(st.nextToken()), -1));
        }

        System.out.println(solution(N, list));
    }

    static int solution(int N, List<Node> list) {
        list.sort(Comparator.comparingInt(Node::getPos));

        int now = 0;
        int result = 0;
        int count = 0;
        for (Node node : list) {
            if (now != node.pos) {
                now = node.pos;
                result = Math.max(result, count);
            }
            count += node.value;
        }

        return result;
    }

    static class Node {
        int pos;
        int value;

        public Node(int pos, int value) {
            this.pos = pos;
            this.value = value;
        }

        public int getPos() {
            return pos;
        }
    }
}