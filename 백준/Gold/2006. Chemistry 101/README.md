# [Gold IV] Chemistry 101 - 2006 

[문제 링크](https://www.acmicpc.net/problem/2006) 

### 성능 요약

메모리: 16556 KB, 시간: 140 ms

### 분류

자료 구조, 해시를 사용한 집합과 맵, 구현, 파싱, 정렬, 문자열

### 제출 일자

2025년 4월 16일 11:10:48

### 문제 설명

<p>Chemistry is all about reactions—you throw a bunch of stuff into a test-tube, heat it up, hoping that it will neither explode or poison you, then cool it down and try to work out whether what you have is what you expected. That’s the easy (and fun) bit—much harder is recording it all. As is usual with skills of this type, chemistry instructors the world over rely on drill—a seemingly endless set of reactions that the students have to complete. The trick is that everything that appears on the left side (the reagents, or ‘input’) must appear on the right side (the products, or ‘output’). This ought to be simple, but generations of chemistry students have demonstrated otherwise.</p>

<p>Professor Plumbius is getting tired of writing the same comments on his student’s worksheets over and over and he wants to automate the process. He wants to be able to enter the equations as written by the students and have the computer produce the comments automatically, thus giving him more time to dream up more equations to give his students to practise on. This is where you come in.</p>

<p>Write a program that will read in a chemistry equation and determine whether it is balanced. If it isn’t, your program must tell the student what elements are out of balance and by how much.</p>

<p>Normally a chemistry equation is written like this:</p>

<p>2H<sub>2</sub>O + SO<sub>2</sub> ←→ H<sub>2</sub> + H<sub>2</sub>SO<sub>4</sub></p>

<p>but due to the limitations of computer input we will present it like this:</p>

<p>2H2O + SO2 = H2 + H2SO4</p>

<p>This example shows the essentials of an equation: each side consists of one or more molecules, separated by ‘+’ signs (the spaces are optional). Each molecule may have a multiplier before it which specifies how many instances of that molecule take part in the reaction. A molecule consists of one or more elements. Each element has a symbol, which is either an uppercase letter, e.g., ‘H’, or an uppercase letter followed by a lowercase letter, e.g., ‘Br’. A symbol may be followed by a multiplier specifying how many atoms of that element are present in that part of the molecule. Thus the first term says that there are two instances of a molecule consisting of two atoms of H and one atom of O. (This happens to be water, but you do not need to know that.)</p>

<p>Given that these are exercises handed out to the students, the left hand sides are, by definition, correct. Thus your job is to determine whether all the atoms that appear on the left also appear on the right. If they do, then the equation is balanced. If not, you must report which elements have been created or lost and how much of each.</p>

### 입력 

 <p>Input will consist of a number of equations, each on a line by itself. Each line will contain no more than 250 characters. Each equation represents a set of reagents and a set of products, separated by an ‘=’ sign. Each set will consist of one or more molecules, possibly with multipliers, separated by ‘+’ signs. There may be zero or more spaces on either side of the ‘+’ and ‘=’ signs.</p>

<p>The last line of input will be a ‘#’ on a line by itself. This line should not be processed.</p>

### 출력 

 <p>There will be at least one line of output for each equation in the input. If the equation is balanced this line will say ‘Equation n is balanced.’, where n is the equation number (starting from 1). If the equation does not balance, then the output line will say ‘Equation n is unbalanced.’ and will be followed by a series of lines of the form</p>

<p><code>You have <created or destroyed> m <atom or atoms> of element. </code></p>

<p>where element is the symbol of the element concerned, m is the number of extra or missing atoms of that element, and ‘atom(s)’ is singular or plural as appropriate. For each unbalanced equation, these lines should be ordered alphabetically by element symbol and terminated by a blank line. (Note that this means that the final line of your output may be a blank line.)</p>

