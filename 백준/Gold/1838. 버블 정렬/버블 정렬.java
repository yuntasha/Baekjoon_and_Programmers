import java.io.*;
import java.util.*;

public class Main {

    static final String FAIL = "-1";

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        List<Num> arr = new ArrayList<>();

        StringTokenizer input = new StringTokenizer(bf.readLine());

        for (int i = 0; i < N; i++) {
            arr.add(new Num(i, Integer.parseInt(input.nextToken())));
        }

        arr.sort(Comparator.comparingInt(Num::getNum));

        int result = 0;

        for (int i = 0; i < N; i++) {
            result = Math.max(result, arr.get(i).idx - i);
        }

        System.out.println(result);
    }

    static class Num {
        int idx;
        int num;

        Num(int idx, int num) {
            this.idx = idx;
            this.num = num;
        }

        int getNum() {
            return num;
        }
    }
}