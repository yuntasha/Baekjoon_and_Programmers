import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        int[] tanghuru = Arrays.stream(bufferedReader.readLine().strip().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(solution(N, tanghuru));
    }

    public static int solution(int N, int[] tanghuru){
        int start = 0;
        HashMap<Integer, Integer> hm = new HashMap<>();
        int result = 0;
        for (int i=0; i<N; i++){
            hm.put(tanghuru[i], i);
            int min = 200001;
            int minNum = 0;
            if (hm.size()>2){
                for (var key:hm.keySet()){
                    if (min>hm.get(key)){
                        min = hm.get(key);
                        minNum = key;
                    }
                }
                result = Math.max(result, i - start);
                start = min+1;
                hm.remove(minNum);
            }
        }
        result = Math.max(result, N - start);

        return result;
    }
}