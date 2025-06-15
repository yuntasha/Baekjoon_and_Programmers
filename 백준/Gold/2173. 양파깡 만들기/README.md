# [Gold III] 양파깡 만들기 - 2173 

[문제 링크](https://www.acmicpc.net/problem/2173) 

### 성능 요약

메모리: 20564 KB, 시간: 532 ms

### 분류

구현, 다이나믹 프로그래밍, 누적 합, 백트래킹

### 제출 일자

2025년 6월 15일 13:48:21

### 문제 설명

<p>(주) 넝심에서는 양파링의 아성에 도전할 만한 아이디어 과자인 양파깡을 만들어냈다. 양파깡은 기존의 양파링과는 달리 직사각형의 모양을 갖는 과자이다. 그런데 (주) 넝심의 과자 기술은 그리 발달된 것이 아니라서 양파깡의 모양을 바로 만들지 못한다. 그래서 궁여지책으로 생각한 것이 넙적한 과자판을 만들어서 양파깡의 모양으로 잘라내는 방법이다.</p>

<p>양파깡으로 잘라내기 전에 먼저 과자판에 소스(양념가루)를 골고루 뿌려야 하는데, 공정상의 실수로 소스가 엉망으로 뿌려지게 되었다. 이 소스는 많으면 많을수록 과자의 맛이 좋다고 한다.</p>

<p>과자판은 N×N크기의 정사각 배열로서 표현될 수 있으며, 각각의 요소는 그 부분에 뿌려진 소스의 양을 나타낸다. 양수는 원래 기준치 소스양보다  많이 뿌려졌음을, 음수는 그 반대를 나타낸다. 다음은 10×10크기의 과자판의 예이다.</p>

<table class="table table-bordered table-center-40 table-2173 td-center">
	<tbody>
		<tr>
			<td>-1</td>
			<td>5</td>
			<td>0</td>
			<td>8</td>
			<td>-1</td>
			<td>-8</td>
			<td>-3</td>
			<td>5</td>
			<td>4</td>
			<td>-5</td>
		</tr>
		<tr>
			<td>-4</td>
			<td>10</td>
			<td>-1</td>
			<td>-6</td>
			<td>-3</td>
			<td>8</td>
			<td>-4</td>
			<td>4</td>
			<td>-8</td>
			<td>-8</td>
		</tr>
		<tr>
			<td>-2</td>
			<td>4</td>
			<td>-7</td>
			<td>-6</td>
			<td>7</td>
			<td>2</td>
			<td>-5</td>
			<td>10</td>
			<td>-9</td>
			<td>-3</td>
		</tr>
		<tr>
			<td>9</td>
			<td>9</td>
			<td>-7</td>
			<td>-6</td>
			<td>-6</td>
			<td>-3</td>
			<td>-8</td>
			<td>-6</td>
			<td>8</td>
			<td>6</td>
		</tr>
		<tr>
			<td>10</td>
			<td>4</td>
			<td>-2</td>
			<td>2</td>
			<td>-3</td>
			<td>-9</td>
			<td>-5</td>
			<td>7</td>
			<td>-4</td>
			<td>-6</td>
		</tr>
		<tr>
			<td>0</td>
			<td>7</td>
			<td>0</td>
			<td>-7</td>
			<td>-7</td>
			<td>-7</td>
			<td>-10</td>
			<td>-5</td>
			<td>-2</td>
			<td>7</td>
		</tr>
		<tr>
			<td>3</td>
			<td>-10</td>
			<td>0</td>
			<td>-5</td>
			<td>6</td>
			<td>-2</td>
			<td>3</td>
			<td>-7</td>
			<td>8</td>
			<td>-3</td>
		</tr>
		<tr>
			<td>9</td>
			<td>-6</td>
			<td>-8</td>
			<td>-1</td>
			<td>0</td>
			<td>-1</td>
			<td>-4</td>
			<td>-3</td>
			<td>-9</td>
			<td>6</td>
		</tr>
		<tr>
			<td>10</td>
			<td>-4</td>
			<td>-1</td>
			<td>-7</td>
			<td>-2</td>
			<td>10</td>
			<td>-5</td>
			<td>-3</td>
			<td>8</td>
			<td>-7</td>
		</tr>
		<tr>
			<td>0</td>
			<td>5</td>
			<td>-4</td>
			<td>8</td>
			<td>-3</td>
			<td>0</td>
			<td>-7</td>
			<td>10</td>
			<td>3</td>
			<td>3</td>
		</tr>
	</tbody>
</table>

<p>양파깡은 다음과 같이 가운데가 빈 직사각형 모양으로 생겼다. (꼭 가운데에 빈 공간이 있어야 한다. 즉, 양파깡은 최소 3x3이상의 크기를 가져야 한다.) 양파깡은 도중에 끊어지지 않은 모양이어야 한다.</p>

<table class="table table-bordered table-center-20 table-2173 td-center">
	<tbody>
		<tr>
			<td class="bg-teal">3</td>
			<td class="bg-teal">5</td>
			<td class="bg-teal">-1</td>
			<td class="bg-teal">-2</td>
			<td class="bg-teal">4</td>
		</tr>
		<tr>
			<td class="bg-teal">-2</td>
			<td> </td>
			<td> </td>
			<td> </td>
			<td class="bg-teal">-5</td>
		</tr>
		<tr>
			<td class="bg-teal">1</td>
			<td> </td>
			<td> </td>
			<td> </td>
			<td class="bg-teal">3</td>
		</tr>
		<tr>
			<td class="bg-teal">-3</td>
			<td> </td>
			<td> </td>
			<td> </td>
			<td class="bg-teal">7</td>
		</tr>
		<tr>
			<td class="bg-teal">4</td>
			<td> </td>
			<td> </td>
			<td> </td>
			<td class="bg-teal">1</td>
		</tr>
		<tr>
			<td class="bg-teal">2</td>
			<td class="bg-teal">3</td>
			<td class="bg-teal">4</td>
			<td class="bg-teal">-6</td>
			<td class="bg-teal">0</td>
		</tr>
	</tbody>
</table>

<p>양파깡의 맛은 잘라낸 부분의 소스의 양들의 합으로 정의된다. 위의 양파깡의 맛은 3 + 5 + (-1) + (-2) + 4 + (-5) + 3 + 7 + 1 + 0 + (-6) + 4 + 3 + 2 + 4 + (-3) + 1 + (-2) = 18 이다.</p>

<p>당신은 (주) 넝심의 수석 프로그래머로써 상사의 명령에 따라 과자판으로부터 양파깡을 잘라내는 프로그램을 만들어야 한다. 상사가 원하는 프로그램은 다음과 같다.</p>

<ol>
	<li>현재 만들어질 수 있는 양파깡 중에서 가장 맛있도록 양파깡을 잘라내야 한다.</li>
	<li>이런 방법으로 M번 반복하여 M개의 양파깡을 잘라낸다.</li>
	<li>만들어진 모든 양파깡 들은 도중에 끊어지지 않고 가운데가 빈 모양이어야 한다.</li>
</ol>

<p>양파깡이 도중에 끊어지지 않아야 한다는 말은 과자판에서 양파깡들이 서로 겹치지 않아야 된다는 말과 같은 뜻이다.</p>

<p>예로 위의 과자판에서 4개의 양파깡을 만들어 내는 문제를 생각해 보자. 첫 번째로 만들 수 있는 가장 맛 좋은 양파깡은 (3, 1)-(10, 9) 위치에서 잘라내면 만들 수 있다. 이때의 양파깡의 맛은 48이다.</p>

<table class="table table-bordered table-center-40 table-2173 td-center">
	<tbody>
		<tr>
			<td>-1</td>
			<td>5</td>
			<td>0</td>
			<td>8</td>
			<td>-1</td>
			<td>-8</td>
			<td>-3</td>
			<td>5</td>
			<td>4</td>
			<td>-5</td>
		</tr>
		<tr>
			<td>-4</td>
			<td>10</td>
			<td>-1</td>
			<td>-6</td>
			<td>-3</td>
			<td>8</td>
			<td>-4</td>
			<td>4</td>
			<td>-8</td>
			<td>-8</td>
		</tr>
		<tr>
			<td class="bg-teal">-2</td>
			<td class="bg-teal">4</td>
			<td class="bg-teal">-7</td>
			<td class="bg-teal">-6</td>
			<td class="bg-teal">7</td>
			<td class="bg-teal">2</td>
			<td class="bg-teal">-5</td>
			<td class="bg-teal">10</td>
			<td class="bg-teal">-9</td>
			<td>-3</td>
		</tr>
		<tr>
			<td class="bg-teal">9</td>
			<td>9</td>
			<td>-7</td>
			<td>-6</td>
			<td>-6</td>
			<td>-3</td>
			<td>-8</td>
			<td>-6</td>
			<td class="bg-teal">8</td>
			<td>6</td>
		</tr>
		<tr>
			<td class="bg-teal">10</td>
			<td>4</td>
			<td>-2</td>
			<td>2</td>
			<td>-3</td>
			<td>-9</td>
			<td>-5</td>
			<td>7</td>
			<td class="bg-teal">-4</td>
			<td>-6</td>
		</tr>
		<tr>
			<td class="bg-teal">0</td>
			<td>7</td>
			<td>0</td>
			<td>-7</td>
			<td>-7</td>
			<td>-7</td>
			<td>-10</td>
			<td>-5</td>
			<td class="bg-teal">-2</td>
			<td>7</td>
		</tr>
		<tr>
			<td class="bg-teal">3</td>
			<td>-10</td>
			<td>0</td>
			<td>-5</td>
			<td>6</td>
			<td>-2</td>
			<td>3</td>
			<td>-7</td>
			<td class="bg-teal">8</td>
			<td>-3</td>
		</tr>
		<tr>
			<td class="bg-teal">9</td>
			<td>-6</td>
			<td>-8</td>
			<td>-1</td>
			<td>0</td>
			<td>-1</td>
			<td>-4</td>
			<td>-3</td>
			<td class="bg-teal">-9</td>
			<td>6</td>
		</tr>
		<tr>
			<td class="bg-teal">10</td>
			<td>-4</td>
			<td>-1</td>
			<td>-7</td>
			<td>-2</td>
			<td>10</td>
			<td>-5</td>
			<td>-3</td>
			<td class="bg-teal">8</td>
			<td>-7</td>
		</tr>
		<tr>
			<td class="bg-teal">0</td>
			<td class="bg-teal">5</td>
			<td class="bg-teal">-4</td>
			<td class="bg-teal">8</td>
			<td class="bg-teal">-3</td>
			<td class="bg-teal">0</td>
			<td class="bg-teal">-7</td>
			<td class="bg-teal">10</td>
			<td class="bg-teal">3</td>
			<td>3</td>
		</tr>
	</tbody>
</table>

<p>과자판에서 양파깡을 잘라내면 다음과 같은 모양이 된다.</p>

<table class="table table-bordered table-center-40 table-2173 td-center">
	<tbody>
		<tr>
			<td>-1</td>
			<td>5</td>
			<td>0</td>
			<td>8</td>
			<td>-1</td>
			<td>-8</td>
			<td>-3</td>
			<td>5</td>
			<td>4</td>
			<td>-5</td>
		</tr>
		<tr>
			<td>-4</td>
			<td>10</td>
			<td>-1</td>
			<td>-6</td>
			<td>-3</td>
			<td>8</td>
			<td>-4</td>
			<td>4</td>
			<td>-8</td>
			<td>-8</td>
		</tr>
		<tr>
			<td class="bg-dark"> </td>
			<td class="bg-dark"> </td>
			<td class="bg-dark"> </td>
			<td class="bg-dark"> </td>
			<td class="bg-dark"> </td>
			<td class="bg-dark"> </td>
			<td class="bg-dark"> </td>
			<td class="bg-dark"> </td>
			<td class="bg-dark"> </td>
			<td>-3</td>
		</tr>
		<tr>
			<td class="bg-dark"> </td>
			<td>9</td>
			<td>-7</td>
			<td>-6</td>
			<td>-6</td>
			<td>-3</td>
			<td>-8</td>
			<td>-6</td>
			<td class="bg-dark"> </td>
			<td>6</td>
		</tr>
		<tr>
			<td class="bg-dark"> </td>
			<td>4</td>
			<td>-2</td>
			<td>2</td>
			<td>-3</td>
			<td>-9</td>
			<td>-5</td>
			<td>7</td>
			<td class="bg-dark"> </td>
			<td>-6</td>
		</tr>
		<tr>
			<td class="bg-dark"> </td>
			<td>7</td>
			<td>0</td>
			<td>-7</td>
			<td>-7</td>
			<td>-7</td>
			<td>-10</td>
			<td>-5</td>
			<td class="bg-dark"> </td>
			<td>7</td>
		</tr>
		<tr>
			<td class="bg-dark"> </td>
			<td>-10</td>
			<td>0</td>
			<td>-5</td>
			<td>6</td>
			<td>-2</td>
			<td>3</td>
			<td>-7</td>
			<td class="bg-dark"> </td>
			<td>-3</td>
		</tr>
		<tr>
			<td class="bg-dark"> </td>
			<td>-6</td>
			<td>-8</td>
			<td>-1</td>
			<td>0</td>
			<td>-1</td>
			<td>-4</td>
			<td>-3</td>
			<td class="bg-dark"> </td>
			<td>6</td>
		</tr>
		<tr>
			<td class="bg-dark"> </td>
			<td>-4</td>
			<td>-1</td>
			<td>-7</td>
			<td>-2</td>
			<td>10</td>
			<td>-5</td>
			<td>-3</td>
			<td class="bg-dark"> </td>
			<td>-7</td>
		</tr>
		<tr>
			<td class="bg-dark"> </td>
			<td class="bg-dark"> </td>
			<td class="bg-dark"> </td>
			<td class="bg-dark"> </td>
			<td class="bg-dark"> </td>
			<td class="bg-dark"> </td>
			<td class="bg-dark"> </td>
			<td class="bg-dark"> </td>
			<td class="bg-dark"> </td>
			<td>3</td>
		</tr>
	</tbody>
</table>

<p>이 과자판에서 가장 맛 좋은 양파깡을 찾아보면 (7, 5)-(9, 7) 부분을 잘라내면 된다. 이 양파깡의 맛은 6이다.</p>

<table class="table table-bordered table-center-40 table-2173 td-center">
	<tbody>
		<tr>
			<td>-1</td>
			<td>5</td>
			<td>0</td>
			<td>8</td>
			<td>-1</td>
			<td>-8</td>
			<td>-3</td>
			<td>5</td>
			<td>4</td>
			<td>-5</td>
		</tr>
		<tr>
			<td>-4</td>
			<td>10</td>
			<td>-1</td>
			<td>-6</td>
			<td>-3</td>
			<td>8</td>
			<td>-4</td>
			<td>4</td>
			<td>-8</td>
			<td>-8</td>
		</tr>
		<tr>
			<td class="bg-dark"> </td>
			<td class="bg-dark"> </td>
			<td class="bg-dark"> </td>
			<td class="bg-dark"> </td>
			<td class="bg-dark"> </td>
			<td class="bg-dark"> </td>
			<td class="bg-dark"> </td>
			<td class="bg-dark"> </td>
			<td class="bg-dark"> </td>
			<td>-3</td>
		</tr>
		<tr>
			<td class="bg-dark"> </td>
			<td>9</td>
			<td>-7</td>
			<td>-6</td>
			<td>-6</td>
			<td>-3</td>
			<td>-8</td>
			<td>-6</td>
			<td class="bg-dark"> </td>
			<td>6</td>
		</tr>
		<tr>
			<td class="bg-dark"> </td>
			<td>4</td>
			<td>-2</td>
			<td>2</td>
			<td>-3</td>
			<td>-9</td>
			<td>-5</td>
			<td>7</td>
			<td class="bg-dark"> </td>
			<td>-6</td>
		</tr>
		<tr>
			<td class="bg-dark"> </td>
			<td>7</td>
			<td>0</td>
			<td>-7</td>
			<td>-7</td>
			<td>-7</td>
			<td>-10</td>
			<td>-5</td>
			<td class="bg-dark"> </td>
			<td>7</td>
		</tr>
		<tr>
			<td class="bg-dark"> </td>
			<td>-10</td>
			<td>0</td>
			<td>-5</td>
			<td class="bg-teal">6</td>
			<td class="bg-teal">-2</td>
			<td class="bg-teal">3</td>
			<td>-7</td>
			<td class="bg-dark"> </td>
			<td>-3</td>
		</tr>
		<tr>
			<td class="bg-dark"> </td>
			<td>-6</td>
			<td>-8</td>
			<td>-1</td>
			<td class="bg-teal">0</td>
			<td>-1</td>
			<td class="bg-teal">-4</td>
			<td>-3</td>
			<td class="bg-dark"> </td>
			<td>6</td>
		</tr>
		<tr>
			<td class="bg-dark"> </td>
			<td>-4</td>
			<td>-1</td>
			<td>-7</td>
			<td class="bg-teal">-2</td>
			<td class="bg-teal">10</td>
			<td class="bg-teal">-5</td>
			<td>-3</td>
			<td class="bg-dark"> </td>
			<td>-7</td>
		</tr>
		<tr>
			<td class="bg-dark"> </td>
			<td class="bg-dark"> </td>
			<td class="bg-dark"> </td>
			<td class="bg-dark"> </td>
			<td class="bg-dark"> </td>
			<td class="bg-dark"> </td>
			<td class="bg-dark"> </td>
			<td class="bg-dark"> </td>
			<td class="bg-dark"> </td>
			<td>3</td>
		</tr>
	</tbody>
</table>

<p>이런 식으로 4개의 양파깡을 만들어 내면 된다.</p>

<p>N×N개의 과자판이 주어질 때, 상사의 요구를 만족시는 프로그램을 작성하시오.</p>

### 입력 

 <p>맨 첫줄에는 과자판의 크기 N과 잘라낼 양파깡의 개수 M이 빈 칸을 사이에 두고 주어진다. 다음 줄부터는 과자판이 N×N의 형태로 빈 칸을 사이에 두고 주어진다.</p>

### 출력 

 <p>첫 번째 줄부터 잘라낸 순서대로 양파깡의 정보를 M줄 만큼 출력한다. 만약 상사의 요구를 만족하는 양파깡을 M개 잘라내는 것이 불가능하다면 맨 첫줄에 0만을 출력한다.</p>

<p>양파깡의 정보는 처음에 양파깡의 맛을 출력하고, 그 뒤에 양파깡의 위치를 출력한다. 양파깡의 위치는 왼쪽 위의 좌표를 나타내는 두 정수(행, 열의 순서)와 오른쪽 아래의 좌표를 나타내는 두 정수를 차례대로 쓴다.</p>

<p>답이 여러가지인 경우 아무거나 출력한다.</p>

