import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        int M = Integer.parseInt(bufferedReader.readLine());

        var charArray = bufferedReader.readLine().strip().toCharArray();

        var resultList = new LinkedList<Integer>();

        int now = 0;

        int nowResult = 0;
        while (now< charArray.length-2){
            if (charArray[now]=='I' && charArray[now+1]=='O' && charArray[now+2]=='I'){
                nowResult++;
                now+=2;
            }
            else{
                if (nowResult>0) {
                    resultList.add(nowResult);
                    nowResult=0;
                }
                now++;
            }
        }

        if (nowResult>0) resultList.add(nowResult);

        System.out.println(resultList.stream().filter(i -> i > 0).mapToInt(i -> i>=N?i-N+1:0).sum());
    }
}