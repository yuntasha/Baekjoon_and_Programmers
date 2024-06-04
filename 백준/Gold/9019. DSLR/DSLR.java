import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < N; i++) {
            String[] result = new String[10000];
            String[] inp = bufferedReader.readLine().split(" ");
            int start = Integer.parseInt(inp[0]);
            int end = Integer.parseInt(inp[1]);
            Queue<Integer> q = new LinkedList<>();
            q.add(start);
            result[start] = "";

            while (!q.isEmpty()){
                int now = (int) q.remove();
                // *2 +9999%10000 앞으로 뒤로
                String nows = String.valueOf(now);
                while (nows.length()<4){
                    nows = "0"+nows;
                }

                int[] next = new int[]{Integer.parseInt(nows.substring(1)+nows.charAt(0)),
                        Integer.parseInt(nows.charAt(nows.length()-1)+nows.substring(0, nows.length()-1)),
                        (now*2)%10000,
                        (now+9999)%10000};
                String[] cmd = new String[]{"L", "R", "D", "S"};

                boolean flag = false;

                for (int j=0; j<4; j++){
                    if (result[next[j]] == null){
                        q.add(next[j]);
                        result[next[j]] = result[now] + cmd[j];
                        if (next[j]==end) {
                            System.out.println(result[next[j]]);
                            flag = true;
                        }
                    }
                }

                if (flag) break;
            }
        }
    }
}