import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, Tree> hm = new HashMap<>();
        var N = Integer.parseInt(bufferedReader.readLine());
        for (int i=0; i<N; i++){
            var cmd = bufferedReader.readLine().strip().split(" ");

            var now = hm.getOrDefault(cmd[0], new Tree(cmd[0]));
            hm.put(cmd[0], now);

            if (!cmd[1].equals(".")) {
                now.left = hm.getOrDefault(cmd[1], new Tree(cmd[1]));
                hm.put(cmd[1], now.left);
            }

            if (!cmd[2].equals(".")) {
                now.right = hm.getOrDefault(cmd[2], new Tree(cmd[2]));
                hm.put(cmd[2], now.right);
            }
        }

        var now = hm.get("A");
        System.out.println(now.preOrder());
        System.out.println(now.inOrder());
        System.out.println(now.postOrder());
    }

    static class Tree{
        String data;
        Tree left=null;
        Tree right=null;

        Tree(String data){
            this.data = data;
        }

        String preOrder(){
            String result = this.data;
            if (left!=null){
                result = result + left.preOrder();
            }
            if (right!=null){
                result = result + right.preOrder();
            }

            return result;
        }

        String inOrder(){
            String result = this.data;
            if (left!=null){
                result = left.inOrder() + result;
            }
            if (right!=null){
                result = result + right.inOrder();
            }

            return result;
        }

        String postOrder(){
            String result = this.data;
            if (right!=null){
                result = right.postOrder() + result;
            }
            if (left!=null){
                result = left.postOrder() + result;
            }

            return result;
        }
    }
}