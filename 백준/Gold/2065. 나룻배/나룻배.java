/*
선박장 좌우로 구분
제일 처음에는 좌측에 위치
최대 M명 탑승 가능
양방향 둘 다 t초면 갈 수 있음

10만 이하로 도착
그리고 1만명까지 있을 수 있음
그리고 1만시간이 걸릴 수 있음
그럼 최대 숫자가
10만부터 시작해서
1만 * 1만 * 2정도?
100_000_000
2억인데? int 범위안임

1 10000 1
100000 right
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int M = Integer.parseInt(input.nextToken());
        int T = Integer.parseInt(input.nextToken());
        int N = Integer.parseInt(input.nextToken());

        ArrayDeque<Person> left = new ArrayDeque<>();
        ArrayDeque<Person> right = new ArrayDeque<>();
        List<Person> people = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            Person p = new Person(bf.readLine().split(" "));

            if (p.a == 0) {
                left.add(p);
            } else {
                right.add(p);
            }

            people.add(p);
        }

        System.out.println(solution(M, T, N, left, right, people));
    }

    static String solution(int M, int T, int N, ArrayDeque<Person> left, ArrayDeque<Person> right, List<Person> people) {
        int now = 0;
        int where = 0;

        while (!left.isEmpty() || !right.isEmpty()) {
            if (where == 0) {
                if (left.isEmpty()) {
                    now = Math.max(now, right.peek().t) + T;
                    where = 1;
                    continue;
                }
                if (left.peek().t > now) {
                    if (!right.isEmpty() && left.peek().t > right.peek().t) {
                        now = Math.max(now, right.peek().t) + T;
                        where = 1;
                        continue;
                    }
                    now = left.peek().t;
                }

                int count = 0;
                while (!left.isEmpty() && left.peek().t <= now && count < M) {
                    left.remove().dest = now + T;
                    count++;
                }
                where = 1;
            } else {
                if (right.isEmpty()) {
                    now = Math.max(now, left.peek().t) + T;
                    where = 0;
                    continue;
                }
                if (right.peek().t > now) {
                    if (!left.isEmpty() && left.peek().t < right.peek().t) {
                        now = Math.max(now, left.peek().t) + T;
                        where = 0;
                        continue;
                    }
                    now = right.peek().t;
                }

                int count = 0;
                while (!right.isEmpty() && right.peek().t <= now && count < M) {
                    right.remove().dest = now + T;
                    count++;
                }
                where = 0;
            }
            now += T;
        }

        return people.stream().map(p -> String.valueOf(p.dest)).collect(Collectors.joining("\n"));
    }

    static class Person {
        int t;
        int a;
        int dest;

        Person(String[] input) {
            this.t = Integer.parseInt(input[0]);
            this.a = input[1].equals("right") ? 1 : 0;
        }
    }
}