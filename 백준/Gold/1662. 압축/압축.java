import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
재귀로 풀어보자 (스택은 이미 이런거 해봤음)
숫자나오면 그냥 더해주기
(나오면 재귀시키기
)나오면 결과 반환하기
*/

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String S = bf.readLine();

        System.out.println(solution(S));
    }

    static int solution(String S) {
        return find(S, 0).len;
    }

    static Node find(String S, int idx) {
        int now = 0;
        while (idx < S.length()) {
            if (S.charAt(idx) == '(') {
                Node node = find(S, idx + 1);
                now += (S.charAt(idx - 1) & 15) * node.len - 1;
                idx = node.idx + 1;
            } else if (S.charAt(idx) == ')') {
                break;
            } else {
                now++;
                idx++;
            }
        }

        return new Node(idx, now);
    }

    static class Node {
        int idx;
        int len;

        public Node(int idx, int len) {
            this.idx = idx;
            this.len = len;
        }
    }
}