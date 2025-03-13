/*
해당하지 않는 알파벳 제거

앞에서부터 전부 노드로 만들어서 처리해야하나?

1 4 5 9 10 40 50 100 400 500 1000
이거 노드에 각각 넣어서 처리한다면?

0   1   2   3   4   5   6
I	V	X	L	C	D	M
1   5   10  50  100 500 1000
3   1   3   1   3   1   3

만약 V앞에 I가 나오면 IV로 4가 됨
즉 홀수라면 -1했을때 1이라면 넣을 수 있음

0   1   2
IV  XL  CD
4   40  400
9   90  900

0이면 그냥 0에 추가 혹은 다른 것에서 0
1이면 앞에 0나온거 하나빼고 여기 넣기

9 다음엔 5가 나올 수 있잖아

그냥 싹다하자


0   1   2   3   4   5   6   7   8   9   10  11  12
I   IV	V	IX  X   XL	L   XC	C   CD	D   CM	M
1   4   5   9   10  40  50  90  100 400 500 900 1000
3   1   1   1   3   1   1   1   3   1   1   1   3

아 잠만 저 뒤에 작은거 암만 붙여봤자 뒤에 큰거 하나라도 있으면 그거 집으면 되는구나...
그럼 M을 끝까지 찾고
D를 1개 찾고 또 찾기면 CD 존재하는지 찾기

잠만 40 50이 안되잖아

그럼 3번하는거 찾고 4번째면 앞에 그거 있는지 찾고 9따리
9 나오면 그 밑에 안찾음

 */

import java.io.*;
import java.util.*;

public class Main {

    static char[] A =   {'i',   'v',    'x',    'l',    'c',    'd',    'm'};
    static int[] N =    {1,     5,      10,     50,     100,    500,    1000};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < N; i++) {
            result.append(solution(bf.readLine())).append("\n");
        }

        System.out.println(result);
    }

    static int solution(String s) {
        char[] cs = s.toCharArray();

        int idx = 0;
        int result = 0;

        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == 'm') {
                idx = i + 1;
                result += 1000;
            }
        }

        for (int i = idx; i < cs.length; i++) {
            if (cs[i] == 'd') {
                result += 500;
                idx = i + 1;
                break;
            }
        }

        int count = 0;

        for (int i = idx; i < cs.length; i++) {
            if (cs[i] == 'c') {
                count++;
                result += 100;
                idx = i + 1;

                if (count == 3) break;
            }
        }

        boolean isXC = false;
        boolean isX = false;

        for (int i = idx; i < cs.length; i++) {
            if (cs[i] == 'x') isX = true;
            if (isX && cs[i] == 'c') {
                result += 90;
                idx = i + 1;
                isXC = true;
                break;
            }
        }

        // XC가 있으면 10 ~ 50 조회 안해도됨 40은 없는 숫자
        if (!isXC) {
            for (int i = idx; i < cs.length; i++) {
                if (cs[i] == 'l') {
                    result += 50;
                    idx = i + 1;
                    break;
                }
            }

            count = 0;

            for (int i = idx; i < cs.length; i++) {
                if (cs[i] == 'x') {
                    result += 10;
                    idx = i + 1;
                    count++;

                    if (count == 3) break;
                }
            }
        }

        // xi 찾기
        boolean isIX = false;
        boolean isI = false;

        for (int i = idx; i < cs.length; i++) {
            if (cs[i] == 'i') {
                isI = true;
            }
            if (cs[i] == 'x' && isI) {
                isIX = true;
                result += 9;
                idx = i + 1;
                break;
            }
        }

        if (!isIX) {
            for (int i = idx; i < cs.length; i++) {
                if (cs[i] == 'v') {
                    result += 5;
                    idx = i + 1;
                    break;
                }
            }

            count = 0;

            for (int i = idx; i < cs.length; i++) {
                if (cs[i] == 'i') {
                    result += 1;
                    idx = i + 1;
                    count++;

                    if (count == 3) break;
                }
            }
        }

        return result;
    }

    public static int getIdx(char c) {
        for (int idx = 0; idx < 7; idx++) {
            if (A[idx] == c) return idx;
        }

        return -1;
    }
}