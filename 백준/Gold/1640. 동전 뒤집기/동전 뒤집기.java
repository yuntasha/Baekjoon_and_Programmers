import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/*
세로로 홀수인 애들 전부 짝수로 만듦
그리고 가로로 비교하면서 홀수인 애들 전부 뒤집어
전부 xor시킴

전부 짝수로 만듦 -> 그럼 다른쪽은 짝수만큼 움직여야함
전부 홀수로 만듦 -> 그럼 다른쪽은 홀수만큼 움직여야함

짝수만큼 움직임 -> 짝수로 만들어야함
홀수만큼 움직임 -> 홀수로 만들어야함

가로 세로
홀  홀 가능 (홀수 개수 + 짝수 개수, 짝수 개수 + 홀수 개수)
홀  짝 불가
짝  홀 불가
짝  짝 가능 (홀수 개수 + 홀수 개수, 짝수 개수 + 짝수 개수)

가로가 홀이면
짝수로 바꿀때  -> 홀개수만큼 바뀜 -> 홀수니개니까 -> 홀수로 바꿔야함 -> 세로의 짝수의 개수 가 짝수
홀수로 바꿀때 -> 짝개수만큼 바뀜 -> 짝수개니까 -> 짝수로 바꿔야함 -> 세로의 홀수의 개수가 홀수
가로가 짝이면
짝수로 바꿀때 -> 홀 개수만큼 바뀜 -> 짝수개니까 -> 짝수로 바꿔야함 -> 세로의 홀수의 개수가 짝수
홀수로 바꿀때 -> 짝 개수만큼 바뀜 -> 홀수개니까 -> 홀수로 바꿔야함 -> 세로의 짝수의 개수가 홀수
*/

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][];

        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(bf.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(solution(N, M, map));
    }

    static int solution(int N, int M, int[][] map) {
        int[] cnt = count(N, M, map);

        if ((cnt[0]&1)==1) {
            if ((cnt[1]&1)==0) return -1;
            return Math.min(cnt[0] + M - cnt[1], N-cnt[0] + cnt[1]);
        } else {
            if ((cnt[1]&1)==1) return -1;
            return Math.min(cnt[0] + cnt[1], N + M - cnt[0] - cnt[1]);
        }
    }

    static int[] count(int N, int M, int[][] map) {
        int[] n = new int[N];
        int[] m = new int[M];


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                n[i] = n[i] ^ map[i][j];
                m[j] = m[j] ^ map[i][j];
            }
        }

        int[] result = new int[2];

        for (int i = 0; i < N; i++) {
            result[0] += n[i];
        }

        for (int i = 0; i < M; i++) {
            result[1] += m[i];
        }

        return result;
    }
}