import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var NMK = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        var N = NMK[0];
        var M = NMK[1];
        var K = NMK[2];

        var cards = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        var chulsu = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solution(N, M, K, cards, chulsu).stream().map(String::valueOf).collect(Collectors.joining("\n")));
    }

    static List<Integer> solution(int N, int M, int K, int[] cards, int[] chulsu){
        Arrays.sort(cards);
        var next = IntStream.range(0, M).toArray();
        var result = new ArrayList<Integer>();

        for (int i: chulsu) {
            var n = findN(i, cards);
            var now = findNext(n, next);
            result.add(cards[now]);
            next[now] = now+1;
        }

        return result;
    }

    static int findNext(int n, int[] next) {
        if (next[n] == n) return n;
        var result = findNext(next[n], next);
        next[n] = result;
        return result;
    }

    static int findN(int n, int[] card){
        int s = 0;
        int e = card.length-1;

        if (card[s]>n) return s;
        if (card[e]<=n) return card.length;

        while (s+1<e) {
            var mid = (s+e)/2;
            if (card[mid]<=n) s = mid;
            else e = mid;
        }
        return e;
    }
}