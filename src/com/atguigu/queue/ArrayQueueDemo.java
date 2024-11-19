package com.atguigu.queue;

import java.util.Scanner;

public class ArrayQueueDemo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        ArrayDemo arrayDemo = null;
        while (flag) {
            //每次进入给用户菜单提示
            System.out.println("i(init): 初始化队列");
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            //接收用户输入参数
            char c = scanner.next().charAt(0);
            //根据输入进行不同逻辑处理
            switch (c) {
                case 'i':
                    System.out.println("请输入需要创建队列的大小");
                    int size = scanner.nextInt();
                    arrayDemo = new ArrayDemo(size);
                    System.out.printf("队列初始化成功，大小为\t%d\n", arrayDemo.getMaxSize());
                    break;
                case 's':
                    try {
                        arrayDemo.showQueue();
                    } catch (Exception e) {
                        if (e instanceof NullPointerException) {
                            System.out.println("队列未被初始化");
                        } else {
                            System.out.printf("异常信息:%d\n", e.getMessage());
                        }
                    }
                    break;
                case 'e':
                    flag = false;
                    scanner.close();
                    break;
                case 'a':
                    try {
                        System.out.println("请输入要添加的数字");
                        int i = scanner.nextInt();
                        arrayDemo.addQueue(i);
                    } catch (Exception e) {
                        if (e instanceof NullPointerException) {
                            System.out.println("队列未被初始化");
                        } else {
                            System.out.printf("异常信息:%s\n", e.getMessage());
                        }
                    }
                    break;
                case 'g':
                    try {
                        System.out.printf("取出数据\t%d\n", arrayDemo.getQueue());
                    } catch (Exception e) {
                        if (e instanceof NullPointerException) {
                            System.out.println("队列未被初始化");
                        } else {
                            System.out.printf("异常信息:%s\n", e.getMessage());
                        }
                    }
                    break;
                case 'h':
                    try {
                        System.out.printf("取出头数据\t%d\n", arrayDemo.headQueue());
                    } catch (Exception e) {
                        if (e instanceof NullPointerException) {
                            System.out.println("队列未被初始化");
                        } else {
                            System.out.printf("异常信息:%s\n", e.getMessage());
                        }
                    }
                    break;
                default:
                    break;

            }

        }
        System.out.println("程序退出");


    }
}


/**
 * 通过数组实现队列
 */
class ArrayDemo {

    //使用数组存储队列的数据
    private int[] array;
    //队列的头位置
    private int front;
    //队列尾位置
    private int rear;
    //队列存储最大限制
    private int maxSize;


    //初始化队列
    public ArrayDemo(int maxSize) {
        this.array = new int[maxSize];
        this.maxSize = maxSize;
        this.front = -1;
        this.rear = -1;
    }

    //判断队列是否已满
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    //获取队列数据
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空～");
        }
        front++;
        return array[front];

    }

    //存放队列数据
    public void addQueue(int data) {
        if (isFull()) {
            System.out.println("队列已满～");
            return;
        }
        rear++;
        array[rear] = data;
    }

    //显示队列全部数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列中没有数据～");
            return;
        }
        for (int i = 0; i < rear - front; i++) {
            System.out.printf("arr[%d]=%d\n", front + 1 + i, array[front + 1 + i]);
        }
    }

    //显示队列头数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列中无数据～");
        }
        return array[front + 1];
    }

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public int getFront() {
        return front;
    }

    public void setFront(int front) {
        this.front = front;
    }

    public int getRear() {
        return rear;
    }

    public void setRear(int rear) {
        this.rear = rear;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }
}