# [Gold II] špilja - 1902 

[문제 링크](https://www.acmicpc.net/problem/1902) 

### 성능 요약

메모리: 18144 KB, 시간: 196 ms

### 분류

이분 탐색, 기하학, 매개 변수 탐색

### 제출 일자

2025년 4월 27일 17:05:48

### 문제 설명

<p>In a cave near Mirko's village lived his ancestors thousands of years ago. </p>

<p>Mirko came to a conclusion that he is the only one who can find the remains of this ancient civilization so he started with preparations to explore. Therefore, he bought new pants, boots, a shovel and a hammer. Just before research he realized that there was no power supply in the cave and that he has to buy some lamps. But as he spent all the money for buying an up-to-date hammer, he realized that he had money only for one lamp. </p>

<p>As Mirko wants to choose a spot from where he can see the entire floor, help him to determine a minimum height to which he must raise a lamp in order to illuminate the entire floor of the cave. </p>

<p>We will imagine the cave's floor as a broken line in a coordinate system consisting of N peaks t<sub>1</sub>, t<sub>2</sub> t<sub>N</sub> and lengths which connect consecutive peaks. </p>

<p>The floor always goes from left to right, i.e. for every i=1, ..., N-1 x-coordinate of t<sub>i</sub> is smaller than xcoordinate of t<sub>i+1</sub>. </p>

<p>Example (possible solution for third test example)</p>

<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/95154642-dca4-418b-bbbf-e357dba6f82a/-/preview/" style="width: 231px; height: 230px;"></p>

<p>The lamp should be placed somewhere in a spot "above" cave's floor so that it illuminates the entire floor. To put it more precisely, x coordinate of the lamp must be placed between x coordinate of the first and last point of the floor (inclusive), and y coordinate of the lamp must be bigger than or equal to y coordinate of the floor point with the same x coordinate. </p>

<p>We can say that the lamp illuminates the entire floor, if for every point on the floor we can say that the length which connects this point with a lamp does not perforate the broken line which represents the floor. However, it is allowed for the length and the broken line to connect at some points or along certain segments. </p>

<p>Write a program that will determine the smallest possible height on which we can place a lamp sothat it illuminates the entire floor. </p>

<p>You can presume that the result will always be less than or equal to 1,000,000 (one million).</p>

### 입력 

 <p>In the first line,there is an integer N, 2 ≤ N ≤ 5000, number of peaks of the floor. </p>

<p>In each of the next N lines, there are integers X<sub>i</sub> i Y<sub>i</sub>, 0 ≤ X<sub>i</sub>, Y<sub>i</sub> ≤ 100,000, in ith line numbers X<sub>i</sub> i Y<sub>i</sub> i.e. coordinates of ith peak. Numbers X<sub>i</sub> will be in ascending order.</p>

### 출력 

 <p>In first and only line you should write y coordinate where you will put a lamp, real decimal number rounded to 2 decimal places. </p>

<p>Your output value must be within 0.01 absolute or relative error of the correct value.</p>

