/*
힘민지가 전부 커야한다

완탐
힘민지를 싹다 돌려
그리고 비교

힘 민 지 가능한거 그 커트라인까지만 일단 올리면 된다
100 * 100 * 100

그러면 100 * 100 * 100 * 100이되네?
100,000,000;
이건 딱 1억

힘민까지만 해버려
저거 힘민 가능한 애들만 남기고 지능순으로 정렬
N번째 지능인 애로 구함
그럼 100 * 100 * (100 + 100 * log100)
오 짧아졌다

이거 미리 지능순으로 정렬하자
그러면 K개까지 하면 될 듯
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int K = Integer.parseInt(input.nextToken());

        List<User> users = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            input = new StringTokenizer(bf.readLine());
            users.add(new User(Integer.parseInt(input.nextToken()), Integer.parseInt(input.nextToken()), Integer.parseInt(input.nextToken())));
        }

        System.out.println(solution(N, K, users));
    }

    public static int solution(int N, int K, List<User> users) {
        users.sort(Comparator.comparingInt(User::getI));

        int result = 3_000_001;

        for (User us : users) {
            for (User ud : users) {
                int count = 0;
                for (User u : users) {
                    if (us.s >= u.s && ud.d >= u.d) count++;
                    if (count == K) {
                        result = Math.min(result, u.i + us.s + ud.d);
                        break;
                    }
                }
            }
        }

        return result;
    }

    public static class User {
        int s;
        int d;
        int i;

        User(int s, int d, int i) {
            this.s = s;
            this.d = d;
            this.i = i;
        }

        int getI() {
            return i;
        }
    }
}