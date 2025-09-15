/*
((()))()()
(을 +1
)을 -1로 설정
만약 누적합이 음수라면
)가 많은 거임
((()))))이런느김
그럼 )중에 하나를 (로 바꿔야함
123210-1-2 인데
제일 마지막을 제외한 모든 것들을 바꿔도될걸?
제일 첫번째가 음수면 거기만 바꿔야함
반례가 있을듯
(()(()
모두 처리해버리면
())(() 이렇게 되버림
121232
이렇게 됨
-1로 바꾸면 2가 줄어들기 때문에 뒤에 1이 없어야한다
반대로 1로 바꾸면 2가 증가하기 때문에
(   )   )   (   )   )
1   0   -1  0   -1  -2

아 그냥 음수가 안나와야한다

(   (   )   (   (   )
1   2   1   2   3   2

 */

import java.io.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(solution(bf.readLine().toCharArray()));
    }

    public static int solution(char[] S) {
        int N = S.length;

        int[] prefixSum = new int[N];

        prefixSum[0] = convert(S[0]);

        for (int i = 1; i < N; i++) {
            prefixSum[i] = prefixSum[i - 1] + convert(S[i]);
        }

        if (prefixSum[N - 1] < 0) {
            int count = 0;

            for (int i = 0; i < N; i++) {
                if (S[i] == ')') count++;
                if (prefixSum[i] < 0) break;
            }
            return count;
        } else {
            int count = 0;

            for (int i = N - 1; i >= 0; i--) {
                if (prefixSum[i] < 2) break;
                if (S[i] == '(') count++;
            }
            return count;
        }
    }

    public static int convert(char c) {
        return c == '(' ? 1 : -1;
    }
}