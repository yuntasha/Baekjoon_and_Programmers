# [Gold I] 구역 나누기 - 1618 

[문제 링크](https://www.acmicpc.net/problem/1618) 

### 성능 요약

메모리: 204388 KB, 시간: 2748 ms

### 분류

이분 탐색, 브루트포스 알고리즘, 구현, 매개 변수 탐색

### 제출 일자

2025년 1월 1일 14:36:17

### 문제 설명

<p>수평으로 n개, 수직으로 m개의 길이 나 있는 도시가 있다. 이 길들에 의해서, 도시는 (n+1)×(m+1)개의 소구역으로 나눠지며, 각각의 소구역에는 몇 명의 사람들이 살고있다. n=2, m=3일때의 예를 들면, 아래의 그림과 같은 구조이다.</p>

<p style="text-align: center;"><img alt="" height="144" src="https://www.acmicpc.net/upload/201004/rndur.PNG" width="367"></p>

<p>이 길들 중 수평으로 X개, 수직으로 Y개의 길을 택해서 도시를 재조정 하려고 한다. 이렇게 될 경우, 도시는 (X+1)×(Y+1)개의 구역으로 다시 나뉜다. 이렇게 나뉘어진 구역중, 사람이 제일 많이 살고있는 구역의 사람의 수가 최소가 되도록 도시를 재조정 하려고 한다. 위의 위의 예제에서 X=1, Y=2일때의 최적해는 굵은 선과 같으며, 이때는 모든 구역의 사람의 수가 20이 된다.</p>

<p>이 문제를 해결하는 프로그램을 작성하시오.</p>

### 입력 

 <p>첫째 줄에는 두 정수 n(1 ≤ n ≤ 20), m(1 ≤ m ≤ 100)이 주어진다. 두 번째 줄에는 두 정수 X(1 ≤ X ≤ n), Y(1 ≤ Y ≤ m)이 주어진다. 다음 n+1개의 줄에는 m+1개의 자연수가 주어진다. 이 값들은 10,000이하이다. 각각의 수들 사이에는 공백이 하나씩 있다.</p>

### 출력 

 <p>첫째 줄에는 선택한 X개의 수평선의 번호를 출력한다. 둘째 줄에는 선택한 Y개의 수직선의 번호를 출력한다. 수평선은 위에서 아래로, 수직선은 왼쪽에서 오른쪽으로 차례로 1, 2, 3의 번호를 갖는다. 세 번째 줄에는 이때 가장 많은 사람이 속해있는 구역에 속한 사람의 수를 출력한다. 각 숫자들 사이에는 1개 이상의 공백을 둔다.</p>

