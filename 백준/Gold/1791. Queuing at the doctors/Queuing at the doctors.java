import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/*
그니까 각 사람은 각 의사들을 만남
근데 의사는 한번에 한 사람만 가능
모든 진료는 1초가 걸림
일단 각 사람들을 만들고
큐를 돌리는데 현재 시간, 다른 사람들 어떻게 움직이는지 적어두자

접수 -> 진료
각 진료실마다 대기줄
대기줄에 쫙 세움
각 사람들의 시간을 나타내자
이거 흠..
일단 가장 작은 시간을 찾아야함
진료실에 박고 그다음에 하자
중복은 어차피 안됨
일단 가장 빠른애 넣고
시간이 그 다음 빠른애랑 같다면 그걸 처리하자
오는 사람들이 더 빠름
따라서 먼저 넣어주고 진료봐야함
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int C = Integer.parseInt(bf.readLine());

        StringJoiner result = new StringJoiner("\n");

        for (int c = 0; c < C; c++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());

            int N = Integer.parseInt(input.nextToken());
            int M = Integer.parseInt(input.nextToken());

            ArrayList<Person> people = new ArrayList<>();

            for (int n = 0; n < N; n++) {
                input = new StringTokenizer(bf.readLine());
                people.add(new Person(n, Integer.parseInt(input.nextToken())));

                int a = Integer.parseInt(input.nextToken());

                for (int i = 0; i < a; i++) {
                    people.get(n).visiteList.add(Integer.parseInt(input.nextToken()));
                }
            }

            result.add(String.valueOf(solution(N, M, people)));
        }

        System.out.println(result);
    }

    static int solution(int N, int M, List<Person> people) {
        people.sort(Comparator.comparingInt(Person::getT));
        int t = people.get(0).t;

        PriorityQueue<Person>[] doctors = new PriorityQueue[M+1];

        for (int i = 0; i <= M; i++) {
            doctors[i] = new PriorityQueue<>(Comparator.comparingInt(Person::getT)
                    .thenComparingInt(Person::getN));
        }

        int pIdx = 0;
        int remain = 0;

        while (pIdx < N && people.get(pIdx).t == t) {
            doctors[people.get(pIdx).visiteList.remove()].add(people.get(pIdx));
            pIdx++;
            remain++;
        }

        while (pIdx < N || remain > 0) {
            if (remain == 0) {
                t = people.get(pIdx).t;
            } else {
                t++;
            }
            // 다음 애들 미리 대기줄 넣기
            while (pIdx < N && people.get(pIdx).t == t) {
                doctors[people.get(pIdx).visiteList.remove()].add(people.get(pIdx));
                pIdx++;
                remain++;
            }

            // 진료
            for (PriorityQueue<Person> doctor : doctors) {
                if (doctor.isEmpty()) continue;
                if (doctor.peek().t >= t) continue;
                Person p = doctor.remove();
                if (p.visiteList.isEmpty()) {
                    remain--;
                } else {
                    p.t = t;
                    doctors[p.visiteList.remove()].add(p);
                }
            }
        }

        return t;
    }

    static class Person {
        int n;
        int t;
        ArrayDeque<Integer> visiteList;

        public Person(int n, int t) {
            this.n = n;
            this.t = t;
            visiteList = new ArrayDeque<>();
        }

        public int getT() {
            return t;
        }

        public int getN() {
            return n;
        }
    }
}