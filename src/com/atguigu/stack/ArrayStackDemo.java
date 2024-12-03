package com.atguigu.stack;

import java.nio.channels.Pipe;
import java.util.Calendar;
import java.util.Objects;
import java.util.Scanner;

public class ArrayStackDemo {

    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(4);
        String key;
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;

        System.out.println("任意输入字符以开始程序...");
        while (loop && scanner.hasNext()) {
            System.out.println("show：表示遍历栈中的数据");
            System.out.println("pop：表示从栈中取出数据");
            System.out.println("push：表示向栈中存放数据");
            System.out.println("exit：退出程序");

            key = scanner.next();
            switch (key) {
                case "show":
                    arrayStack.list();
                    break;
                case "pop":
                    try {
                        int res = arrayStack.pop();
                        System.out.printf("取出的数据为：%d\n", res);
                    }catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "push":
                    System.out.println("请输入要存放的数据");
                    int value = scanner.nextInt();
                    arrayStack.push(value);
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
            }
        }
    }
}

//使用数组模拟栈
class ArrayStack {
    int[] stack;
    int top = -1;
    int maxSize;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //判断栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //判断栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //添加数据到栈
    public void push(int data) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top ++;
        stack[top] = data;
    }

    //从栈中取出数据
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        int res = stack[top];
        top --;
        return res;
    }

    //遍历栈中的数据
    public void list(){
        if (isEmpty()) {
            System.out.println("栈空");
            return;
        }
        for (int i = top; i >= 0 ; i--) {
            System.out.printf("stack[%d]：%d\n", i,  stack[i]);
        }
    }
}
