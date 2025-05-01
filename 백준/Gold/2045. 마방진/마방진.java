/*
가로 세로 이렇게 합을 싹 구해
하나를 붙잡고 그거 기준으로 양 옆은 같은 숫자가 나와야 한다
만약 0이 2개라면 문제가 있겠지만 이거 존재하네
ㅁ ㅁ -
ㅁ - ㅁ
- ㅁ ㅁ
이러면 예외 생김
근데 이 경우 숫자 뭐가 들어가도 되지않나?
대각선도 같아야한다

a + b + c = 한 줄
9개 다 더하면 한줄이 0이 됐으니까 2줄의 크기 이렇게 가자

저게 아니라면 엥간하면 한줄 크기 구할 수 있음
ㅁㅁ-
-ㅁㅁ
ㅁ-ㅁ
이렇게 안됨

그럼 저거 위의 경우만 지워내고 가장 큰거 기준으로 위치 잡고 털자
만드는 방법은 가로 세로 둘 다 0이 또 있으면 일단 거르고 하나 있으면 그거로 계속 채우자

 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int[][] arr = new int[3][];

        arr[0] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        arr[1] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        arr[2] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(Arrays.stream(solution(arr)).map(a -> Arrays.stream(a).mapToObj(String::valueOf).collect(Collectors.joining(" "))).collect(Collectors.joining("\n")));
    }

    static int[][] solution(int[][] arr) {
        if (arr[0][0] + arr[1][1] + arr[2][2] == 0 || arr[2][0] + arr[1][1] + arr[0][2] == 0) {
            int result = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    result += arr[i][j];
                }
            }

            result = result >> 1;

            for (int i = 0; i < 3; i++) {
                int now = result;
                for (int j = 0; j < 3; j++) {
                    now -= arr[i][j];
                }

                for (int j = 0; j < 3; j++) {
                    if (arr[i][j] == 0) {
                        arr[i][j] = now;
                    }
                }
            }

            return arr;
        }

        int result = 0;

        for (int i = 0; i < 3; i++) {
            int now1 = 0;
            int now2 = 0;
            for (int j = 0; j < 3; j++) {
                now1 += arr[i][j];
                now2 += arr[j][i];
            }

            result = Math.max(result, Math.max(now1, now2));
        }

        result = Math.max(result, Math.max(arr[0][0] + arr[1][1] + arr[2][2], arr[2][0] + arr[1][1] + arr[0][2]));

        // 가로
        Loop : for (int i = 0; i < 3; i++) {
            boolean justOne = false;
            int now = result;
            for (int j = 0; j < 3; j++) {
                now -= arr[i][j];
                if (arr[i][j] == 0) {
                    if (justOne) continue Loop;
                    justOne = true;
                }
            }

            if (!justOne) continue;

            for (int j = 0; j < 3; j++) {
                if (arr[i][j] == 0) arr[i][j] = now;
            }
        }

        // 세로
        Loop : for (int i = 0; i < 3; i++) {
            boolean justOne = false;
            int now = result;
            for (int j = 0; j < 3; j++) {
                now -= arr[j][i];
                if (arr[j][i] == 0) {
                    if (justOne) continue Loop;
                    justOne = true;
                }
            }

            if (!justOne) continue;

            for (int j = 0; j < 3; j++) {
                if (arr[j][i] == 0) arr[j][i] = now;
            }
        }

//        boolean justOne = false;
//        for (int i = 0; i < 3; i++) {
//            if (arr[i][i] == 0) {
//                if (justOne) {
//                    justOne = false;
//                    break;
//                }
//                justOne = true;
//            }
//        }

        return arr;
    }
}