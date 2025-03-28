# [Gold IV] 로마 숫자 - 1872 

[문제 링크](https://www.acmicpc.net/problem/1872) 

### 성능 요약

메모리: 15276 KB, 시간: 116 ms

### 분류

그리디 알고리즘, 구현, 문자열

### 제출 일자

2025년 3월 13일 15:29:18

### 문제 설명

<p>로마 시대 때는 현재 사용하는 아라비아 숫자가 아닌 다른 방법으로 수를 표현하였다. 로마 숫자는 다음과 같은 7개의 기호로 이루어진다.</p>

<table class="table table-bordered" style="width: 48%;">
	<thead>
		<tr>
			<th style="width: 6%;">기호</th>
			<th style="width: 6%;">I</th>
			<th style="width: 6%;">V</th>
			<th style="width: 6%;">X</th>
			<th style="width: 6%;">L</th>
			<th style="width: 6%;">C</th>
			<th style="width: 6%;">D</th>
			<th style="width: 6%;">M</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>값</td>
			<td>1</td>
			<td>5</td>
			<td>10</td>
			<td>50</td>
			<td>100</td>
			<td>500</td>
			<td>1000</td>
		</tr>
	</tbody>
</table>

<p>수를 만드는 규칙은 다음과 같다.</p>

<ul>
	<li>규칙 1. 보통 큰 숫자를 왼쪽에, 작은 숫자를 오른쪽에 쓴다. 그리고 그 값은 모든 숫자의 값을 더한 값이 된다. 예를 들어 LX = 50 + 10 = 60이 되고, MLI = 1000 + 50 + 1 = 1051이 된다.</li>
	<li>규칙 2. 기호 V, L, D는 한 번만 사용할 수 있고, 기호 I, X, C은 연속해서 세 번까지만 사용할 수 있다. 기호 M은 몇 번이고 사용할 수 있다. 예를 들어 VV나 LXIIII와 같은 수는 없다. 그리고 같은 숫자가 반복되면 그 값도 규칙 1과 마찬가지로 모든 숫자의 값을 더한 값이 된다. 예를 들어 XXX = 10 + 10 + 10 = 30이 되고, CCLIII = 100 + 100 + 50 + 1 + 1 + 1 = 253이 된다.</li>
	<li>규칙 3. 작은 숫자가 큰 숫자의 왼쪽에 오는 경우는 다음과 같다. IV = 4, IX = 9, XL = 40, XC = 90, CD = 400, CM = 900을 나타낸다. 이들 각각은 한 번씩만 사용할 수 있다. 이에 따라 LIX = 50 + 9 = 59, CXC = 100 + 90 = 190이 된다. 그런데 IV와 IX는 같이 쓸 수 없으며 XL과 XC, CD와 CM 또한 같이 쓸 수 없다.또한 이들 외에는 작은 숫자가 큰 숫자 왼쪽 어디에도 나올 수 없다. 예를 들어 XCXC나 CMCD, VX나 IIX와 같은 수는 없다.</li>
	<li>규칙 4. 모든 수는 가능한 가장 적은 개수의 로마 숫자들로 표현해야 한다. 예를 들어 60은 LX이지 XLXX가 아니고, 5는 V이지 IVI가 아니다.</li>
</ul>

<p>이러한 네 가지 규칙에 따라, 아래는 몇 개의 수를 로마 숫자로 표현해 본 것이다.</p>

<ul>
	<li>235 = CCXXXV</li>
	<li>1940 = MCMXL</li>
	<li>5493 = MMMMMCDXCIII</li>
</ul>

<p>중세 시대의 사람들은 때때로 로마 숫자로 표현된 문자열의 앞이나 뒤, 또는 문자 사이에 다른 문자나 공백을 적절히 삽입하는 운치있는 표현을 즐겼다고 한다. 예를 들어 아래와 같은 문장은 1051 = MLI를 표현한 것이다.</p>

<ul>
	<li>matt is the best school in korea</li>
</ul>

<p>문자열의 순서를 뒤바꿀 수는 없기 때문에, 문장 안에서도 원래의 로마 숫자에 등장하는 순서대로 문자들이 나타나야 한다. 즉, 아래와 같은 문장은 1051 = MLI를 표현한 것으로 볼 수 없다.</p>

<ul>
	<li>mind control</li>
</ul>

<p>하지만 이러한 표현은 항상 한 가지로만 해석되는 것이 아니다. 위의 MLI를 표현한 문장은 아래와 같이 1151 = MCLI로 볼 수도 있기 때문이다.</p>

<ul>
	<li>matt is the best school in korea</li>
</ul>

<p>운치있게 표현된 영어 문장이 주어졌을 때, 이 문장이 어떤 수를 표현한 것인지를 찾아내는 프로그램을 작성하시오. 단, 이 문장이 표현하고 있는 것으로 예상되는 수가 두 가지 이상인 경우는, 그 중 가장 큰 수를 찾아야 한다.</p>

### 입력 

 <p>첫째 줄에 문장의 개수 N(1 ≤ N ≤ 10)이 주어진다. 이어서 N개의 줄에 걸쳐 운치있게 표현된 문장이 한 줄에 하나씩 주어진다. 입력되는 문장은 알파벳 소문자와 띄어쓰기만으로 이루어져 있으며, 그 길이는 10,000을 넘지 않는다.</p>

### 출력 

 <p>첫째 줄부터 N개의 줄에 걸쳐 각 문장이 표현하고 있는 가장 큰 수를 출력한다. 만일 어떤 숫자도 표현한다고 볼 수 없는 경우는 0을 출력한다.</p>

