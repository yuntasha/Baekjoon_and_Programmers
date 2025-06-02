import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
1_000_000_000_000
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(input.nextToken());
        int L = Integer.parseInt(input.nextToken());

        Ant[] ants = new Ant[N];

        for (int i = 0; i < N; i++) {
            ants[i] = new Ant(i + 1, Integer.parseInt(bf.readLine()));
        }

        System.out.println(solution(L, N, ants));
    }

    public static String solution(int L, int N, Ant[] ants) {
        int idx = -1;
        int max = -1;
        int LCount = 0;

        Arrays.sort(ants, Comparator.comparingInt(Ant::getL));

        for (int i = 0; i < N; i++) {
            int ant = ants[i].l;
            if (ant < 0) {
                LCount++;
                if (max > -ant) continue;
                max = -ant;
                idx = i;
            } else {
                if (max > L - ant) continue;
                max = L - ant;
                idx = i;
            }
        }

        if (ants[idx].l < 0) {
            idx = ants[LCount - 1].n;
        } else {
            idx = ants[LCount].n;
        }

        return idx + " " + max;
    }

    static class Ant {
        int n;
        int l;

        public Ant(int n, int l) {
            this.n = n;
            this.l = l;
        }

        public int getL() {
            return Math.abs(l);
        }
    }
}