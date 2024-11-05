import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bf.readLine());

        var arr = new ArrayList<Node>();

        for (int i = 0; i < N; i++) {
            arr.add(new Node(i, Integer.parseInt(bf.readLine())));
        }

        System.out.println(solution(N, arr));
    }

    static int solution(int N, List<Node> arr) {
        arr.sort(Comparator.comparingInt(Node::getValue));

        var result = 0;

        for (int i=0; i<N; i++) {
            result = Math.max(result, arr.get(i).idx-i);
        }

        return result+1;
    }

    static class Node {
        int idx;
        int value;

        Node(int idx, int value) {
            this.idx = idx;
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}