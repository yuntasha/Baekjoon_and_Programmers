import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;


public class Main {

    static int R;
    static int C;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var RCM = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();


        R = RCM[0];
        C = RCM[1];
        var M = RCM[2];

        var sharks = new Shark[M+1];

        for (int i=0; i<M; i++){
            sharks[i+1] = new Shark(Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray());
        }

        System.out.println(solution(M, sharks));
    }

    static int solution(int M, Shark[] sharks){
        var result = 0;
        for (int c=0; c<C; c++) {
            var pool = new int[R][C];
            Shark fished = null;
            var fishedi = 0;
            for (int i=1; i<=M; i++) {
                var s = sharks[i];
                if (!s.isLive) continue;
                if (s.c==c) {
                    if (fished==null) {
                        fished = s;
                        fishedi = i;
                        continue;
                    } else if (s.r < fished.r) {

                        fished.move();

                        if (pool[fished.r][fished.c]==0) {
                            pool[fished.r][fished.c] = fishedi;
                        } else if (sharks[pool[fished.r][fished.c]].size < fished.size) {
                            sharks[pool[fished.r][fished.c]].isLive = false;
                            pool[fished.r][fished.c] = fishedi;
                        } else {
                            fished.isLive = false;
                        }

                        fished = s;
                        fishedi = i;
                        continue;
                    }
                }

                s.move();

                if (pool[s.r][s.c]==0) {
                    pool[s.r][s.c] = i;
                } else if (sharks[pool[s.r][s.c]].size < s.size) {
                    sharks[pool[s.r][s.c]].isLive = false;
                    pool[s.r][s.c] = i;
                } else {
                    s.isLive = false;
                }
            }

//            System.out.println(Arrays.stream(pool).map(arr -> Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.joining(" "))).collect(Collectors.joining("\n", "\n=========================\n", "\n=========================\n")));

            if (fished!=null) {
                result += fished.size;
                fished.isLive = false;
            }
        }
        return result;
    }

    static class Shark {
        int r;
        int c;
        Direct direct;
        int speed;
        int size;
        boolean isLive;

        Shark(int[] shark) {
            this.r = shark[0]-1;
            this.c = shark[1]-1;
            this.speed = shark[2];
            this.size = shark[4];
            this.isLive = true;
            switch (shark[3]) {
                case 1:
                    this.direct = Direct.UP;
                    break;
                case 2:
                    this.direct = Direct.DOWN;
                    break;
                case 3:
                    this.direct = Direct.RIGHT;
                    break;
                case 4:
                    this.direct = Direct.LEFT;
                    break;
            }
        }

        void move(){
            var n = speed;
            while (n>0) {
                int m;
                switch (this.direct) {
                    case RIGHT:
                        m = Math.min(C-1-c, n);
                        n-=m;
                        c+=m;
                        if (c==C-1) {
                            this.direct = Direct.LEFT;
                        }
                        break;
                    case LEFT:
                        m = Math.min(c, n);
                        n-=m;
                        c-=m;
                        if (c==0) {
                            this.direct = Direct.RIGHT;
                        }
                        break;
                    case UP:
                        m = Math.min(r, n);
                        n-=m;
                        r-=m;
                        if (r==0) {
                            this.direct = Direct.DOWN;
                        }
                        break;
                    case DOWN:
                        m = Math.min(R-1-r, n);
                        n-=m;
                        r+=m;
                        if (r==R-1) {
                            this.direct = Direct.UP;
                        }
                        break;
                }
            }
        }

        static enum Direct {
            RIGHT, LEFT, UP, DOWN;
        }
    }
}