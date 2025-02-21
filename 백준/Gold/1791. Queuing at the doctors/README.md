# [Gold II] Queuing at the doctors - 1791 

[문제 링크](https://www.acmicpc.net/problem/1791) 

### 성능 요약

메모리: 54216 KB, 시간: 1084 ms

### 분류

자료 구조, 구현, 큐, 시뮬레이션

### 제출 일자

2025년 2월 21일 14:36:56

### 문제 설명

<p>Due to the increasing number of weird viruses spreading around, all the members of the International Confederation of Revolver Enthusiasts (ICORE) are required by their boss to do quarterly physical checkups at General Hospital. All checkups are arranged by the boss and scheduled on the same day. Each member of ICORE gets instructions where they are given</p>

<ul>
	<li>their number from the set {1 ... <em>n</em>}</li>
	<li>the time of the day when they are supposed to show up at General Hospital</li>
	<li>a list of doctors' offices that they are to visit in the listed order.</li>
</ul>

<p>Doctors' offices in General Hospital are numbered with numbers from the set {1 ... <em>m</em>}.</p>

<p>All the members of ICORE have been convinced that the schedule of the checkups has been professionally prepared and that there would be no lining up and waiting at the doctors' doors. However, since their boss was a political appointment their hopes for not wasting time had to be abandoned as soon as they started arriving at the hospital. The queues were forming rapidly despite the fact that the doctors were very efficient due to their usual sloppiness. The members of ICORE are all very disciplined and obey the following rules for visiting the doctors</p>

<ul>
	<li>if an ICORE member was supposed to show up at the hospital at time <em>t</em>, then at time <em>t</em> they show up at the first doctors' office on their list;</li>
	<li>if several people show up a doctor's office at time <em>t</em> then they form a queue in increasing order of their numbers and join the end of the queue already formed by people who arrived earlier;</li>
	<li>if at time <em>t</em> in front of office <em>x</em> there is a queue of people who arrived earlier or at time <em>t</em>, then the first person from the queue enters office <em>x</em>. This person after a time unit (the doctors do a sloppy job, remember) exits the office and at time <em>t+1</em> appears at the next office from their list of offices to visit. At that time the first person from the queue enters office <em>x</em>;</li>
	<li>if a visit at office <em>x</em> at time <em>t</em> was for the given visitor the last visit on their list, then at time <em>t+1</em> this visitor leaves the hospital.</li>
</ul>

<p>Your task is to find the time when the last visitor leaves the hospital.</p>

### 입력 

 <p>The first line of input contains a natural number <em>c</em> giving the number of cases to handle. The following lines form the input for the <em>c</em> cases, each in the format described below. The first line of data for a case contains two natural numbers <em>n</em> and <em>m</em>, 1 ≤ <em>n</em>, <em>m</em> ≤ 1000, giving the number of the visitors and the number of doctors' offices for the case. Each of the following <em>n</em> lines contains a sequence of natural numbers. Among these lines, line <em>i</em> (1  ≤ <em>i</em> ≤ <em>n</em>) has the following format</p>

<p style="text-align: center;"><em>t</em>  <em>k</em>  <em>g</em><sub>1</sub>  <em>g</em><sub>2</sub>... <em>g</em><sub>k</sub></p>

<p>meaning that the <em>i</em>th visitor arrives at time <em>t</em> and has to visit <em>k</em> offices in the order given by <em>g</em><sub>1</sub> <em>g</em><sub>2</sub> ... <em>g</em><sub>k</sub> where each <em>g</em><sub>j</sub> is a number of doctor's office, 1 ≤ <em>g</em><sub>j</sub> ≤ <em>m</em>. We have that 0 ≤ <em>t</em> ≤ 1000000 and there is no more than 1000000 visits scheduled for a day at the hospital.</p>

### 출력 

 <p>For each of the <em>c</em> input cases print one line giving the time when the last visitor leaves the hospital.</p>

