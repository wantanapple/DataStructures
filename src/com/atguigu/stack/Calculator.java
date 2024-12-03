package com.atguigu.stack;

/**
 * 综合计算器
 */
public class Calculator {

    public static void main(String[] args) {
        //定义一个需要运算的表达式
        String expression = "11*2-(2*4-3)";
        int res = calRes(expression);
        System.out.println("计算结果为" + res);

    }

    public static int calRes(String expression) {
        //定义数栈，用于存放数字
        ArrayStack2 numStack = new ArrayStack2(10);
        //定义符号栈，用于存放运算符
        ArrayStack2 operatorStack = new ArrayStack2(10);
        //定义一个索引，用于扫描表达式
        int index = 0;
        //存放扫描的结果
        char c;
        //存放多位数
        String numString = "";

        while (true) {
            c = expression.substring(index, index + 1).charAt(0);
            if (c == '(') {
                index++;
                String expression1 = "";
                while (true) {
                    c = expression.substring(index, index + 1).charAt(0);
                    if (c == ')') {
                        index++;
                        break;
                    }
                    expression1 = expression1.concat(String.valueOf(c));
                    index ++;
                }
                //获取括号里面的计算结果
                int res = calRes(expression1);
                //将计算结果放到数栈
                numStack.push(res);
                //如果括号在最后，算完了直接跳出
                if (index == expression.length()) {
                    break;
                }
            }

            //判断是不是符号
            if (operatorStack.isOperator(c)) {
                //是符号
                //判断符号栈是否为空
                if (operatorStack.isEmpty()) {
                    //为空直接入栈
                    operatorStack.push(c);
                } else {
                    while (true) {
                        if (operatorStack.top < 0) {
                            //运算符取完了  运算符直接入栈
                            operatorStack.push(c);
                            break;
                        }
                        //不为空则判断符号优先级
                        if (operatorStack.priority(c) <= operatorStack.priority(operatorStack.top())) {
                            //当前符号小于或等于栈顶符号优先级  进行计算
                            int num1 = numStack.pop();
                            int num2 = numStack.pop();
                            int operator = operatorStack.pop();
                            int res = numStack.cal(num1, num2, operator);
                            //计算结果入栈
                            numStack.push(res);

                        } else {
                            //当前符号大于栈顶符号优先级  直接入栈
                            operatorStack.push(c);
                            break;
                        }
                    }

                }
            } else {
                //是数字 有可能是两位数
                numString = numString.concat(String.valueOf(c));

                if (index == expression.length() -1) {
                    //扫描到最后也要直接入栈
                    numStack.push(Integer.parseInt(numString));
                } else {
                    //判断后一位是不是符号  是符号就直接入栈
                    if (numStack.isOperator(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(numString));
                        numString = "";
                    }
                }

            }

            index++;
            if (index == expression.length()) {
                break;
            }

        }

        //取出栈中的数据进行计算
        while (true) {
            if (operatorStack.isEmpty()) {
                //符号栈为空时表示计算完成  数栈中剩下的一个数据就是计算结果
                break;
            }
            int num1 = numStack.pop();
            int num2 = numStack.pop();
            int operator = operatorStack.pop();
            int res = numStack.cal(num1, num2, operator);
            //计算结果入栈
            numStack.push(res);

        }

        return numStack.pop();
    }
}

class ArrayStack2 {
    int[] array;
    int maxSize;
    int top = -1;

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        array = new int[this.maxSize];
    }

    //判断栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //判断栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //添加数据
    public void push(int data) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }

        top++;
        array[top] = data;
    }

    //取出数据
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }

        int res = array[top];
        top--;
        return res;
    }

    //遍历栈
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空");
            return;
        }

        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]：%d\n", i, array[i]);
        }
    }

    //获取栈顶的数据
    public int top() {
        return array[top];
    }

    //判断是否为计算符号
    public boolean isOperator(int value) {
        return value == '*' || value == '/' || value == '+' || value == '-';
    }

    //根据计算符号返回优先级  返回值越大优先级越高
    public int priority(int operator) {
        if (operator == '*' || operator == '/') {
            return 1;
        } else if (operator == '+' || operator == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    //判断符号是不是减号
    public boolean isMinus(int operator) {
        return operator == '-';
    }

    //计算两个数的结果
    public int cal(int num1, int num2, int operator) {
        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num2 - num1;
            case '*':
                return num1 * num2;
            case '/':
                return num2 / num1;
            default:
                throw new RuntimeException(operator + "不是一个运算符");
        }
    }
}


