import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String inputLine = bufferedReader.readLine();
        int N = Integer.parseInt(inputLine);

        inputLine = bufferedReader.readLine();

        String[] ss = inputLine.split(" ");
        int[] nums = new int[ss.length];

        for (int i=0; i<N; i++){
            nums[i] = Integer.parseInt(ss[i]);
        }

        Solution solution = new Solution(N, nums);
        solution.result();
    }

    private static class Solution{
        int N;
        int[] nums;

        Solution(int N, int[] nums){
            this.N = N;
            this.nums = nums;
        }

        void result(){
            Set<Integer> s = new HashSet<>();
            HashMap<Integer, Integer> hs = new HashMap<>();
            for (int i=0; i<N; i++) s.add(nums[i]);
            Integer[] array = s.toArray(Integer[]::new);
            Arrays.sort(array);
            for (int i=0; i< array.length; i++) hs.put(array[i], i);

            for (int i=0; i<N; i++) nums[i] = hs.get(nums[i]);

            StringBuilder sb = new StringBuilder();
            for (int i=0; i<N-1; i++){
                sb.append(nums[i]);
                sb.append(" ");
            }
            sb.append(nums[N-1]);
            System.out.println(sb);
        }
    }
}