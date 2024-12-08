package com.atguigu.recursion;

public class RecursionTest {
    public static void main(String[] args) {
        test(4);

        int factorial = factorial(3);

        System.out.println(factorial);

    }

    public static void test(int i) {
        if (i > 2) {
            test(i - 1);
        }
        System.out.println(i);
    }

    public static int factorial(int n) {
        if (n == 1) {
            return n;
        }else {
            return factorial(n - 1) * n;
        }

    }
}

