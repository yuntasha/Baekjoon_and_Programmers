import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bufferedReader.readLine());

        var inOrder = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        var postOrder = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(Arrays.stream(solution(N, inOrder, postOrder)).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }

    static int[] solution(int N, int[] inOrder, int[] postOrder){
        var preOrder = new int[N];
        findGroup(inOrder, postOrder, preOrder, 0, N-1, 0, N-1, 0);
        return preOrder;
    }

    static int findGroup(int[] inOrder, int[] postOrder, int[] preOrder, int inStart, int inEnd, int postStart, int postEnd, int n){
//        System.out.println(Arrays.stream(preOrder).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
        if (postEnd<0 || postEnd>=postOrder.length || postEnd<postStart) return n-1;
        final int now = postOrder[postEnd];
        preOrder[n] = now;
        if (inStart == inEnd) return n;
        for (int i = inStart; i <= inEnd; i++) {
            if (now == inOrder[i]) {
                n = findGroup(inOrder, postOrder, preOrder, inStart, i-1, postStart, postStart + (i-1-inStart), n+1);
                n = findGroup(inOrder, postOrder, preOrder, i+1, inEnd, postStart + (i-inStart), postEnd-1, n+1);
                break;
            }
        }
        return n;
    }
}