import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
제일 뒤에가 빈 경우 그냥 나올 수 있는 거 싹다 해버리면 될듯

제일 뒤에가 있는 경우 DP[윤년(0,1)][현재 위치(년도, 달, 일, A10개, 13이 되겠지?)][%19 했을때 수] = 개수
년도부터 정해서 윤년 여부를 따진 다음에 C구하는 공식을 통해 나오는 수를 넣음 -> [윤년][0][%19]

달 1 - 12, 해당 날짜 전체 탐색

10000 + 12 * 30 + 10 * 10 끝!

반례 290220001234567890X
*/

public class Main {

    static int[][] DAYS = {
            {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31},
            {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}
    };
    static int MONTHS = 12;
    static int YEARS = 9999;
    static int[] AV = {2, 10, 9, 8, 7, 6, 5, 4, 3, 2};

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(solution(bf.readLine()));
    }

    static long solution(String S) {
        long[][][] DP = new long[2][12][19];

        String YYYY = S.substring(4, 8);
//        System.out.println("YYYY = " + YYYY);
        String MM = S.substring(2, 4);
//        System.out.println("MM = " + MM);
        String DD = S.substring(0, 2);
//        System.out.println("DD = " + DD);
        String AS = S.substring(8, 18);
//        System.out.println("AS = " + AS);
        char C = S.charAt(18);

        // 년도 처리
        for (int Y1 = 0; Y1 < 10; Y1++) {

            if (!isOK(YYYY.charAt(0), Y1)) continue;

            for (int Y2 = 0; Y2 < 10; Y2++) {

                if (!isOK(YYYY.charAt(1), Y2)) continue;

                for (int Y3 = 0; Y3 < 10; Y3++) {

                    if (!isOK(YYYY.charAt(2), Y3)) continue;

                    for (int Y4 = 0; Y4 < 10; Y4++) {

                        if (!isOK(YYYY.charAt(3), Y4)) continue;
                        if (Y1 + Y2 + Y3 + Y4 == 0) continue;
                        DP[isYun(Y1 * 1000 + Y2 * 100 + Y3 * 10 + Y4)][0][(Y1 * 6 + Y2 * 5 + Y3 * 4 + Y4 * 3) % 19]++;
                    }
                }
            }
        }


        // 달 일 처리
        for (int M = 1; M <= 12; M++) {
            // 십의자리 처리
            if (!isOK(MM.charAt(0), M / 10)) continue;
            if (!isOK(MM.charAt(1), M % 10)) continue;

            for (int Y = 0; Y < 2; Y++) { // 윤년 여부
                for (int D = 1; D <= DAYS[Y][M]; D++) {
                    if (!isOK(DD.charAt(0), D / 10)) continue;
                    if (!isOK(DD.charAt(1), D % 10)) continue;

                    for (int s = 0; s < 19; s++) {
                        DP[Y][1][(s + ((M / 10) * 8) + ((M % 10) * 7) + ((D / 10) * 10) + ((D % 10) * 9)) % 19] += DP[Y][0][s];
                    }
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            char c = AS.charAt(i);

            for (int A = 0; A < 10; A++) {
                if (!isOK(c, A)) continue;

                for (int Y = 0; Y < 2; Y++) {
                    for (int s = 0; s < 19; s++) {
                        DP[Y][i + 2][(s + (A * AV[i])) % 19] += DP[Y][i + 1][s];
                    }
                }
            }
        }

        long result = 0;

        for (int s = 0; s < 19; s++) {
            if (s <= 9) {
                if (isOK(C, s)) {
                    result += DP[0][11][s] + DP[1][11][s];
                }
            } else {
                if (isOK(C, 19 - s)) {
                    result += DP[0][11][s] + DP[1][11][s];
                }
            }
        }

        return result;
    }

    static boolean isOK(char c, int i) {
        if (c == 'X') return true;
        return (c & 15) == i;
    }

    static int isYun(int N) {
        if ((N % 4) == 0) { // 4의 배수
            if (N % 100 == 0) {
                if ((N % 400) == 0) { // 16의 배수 즉 400 배수
                    return 1;
                }
                return 0;
            }
            return 1;
        }

        return 0;
    }
}