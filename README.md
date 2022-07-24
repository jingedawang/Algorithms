# Algorithms

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![build](https://github.com/jingedawang/Algorithms/actions/workflows/build.yml/badge.svg?branch=master)](https://github.com/jingedawang/Algorithms/actions/workflows/build.yml)
[![check-url](https://github.com/jingedawang/Algorithms/workflows/Url/badge.svg?branch=master)](https://github.com/jingedawang/Algorithms/actions/workflows/check-url.yml)
[![codecov](https://codecov.io/gh/jingedawang/Algorithms/branch/master/graph/badge.svg?token=01G6F59X5L)](https://codecov.io/gh/jingedawang/Algorithms)
[![Gitpod](https://img.shields.io/badge/Gitpod-ready--to--code-FFB45B?logo=gitpod)](https://gitpod.io/#https://github.com/jingedawang/Algorithms)

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

### Quick Start

We support Gitpod as online coding platform, you can try it [here](https://gitpod.io/#https://github.com/jingedawang/Algorithms).

You can use either Intellij IDEA or Gradle to build and test the code locally. But you can only use Gradle as build tool online since Gitpod is using VSCode.

#### Intellij IDEA

We encourage you to open the project with IntelliJ IDEA, which can provide more flexible and powerful debug experiences.

Start IntelliJ IDEA, click [**Open**], choose the cloned `Algorithms` directory,
confirm by clicking [**OK**]. Then you will be able to see the project structure.

To run a demo of an algorithm, please right-click the source file, choose [**Run '\<className\>.main()'**].

To run all tests, please right-click the `test` folder from the Project window, choose [**Run 'All Tests'**].

#### Gradle

If you don't have Intellij IDEA installed or if you are using Gitpod, it's also convenient to build and test with Gradle.
You don't need to have Gradle installed, `gradlew` script will download a proper version for you
during the first run.

To run a demo of an algorithm, use command like
```shell
./gradlew run -PclassName='<package>.<className>'
```
For example, run BFPRT algorithm like
```shell
./gradlew run -PclassName='select.BFPRT'
```

To run all tests, use command like
```shell
./gradlew test
```

### Catalogue

To help people understand the core concept of each algorithm, we provided multiple resources.
Except for code, you can click the `book` and `blog` links for more information.

+ [Cache](https://github.com/jingedawang/Algorithms/tree/master/src/cache)
    + [LRUCache](https://github.com/jingedawang/Algorithms/blob/master/src/cache/LRUCache.java)
      [`blog`](https://www.jianshu.com/p/b1ab4a170c3c)
    + [LRUCacheSimple](https://github.com/jingedawang/Algorithms/blob/master/src/cache/LRUCacheSimple.java)
+ [Container](https://github.com/jingedawang/Algorithms/tree/master/src/container)
    + [BinaryHeap](https://github.com/jingedawang/Algorithms/blob/master/src/container/BinaryHeap.java)
      [`book`](https://edutechlearners.com/download/Introduction_to_algorithms-3rd%20Edition.pdf#page=172)
    + [BinarySearchTree](https://github.com/jingedawang/Algorithms/blob/master/src/container/BinarySearchTree.java)
      [`book`](https://edutechlearners.com/download/Introduction_to_algorithms-3rd%20Edition.pdf#page=307)
    + [BTree](https://github.com/jingedawang/Algorithms/blob/master/src/container/BTree.java)
      [`book`](https://edutechlearners.com/download/Introduction_to_algorithms-3rd%20Edition.pdf#page=505)
      [`blog`](https://zhuanlan.zhihu.com/p/342999669)
    + [FibonacciHeap](https://github.com/jingedawang/Algorithms/blob/master/src/container/FibonacciHeap.java)
      [`book`](https://edutechlearners.com/download/Introduction_to_algorithms-3rd%20Edition.pdf#page=526)
    + [PriorityQueue](https://github.com/jingedawang/Algorithms/blob/master/src/container/PriorityQueue.java)
      [`book`](https://edutechlearners.com/download/Introduction_to_algorithms-3rd%20Edition.pdf#page=183)
    + [RedBlackTree](https://github.com/jingedawang/Algorithms/blob/master/src/container/RedBlackTree.java)
      [`book`](https://edutechlearners.com/download/Introduction_to_algorithms-3rd%20Edition.pdf#page=329)
      [`blog`](https://zhuanlan.zhihu.com/p/335016486)
+ [Divide and Conquer](https://github.com/jingedawang/Algorithms/tree/master/src/divideandconquer)
    + [FindMaximumSubarray](https://github.com/jingedawang/Algorithms/blob/master/src/divideandconquer/FindMaximumSubarray.java)
+ [Dynamic Programming](https://github.com/jingedawang/Algorithms/tree/master/src/dp)
    + [CutRod](https://github.com/jingedawang/Algorithms/blob/master/src/dp/CutRod.java)
      [`book`](https://edutechlearners.com/download/Introduction_to_algorithms-3rd%20Edition.pdf#page=381)
      [`blog`](https://zhuanlan.zhihu.com/p/337544873)
    + [LongestCommonSubsequence](https://github.com/jingedawang/Algorithms/blob/master/src/dp/LongestCommonSubsequence.java)
      [`book`](https://edutechlearners.com/download/Introduction_to_algorithms-3rd%20Edition.pdf#page=411)
    + [ZeroOneKnapsack](https://github.com/jingedawang/Algorithms/blob/master/src/dp/ZeroOneKnapsack.java)
+ [Greedy](https://github.com/jingedawang/Algorithms/tree/master/src/greedy)
    + [HuffmanCode](https://github.com/jingedawang/Algorithms/blob/master/src/greedy/HuffmanCode.java)
      [`book`](https://edutechlearners.com/download/Introduction_to_algorithms-3rd%20Edition.pdf#page=449)
+ [Math](https://github.com/jingedawang/Algorithms/tree/master/src/math)
    + [Fibonacci](https://github.com/jingedawang/Algorithms/blob/master/src/math/Fibonacci.java)
+ [Matrix](https://github.com/jingedawang/Algorithms/tree/master/src/matrix)
    + [PlainMultiplier](https://github.com/jingedawang/Algorithms/blob/master/src/matrix/PlainMultiplier.java)
    + [StrassenMultiplier](https://github.com/jingedawang/Algorithms/blob/master/src/matrix/StrassenMultiplier.java)
      [`book`](https://edutechlearners.com/download/Introduction_to_algorithms-3rd%20Edition.pdf#page=96)
      [`blog`](https://zhuanlan.zhihu.com/p/268392799)
+ [Select](https://github.com/jingedawang/Algorithms/tree/master/src/select)
    + [BFPRT](https://github.com/jingedawang/Algorithms/blob/master/src/select/BFPRT.java)
      [`book`](https://edutechlearners.com/download/Introduction_to_algorithms-3rd%20Edition.pdf#page=241)
      [`blog`](https://zhuanlan.zhihu.com/p/291206708)
    + [QuickSelect](https://github.com/jingedawang/Algorithms/blob/master/src/select/QuickSelect.java)
      [`book`](https://edutechlearners.com/download/Introduction_to_algorithms-3rd%20Edition.pdf#page=236)
+ [Sort](https://github.com/jingedawang/Algorithms/tree/master/src/sort)
    + [BucketSort](https://github.com/jingedawang/Algorithms/blob/master/src/sort/BucketSort.java)
      [`book`](https://edutechlearners.com/download/Introduction_to_algorithms-3rd%20Edition.pdf#page=221)
      [`blog`](https://zhuanlan.zhihu.com/p/270158986)
    + [CountingSort](https://github.com/jingedawang/Algorithms/blob/master/src/sort/CountingSort.java)
      [`book`](https://edutechlearners.com/download/Introduction_to_algorithms-3rd%20Edition.pdf#page=215)
      [`blog`](https://zhuanlan.zhihu.com/p/270158986)
    + [HeapSort](https://github.com/jingedawang/Algorithms/blob/master/src/sort/HeapSort.java)
      [`book`](https://edutechlearners.com/download/Introduction_to_algorithms-3rd%20Edition.pdf#page=172)
      [`blog`](https://zhuanlan.zhihu.com/p/269427787)
    + [HybridSort](https://github.com/jingedawang/Algorithms/blob/master/src/sort/HybridSort.java)
      [`blog`](https://zhuanlan.zhihu.com/p/259208295)
    + [InsertionSort](https://github.com/jingedawang/Algorithms/blob/master/src/sort/InsertionSort.java)
      [`book`](https://edutechlearners.com/download/Introduction_to_algorithms-3rd%20Edition.pdf#page=37)
      [`blog`](https://zhuanlan.zhihu.com/p/258827607)
    + [MergeSort](https://github.com/jingedawang/Algorithms/blob/master/src/sort/MergeSort.java)
      [`book`](https://edutechlearners.com/download/Introduction_to_algorithms-3rd%20Edition.pdf#page=50)
      [`blog`](https://zhuanlan.zhihu.com/p/259208295)
    + [QuickSort](https://github.com/jingedawang/Algorithms/blob/master/src/sort/QuickSort.java)
      [`book`](https://edutechlearners.com/download/Introduction_to_algorithms-3rd%20Edition.pdf#page=191)
      [`blog`](https://zhuanlan.zhihu.com/p/269871839)
    + [RadixSort](https://github.com/jingedawang/Algorithms/blob/master/src/sort/RadixSort.java)
      [`book`](https://edutechlearners.com/download/Introduction_to_algorithms-3rd%20Edition.pdf#page=218)
      [`blog`](https://zhuanlan.zhihu.com/p/270158986)
    + [RandomizedQuickSort](https://github.com/jingedawang/Algorithms/blob/master/src/sort/RandomizedQuickSort.java)
      [`book`](https://edutechlearners.com/download/Introduction_to_algorithms-3rd%20Edition.pdf#page=200)

### Contribute

We highly encourage you to contribute to this project, especially if you are a beginner to algorithms. Read this [Contributing Guideline](https://github.com/jingedawang/Algorithms/blob/master/CONTRIBUTING.md) to learn more.

### Contact

Please create an issue or send E-mail to wjg172184@163.com.
