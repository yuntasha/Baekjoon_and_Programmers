/*
서브넷 마스크는 같은 부분 엮어서 그 부분을 전부 1로 처리하는 것
현재 서브넷과 IP들하고 xor로 만들고 or로 처리하면 다른 부분 최대 1이 나올 것임
그러면 거기서부터 1이면 0을 넣어주면 될듯?

일단 마스크부터 만들자
그러면 뭘 해야하나
일단 1000 * 1000으로 그냥 전부 xor하면 같은 부분 0처리 되니까 0.0.0.0이랑 or처리
각 쌍으로 xor하고 0.0.0.0이랑 or

그리고 앞에서부터 탐색하면서 1나오기 시작할때부터 그냥 1로 만들고
255와 xor해서 0, 1 뒤집어주기
그러면 서브넷 마스크 만들어짐
그리고 애들중에 하나 서브넷 마스크 그냥 씌워주면 끝


 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        List<Ip> ipList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            ipList.add(new Ip(bf.readLine()));
        }

        System.out.println(solution(N, ipList));
    }

    static String solution(int N, List<Ip> ipList) {
        Ip diff = new Ip("0.0.0.0");
        Ip temp = new Ip("0.0.0.0");

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                temp.clear();
                temp.or(ipList.get(i));
                temp.xor(ipList.get(j));
                diff.or(temp);
            }
        }

        Ip mask = diff.makeMask();

        Ip subnet = new Ip(mask.toString());
        subnet.and(ipList.get(0));

        return subnet + "\n" + mask;
    }

    static class Ip {
        int[] n;

        Ip(String input) {
            String[] ips = input.split("\\.");
            this.n = new int[4];
            for (int i = 0; i < 4; i++) {
                n[i] = Integer.parseInt(ips[i]);
            }
        }

        void and(Ip ip) {
            for (int i = 0; i < 4; i++) {
                n[i] &= ip.n[i];
            }
        }

        void xor(Ip ip) {
            for (int i = 0; i < 4; i++) {
                n[i] ^= ip.n[i];
            }
        }

        void or(Ip ip) {
            for (int i = 0; i < 4; i++) {
                n[i] |= ip.n[i];
            }
        }

        Ip makeMask() {
            Ip mask = new Ip("0.0.0.0");

            Loop : for (int i = 0; i < 4; i++) {
                for (int b = 7; b >= 0; b--) {
                    if ((n[i] & (1 << b)) > 0) {
                        break Loop;
                    }
                    mask.n[i] |= (1 << b);
                }
            }

            return mask;
        }

        void clear() {
            Arrays.fill(n, 0);
        }

        @Override
        public String toString() {
            return Arrays.stream(n).mapToObj(String::valueOf).collect(Collectors.joining("."));
        }

        public String toBString() {
            return Arrays.stream(n).mapToObj(Integer::toBinaryString).collect(Collectors.joining("."));
        }
    }
}