import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/*
뒤에서부터 탐색
그래서 스택으로 만들고 같은 애들 계속 없애면서 전진
탐색 방법
새로 나오는거 계속 탐색
1 2 1
1
이거 0 또는 1이 연속해서 몇 개던지 줄이는게 가능
앞에 나온 것들은 전부 1로 만들어지면 넘겨버리고
잠깐만 이거 애드혹이 이유가 있다
1이면 넘어가
그럼 1인 부분 그냥 체크해서 갯수 세어줌
그리고 2 이상인 부분 만나면 거기가 사실상 끝임
앞 뒤 다르게 끝나기
1 끝난 애랑 같게 끝나면 그 위치까지
1 끝난 애랑 다르게 끝나면 그 전 위치까지

첨부터 시작이면 끝이랑 같으면 1
다르면 2
2 3 4 5
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        char[] input = bf.readLine().toCharArray();

        System.out.println(solution(input));
    }

    static int solution(char[] input) {
        int n = 0;

        boolean diff = false;

        for (int i = 0; i < input.length - 1; i++) {
            if (input[i] == input[i + 1]) {
                if (diff) {
                    break;
                }
            } else {
                n++;
                diff = true;
            }
        }

        if (!diff) return 1;

        return n + 1;
    }
}

/*
0101011110011010111000000101010101
 */