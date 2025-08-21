import java.util.*;
import java.util.stream.*;

class Solution
{
    public int solution(int []A, int []B)
    {
        Arrays.sort(A);
        Arrays.sort(B);
        return IntStream.range(0, A.length).reduce(0, (a, b) -> a + (A[b] * B[A.length - b - 1]));
    }
}