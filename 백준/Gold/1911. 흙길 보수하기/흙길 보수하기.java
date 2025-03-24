/*
웅덩이가 N개 있고 L의 길이를 가지는 널빤지
그럼 그냥 앞에서부터 전부 깔아버리면 될 듯
1 6이면 1, 2, 3, 4, 5에 널빤지가 있다는 뜻
1 7이면 6의 길이가 필요하다
그리디로 만들자
앞에서 그냥 널빤지 필요하면 그걸로 처리

널빤지 처음엔 -1
널빤지의 끝 초깃값 -1
1 6 3이라면 6 - 1 = 5길이 5/3 + (5%3==0 ? 0 : 1 = 2개 끝은 6
따라서 개수는 그냥 웅덩이 길이 / 널빤지 길이 + 나머지 있으면 1
1 + 6 - 1 = 6 시작점 + 널빤지 길이 * 널빤지 개수 - 1= 끝점
시작점 = Math.max(웅덩이 시작점, 널빤지 끝점 + 1)

출력 최대 10억
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        int N = read();
        int L = read();

        List<Water> waters = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            waters.add(new Water(read(), read()));
        }

        System.out.println(solution(N, L, waters));
    }

    public static int solution(int N, int L, List<Water> waters) {
        int last = -1;
        int result = 0;

        waters.sort(Comparator.comparingInt(Water::getStart));

        for (Water water : waters) {
            if (water.end <= last + 1) continue;

            int s = Math.max(last + 1, water.start);
            int needD = water.end - s;
            int count = needD / L + (needD % L == 0 ? 0 : 1);
            result += count;
            last = s + L * count - 1;
        }

        return result;
    }

    static int read() throws IOException {
        int n = 0;
        int c;
        while ((c = System.in.read()) >= '0') {
            n = (n << 3) + (n << 1) + (c & 15);
        }

        return n;
    }

    static class Water {
        int start;
        int end;

        Water(int start, int end) {
            this.start = start;
            this.end = end;
        }

        int getStart() {
            return start;
        }
    }
}