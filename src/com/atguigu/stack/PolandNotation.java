package com.atguigu.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {

    public static void main(String[] args) {

        //先定义一个逆波兰表达式（3+4）*5-6
        /*
        String suffixExpression = "3 4 + 5 * 6 -";

        //将数字和符号扫描加入到ArrayList中
        List<String> list = toList(suffixExpression);

        //计算逆波兰表达式结果
        int result =  calculate(list);
        System.out.println("表达式计算的结果为：" + result);
        **/

        //完成将一个中缀表达式转成后缀表达式的功能
        //说明
        //1. 1+((2+3)×4)-5 => 转成  1 2 3 + 4 × + 5 –
        //2. 因为直接对str 进行操作，不方便，因此 先将  "1+((2+3)×4)-5" =》 中缀的表达式对应的List
        //   即 "1+((2+3)×4)-5" => ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]
        //3. 将得到的中缀表达式对应的List => 后缀表达式对应的List
        //   即 ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]  =》 ArrayList [1,2,3,+,4,*,+,5,–]
        String expression = "1+((2+3)*4)-5";
        List<String> expressionList = toInfixExpressionList(expression);
        System.out.println(expressionList);

        //将中缀表达式转换成后缀表达式
        List<String> suffixExpression = parseSuffixExpreesionList(expressionList);
        System.out.println(suffixExpression);

        int res = calculate(suffixExpression);
        System.out.println(res);



    }

    private static List<String> parseSuffixExpreesionList(List<String> expressionList) {
        //存放符号栈
        Stack<String> operatorStack = new Stack<>();
        //数栈不需要出栈等操作，而且最终栈的倒叙是后缀表达式，所以直接使用list即可
        List<String> list = new ArrayList<>();

        for (String item : expressionList) {
            if (item.matches("\\d+")) {//多位数直接入栈
                list.add(item);

            } else if("(".equals(item)) {
                //左括号直接入栈
                operatorStack.add(item);
            } else if(")".equals(item)){
                //遇到右括号  符号栈弹栈直到遇到左括号
                while (!operatorStack.peek().equals("(")) {
                    String pop = operatorStack.pop();
                    list.add(pop);
                }
                //消除左括号
                operatorStack.pop();

            } else {

                //判断栈顶元素优先级
                while (operatorStack.size() != 0 && Operation.getValue(item) <= Operation.getValue(operatorStack.peek())) {
                    String pop = operatorStack.pop();
                    list.add(pop);
                }
                operatorStack.push(item);

            }

        }

        //将符号栈的剩余元素依次弹出放入list
        while (operatorStack.size() != 0) {
            String pop = operatorStack.pop();
            list.add(pop);
        }

        return list;
    }


    private static List<String> toInfixExpressionList(String expression) {
        //定义一个索引进行扫描数字和符号
        int index = 0;
        String str;
        ArrayList<String> list = new ArrayList<>();
        do {
            char c = expression.charAt(index);
            //判断是不是数字
            if (c < 48 || c > 57) {
                //符号直接添加到list
                list.add("" + c);
                index++;
            }else {
                str = "";
                //数字需要考虑多位数
                while (index < expression.length() && expression.charAt(index) >= 48 &&  expression.charAt(index) <= 57) {
                    str += c;
                    index++;
                }
                list.add(str);
            }
        } while (index < expression.length());

        return list;
    }

    public static List<String> toList(String suffixExpression) {
        List<String> list = new ArrayList<>();
        String[] strings = suffixExpression.split(" ");
        for (String string : strings) {
            list.add(string);
        }

        return list;

    }

    private static int calculate(List<String> list) {
        Stack<String> stack = new Stack<>();
        for (String item : list) {
            //如果是符号直接压入栈中
            if (item.matches("\\d+")) {//匹配多位数
                stack.push(item);
            } else {
                //是符号就取出两个数计算  结果压入栈中
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int result = 0;
                switch (item) {
                    case "+" :
                        result = num2 + num1;
                        break;
                    case "-" :
                        result = num2 - num1;
                        break;
                    case "*" :
                        result = num2 * num1;
                        break;
                    case "/" :
                        result = num2 / num1;
                        break;
                    default:
                        System.out.println(item + "不是一个符号");
                        break;
                }
                //结果压入栈中
                stack.push(String.valueOf(result));
            }

        }

        //此时栈中的数字就是结果
        return Integer.parseInt(stack.pop());
    }
}

//编写一个类 Operation 可以返回一个运算符 对应的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //写一个方法，返回对应的优先级数字
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符" + operation);
                break;
        }
        return result;
    }

}


