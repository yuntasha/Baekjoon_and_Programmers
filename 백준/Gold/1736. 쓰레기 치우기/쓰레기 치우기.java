import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
이거 그거 뭐냐
위에서 쭉 끝까지 간 다음에 밑에꺼
1   3 4 5
1 2 3 4 5
1       5
1   3 4
        5
밀어 5 끝
아래로 4 아래로 끝
1 아래로 3 끝
그럼 어떻게 푸냐...
최대 N번이면 처리가 가능
하나의 배열에 특정 이상의 인덱스는 다 처리됐다고 저장
n번 반복하는 것 시작
그 다음에 각 배열에서 해당 크기보다 작은 쓰레기 위치 탐색
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        List<Integer>[] p = new List[N];

        for (int i = 0; i < N; i++) {
            p[i] = new ArrayList<>();
            input = new StringTokenizer(bf.readLine());

            for (int j = 0; j < M; j++) {
                int now = Integer.parseInt(input.nextToken());

                if (now == 1) {
                    p[i].add(j);
                }
            }
        }

        System.out.println(solution(N, M, p));
    }

    static int solution(int N, int M, List<Integer>[] p) {
        int[] visited = new int[N];

        Arrays.fill(visited, M);

        int count = 0;

        for (int start = 0; start < N; start++) {
            int now = 0;
            int isAdd = 0; // 추가된 적 있으면 1로 설정할 것임
            for (int n : p[start]) { // 가능한 마지막 점 찾기
                if (n < visited[start]) {
                    now = n;
                    isAdd = 1;
                } else {
                    break;
                }
            }

            visited[start] = 0;

            for (int i = start + 1; i < N; i++) {
                int s = now; // s 부터 탐색임 나중에 visited에 넣어줄 것

                for (int n : p[i]) {
                    if (n < visited[i] && now <= n) {
                        now = n;
                        isAdd = 1;
                    }
                }

                visited[i] = s;
            }

            count += isAdd;
        }

        return count;
    }
}