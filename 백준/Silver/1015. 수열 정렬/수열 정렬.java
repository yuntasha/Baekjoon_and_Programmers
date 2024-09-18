import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bf.readLine());

        var arr = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        var nums = new ArrayList<Num>();

        for (int i=0; i<N; i++) {
            nums.add(new Num(i, arr[i]));
        }

        System.out.println(Arrays.stream(solution(N, nums)).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }

    static int[] solution(int N, List<Num> nums) {
        nums.sort(Comparator.comparingInt(Num::getValue)
                .thenComparingInt(Num::getKey));
        var result = new int[N];
        for (int i=0; i<N; i++) {
            var now = nums.get(i);
            result[now.key] = i;
        }

        return result;
    }

    static class Num{
        int key;
        int value;

        Num(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public int getKey() {
            return key;
        }
    }
}
