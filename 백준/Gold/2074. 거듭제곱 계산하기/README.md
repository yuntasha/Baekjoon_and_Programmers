# [Gold I] 거듭제곱 계산하기 - 2074 

[문제 링크](https://www.acmicpc.net/problem/2074) 

### 성능 요약

메모리: 2084 KB, 시간: 0 ms

### 분류

그래프 이론, 그래프 탐색, 너비 우선 탐색, 런타임 전의 전처리

### 제출 일자

2025년 6월 3일 11:14:49

### 문제 설명

<p>x의 P승을(1 ≤ P ≤ 20,000) 최대한 빠르게 계산하고자 한다. 그런데 계산결과가 굉장히 크므로 여기서는 계산 도중 값을 저장하는 데 있어 두 개의 변수만을 사용한다.</p>

<p>두 변수 중 하나는 x로, 다른 하나는 1로 초기화되어 있다. 우리가 쓸 수 있는 연산은, 현재 저장되어 있는 값들을 서로 곱하거나 나누어서 그 결과값을 임의의 변수에 저장하는 것이다. 최종 결과가 저장되는 위치는 아무래도 좋다.</p>

<p>예를 들어 x<sup>9</sup>을 계산하고자 할 때, 다음과 같은 방법이 가능하다.</p>

<table class="table table-bordered" style="width:48%">
	<tbody>
		<tr>
			<td style="width:6%">연산 회수</td>
			<td style="width:6%">0</td>
			<td style="width:6%">1</td>
			<td style="width:6%">2</td>
			<td style="width:6%">3</td>
			<td style="width:6%">4</td>
		</tr>
		<tr>
			<td>변수1</td>
			<td>x</td>
			<td>x</td>
			<td>x</td>
			<td>x<sup>5</sup></td>
			<td>x<sup>9</sup></td>
		</tr>
		<tr>
			<td>변수2</td>
			<td>1</td>
			<td>x<sup>2</sup></td>
			<td>x<sup>4</sup></td>
			<td>x<sup>4</sup></td>
			<td>x<sup>4</sup></td>
		</tr>
	</tbody>
</table>

<p>P를 입력받아, x<sup>P</sup>를 구하기 위한 최소 연산 회수를 구하여라.</p>

### 입력 

 <p>첫째 줄에 P가 들어온다.</p>

### 출력 

 <p>최소 연산 회수를 출력한다.</p>

