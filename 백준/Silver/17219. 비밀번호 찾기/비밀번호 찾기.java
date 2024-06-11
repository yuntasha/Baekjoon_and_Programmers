import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var s = bufferedReader.readLine().strip().split(" ");
        int N = Integer.parseInt(s[0]);
        int M = Integer.parseInt(s[1]);

        HashMap<String, String> hm = new HashMap<>(N);

        for (int i=0; i<N; i++){
            s = bufferedReader.readLine().split(" ");
            hm.put(s[0], s[1]);
        }

        for (int i=0; i<M; i++){
            System.out.println(hm.get(bufferedReader.readLine().strip()));
        }
    }
}