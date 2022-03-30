# Algorithms

Welcome to the world of **Algorithms**.

This repository provides commonly used algorithms according to *Introduction to Algorithms*.
Codes are implemented in Java, since it's a functional and flexible language, which can clearly
show the principles of the algorithms.

As you know, the best way of mastering an algorithm is implementing it.
But before that, you need to understand how it works.
We have a great book *Introduction to Algorithms* which tells us about that.
However, it's not always smooth to catch the point.
What we did here is providing an easy-to-understand implementation, in which you can debug step-by-step.
Then grasp the main idea of the algorithm by careful observation.

We created the most intuitive demos for each algorithm.
Just run it, the core principle will flash on you at once.

### Setup

We encourage you to open the project with IntelliJ IDEA, which can provide more flexible and powerful debug experiences.

Open IntelliJ IDEA, click [**Open**], choose the cloned `Algorithms` directory,
confirm by clicking [**OK**]. Then you will be able to see the project structure.

After that, right-click the `test` folder on the left side, choose [**Run 'All Tests'**].
It triggers the build procedure, and after that, all tests will be executed.
If all tests succeeds, congratulations, the code works well on your machine.

### Usage
You can try to run each algorithm individually.
We provide a simple demo directly in the `main` method of each algorithm class.
Meanwhile, there are more sophisticated examples in corresponding tests. (TODO)

As a technical writer, I published several blogs in my Zhihu column [算法导论](https://www.zhihu.com/column/introduction-to-algorithms).
It is recommended to follow it and receive the latest update.

Following is the catalogue of the content.
For each algorithm, we provide code, test, chapter of the original book, and blog.

+ [Cache](https://github.com/jingedawang/Algorithms/tree/master/src/cache)
    + [LRUCache](https://github.com/jingedawang/Algorithms/blob/master/src/cache/LRUCache.java) [blog](https://www.jianshu.com/p/b1ab4a170c3c)
    + [LRUCacheSimple](https://github.com/jingedawang/Algorithms/blob/master/src/cache/LRUCacheSimple.java)
+ [Container](https://github.com/jingedawang/Algorithms/tree/master/src/container)
    + [BTree](https://github.com/jingedawang/Algorithms/blob/master/src/container/BTree.java) [blog](https://zhuanlan.zhihu.com/p/342999669)
    + [BinarySearchTree](https://github.com/jingedawang/Algorithms/blob/master/src/container/BinarySearchTree.java)
    + [Heap](https://github.com/jingedawang/Algorithms/blob/master/src/container/Heap.java)
    + [PriorityQueue](https://github.com/jingedawang/Algorithms/blob/master/src/container/PriorityQueue.java)
    + [RedBlackTree](https://github.com/jingedawang/Algorithms/blob/master/src/container/RedBlackTree.java) [blog](https://zhuanlan.zhihu.com/p/335016486)
+ [Dynamic Programming](https://github.com/jingedawang/Algorithms/tree/master/src/dp) [blog](https://zhuanlan.zhihu.com/p/337544873)
    + [CutRod](https://github.com/jingedawang/Algorithms/blob/master/src/dp/CutRod.java)
    + [LongestCommonSubsequence](https://github.com/jingedawang/Algorithms/blob/master/src/dp/LongestCommonSubsequence.java)
    + [ZeroOneKnapsack](https://github.com/jingedawang/Algorithms/blob/master/src/dp/ZeroOneKnapsack.java)
+ [Greedy](https://github.com/jingedawang/Algorithms/tree/master/src/greedy)
    + [HuffmanCode](https://github.com/jingedawang/Algorithms/blob/master/src/greedy/HuffmanCode.java)
+ [Math](https://github.com/jingedawang/Algorithms/tree/master/src/math)
    + [Fibonacci](https://github.com/jingedawang/Algorithms/blob/master/src/math/Fibonacci.java)
+ [Matrix](https://github.com/jingedawang/Algorithms/tree/master/src/matrix)
    + [PlainMultiplier](https://github.com/jingedawang/Algorithms/blob/master/src/matrix/PlainMultiplier.java)
    + [StrassenMultiplier](https://github.com/jingedawang/Algorithms/blob/master/src/matrix/StrassenMultiplier.java) [blog](https://zhuanlan.zhihu.com/p/268392799)
+ [Select](https://github.com/jingedawang/Algorithms/tree/master/src/select)
    + [BFPRT](https://github.com/jingedawang/Algorithms/blob/master/src/select/BFPRT.java) [blog](https://zhuanlan.zhihu.com/p/291206708)
    + [QuickSelect](https://github.com/jingedawang/Algorithms/blob/master/src/select/QuickSelect.java)
+ [Sort](https://github.com/jingedawang/Algorithms/tree/master/src/sort)
    + [BucketSort](https://github.com/jingedawang/Algorithms/blob/master/src/sort/BucketSort.java) [blog](https://zhuanlan.zhihu.com/p/270158986)
    + [CountingSort](https://github.com/jingedawang/Algorithms/blob/master/src/sort/CountingSort.java)
    + [HeapSort](https://github.com/jingedawang/Algorithms/blob/master/src/sort/HeapSort.java) [blog](https://zhuanlan.zhihu.com/p/269427787)
    + [HybridSort](https://github.com/jingedawang/Algorithms/blob/master/src/sort/HybridSort.java)
    + [InsertionSort](https://github.com/jingedawang/Algorithms/blob/master/src/sort/InsertionSort.java) [blog](https://zhuanlan.zhihu.com/p/258827607)
    + [MergeSort](https://github.com/jingedawang/Algorithms/blob/master/src/sort/MergeSort.java) [blog](https://zhuanlan.zhihu.com/p/259208295)
    + [QuickSort](https://github.com/jingedawang/Algorithms/blob/master/src/sort/QuickSort.java) [blog](https://zhuanlan.zhihu.com/p/269871839)
    + [RadixSort](https://github.com/jingedawang/Algorithms/blob/master/src/sort/RadixSort.java)
    + [RandomizedQuickSort](https://github.com/jingedawang/Algorithms/blob/master/src/sort/RandomizedQuickSort.java)