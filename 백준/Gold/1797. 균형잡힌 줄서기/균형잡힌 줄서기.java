import java.io.*;
import java.util.*;

/*
앞에서부터 차이를 구해
만약 최초의 차이라면 그 값을 넣어줘
그 반대의 값과 비교해서 빼줘

 */

public class Main {

    static final int MAN = 0;
    static final int WOMAN = 1;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bf.readLine());

        ArrayList<Person> people = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());

            int a = Integer.parseInt(input.nextToken());
            int b = Integer.parseInt(input.nextToken());

            people.add(new Person(a, b));
        }

        System.out.println(solution(N, people));
    }

    static int solution(int N, ArrayList<Person> people){
        people.sort(Comparator.comparingInt(Person::getX));

        int result = 0;
        HashMap<Integer, Integer> start = new HashMap<>();
        int now = 0;
        start.put(0, people.get(0).x);

        for (int i = 0; i < N; i++) {
            now += people.get(i).gender;

            if (!start.containsKey(now)) {
                if (i + 1 < N) {
                    start.put(now, people.get(i + 1).x);
                }
            } else {
                result = Math.max(result, people.get(i).x - start.get(now));
            }
        }

        return result;
    }

    static class Person {
        int gender;
        int x;

        public Person(int gender, int x) {
            this.gender = gender == MAN ? -1 : 1;
            this.x = x;
        }

        public int getX() {
            return x;
        }
    }
}