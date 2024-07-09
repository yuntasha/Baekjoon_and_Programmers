import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var N = new int[2];
        HashMap<Integer, LinkedList<Integer>>[] hm = new HashMap[2];

        for (int i=0; i<2; i++){
            N[i] = Integer.parseInt(bufferedReader.readLine());
            hm[i] = new HashMap<Integer, LinkedList<Integer>>();

            var ip = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for (int k=0; k<N[i]; k++){
                if (!hm[i].containsKey(ip[k])) {
                    hm[i].put(ip[k], new LinkedList<>());
                }
                hm[i].get(ip[k]).add(k);
            }
        }

        System.out.println(solution(N, hm));
    }


    static String solution(int[] N, HashMap<Integer, LinkedList<Integer>>[] hm){
        var list = hm[0].keySet().stream().filter(n -> hm[1].containsKey(n)).sorted(Comparator.reverseOrder()).toArray(Integer[]::new);

        var idx = new int[2];
        var result = new ArrayList<Integer>();

        for (Integer s: list){
            while (!hm[0].get(s).isEmpty() && !hm[1].get(s).isEmpty()){
                var flag = true;

                for (int i=0; i<2; i++){
                    var now = hm[i].get(s).get(0);
                    if (now<idx[i]){
                        hm[i].get(s).remove();
                        flag = false;
                    }
                }

                if (flag) {
                    for (int i=0; i<2 ; i++){
                        idx[i] = hm[i].get(s).remove();
                    }
                    result.add(s);
                }
            }
        }

        return result.size()==0?"0":result.size() + "\n"
                + result.stream().map(String::valueOf).collect(Collectors.joining(" "));
    }
}