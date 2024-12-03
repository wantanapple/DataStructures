package com.atguigu.stack;

import jdk.nashorn.internal.ir.IfNode;

import java.util.Scanner;

public class LinkedStackDemo {
    public static void main(String[] args) {
        LinkedStack stack = new LinkedStack();
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
                    stack.list();
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.printf("取出的数据为：%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "push":
                    System.out.println("请输入要存放的数据");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
            }
        }
    }

}

//使用带头单链表模拟栈
class LinkedStack {
    private LinkedNode head = new LinkedNode(0);

    //添加数据到链表
    public void push(int data) {
        //从头部添加
        LinkedNode tamp = head;
        LinkedNode next = head.getNext();
        LinkedNode dataNode = new LinkedNode(data);
        tamp.setNext(dataNode);
        dataNode.setNext(next);
    }

    //从栈中取出数据
    public int pop() {
        if (head.getNext() == null) {
            throw new RuntimeException("栈空");
        }

        LinkedNode data = head.getNext();
        head.setNext(data.getNext());
        return data.getData();
    }

    //遍历栈中的数据
    public void list() {
        if (head.getNext() == null) {
            System.out.println("栈空");
            return;
        }

        LinkedNode cur = head.getNext();
        while (true) {
            System.out.println(cur.getData());
            if (cur.getNext() == null) {
                break;
            }
            cur = cur.getNext();
        }
    }

}

class LinkedNode {
    private int data;
    private LinkedNode next;

    public LinkedNode(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public LinkedNode getNext() {
        return next;
    }

    public void setNext(LinkedNode next) {
        this.next = next;
    }
}
