/*
그리디하게 풀어나가는게 좋을 것 같다
C보다 크거나 같은 단위가 있으면 그거 한개씩 주면됨
작은 단위가 있다면 큰거부터 넣을 수 있는 만큼 계속 넣어주자
큰 것부터 넣어서 넣었을때 C보다 커지면 작은거 트라이
만들 수 있는 최소 개수 구해주고 줄여가자
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int C = Integer.parseInt(input.nextToken());

        List<Coin> coins = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            input = new StringTokenizer(bf.readLine());

            int v = Integer.parseInt(input.nextToken());
            int b = Integer.parseInt(input.nextToken());

            coins.add(new Coin(v, b));
        }

        System.out.println(solution(N, C, coins));
    }

    public static int solution(int N, int C, List<Coin> coins) {
        int result = 0;

        coins.sort(Comparator.comparingInt(Coin::getRValue));

        int idx = 0;

        while (idx < N && coins.get(idx).value >= C) {
            result += coins.get(idx).count;
            coins.get(idx).count = 0;
            idx++;
        }

        while (true) {
            int now = C;
            int[] counts = new int[N];

            for (int i = idx; i < N; i++) {
                counts[i] = Math.min(now / coins.get(i).value, coins.get(i).count);
                now -= counts[i] * coins.get(i).value;
            }

            for (int i = N - 1; i >= idx && now > 0; i--) {
                if (counts[i] < coins.get(i).count) {
                    counts[i]++;
                    now -= coins.get(i).value;
                    break;
                }
            }


            if (now > 0) break;

            int min = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                if (counts[i] == 0) continue;
                min = Math.min(min, coins.get(i).count / counts[i]);
            }

            for (int i = 0; i < N; i++) {
                coins.get(i).count -= counts[i] * min;
            }
            
            result += min;
        }

        return result;
    }

    static class Coin {
        int value;
        int count;

        public Coin(int value, int count) {
            this.value = value;
            this.count = count;
        }

        public int getRValue() {
            return -value;
        }
    }
}