# [Gold II] P배열 - 1566 

[문제 링크](https://www.acmicpc.net/problem/1566) 

### 성능 요약

메모리: 48588 KB, 시간: 556 ms

### 분류

비트마스킹, 브루트포스 알고리즘

### 제출 일자

2024년 12월 16일 14:55:20

### 문제 설명

<p>정수로 이루어진 2차원 배열이 P배열이 되려면, 각각의 열에 있는 원소의 합과, 행에 있는 원소의 합이 모두 0보다 커야 한다.</p>

<p>예를 들어,</p>

<pre> 2  1 -1
-1  2  2</pre>

<p>는 P배열이지만,</p>

<pre> 1  1 -1
-1  2  2</pre>

<p>는 P배열이 아니다.</p>

<p>세준이는 어떤 행이나 열을 선택한 다음에, 그 행이나 열의 모든 원소의 부호를 바꿀 수 있다. (-1을 곱한다.) 이차원 배열이 주어졌을 때, 이 배열을 P배열로 만들기 위해서 필요한 선택의 회수의 최솟값을 구하는 프로그램을 작성하시오.</p>

### 입력 

 <p>첫째 줄에 배열의 행의 개수 N과 열의 개수 M이 주어진다. 둘째 줄부터 N개의 줄에 M개의 수가 주어진다. N과 M은 18보다 작거나 같고, 이차원 배열에 있는 수는 -26보다 크거나 같고, 35보다 작거나 같은 정수이다.</p>

### 출력 

 <p>첫째 줄에 정답을 출력한다. 만약 불가능 할 때에는 -1을 출력한다.</p>

