import java.io.*;
import java.util.*;

import static java.lang.Math.*;

/*
앞에서부터 가능한거 PQ에 싹다 넣자
그러고 가장 좋은거부터 계속 가져오면 굿
 */

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        double speed = Double.parseDouble(bf.readLine());

        Person[] people = new Person[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());

            double x = Double.parseDouble(input.nextToken());
            double y = Double.parseDouble(input.nextToken());
            double d = Double.parseDouble(input.nextToken());
            double a = Double.parseDouble(input.nextToken());

            people[i] = new Person(x, y, d, a);
        }

        System.out.println(solution(N, speed, people));
    }

    static long solution(int N, double speed, Person[] people) {
        return round(dfs(N, speed, people, new boolean[N], 0, 0, 0, 0, 0, 1e9));
    }

    static double dfs(int N, double speed, Person[] people, boolean[] visited, double nx, double ny, int count, double now, double maxT, double resultT) {
        if (count == N) {
            return max(sqrt(nx * nx + ny * ny) / speed, maxT);
        }

        double result = resultT;

        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            double nowT = getTime(people[i], now, nx, ny, speed);
            double x = people[i].x + cos(people[i].angle) * people[i].speed * (nowT + now);
            double y = people[i].y + sin(people[i].angle) * people[i].speed * (nowT + now);
            double t = now + nowT + sqrt(x * x + y * y) / people[i].speed;
            if (t <= resultT) {
                result = Math.min(result, dfs(N, speed, people, visited, x, y, count + 1, now + nowT, max(maxT, t), resultT));
            }
            visited[i] = false;
        }

        return result;
    }

    static double getTime(Person p, double nowT, double x2, double y2, double s2) {
        double x1 = p.x + cos(p.angle) * p.speed * nowT;
        double y1 = p.y + sin(p.angle) * p.speed * nowT;
        double ax = x1 - x2;
        double ay = y1 - y2;
        double dx = p.speed * cos(p.angle);
        double dy = p.speed * sin(p.angle);

        double a = dx * dx + dy * dy - s2 * s2;
        double b = ax * dx + ay * dy;
        double c = pow(ax, 2) + pow(ay, 2);

        return max((-b - sqrt(pow(b, 2) - a * c)) / a, (-b + sqrt(pow(b, 2) - a * c)) / a);
    }

    static class Person {
        double x;
        double y;
        double speed;
        double angle;

        public Person(double x, double y, double speed, double angle) {
            this.x = x;
            this.y = y;
            this.speed = speed;
            this.angle = angle;
        }
    }
}