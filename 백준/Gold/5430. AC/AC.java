import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());

        ACLangCal cal = new ACLangCal();

        for (int i=0; i<N; i++){
            boolean isSuccess = true;
            char[] cmd = bufferedReader.readLine().strip().toCharArray();
            int M = Integer.parseInt(bufferedReader.readLine());
            cal.setCal(M, bufferedReader.readLine().strip());
            for (char c: cmd){
                if (!cal.command(c)){
                    isSuccess = false;
                    break;
                }
            }
            System.out.println(isSuccess?cal.getResult():"error");
        }
    }

    static class ACLangCal{
        LinkedList<Integer> l = new LinkedList<>();
        boolean flag = true;

        void setCal(int m, String s){
            l.clear();
            flag = true;
            if (m==0) return;
            String[] nums = s.replaceAll("[^0-9,]", "").split(",");
            for (String num: nums){
                l.add(Integer.parseInt(num));
            }
        }

        boolean command(char c){
            if (c=='R') flag = !flag;
            else {
                if (l.isEmpty()) return false;
                if (flag) l.removeFirst();
                else l.removeLast();
            }
            return true;
        }

        String getResult(){
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            while (!l.isEmpty()){
                int n = flag?l.removeFirst():l.removeLast();
                sb.append(n);
                if (!l.isEmpty()) sb.append(",");
            }
            sb.append("]");
            return sb.toString();
        }
    }
}