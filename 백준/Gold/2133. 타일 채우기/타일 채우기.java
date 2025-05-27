import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        int[] db = {1, 0, 3, 0, 11, 0, 41, 0, 153, 0, 571, 0, 2131, 0, 7953, 0, 29681, 0, 110771, 0, 413403, 0, 1542841, 0, 5757961, 0, 21489003, 0, 80198051, 0, 299303201};

        int N = read();

        System.out.println(db[N]);
    }

    static int read() throws IOException {
        int n = 0;
        int c;

        while ((c = System.in.read()) >= '0') {
            n = (n << 3) + (n << 1) + (c & 15);
        }

        return n;
    }
}