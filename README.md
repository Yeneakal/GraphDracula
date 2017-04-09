# GraphDracula
Implementation of an approximation algorithm that solves the Travelling-Salesman-Person's (TSP) problem in Java.

***What it does***
- generate a full weighted graph with a random coordinate for each vertex.
- compute a minimum spanning tree (MST).
- do a depth-first-search (DFS) on the previously generated MST. Doing this will deliver a solution to the TSP-Problem implicitly.

***How good is the solution?***
At worst, this implementation will yield a solution twice the cost as the optimal solution at worst case.
For more detail have a look at the PDF I wrote (in the repo) that will explain the math behind it. It is in german, though. Translation to english will follow soonish, hopefully.

***Example***

**Generate the MST**

![mst](https://cloud.githubusercontent.com/assets/11651836/24837635/edfcedca-1d38-11e7-9892-332e4981122c.PNG)

**Do a depth-first-search, it will yield a solution to the TSP-Problem:**
![dfs](https://cloud.githubusercontent.com/assets/11651836/24837630/b1d615ec-1d38-11e7-8d70-63e66308c793.PNG)

***The costs for this example were***
```
original graph total weight: 3.7791325287807435E8
mst graph weight: 13562.310562942272
tsp-tour weight: 21146.246698003408
```
for a graph with 1700 vertices.

***Todos:***
- the GUI is quite basic. Also, the edges will not point to the vertex positions accurately.
- There is some nasty typecasting going on!
- Graphs will be copied at some point. The question is if one wants to keep the graph immutable, which will lead to more memory consumption as well as a slower runtime performance as there is copying going on all the time.
