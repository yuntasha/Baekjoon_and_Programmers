# [Gold V] Cow Pizza - 1887 

[문제 링크](https://www.acmicpc.net/problem/1887) 

### 성능 요약

메모리: 14100 KB, 시간: 116 ms

### 분류

비트마스킹, 브루트포스 알고리즘

### 제출 일자

2025년 3월 17일 15:50:43

### 문제 설명

<p>Oh how the cows love their pizza! Even though they are picky, they do love variety, too. They order from the local pizza parlor that features T (1 <= T <= 20) different toppings in addition to a complete set of soft drinks and healthy salads.</p>

<p>The toppings are conveniently numbered 1..T so the cows can order by number. Your job is to calculate how many possible pizzas can be created given that some cows will not tolerate various combinations of toppings (e.g., some cows simply will not eat Anchovies or the combination of Mushrooms and Asparagus).</p>

<p>Given a set of N (1 <= N <= 52) constraints, figure out how many pizzas can be made using all possible combinations of the ingredients (which, of course, includes no ingredients at all). Each constraint is a set of numbers of size 1..T that lists the ingredients that disqualify a pizza from being considered.</p>

<p>A constraint like "5 3" means that no pizza can contain ingredient #5 and also ingredient #3. This means a pizza with ingredients 3, 5, and 6 will not be counted as acceptable.</p>

### 입력 

 <ul>
	<li>Line 1: Two space-separated integers: T and N</li>
	<li>Lines 2..N+1: Each line describes a constraint using space-separated integers. The first integer is the number of ingredients in the constraint, Z (1 <= Z <= T). The subsequent Z integers (which are unique) list the ingredient(s) whose combination disqualifies a pizza from consideration for the cows.</li>
</ul>

### 출력 

 <ul>
	<li>Line 1: A single integer that is the total number of pizzas that can be created using the number of ingredients and constraints.</li>
</ul>

