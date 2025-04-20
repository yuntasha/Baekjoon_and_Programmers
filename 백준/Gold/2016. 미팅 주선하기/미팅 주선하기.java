/*
5 대 5 미팅
내가 1번 남자 2 - 5번 여자 6 - 10번
가장 좋아하는 사람부터 앞에서 차례대로 적기
6번 여자부터 맘에 드는 사람에게 프로포즈

자신에게 퇴짜를 놓은 적 있다면 다음 순위의 남자에게 프로포즈
남자는 짝이 없으면 무조건 받아들이는데 이미 있다면 2명중 더 맘에드는 사람과 짝짓기
퇴짜 맞은 여학생만 다음 라운드에 다시 고백
전부 짝을 찾을 때 까지 반복

1. 여자가 먼저 선호도에 따라 고백함
2. 남자는 선호도가 높은 애를 받고 나머지는 거절함
3. 그것을 반복함

나는 6 -> 10 순서로 호감도를 가질 경우
순서를 바꿔 기존 사람보다 호감도가 높은 사람을 받을 수 있도록 조정하는 경우의 수가 있는지

그냥 모든 경우의 수를 넣어보자
그럼 1회당 120회
총 20회
한번 시뮬레이션을 돌리는 허용 시간 복잡도
40_000
즉 4만

일단 그럼 여자애들 기준으로 똿똿똿넣고
이미 방문한 애들이면 거르고!
아자아자 파이팅!
 */

import java.io.*;
import java.util.*;

public class Main {

    static String YES = "YES";
    static String NO = "NO";

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(bf.readLine());

        for (int t = 0; t < T; t++) {
            int[][] points = new int[11][5];

            for (int i = 2; i <= 10; i++) {
                points[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            System.out.println(solution(points));
        }
    }

    static String solution(int[][] points) {
        int visited = 0;
        for (int i = 0; i < 5; i++) {
            points[1][i] = i + 6;
        }
        int ori = match(points);

        for (int i1 = 1; i1 < 6; i1++) {
            visited |= 1 << i1;
            for (int i2 = 1; i2 < 6; i2++) {
                if ((visited & (1 << i2)) > 0) continue;
                visited |= 1 << i2;
                for (int i3 = 1; i3 < 6; i3++) {
                    if ((visited & (1 << i3)) > 0) continue;
                    visited |= 1 << i3;
                    for (int i4 = 1; i4 < 6; i4++) {
                        if ((visited & (1 << i4)) > 0) continue;
                        visited |= 1 << i4;
                        for (int i5 = 1; i5 < 6; i5++) {
                            if ((visited & (1 << i5)) > 0) continue;
                            visited |= 1 << i5;
                            points[1] = new int[]{i1 + 5, i2 + 5, i3 + 5, i4 + 5, i5 + 5};
                            int now = match(points);
                            if (now < ori) return YES;
                            visited ^= 1 << i5;
                        }
                        visited ^= 1 << i4;
                    }
                    visited ^= 1 << i3;
                }
                visited ^= 1 << i2;
            }
            visited ^= 1 << i1;
        }

        return NO;
    }

    static int match(int[][] points) {
        int[] match = new int[11];

//        System.out.println("Arrays.toString(points[1]) = " + Arrays.toString(points[1]));

        int[] idx = new int[5];

        while (!isAllMatch(match)) {
            for (int w = 6; w <= 10; w++) {

                if (match[w] > 0) continue;

                int m = points[w][idx[w - 6]++];
                if (match[m] == 0) {
                    match[m] = w;
                    match[w] = m;
                } else {
                    int rw = findFirst(w, match[m], m, points);
                    if (rw == match[m]) continue;
                    match[match[m]] = 0;
                    match[m] = w;
                    match[w] = m;
                }
            }
//            System.out.println(Arrays.toString(match));
        }

        return match[1];
    }

    static int findFirst(int w1, int w2, int m, int[][] points) {
        for (int i = 0; i < 5; i++) {
            if (points[m][i] == w1) return w1;
            if (points[m][i] == w2) return w2;
        }
        return 0;
    }

    static boolean isAllMatch(int[] match) {
        for (int i = 1; i < 11; i++) {
            if (match[i] == 0) return false;
        }

        return true;
    }
}