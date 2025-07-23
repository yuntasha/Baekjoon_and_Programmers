/*
마을 위치기준으로 정렬
그래서 왼쪽에 몇명 오른쪽에 몇명인지 찾기
초기 거리 구해놓기
그래서 현재 위치기준 왼쪽(현재 포함), 현재 위치 기준 오른쪽
왼쪽 * 거리 - 오른쪽 * 거리
이렇게 쭉 하면서 최소값 구해서 저장해두기

그냥 절반가면 컷...
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        List<Town> towns = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());
            towns.add(new Town(Long.parseLong(input.nextToken()), Long.parseLong(input.nextToken())));
        }

        System.out.print(solution(N, towns));
    }

    public static long solution(int N, List<Town> towns) {
        towns.sort(Comparator.comparingLong(Town::getLoc));

        long sum = 0;

        for (int i = 0; i < N; i++) {
            sum += towns.get(i).people;
        }

        long now = 0;
        long half = (sum >> 1) + (sum & 1);

        for (int i = 0; i < N; i++) {
            now += towns.get(i).people;
            if (half <= now) return towns.get(i).loc;
        }

        return towns.get(0).loc;
    }

    public static class Town {
        long loc;
        long people;

        public Town(long loc, long people) {
            this.loc = loc;
            this.people = people;
        }

        public long getLoc() {
            return loc;
        }
    }
}

